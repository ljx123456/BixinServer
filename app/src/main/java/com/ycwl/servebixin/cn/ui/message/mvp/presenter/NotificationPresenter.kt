package com.ycwl.servebixin.cn.ui.message.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.message.mvp.bean.NotificationBean
import com.ycwl.servebixin.cn.ui.message.mvp.bean.ReadNotiBean
import com.ycwl.servebixin.cn.ui.message.mvp.body.NotificationBody
import com.ycwl.servebixin.cn.ui.message.mvp.body.ReadNotiBody
import com.ycwl.servebixin.cn.ui.message.mvp.data.NotificationData
import com.ycwl.servebixin.cn.ui.message.mvp.data.ReadNotiData
import com.ycwl.servebixin.cn.ui.message.mvp.view.NotificationView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class NotificationPresenter(owner: LifecycleOwner, val view: NotificationView, val mContext: Context) : BasePresenter(owner, view, mContext), NotificationData.Notification,ReadNotiData.ReadNoti{
    override fun getReadNotiRequest(data: ReadNotiBean) {
        view.dismissLoading(mContext)
        view.getReadNotiRequest()
    }

    override fun getReadNotiError() {
        view.dismissLoading(mContext)
    }

    private val notification=NotificationData(this)
    private val read=ReadNotiData(this)


    override fun addDisposableList(list: ArrayList<Disposable>) {
        notification.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getNotificationRequest(data: NotificationBean) {
//        view.dismissLoading(mContext)
        view.getNotificationRequest(data)
    }

    override fun getNotificationError() {
//        view.dismissLoading(mContext)
        view.getNotificationError()
    }

    fun getNotification(body:NotificationBody){
//        view.showLoading(mContext)
        notification.getNotification(body)

    }

    fun getReadNoti(body:ReadNotiBody){
        view.showLoading(mContext)
        read.getReadNoti(body)
    }
}