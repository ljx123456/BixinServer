package com.ycwl.servebixin.cn.ui.order.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderDrinkCodeBean
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean

interface OrderFormDrinkView:BaseView{
    fun getDrinksRequest(data:DrinksBean)
    fun getDrinkCodeRequest(data:OrderDrinkCodeBean)
}