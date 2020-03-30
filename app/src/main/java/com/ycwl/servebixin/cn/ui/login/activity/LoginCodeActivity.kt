package com.ycwl.servebixin.cn.ui.login.activity

import android.os.Handler
import android.os.Message
import android.util.Log
import cn.jpush.android.api.JPushInterface
import cn.jpush.android.api.TagAliasCallback
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.*
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.ByCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.body.SendCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.data.ByCodeData
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.ByCodePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.ByCodeView
import com.ycwl.servebixin.cn.ui.login.utils.CodeTime
import com.ycwl.servebixin.cn.ui.login.utils.PhoneCode
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_login_code.*
import kotlinx.android.synthetic.main.layout_title.*

class LoginCodeActivity:BaseActivity(),ByCodeView{


    private val codeTime = CodeTime()
    private val presenter by lazy { ByCodePresenter(this, this, this) }
    private val  MSG_SET_ALIAS = 1001
    private val mAliasCallback= object :TagAliasCallback{
        override fun gotResult(p0: Int, p1: String?, p2: MutableSet<String>?) {
            when(p0){
                0->{
                    Log.e("测试","别名设置成功")
                    mHandler.removeCallbacksAndMessages(null)
                }
                6002->{
                    Log.e("测试","别名延时设置")
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60)
                }
            }
        }
    }
    private val mHandler : Handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                MSG_SET_ALIAS ->{
                    Log.e("测试","收到消息")
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(applicationContext,
                            msg.obj as String,
                            null,
                            mAliasCallback)

                }
                else -> {

                }
            }
        }
    }

    private var alias=""

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_login_code

    override fun setActivityTitle() {
        titleText.setText("短信登录")
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
    }

    override fun initActivityData() {
        codeTime.codeCountTimer(sendAgainCode)
        tv_login_code_phone.text=intent.getStringExtra("phone")

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        pc_login.setOnInputListener(object :PhoneCode.OnInputListener{
            override fun onSucess(code: String?) {
                presenter.getCode(ByCodeBody(tv_login_code_phone.text.toString(),pc_login.phoneCode))
                pc_login.clean()
            }

            override fun onInput() {
            }
        })
        Click.viewClick(sendAgainCode).subscribe{
            presenter.getSendCode(SendCodeBody(tv_login_code_phone.text.toString(),"",0))
        }
    }

    override fun getSendCodeRequest() {
        codeTime.codeCountTimer(sendAgainCode)
    }

    override fun getByCodeRequest(data: ByCodeBean) {
        LogUtils.a("登陆数据", Gson().toJson(data))
        when (data.code) {
            -1101 -> {
                var mCache = ACache.get(this)
                mCache.put("phone",tv_login_code_phone.text.toString())
                //intentUtils.intentFaceRecognition()//人脸认证
                intentUtils.intentSetPassWord()
            }
            0 -> {
                intentUtils.intentMain()
                LogUtils.a("极光别名", data.data.userId)
//                alias=data.data.userId
//                //调用 Handler 来异步设置别名
//                mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias))
                JPushInterface.setAlias(this, 1, data.data.userId)
//                JPushInterface.setAlias(this,data.data.userId,object :TagAliasCallback{
//                    override fun gotResult(p0: Int, p1: String?, p2: MutableSet<String>?) {
//                        Log.e("测试别名设置",p0.toString())
//                    }
//                })
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
//                        JPushInterface.deleteAlias(this,1)
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

                        finish()
                    }else{
                        DBUtils.setUser(data.data)
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


                    finish()
                }

            }
//                DBUtils.setUser(data.data)
//                if (data.data.occupationName==null||data.data.occupationName==""){
//                    user.setOccupation("")
//                    user.setOccupationID("")
//                }
//                if (data.data.videos!=null&&data.data.videos.size>0){
//                    PicturesUtils.deleteAllPictures()
//                    if (data.data.videos[0].type==1){
//                        DBUtils.setVideo(data.data.videos[0])
//                    }else{
//                        PicturesUtils.addPicture(data.data.videos[0])
//                    }
//
//                    for (i in data.data.videos.indices){
//                        if (i!=0){
//                            PicturesUtils.addPicture(data.data.videos[i])
//                        }
//                    }
//                }
//                if (data.data.tags!=null&&data.data.tags.size>0){
//                    TagsUtils.deleteAllTags()
//                    data.data.tags.forEach {
//                        TagsUtils.addTag(it)
//                    }
//                }
//                finish()
//                intentUtils.intentMain()
//            }
            else -> Toast.Tips("")
        }
    }



}