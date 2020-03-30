package com.ycwl.servebixin.cn.ui.main.activity

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import cn.qqtheme.framework.picker.DatePicker
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.pp.wsy.bosom.app.utils.pickers.pickerUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.PicturesUtils
import com.ycwl.servebixin.cn.db.TagsUtils
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.image.ImageInfo
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.QiniuPresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.QiniuView
import com.ycwl.servebixin.cn.ui.login.utils.SizeFilterWithTextAndLetter
import com.ycwl.servebixin.cn.ui.main.adapter.PictureAdapter
import com.ycwl.servebixin.cn.ui.main.dialog.EditPictureDialog
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.EditUserBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.EditUserPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.EditUserView
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.CameraSelect
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_complete.*
import kotlinx.android.synthetic.main.layout_title.*
import java.io.File
import java.util.*

class CompleteActivity:BaseActivity(), EditUserView, CameraSelect.CameraSelectFace, QiniuView,  EditPictureDialog.EditPictureDialogFace {

    private val qiniuPresenter by lazy { QiniuPresenter(this, this, this) }
    private val editUserPresenter by lazy { EditUserPresenter(this,this,this) }
    private var cameraSelect = CameraSelect(this)
    private lateinit var adapter: PictureAdapter
    private  var piclist:ArrayList<String> = ArrayList()
    var nickname=""
    var birthday=""
    var imageUrl=""
    private lateinit var uri: Uri
    private lateinit var dialogPicture:EditPictureDialog
    private var position=0
    private var picposition=0
    private var flag=false
    private var file: File?=null

    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int =R.layout.activity_complete

    override fun setActivityTitle() {
        titleText.text="完善资料"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
        titleRightText.visibility= View.VISIBLE
    }

    override fun initActivityData() {
        cameraSelect.initSingleCameraSdk(this)
        val user= DBUtils.getUser()
        val pictures= PicturesUtils.getPictures()
        var filter = arrayOf<InputFilter>(SizeFilterWithTextAndLetter(12,6))
        userName.filters=filter
        userName.setText(user.nickname)
        userBirthday.text=user.birthday.substringBefore(" ")
        if (user.sex==1)
            userSex.text="男"
        else
            userSex.text="女"

        if (pictures!=null&&pictures.size>0){
            PicturesUtils.deleteAllPictures()
        }

        dialogPicture= EditPictureDialog(this)
        dialogPicture.setDialogFace(this)

        adapter=PictureAdapter(this)
        gv_picture.adapter=adapter

    }

    override fun clickListener() {

        userName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s==null||s.isEmpty()||s.toString()==""){
                    var filter = arrayOf<InputFilter>(SizeFilterWithTextAndLetter(12,6))
                    userName.filters=filter
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(userBirthday).subscribe {
            getTime()
        }

        Click.viewClick(userAddPicture).subscribe {
//            type=2
            flag=false
            cameraSelect.openCamera()
        }

        gv_picture.setOnItemClickListener { parent, view, position, id ->
            if (piclist.size<6&&position==parent.childCount-1){

//                type=2
                flag=false
                cameraSelect.openCamera()
            }else{
                var imagelist = ArrayList<ImageInfo>()
                piclist.forEach {
                    imagelist.add(ImageInfo(it, false, 2))
                }
                intentUtils.intentImage(false, imagelist, position)
            }
        }
        gv_picture.setOnItemLongClickListener { parent, view, position, id ->
            if (piclist.size==6||position!=parent.childCount-1){
                this.position=position
                dialogPicture.showDialog(piclist.size)
            }
            return@setOnItemLongClickListener true
        }

        Click.viewClick(titleRightText).subscribe {
            //            if (user.getisPerfectData()==0){
            if (piclist.size==0) {
                Toast.Tips("请上传照片")
                return@subscribe
            }
//            }
            var videos =ArrayList<EditUserBody.VideosBean>()
            if (piclist.size>0){
                piclist.forEach {
                    videos.add(EditUserBody.VideosBean(it,2))
                }
            }

            if (!userName.text.toString().equals("")&&userName.text!=null) {
                if (user.getUserNick().equals(userName.text.toString())) {
                    editUserPresenter.getEditUser(EditUserBody(userBirthday.text.toString() + " 00:00:00", null, null, videos))
                } else {
                    editUserPresenter.getEditUser(EditUserBody(userName.text.toString(), userBirthday.text.toString() + " 00:00:00", null, null, videos))
                }
            }else{
                Toast.Tips("昵称不可为空")
            }

        }

    }

