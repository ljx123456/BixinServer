package com.ycwl.servebixin.cn.ui.order.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormAcceptBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormClockBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormRefuseBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormRefuseReasonsBean

interface OrderFormMakeView :BaseView{
    //接单
    fun getAcceptRequest(data:OrderFormAcceptBean)
    fun getAcceptError(code: Int)

    //打卡
    fun getClockRequest(data:OrderFormClockBean)
    fun getClockError(code:Int)

    //拒绝理由
    fun getRefuseReasonsRequest(data: OrderFormRefuseReasonsBean)

    //拒绝订单
    fun getRefuseRequest(data: OrderFormRefuseBean)


}