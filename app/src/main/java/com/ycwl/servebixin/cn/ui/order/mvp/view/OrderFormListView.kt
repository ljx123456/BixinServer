package com.ycwl.servebixin.cn.ui.order.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.order.mvp.bean.GrabOrderListBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormListBean

interface OrderFormListView :BaseView{
    fun getOrderFormListRequest(data:OrderFormListBean)

    fun getOrderFormListError(code:Int)
    fun getGrabOrderListRequest(data:GrabOrderListBean)

    fun getGrabOrderListError(code:Int)
}