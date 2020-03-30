package com.ycwl.servebixin.cn.ui.message.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.message.mvp.bean.NotificationBean

interface NotificationView:BaseView{
    fun getNotificationRequest(data:NotificationBean)
    fun getNotificationError()

    fun getReadNotiRequest()
}