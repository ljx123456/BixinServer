package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderDrinkCodeBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderDrinkCodeBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OrderDrinkCodeData(val order: OrderDrinkCode) : BaseData<OrderDrinkCodeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getOrderDrinkCode(body: OrderDrinkCodeBody) {
        api(Api.getApi().getOrderDrinkCode(body)).build()
    }

    override fun onSucceedRequest(data: OrderDrinkCodeBean, what: Int) {
        order.getOrderDrinkCodeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        order.getOrderDrinkCodeError()
    }

    interface OrderDrinkCode {
        fun getOrderDrinkCodeRequest(data: OrderDrinkCodeBean)
        fun getOrderDrinkCodeError()
    }
}