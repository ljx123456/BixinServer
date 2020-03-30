package com.ycwl.servebixin.cn.ui.order.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormAcceptBean

interface GrabOrderView :BaseView{
    fun getGrabOrderRequest(data: OrderFormAcceptBean)
    fun getGrabOrderError(code:Int)
}