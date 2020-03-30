package com.ycwl.servebixin.cn.ui.login.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.provider.MediaStore
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import cn.jpush.android.api.JPushInterface
import cn.qqtheme.framework.picker.DatePicker
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.makeramen.roundedimageview.RoundedImageView
import com.pp.wsy.bosom.app.utils.pickers.pickerUtils
import com.umeng.analytics.MobclickAgent
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.*
import com.ycwl.servebixin.cn.ui.login.dialog.SexDialog
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.RegisterDataPresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.RegisterDataView
import kotlinx.android.synthetic.main.activity_register_data.*
import kotlinx.android.synthetic.main.layout_title.*
import com.ycwl.servebixin.cn.db.DBUtils.setUser
import com.ycwl.servebixin.cn.ui.login.mvp.bean.CheckNameBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.CheckNameBody
import com.ycwl.servebixin.cn.ui.login.mvp.body.RegisterDataBody
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.QiniuPresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.QiniuView
import com.ycwl.servebixin.cn.ui.login.utils.SizeFilterWithTextAndLetter
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.http.BaseUrl
import com.ycwl.servebixin.cn.utils.image.ImageLoad
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.intent.intentUtils.intentSao
import com.ycwl.servebixin.cn.utils.utils.CameraSelect
import com.ycwl.servebixin.cn.utils.utils.Toast
import com.ycwl.servebixin.cn.utils.utils.UrlParse
import kotlinx.android.synthetic.main.activity_edit_user.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class RegisterDataActivity:BaseActivity(),RegisterDataView, SexDialog.Sex, CameraSelect.CameraSelectFace, QiniuView {
    override fun returnCameraImageList(list: ArrayList<String>) {
        if (list!=null&&list.size>0) {
            Log.e("测试","开始上传")
            qiniuPresenter.setImage(list)
//        if (type==2) {
            file = File(list[0])

//        }
        }
    }

    override fun sendSucceedImage(fileUrlList: ArrayList<String>) {
        avatar = "http://pic.bixinyule.com/" + fileUrlList.get(0)
        LogUtils.a("图片地址" + avatar)
        ImageLoad.setImageNull(avatar,registerDataHead as RoundedImageView)
        if (!file!!.exists()) {
            return
        } else {
            deletePic(file!!.absolutePath)
        }

        if (avatar!=""&&registerDataName.text!=null&&registerDataName.text.toString().length>0&&registerDataName.text.toString().length<=12&&registerDataBirthday.text!=null&&registerDataBirthday.text.toString().length>1&&registerDataSex.text!=null&&registerDataSex.text.toString().length>0&&registerDataID.text!=null&&registerDataID.text.toString().length>0)
            registerDataNext.isEnabled=true
        else
            registerDataNext.isEnabled=false

    }

    override fun sendFileErrorImage() {

    }

    override fun setCheckNameRequest(data: CheckNameBean) {

    }


    private var sexDialog = SexDialog(this)
    private val presenter by lazy { RegisterDataPresenter(this, this, this) }
    private val qiniuPresenter by lazy { QiniuPresenter(this, this, this) }
    private var cameraSelect = CameraSelect(this)
    var sex = 0
    var nickname=""
    var birthday=""
    var user_id=""
    var avatar=""
    var flag=true
    private var file: File?=null

    override fun setSex(s: String, i: Int) {
        registerDataSex.setText(s)
        sex = i
    }

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_register_data

    override fun setActivityTitle() {
        titleText.text="完善基本资料"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
    }

    override fun initActivityData() {
        var filter = arrayOf<InputFilter>(SizeFilterWithTextAndLetter(12,6))
        registerDataName.filters=filter
        cameraSelect.initSingleCameraSdk(this)
    }

    override fun clickListener() {

        Click.viewClick(titleLeft).subscribe { finish() }

        Click.viewClick(registerDataHead).subscribe {
            flag=true
            cameraSelect.openCamera()
        }
        Click.viewClick(registerDataBirthday).subscribe {
            getTime()
        }
        Click.viewClick(registerDataSex).subscribe {
            ShowDialog.showCustomDialog(this,  "性别选择后不可修改\n请慎重选择", "确定", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                    sexDialog.show(supportFragmentManager, "")
                }
            })
        }

        Click.viewClick(regIDBox).subscribe {
            flag=false
            intentSao(this,"")
        }

        Click.viewClick(registerDataNext).subscribe {
//            presenter.setCheckName(CheckNameBody(registerDataName.text.toString()))
            if (check_register.isChecked) {
                var mCache = ACache.get(this)
                presenter.getRegisterData(RegisterDataBody(mCache.getAsString("phone"), registerDataName.text.toString(), sex, birthday, registerDataID.text.toString(), mCache.getAsString("password"),avatar))
            }else{
                Toast.Tips("请同意服务人员使用协议")
            }
        }

        Click.viewClick(tv_show).subscribe {
            intentUtils.intentAgreement()
        }

        Click.viewClick(tv_show2).subscribe {
            intentUtils.intentPrivacy()
        }

        registerDataName.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s==null||s.isEmpty()||s.toString()==""){
                    var filter = arrayOf<InputFilter>(SizeFilterWithTextAndLetter(12,6))
                    registerDataName.filters=filter
                }
                if (avatar!=""&&s!=null&&s.toString().length>0&&s.toString().length<=12&&registerDataBirthday.text!=null&&registerDataBirthday.text.toString().length>1&&registerDataSex.text!=null&&registerDataSex.text.toString().length>0&&registerDataID.text!=null&&registerDataID.text.toString().length>0)
                    registerDataNext.isEnabled=true
                else
                    registerDataNext.isEnabled=false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        registerDataID.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (avatar!=""&&registerDataName.text!=null&&registerDataName.text.toString().length>0&&registerDataName.text.toString().length<=12&&registerDataBirthday.text!=null&&registerDataBirthday.text.toString().length>1&&registerDataSex.text!=null&&registerDataSex.text.toString().length>0&&s!=null&&s.toString().length>0)
                    registerDataNext.isEnabled=true
                else
                    registerDataNext.isEnabled=false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        registerDataBirthday.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (avatar!=""&&s!=null&&s.toString().length>1&&registerDataName.text!=null&&registerDataName.text.toString().length>0&&registerDataName.text.toString().length<=12&&registerDataSex.text!=null&&registerDataSex.text.toString().length>0&&registerDataID.text!=null&&registerDataID.text.toString().length>0)
                    registerDataNext.isEnabled=true
                else
                    registerDataNext.isEnabled=false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        registerDataSex.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (avatar!=""&&registerDataName.text!=null&&registerDataName.text.toString().length>0&&registerDataName.text.toString().length<=12&&registerDataBirthday.text!=null&&registerDataBirthday.text.toString().length>1&&s!=null&&registerDataID.text!=null&&registerDataID.text.toString().length>0)
                    registerDataNext.isEnabled=true
                else
                    registerDataNext.isEnabled=false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

    }

    override fun getRegisterDataRequest(data: ByCodeBean) {
//        setUser(data.data)
//        if (data.data.occupationName==null||data.data.occupationName==""){
//            user.setOccupation("")
//            user.setOccupationID("")
//        }
        //友盟统计注册数：
        if (BaseUrl.HOST_URL=="http://service.bixinyule.com/") {
//            MobclickAgent.onProfileSignIn(data.data.userId)
            MobclickAgent.onEvent(this,"BIXIN_Service_Register")
        }
        try {
            var use= DBUtils.getUser()
            if (use.userID!=data.data.userId){
                DBUtils.DelUser()
                DBUtils.delMerchat()
                ServeUtils.deleteALLServe()
                DrinkUtils.deleteALLDrinks()
                PicturesUtils.deleteAllPictures()
                TagsUtils.deleteAllTags()
                DBUtils.DelVideo()
//                LocationUtils(this).stopLocation()
//                JPushInterface.deleteAlias(activity!!,1)
                user.setOccupation("")
                user.setOccupationID("")
                user.setUserSkillID("")
                user.setSkillTypeID("")

                DBUtils.setUser(data.data)
                if (data.data.videos!=null&&data.data.videos.size>0){
//            if (data.data.videos[0].type==1){
//                DBUtils.setVideo(data.data.videos[0])
//            }else{
//                PicturesUtils.deleteAllPictures()
//                data.data.videos.forEach {
//                    PicturesUtils.addPicture(it)
//                }
//            }
                    PicturesUtils.deleteAllPictures()
                    if (data.data.videos[0].type==1){
                        DBUtils.setVideo(data.data.videos[0])
                    }else{
                        PicturesUtils.addPicture(data.data.videos[0])
                    }

                    for (i in data.data.videos.indices){
                        if (i!=0){
                            PicturesUtils.addPicture(data.data.videos[i])
                        }
                    }
                }
                if (data.data.tags!=null&&data.data.tags.size>0){
                    TagsUtils.deleteAllTags()
                    data.data.tags.forEach {
                        TagsUtils.addTag(it)
                    }
                }
                if (data.data.occupationName==null||data.data.occupationName==""){
                    user.setOccupation("")
                    user.setOccupationID("")
                }
                intentUtils.intentMain()
                finish()
            }
        }catch (e:Exception){
            DBUtils.DelUser()
            PicturesUtils.deleteAllPictures()
            TagsUtils.deleteAllTags()
            DBUtils.DelVideo()

            DBUtils.setUser(data.data)
            if (data.data.videos!=null&&data.data.videos.size>0){
//            if (data.data.videos[0].type==1){
//                DBUtils.setVideo(data.data.videos[0])
//            }else{
//                PicturesUtils.deleteAllPictures()
//                data.data.videos.forEach {
//                    PicturesUtils.addPicture(it)
//                }
//            }
                PicturesUtils.deleteAllPictures()
                if (data.data.videos[0].type==1){
                    DBUtils.setVideo(data.data.videos[0])
                }else{
                    PicturesUtils.addPicture(data.data.videos[0])
                }

                for (i in data.data.videos.indices){
                    if (i!=0){
                        PicturesUtils.addPicture(data.data.videos[i])
                    }
                }
            }
            if (data.data.tags!=null&&data.data.tags.size>0){
                TagsUtils.deleteAllTags()
                data.data.tags.forEach {
                    TagsUtils.addTag(it)
                }
            }
            if (data.data.occupationName==null||data.data.occupationName==""){
                user.setOccupation("")
                user.setOccupationID("")
            }
            intentUtils.intentMain()
            finish()
        }

//    }
//        intentUtils.intentMain()
//        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (flag) {
            Log.e("测试","获取回调")
            cameraSelect.onActivityCameraResult(requestCode, resultCode, data)
        }else {
            if (resultCode == Activity.RESULT_OK) {
                LogUtils.a("扫描成功" + data!!.extras.getString("codedContent"))
//            http://bixinyule.com?bixinClientType=2&qrcodeType=1&bixinId=6546499&identity=2
                var map = UrlParse.getUrlParams(data!!.extras.getString("codedContent"))
                if (map.containsKey("bixinClientType")) {
                    if (map["qrcodeType"] != "" && map["qrcodeType"] == "1" && map["bixinClientType"] == "2" && map.containsKey("bixinId")) {
                        registerDataID.setText(map["bixinId"]!!)
                    } else {
                        Toast.Tips("请扫描达人或经纪人比心二维码")
                    }
                } else {
                    Toast.Tips("请扫描正确的二维码")
                }
            }
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
            registerDataBirthday.setText("$year-$month-$day")
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

    override fun onResume() {
        super.onResume()
        if (BaseUrl.HOST_URL=="http://service.bixinyule.com/") {
            MobclickAgent.onResume(this)
        }
    }

    override fun onPause() {
        super.onPause()
        if (BaseUrl.HOST_URL=="http://service.bixinyule.com/") {
            MobclickAgent.onPause(this)
        }
    }

}