    override fun getEditUserRequest(data: EditUserBean) {
        var info = DBUtils.getUser()
        var infoData = ByCodeBean.DataBean()
//        var tags=ArrayList<ByCodeBean.DataBean.TagsBean>()
//        if (TagsUtils.getTags()!=null&&TagsUtils.getTags()!!.size>0){
//            TagsUtils.getTags()!!.forEach {
//                tags.add(ByCodeBean.DataBean.TagsBean(it.tagId,it.tagName))
//            }
//        }

        var videos =ArrayList<ByCodeBean.DataBean.VideosBean>()
//        if (!videoUrl.equals("")){
//            videos.add(0,ByCodeBean.DataBean.VideosBean(videoUrl,1))
//        }
        if (piclist.size>0){
            piclist.forEach {
                videos.add(ByCodeBean.DataBean.VideosBean(it,2))
            }
        }

//        var occupationName=""
//        if (!userOccupationName.text.toString().equals("选择职业"))
//            occupationName=userOccupationName.text.toString()
        infoData.token=info.token
        infoData.age=info.age
        infoData.avatar=info.avatar
        infoData.birthday=userBirthday.text.toString()+" 00:00:00"
        infoData.nickname=userName.text.toString()
        infoData.bixinId=info.bixinId
        infoData.constellation=info.constellation
//        infoData.tags=tags
        infoData.leaderIDCardAuditState=info.leaderIDCardAuditState
        infoData.dataAuditState=info.dataAuditState
        infoData.fansNum=info.fansNum
        infoData.followNum=info.followNum
        infoData.identity=info.identity
        infoData.isEnableSkill=info.isEnableSkill
        infoData.isPerfectData=info.isPerfectData
        infoData.occupationName=info.occupationName
        infoData.orderNum=info.orderNum
        infoData.phone=info.phone
        infoData.rongToken=info.rongToken
        infoData.userId=info.userID
        infoData.sex=info.sex
        infoData.videos=videos
        infoData.jmpassword=user.getUserIM()
        DBUtils.setUser(infoData)
        finish()
    }

    override fun getEditUserError() {

    }

    override fun returnCameraImageList(list: java.util.ArrayList<String>) {
        if (list!=null&&list.size>0) {
            qiniuPresenter.setImage(list)
//        if (type==2) {
            file = File(list[0])

//        }
        }

    }

    override fun sendSucceedImage(fileUrlList: ArrayList<String>) {
        imageUrl = "http://pic.bixinyule.com/" + fileUrlList.get(0)
        LogUtils.a("图片地址" + imageUrl)

        if (flag) {
            PicturesUtils.changePicture(PicturesUtils.getPictures()!![position],imageUrl)
            gv_picture.visibility = View.VISIBLE
            userAddPicture.visibility = View.GONE
            piclist.clear()
            PicturesUtils.getPictures()!!.forEach {
                piclist.add(it.url)
            }
            Log.e("测试修改list", ":" + piclist.size.toString())
            adapter.updateList(piclist)
            adapter.notifyDataSetChanged()

        }else{
            PicturesUtils.addPicture(ByCodeBean.DataBean.VideosBean(imageUrl, 2))
            gv_picture.visibility = View.VISIBLE
            userAddPicture.visibility = View.GONE
            piclist.add(imageUrl)
            Log.e("测试list", ":" + piclist.size.toString())
            adapter.updateList(piclist)
            adapter.notifyDataSetChanged()
        }
        if (!file!!.exists()) {
            return
        } else {
            deletePic(file!!.absolutePath)
        }
    }

