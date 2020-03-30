package com.ycwl.servebixin.cn.ui.login.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.bean.SendCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.SendCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.data.SendCodeData
import com.ycwl.servebixin.cn.ui.login.mvp.view.SendCodeView
import com.ycwl.servebixin.cn.utils.utils.Toast
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class SendCodePresenter(owner:LifecycleOwner,val view:SendCodeView,val mContext:Context):BasePresenter(owner,view,mContext),SendCodeData.SendCode{

    private val sendCodeData=SendCodeData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        sendCodeData.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getSendCodeRequest(data: SendCodeBean) {
        if (data.code==-1101){
            Toast.Tips(data.message)
        }else{
            Toast.Tips(data.message)
            view.getSendCodeRequest()
        }
        view.dismissLoading(mContext)
    }

    override fun getSendCodeError() {
        view.dismissLoading(mContext)
        view.getSendCodeError()
    }

    fun getCode(body:SendCodeBody){
        view.showLoading(mContext)
        sendCodeData.getSendCode(body)
    }

}