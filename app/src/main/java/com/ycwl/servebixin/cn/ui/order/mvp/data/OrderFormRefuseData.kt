package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormRefuseBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormRefuseBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OrderFormRefuseData(val order: OrderFormRefuse) : BaseData<OrderFormRefuseBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getOrderFormRefuse(body: OrderFormRefuseBody) {
        api(Api.getApi().getOrderFormRefuse(body)).build()
    }

    override fun onSucceedRequest(data: OrderFormRefuseBean, what: Int) {
        order.getOrderFormRefuseRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        order.getOrderFormRefuseError()
    }

    interface OrderFormRefuse {
        fun getOrderFormRefuseRequest(data: OrderFormRefuseBean)
        fun getOrderFormRefuseError()
    }
}