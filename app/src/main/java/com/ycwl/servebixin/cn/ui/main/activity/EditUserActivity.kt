package com.ycwl.servebixin.cn.ui.main.activity

import android.Manifest.permission.*
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import cn.qqtheme.framework.picker.DatePicker
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils

import com.muzhi.camerasdk.SelectCameraDialog
import com.pp.wsy.bosom.app.utils.pickers.pickerUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.DBUtils.getUser
import com.ycwl.servebixin.cn.db.PicturesUtils
import com.ycwl.servebixin.cn.db.TagsUtils
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.jpush.MiuiUtils
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
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.QiniuVideoPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.EditUserView
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.image.ImageLoad
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.intent.intentUtils.intentImage
import com.ycwl.servebixin.cn.utils.utils.CameraSelect
import kotlinx.android.synthetic.main.activity_edit_user.*
import kotlinx.android.synthetic.main.activity_register_data.*
import kotlinx.android.synthetic.main.layout_title.*
import java.util.*
import kotlin.collections.ArrayList
import com.ycwl.servebixin.cn.utils.permissions.UserPermissions
import com.ycwl.servebixin.cn.utils.utils.GetPathFromUri
import com.ycwl.servebixin.cn.utils.utils.PhotoSelet
import com.ycwl.servebixin.cn.utils.utils.Toast
import java.io.File
import java.net.URI

class EditUserActivity :BaseActivity(),EditUserView, CameraSelect.CameraSelectFace, QiniuView , SelectCameraDialog.SelectCameraDialogFace,EditPictureDialog.EditPictureDialogFace,PhotoSelet.PhotoSelectFace{
    override fun returnPhotoImageList(list: ArrayList<String>) {
        Log.e("测试返回结果",list.size.toString())
        if (list!=null&&list.size>0&&File(list[0]).length()>0) {
            list.forEach {
                Log.e("测试返回结果图片",it)
            }
            qiniuPresenter.setImage(list)
            if (list.size==1) {
                file = File(list[0])
            }
        }
    }

