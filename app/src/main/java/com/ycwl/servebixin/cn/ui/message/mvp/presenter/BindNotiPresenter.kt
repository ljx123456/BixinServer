package com.ycwl.servebixin.cn.ui.message.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.message.mvp.bean.BindNotiBean
import com.ycwl.servebixin.cn.ui.message.mvp.body.BindNotiBody
import com.ycwl.servebixin.cn.ui.message.mvp.data.BindNotiData
import com.ycwl.servebixin.cn.ui.message.mvp.view.BindNotiView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class BindNotiPresenter(owner: LifecycleOwner, val view: BindNotiView, val mContext: Context) : BasePresenter(owner, view, mContext), BindNotiData.BindNoti{

    private val bind=BindNotiData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        bind.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getBindNotiRequest(data: BindNotiBean) {
        view.dismissLoading(mContext)
        view.getBindNotiRequest(data)
    }

    override fun getBindNotiError() {
        view.dismissLoading(mContext)
    }

    fun getBindNoti(body:BindNotiBody){
        view.showLoading(mContext)
        bind.getReadNoti(body)
    }
}