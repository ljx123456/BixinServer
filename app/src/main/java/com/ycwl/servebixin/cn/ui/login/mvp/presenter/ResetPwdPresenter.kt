package com.ycwl.servebixin.cn.ui.login.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ResetPwdBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.ResetPwdBody
import com.ycwl.servebixin.cn.ui.login.mvp.data.ResetPwdData
import com.ycwl.servebixin.cn.ui.login.mvp.view.ResetPwdView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ResetPwdPresenter(owner: LifecycleOwner, val view: ResetPwdView, val mContext: Context) : BasePresenter(owner, view, mContext), ResetPwdData.ResetPwd {

    private val resetpwd = ResetPwdData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
       resetpwd.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    //重置密码
    fun getResetPwd(body: ResetPwdBody) {
        view.showLoading(mContext)
        resetpwd.getResetPwd(body)
    }


    //重置成功
    override fun getResetPwdRequest(data: ResetPwdBean) {
        view.dismissLoading(mContext)
        view.getResetPwdRequest()
    }

    //重置失败
    override fun getResetPwdError() {
        view.dismissLoading(mContext)
    }

}