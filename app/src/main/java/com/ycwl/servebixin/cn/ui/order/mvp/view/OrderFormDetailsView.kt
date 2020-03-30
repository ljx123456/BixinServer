package com.ycwl.servebixin.cn.ui.order.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormDetailsBean

interface OrderFormDetailsView :BaseView{
    fun getDetailsRequest(data:OrderFormDetailsBean)
    fun getDetailsError(code:Int)
}