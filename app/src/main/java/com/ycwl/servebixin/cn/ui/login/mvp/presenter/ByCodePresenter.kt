package com.ycwl.servebixin.cn.ui.login.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.bean.SendCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ValidationCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.ByCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.body.SendCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.data.ByCodeData
import com.ycwl.servebixin.cn.ui.login.mvp.data.SendCodeData
import com.ycwl.servebixin.cn.ui.login.mvp.data.ValidationCodeData
import com.ycwl.servebixin.cn.ui.login.mvp.view.ByCodeView
import com.ycwl.servebixin.cn.utils.utils.Toast
import io.reactivex.disposables.Disposable
import java.util.ArrayList

//验证码登陆
class ByCodePresenter(owner: LifecycleOwner, val view: ByCodeView, val mContext: Context) : BasePresenter(owner, view, mContext), ByCodeData.ByCode, SendCodeData.SendCode {

//    //验证码验证成功
//    override fun getValidationCodeRequest(data: ValidationCodeBean) {
//
//    }
//
////    验证码验证失败
//    override fun getValidationCodeError() {
//
//    }


    //验证码登陆成功
    override fun getByCodeRequest(data: ByCodeBean) {
        view.dismissLoading(mContext)
        view.getByCodeRequest(data)

    }

    //验证码登陆失败
    override fun getByCodeError() {
        view.dismissLoading(mContext)
    }

    private val bycode = ByCodeData(this)
    private val sendcode = SendCodeData(this)
//    private val validationCode=ValidationCodeData(this)

//    //验证码验证
//    fun getValidation(body: SendCodeBody,byCodeBody: ByCodeBody){
//        view.showLoading(mContext)
//
//    }

    //验证码登陆
    fun getCode(body: ByCodeBody) {
        view.showLoading(mContext)
        bycode.byCode(body)
    }

    //发送验证码
    fun getSendCode(body: SendCodeBody) {
        view.showLoading(mContext)
        sendcode.getSendCode(body)
    }

    //发送验证码成功
    override fun getSendCodeRequest(data: SendCodeBean) {
        view.dismissLoading(mContext)
        view.getSendCodeRequest()
    }

    //发送验证码失败
    override fun getSendCodeError() {
        view.dismissLoading(mContext)
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {
        bycode.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }
}