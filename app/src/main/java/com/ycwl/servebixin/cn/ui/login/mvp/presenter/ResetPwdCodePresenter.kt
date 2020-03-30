package com.ycwl.servebixin.cn.ui.login.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.bean.SendCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ValidationCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.SendCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.body.ValidationCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.data.SendCodeData
import com.ycwl.servebixin.cn.ui.login.mvp.data.ValidationCodeData
import com.ycwl.servebixin.cn.ui.login.mvp.view.ResetPwdCodeView
import com.ycwl.servebixin.cn.utils.utils.Toast
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ResetPwdCodePresenter(owner: LifecycleOwner, val view: ResetPwdCodeView, val mContext: Context) : BasePresenter(owner, view, mContext),
        SendCodeData.SendCode,  ValidationCodeData.ValidationCode{

    private val sendcode = SendCodeData(this)
    private val validationcode = ValidationCodeData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        validationcode.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    //发送验证码
    fun getSendCode(body: SendCodeBody) {
        view.showLoading(mContext)
        sendcode.getSendCode(body)
    }

    //发送验证码成功
    override fun getSendCodeRequest(data: SendCodeBean) {
        if (data.code==-1101){
            Toast.Tips(data.message)
        }else{
            view.getSendCodeRequest()
        }
        view.dismissLoading(mContext)
    }

    //发送验证码失败
    override fun getSendCodeError() {
        view.dismissLoading(mContext)
    }


    //验证验证码
    fun getValidationCode(body: ValidationCodeBody) {
        validationcode.getValidationCode(body)
    }

    //验证验证码成功
    override fun getValidationCodeRequest(data: ValidationCodeBean) {
        view.dismissLoading(mContext)
        view.getValidationCodeRequest()
    }

    //验证验证码失败
    override fun getValidationCodeError() {
        view.dismissLoading(mContext)
    }


}