package com.ycwl.servebixin.cn.ui.login.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.ByPwdBody
import com.ycwl.servebixin.cn.ui.login.mvp.data.ByPwdData
import com.ycwl.servebixin.cn.ui.login.mvp.view.ByPwdView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ByPwdPresenter(owner: LifecycleOwner, val view: ByPwdView, val mContext: Context) : BasePresenter(owner, view, mContext), ByPwdData.ByPwd {

    private val bypwd = ByPwdData(this)

    //密码登陆
    fun getByPwd(body: ByPwdBody) {
        view.showLoading(mContext)
        bypwd.getByPwd(body)
    }

    //密码登陆成功
    override fun getByPwdRequest(data: ByCodeBean) {
        view.dismissLoading(mContext)
        view.getByPwdRequest(data)
    }

    //密码登陆失败
    override fun getByPwdError() {
        view.dismissLoading(mContext)
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {
        bypwd.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }
}