package com.ycwl.servebixin.cn.ui.login.fragment

import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import cn.jpush.android.api.JPushInterface
import cn.jpush.android.api.TagAliasCallback
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseFragment
import com.ycwl.servebixin.cn.db.*
import com.ycwl.servebixin.cn.db.DBUtils.getUser
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.ByPwdBody
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.ByPwdPresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.ByPwdView
import com.ycwl.servebixin.cn.ui.main.location.LocationUtils
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.fragment_login_password.*

class PwdLoginFragment:BaseFragment(),ByPwdView{

    private var flag=true
    private val pwdPresenter by lazy { ByPwdPresenter(this,this,activity!!) }

    override fun openEventBus(): Boolean =false

    override fun setLayoutTitle() {

    }

    override fun initFragmentData() {
        pwdPassWord.transformationMethod=PasswordTransformationMethod.getInstance()
        pwdHint.setImageResource(R.mipmap.ic_eye_off)
    }

    override fun setFragmentListener() {
        Click.viewClick(pwdHint).subscribe {
            if (flag){
                pwdPassWord.transformationMethod=HideReturnsTransformationMethod.getInstance()
                pwdHint.setImageResource(R.mipmap.ic_eye_on)
                flag=false
            }else{
                pwdPassWord.transformationMethod=PasswordTransformationMethod.getInstance()
                pwdHint.setImageResource(R.mipmap.ic_eye_off)
                flag=true
            }
        }

        Click.viewClick(loginPwd).subscribe {
            pwdPresenter.getByPwd(ByPwdBody(pwdPhone.text.toString(),pwdPassWord.text.toString()))
        }

        Click.viewClick(forgetPwd).subscribe {
            intentUtils.intentResetPwdPhone()
        }

        pwdPhone.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (pwdPassWord.text!=null&&pwdPassWord.text.toString().length>=6&&s!!.toString().length==11)
                    loginPwd.isEnabled=true
                else
                    loginPwd.isEnabled=false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        pwdPassWord.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (pwdPhone.text!=null&&pwdPhone.text.toString().length==11&&s!!.toString().length>=6)
                    loginPwd.isEnabled=true
                else
                    loginPwd.isEnabled=false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    override fun layoutID(): Int = R.layout.fragment_login_password

    override fun getByPwdRequest(data: ByCodeBean) {
        LogUtils.a("极光别名", data.data.userId)
        JPushInterface.setAlias(activity!!, 1, data.data.userId)
        try {
            var use=getUser()
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
                activity!!.finish()
            }else{
                DBUtils.setUser(data.data)
                intentUtils.intentMain()
                activity!!.finish()
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
//            LogUtils.a("极光别名", user.getUserUserID())
//            JPushInterface.setAlias(activity!!, 1, user.getUserUserID())
            intentUtils.intentMain()
            activity!!.finish()
        }

    }
}