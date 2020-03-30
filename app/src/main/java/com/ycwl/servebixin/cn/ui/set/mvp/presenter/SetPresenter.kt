package com.ycwl.servebixin.cn.ui.set.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.set.mvp.bean.LogoutBean
import com.ycwl.servebixin.cn.ui.set.mvp.data.LogoutData
import com.ycwl.servebixin.cn.ui.set.mvp.view.SetView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class SetPresenter(owner: LifecycleOwner, val view: SetView, val mContext: Context) : BasePresenter(owner, view, mContext),LogoutData.Logout{

    private val logout=LogoutData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        logout.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getLogoutRequest(data: LogoutBean) {
        view.dismissLoading(mContext)
        view.getLogoutRequest(data)
    }

    override fun getLogoutError() {
        view.dismissLoading(mContext)
    }

    fun getLogout(){
        view.showLoading(mContext)
        logout.getLogout()
    }

}