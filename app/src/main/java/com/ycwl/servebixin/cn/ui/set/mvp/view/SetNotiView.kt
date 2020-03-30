package com.ycwl.servebixin.cn.ui.set.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.set.mvp.bean.NotiStateBean

interface SetNotiView:BaseView{
    fun getNotiStateRequest(data:NotiStateBean)

    fun getSetNotiRequest()
    fun getSetNotiRequestError()
}