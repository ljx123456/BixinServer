package com.ycwl.servebixin.cn.ui.message.mvp.data

import com.ycwl.servebixin.cn.ui.message.mvp.bean.NotificationBean
import com.ycwl.servebixin.cn.ui.message.mvp.body.NotificationBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class NotificationData(val notification: Notification) : BaseData<NotificationBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getNotification(body: NotificationBody) {
        api(Api.getApi().getNotification(body)).build()
    }

    override fun onSucceedRequest(data: NotificationBean, what: Int) {
        notification.getNotificationRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        notification.getNotificationError()
    }

    interface Notification {
        fun getNotificationRequest(data: NotificationBean)
        fun getNotificationError()
    }
}