    override fun sendFileErrorImage() {

    }

    override fun lookBtn() {
        var imagelist = ArrayList<ImageInfo>()
//        piclist.forEach {
        imagelist.add(ImageInfo(PicturesUtils.getPictures()!![position].url, false, 2))
//        }
        intentUtils.intentImage(false, imagelist, 0)
    }

    override fun changeBtn() {
        ShowDialog.showCustomDialogs(mContext, "是否更换该照片?", "更换", "取消", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        dialog.dismiss()
//                            presenter.getDelServices(DelServicesBody("${data.data.listId}"))
//                                Log.e("测试图片id",":"+position.toString())
//                                Log.e("测试数据库图片id",":"+PicturesUtils.getPictures()!![position].id.toString())
                        flag=true
                        cameraSelect.openCamera()
//                        PicturesUtils.changePicture(PicturesUtils.getPictures()!![position],PicturesUtils.getPictures()!![position].id)
//
////                                PicturesUtils.deleteAllPictures()
//                        piclist.remove(piclist[position])
//                        if (piclist.size>0) {
//                            adapter.updateList(piclist)
//                            adapter.notifyDataSetChanged()
//                        }else{
//                            userAddPicture.visibility=View.VISIBLE
//                            gv_picture.visibility=View.GONE
//                        }

                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }
        })
    }

    override fun delBtn() {
        ShowDialog.showCustomDialogs(mContext, "是否删除该照片?", "删除", "取消", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        dialog.dismiss()
//                            presenter.getDelServices(DelServicesBody("${data.data.listId}"))
//                                Log.e("测试图片id",":"+position.toString())
//                                Log.e("测试数据库图片id",":"+PicturesUtils.getPictures()!![position].id.toString())

                        PicturesUtils.deletePicture(PicturesUtils.getPictures()!![position].id)
//                                PicturesUtils.deleteAllPictures()
                        piclist.remove(piclist[position])
                        if (piclist.size>0) {
                            adapter.updateList(piclist)
                            adapter.notifyDataSetChanged()
                        }else{
                            userAddPicture.visibility=View.VISIBLE
                            gv_picture.visibility=View.GONE
                        }

                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cameraSelect.onActivityCameraResult(requestCode, resultCode, data)
    }

    /**
     * 设置生日
     */
    val c = Calendar.getInstance()//可以对每个时间域单独修改
    var years = c.get(Calendar.YEAR)
    var months = c.get(Calendar.MONTH) + 1
    var dates = c.get(Calendar.DATE)
    fun getTime() {
        var picker = DatePicker(this)
        pickerUtils.showPicker(picker)
        picker.setSelectedItem(years, months, dates)
        picker.setBackgroundColor(Color.parseColor("#FAFAFA"))
        picker.setTopBackgroundColor(Color.parseColor("#ffffff"))
        picker.setLabelTextColor(Color.parseColor("#999999"))
        picker.setTitleText("生日")
        picker.setTitleTextColor(Color.parseColor("#333333"))
        picker.setTitleTextSize(16)
        picker.setOnDatePickListener(cn.qqtheme.framework.picker.DatePicker.OnYearMonthDayPickListener { year, month, day ->
            years = year.toInt()
            months = month.toInt()
            dates = day.toInt()
            userBirthday.setText("$year-$month-$day")
            birthday="$year-$month-$day"+" 00:00:00"
        })
        picker.show()
    }
    private fun deletePic(path:String){
        if(!TextUtils.isEmpty(path)){
            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val contentResolver = this.getContentResolver()//cutPic.this是一个上下文
//            val url =  MediaStore.Images.Media.DATA + "='" + path + "'"
//            //删除图片
//            contentResolver.delete(uri, url, null)
            val url =  MediaStore.Images.Media.DATA + "=?"
            //删除图片
            contentResolver.delete(uri, url, arrayOf(path))
        }
    }

}