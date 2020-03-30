package com.ycwl.servebixin.cn.ui.order.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.order.mvp.bean.GrabOrderDetailsBean

interface GrabOrderDetailsView:BaseView{
    fun getGrabOrderDetailsRequest(data: GrabOrderDetailsBean)
    fun getGrabOrderDetailsError(code:Int)
}