    override fun lookBtn() {
        var imagelist = ArrayList<ImageInfo>()
//        piclist.forEach {
            imagelist.add(ImageInfo(PicturesUtils.getPictures()!![position].url, false, 2))
//        }
        intentImage(false, imagelist, 0)
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
                        type=2
                        flag=true
                        photoSelet.initSinglePhoto(this@EditUserActivity)
                        photoSelet.openCamera()
//                        cameraSelect.openCamera()
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

    override fun cameraBtn() {
        var intent=Intent()
        intent.action=MediaStore.ACTION_VIDEO_CAPTURE
        startActivityForResult(intent, TAKE_VIDEO_FRAM_CAMERA)
//        PictureSelector.create(this).openCamera(PictureMimeType.ofVideo()).recordVideoSecond(10).forResult(TAKE_VIDEO_FRAM_CAMERA)

    }

    override fun photoBtn() {
        if (MiuiUtils.isMIUI()) {
            var intent =  Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"video/*")
            startActivityForResult(Intent.createChooser(intent, "选择要导入的视频"), TAKE_VIDEO)
        } else {
            var intent = Intent()
            if (Build.VERSION.SDK_INT < 19) {
                intent.setAction(Intent.ACTION_GET_CONTENT)
                intent.setType("video/*")
            } else {
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.setType("video/*")
            }
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, false)
            startActivityForResult(intent, TAKE_VIDEO)
        }
//        PictureSelector.create(this).openGallery(PictureMimeType.ofVideo()).imageSpanCount(3).selectionMode(PictureConfig.SINGLE).videoMaxSecond(10).previewVideo(true).forResult(TAKE_VIDEO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cameraSelect.onActivityCameraResult(requestCode, resultCode, data)
        photoSelet.onActivityPhotoResult(requestCode,resultCode,data)
        if (resultCode== Activity.RESULT_OK){
            when(requestCode){
                TAKE_VIDEO_FRAM_CAMERA ->{
                    uri=data!!.data!!
                    videos.clear()
                    videoPath=GetPathFromUri.getPath(this,uri)
                    videos.add(videoPath)
                    if (getDuration(videoPath)) {
//                        Log.e("测试","选取视频"+videos.size)
                        qiniuVideoPresenter.setImage(videos)
                    }else{
                        Toast.Tips("请上传10s以内的视频")
                    }
                }

                TAKE_VIDEO ->{
                    uri=data!!.data!!
                    videos.clear()
                    videoPath=GetPathFromUri.getPath(this,uri)
                    videos.add(videoPath)
                    if (getDuration(videoPath)) {
//                        Log.e("测试","选取视频"+videos.size)
                        qiniuVideoPresenter.setImage(videos)
                    }else{
                        Toast.Tips("请上传10s以内的视频")
                    }
                }

            }
        }
    }

    override fun returnCameraImageList(list: ArrayList<String>) {
        if (list!=null&&list.size>0) {
            qiniuPresenter.setImage(list)

//        if (type==2) {
                file = File(list[0])


//        }
        }
    }

    //图片上传成功
    override fun sendSucceedImage(fileUrlList: ArrayList<String>) {
        if (fileUrlList.size>1){
            fileUrlList.forEach {
                Log.e("测试上传结果","http://pic.bixinyule.com/"+it)
                PicturesUtils.addPicture(ByCodeBean.DataBean.VideosBean("http://pic.bixinyule.com/"+it, 2))
                gv_picture.visibility = View.VISIBLE
                userAddPicture.visibility = View.GONE
                piclist.add("http://pic.bixinyule.com/"+it)
            }
            Log.e("测试list", ":" + piclist.size.toString())
            adapter.updateList(piclist)
            adapter.notifyDataSetChanged()
        }else {
            imageUrl = "http://pic.bixinyule.com/" + fileUrlList.get(0)
            LogUtils.a("图片地址" + imageUrl)
            if (isAvatar) {
                ImageLoad.setUserHead(imageUrl, userAvatar)
                avatar = imageUrl
                if (!file!!.exists()) {
                    return
                } else {
//                file!!.delete()
                    deletePic(file!!.absolutePath)
                }
            } else {
                if (type == 2) {
                    if (flag) {
                        PicturesUtils.changePicture(PicturesUtils.getPictures()!![position], imageUrl)
                        gv_picture.visibility = View.VISIBLE
                        userAddPicture.visibility = View.GONE
                        piclist.clear()
                        PicturesUtils.getPictures()!!.forEach {
                            piclist.add(it.url)
                        }
                        Log.e("测试修改list", ":" + piclist.size.toString())
                        adapter.updateList(piclist)
                        adapter.notifyDataSetChanged()

                    } else {
                        PicturesUtils.addPicture(ByCodeBean.DataBean.VideosBean(imageUrl, 2))
                        gv_picture.visibility = View.VISIBLE
                        userAddPicture.visibility = View.GONE
                        piclist.add(imageUrl)
                        Log.e("测试list", ":" + piclist.size.toString())
                        adapter.updateList(piclist)
                        adapter.notifyDataSetChanged()
                    }
                    if (file==null||!file!!.exists()) {
                        return
                    } else {
//                file!!.delete()
                        deletePic(file!!.absolutePath)
                    }
                    if (file!=null&&file!!.exists()){
                        file!!.delete()
                    }
                } else {
                    videoUrl = imageUrl
                    layout_video.visibility = View.VISIBLE
                    ImageLoad.setImage(videoUrl, userVideo)
                    iv_addVideo.setImageResource(R.mipmap.nav_button_share_black_nor)
                    DBUtils.setVideo(ByCodeBean.DataBean.VideosBean(videoUrl, 1))
                }
            }
        }

    }

    //图片上传失败
    override fun sendFileErrorImage() {

    }

    companion object {
        private val TAKE_VIDEO_FRAM_CAMERA=1
        private val TAKE_VIDEO=2
    }
    private val qiniuPresenter by lazy { QiniuPresenter(this, this, this) }
    private val qiniuVideoPresenter by lazy { QiniuVideoPresenter(this, this, this) }
    private val editUserPresenter by lazy { EditUserPresenter(this,this,this) }
    private var cameraSelect = CameraSelect(this)
    private lateinit var adapter:PictureAdapter
    private var piclist:ArrayList<String> = ArrayList()
    var nickname=""
    var birthday=""
    var imageUrl=""
    var type =2
    var videoPath=""
    var videoUrl=""
    private lateinit var uri: Uri
    private lateinit var dialog: SelectCameraDialog
    private lateinit var dialogPicture:EditPictureDialog
    private var videos= arrayListOf<String>()
    private var position=0
    private var picposition=0
    private var flag=false
    private var file:File?=null
    private var isAvatar=false
    private var avatar=""

    private var photoSelet=PhotoSelet(this)

    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_edit_user

    override fun setActivityTitle() {

        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
        titleRightText.visibility=View.VISIBLE
    }

    override fun initActivityData() {
        if (intent.getStringExtra("i")!=null){
            titleText.text="完善资料"
        }else{
            titleText.text="修改资料"
        }
        cameraSelect.initSingleCameraSdk(this)
        val user=DBUtils.getUser()
        val pictures=PicturesUtils.getPictures()
        val video=DBUtils.getVideo()
        val tags=TagsUtils.getTags()
        var filter = arrayOf<InputFilter>(SizeFilterWithTextAndLetter(12,6))
        userName.filters=filter
        userName.setText(user.nickname)
        userBirthday.text=user.birthday.substringBefore(" ")
        ImageLoad.setUserHead(user.avatar,userAvatar)
        if (user.sex==1)
            userSex.text="男"
        else
            userSex.text="女"

        if (user.occupationName!="")
            userOccupationName.text=user.occupationName

        if (tags==null||tags.size==0) {
            userTagsLabel.visibility = View.GONE
            userTagNum.visibility=View.GONE
        }else{
            userTagsLabel.visibility = View.VISIBLE
            userTagNum.visibility=View.VISIBLE
            userTagNum.text=tags.size.toString()+"/12"
            var list:ArrayList<String> = ArrayList()
            tags.forEach {
                list.add(it.tagName)
            }
            userTagsLabel.setList(list)
        }

        adapter=PictureAdapter(this)
        gv_picture.adapter=adapter
        if (pictures==null||pictures.size==0){
           gv_picture.visibility=View.GONE
            userAddPicture.visibility=View.VISIBLE
        }else{
            gv_picture.visibility=View.VISIBLE
            userAddPicture.visibility=View.GONE
            pictures.forEach {
                piclist.add(it.url)
            }
            adapter.updateList(piclist)
        }

        if (video==null){
            layout_video.visibility=View.GONE
        }else{
            layout_video.visibility=View.VISIBLE
            ImageLoad.setImage(video.url,userVideo)
            videoUrl=video.url
            iv_addVideo.setImageResource(R.mipmap.nav_button_share_black_nor)
        }

        popView = LayoutInflater.from(mContext).inflate(R.layout.pop_edit_video, null)
        pop = PopupWindowHelper(popView)

        dialog = SelectCameraDialog(this)
        dialog.setDialogFace(this)

        dialogPicture= EditPictureDialog(this)
        dialogPicture.setDialogFace(this)
    }

    override fun clickListener() {
        userName.addTextChangedListener(object :TextWatcher{
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

        Click.viewClick(userOccupationName).subscribe {
            user.setOccupation(userOccupationName.text.toString())
            intentUtils.intentOccupation()
        }

        Click.viewClick(layoutAvatar).subscribe {
            isAvatar=true
            cameraSelect.initSingleCameraSdk(this)
            cameraSelect.openCamera()
        }

        Click.viewClick(userAddTags).subscribe {
            intentUtils.intentTags()
        }
        Click.viewClick(userAddPicture).subscribe {
            isAvatar=false
            type=2
            flag=false
            photoSelet.initMultiCameraSdk(this,12)
            photoSelet.openCamera()
        }
        Click.viewClick(userAddVideo).subscribe {
            isAvatar=false
            type=1
            if (DBUtils.getVideo()==null){
//                dialog.showDialog()
                val rxPermission=RxPermissions(this)
                rxPermission.request(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA).subscribe {
                    if (it)
                        dialog.showDialog()
                    else
                        Toast.Tips("请打开相机，内存读取权限")
                }
            }else{
                pop.showAsDropDown(userAddVideo, 0, 0)
            }
        }

        Click.viewClick(popView.findViewById(R.id.goReport)).subscribe {
            isAvatar=false
            pop.dismiss()
            type=1
            val rxPermission=RxPermissions(this)
            rxPermission.request(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA).subscribe {
                if (it)
                    dialog.showDialog()
                else
                    Toast.Tips("请打开相机，内存读取权限")
            }
        }

        Click.viewClick(popView.findViewById(R.id.goBlock)).subscribe {
            pop.dismiss()
            ShowDialog.showCustomDialogs(mContext, "是否删除该视频?", "删除", "取消", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            dialog.dismiss()
//                            presenter.getDelServices(DelServicesBody("${data.data.listId}"))
                            DBUtils.DelVideo()
                            videoUrl=""
                            layout_video.visibility=View.GONE
                            iv_addVideo.setImageResource(R.mipmap.add_black)
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }
            })
        }



        gv_picture.setOnItemClickListener { parent, view, position, id ->
            if (piclist.size<12&&position==parent.childCount-1){
                isAvatar=false
                type=2
                flag=false
//                cameraSelect.openCamera()
                photoSelet.initMultiCameraSdk(this,(12-piclist.size))
                photoSelet.openCamera()
            }else{
                var imagelist = ArrayList<ImageInfo>()
                piclist.forEach {
                    imagelist.add(ImageInfo(it, false, 2))
                }
                intentImage(false, imagelist, position)
            }
        }
        gv_picture.setOnItemLongClickListener { parent, view, position, id ->
            if (piclist.size==12||position!=parent.childCount-1){
                this.position=position
                dialogPicture.showDialog(piclist.size)
            }
            return@setOnItemLongClickListener true
        }

        Click.viewClick(play).subscribe {
            intentUtils.intentVideo(DBUtils.getVideo()!!.url)
        }

//        gv_picture.setOnChangeListener { form, to ->
//            val temp=piclist.get(form)
//            if (form<to){
//                for (i in form until to){
//                    Collections.swap(piclist,i,i+1)
//                }
//            }else if (form>to){
//                for (i in form downTo to+1 ){
//                    Collections.swap(piclist,i,i-1)
//                }
//            }
//
//            piclist.set(to,temp)
//            adapter.notifyDataSetChanged()
//        }
        Click.viewClick(titleRightText).subscribe {
//            if (user.getisPerfectData()==0){
                if (piclist.size<6) {
                    Toast.Tips("请最少上传6张照片")
                    return@subscribe
                }
            if (videoUrl.equals("")){
                Toast.Tips("请上传视频")
                return@subscribe
            }
//            }
                var tags=ArrayList<Int>()
                if (TagsUtils.getTags()!=null&&TagsUtils.getTags()!!.size>0){
                    TagsUtils.getTags()!!.forEach {
                        tags.add(it.tagId)
                    }
                }
                var videos =ArrayList<EditUserBody.VideosBean>()
                if (!videoUrl.equals("")){
                    videos.add(0,EditUserBody.VideosBean(videoUrl,1))
                }
                if (piclist.size>0){
                    piclist.forEach {
                        videos.add(EditUserBody.VideosBean(it,2))
                    }
                }
                if (!userName.text.toString().equals("")&&userName.text!=null) {
                    if (user.getUserNick().equals(userName.text.toString())) {
                        if(avatar!="") {
                            editUserPresenter.getEditUser(EditUserBody(userBirthday.text.toString() + " 00:00:00", user.getOccupationID(), tags, videos,avatar))
                        }else{
                            editUserPresenter.getEditUser(EditUserBody(userBirthday.text.toString() + " 00:00:00", user.getOccupationID(), tags, videos))
                        }
                    } else {
                        if(avatar!=""){
                            editUserPresenter.getEditUser(EditUserBody(userName.text.toString(), userBirthday.text.toString() + " 00:00:00", user.getOccupationID(), avatar,tags, videos))
                        }else {
                            editUserPresenter.getEditUser(EditUserBody(userName.text.toString(), userBirthday.text.toString() + " 00:00:00", user.getOccupationID(), tags, videos))
                        }
                    }
                }else{
                    Toast.Tips("昵称不可为空")
                }

        }

    }

    override fun getEditUserRequest(data: EditUserBean) {
        var info = getUser()
        var infoData = ByCodeBean.DataBean()
        var tags=ArrayList<ByCodeBean.DataBean.TagsBean>()
        if (TagsUtils.getTags()!=null&&TagsUtils.getTags()!!.size>0){
            TagsUtils.getTags()!!.forEach {
                tags.add(ByCodeBean.DataBean.TagsBean(it.tagId,it.tagName))
            }
        }

        var videos =ArrayList<ByCodeBean.DataBean.VideosBean>()
        if (!videoUrl.equals("")){
            videos.add(0,ByCodeBean.DataBean.VideosBean(videoUrl,1))
        }
        if (piclist.size>0){
            piclist.forEach {
                videos.add(ByCodeBean.DataBean.VideosBean(it,2))
            }
        }

        var occupationName=""
        if (!userOccupationName.text.toString().equals("选择职业"))
            occupationName=userOccupationName.text.toString()
        infoData.token=info.token
        infoData.age=info.age
        infoData.avatar=info.avatar
        infoData.birthday=userBirthday.text.toString()+" 00:00:00"
        infoData.nickname=userName.text.toString()
        infoData.bixinId=info.bixinId
        infoData.constellation=info.constellation
        infoData.tags=tags
        infoData.dataAuditState=info.dataAuditState
        infoData.leaderIDCardAuditState=info.leaderIDCardAuditState
        infoData.fansNum=info.fansNum
        infoData.followNum=info.followNum
        infoData.identity=info.identity
        infoData.isEnableSkill=info.isEnableSkill
        infoData.isPerfectData=info.isPerfectData
        infoData.serviceIDCardAuditState=info.serviceIDCardAuditState
        infoData.occupationName=occupationName
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

    override fun onResume() {
        super.onResume()
        if (user.getOccupation().equals("")){
            if (user.getUserOccupation()!="")
                userOccupationName.text=user.getUserOccupation()
        }else{
            userOccupationName.text=user.getOccupation()
            user.setOccupation("")
        }
        val tags=TagsUtils.getTags()
        if (tags==null||tags.size==0) {
            userTagsLabel.visibility = View.GONE
            userTagNum.visibility=View.GONE
        }else{
            userTagsLabel.visibility = View.VISIBLE
            userTagNum.visibility=View.VISIBLE
            userTagNum.text=tags.size.toString()+"/12"
            var list:ArrayList<String> = ArrayList()
            tags.forEach {
                list.add(it.tagName)
            }
            userTagsLabel.setList(list)
        }
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

    /**
     * 判断视频时长是否10s
     */
    fun getDuration(file: String) : Boolean{
        var player=MediaPlayer()
        try {
            player.setDataSource(file)
            player.prepare()
        }catch (e :Exception){
            e.printStackTrace()
        }
        var time=player.duration
        Log.e("测试时长",":"+time.toString())
        player.release()
        return if (time<=10000) true else false
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