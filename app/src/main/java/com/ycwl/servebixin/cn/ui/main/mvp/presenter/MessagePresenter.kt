package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ClearNotificationBean
import com.ycwl.servebixin.cn.ui.main.mvp.data.ClearNotificationData
import com.ycwl.servebixin.cn.ui.main.mvp.view.MessageView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class MessagePresenter(owner: LifecycleOwner, val view: MessageView, val mContext: Context) : BasePresenter(owner, view, mContext), ClearNotificationData.ClearNotification{

    private val clear=ClearNotificationData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        clear.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getClearNotificationRequest() {
        view.dismissLoading(mContext)
        view.getClearNotificationRequest()
    }

    override fun getClearNotificationError() {
        view.dismissLoading(mContext)
        view.getClearNotificationError()
    }

    fun clearNotification(){
        view.showLoading(mContext)
        clear.getClearNotification()
    }
}