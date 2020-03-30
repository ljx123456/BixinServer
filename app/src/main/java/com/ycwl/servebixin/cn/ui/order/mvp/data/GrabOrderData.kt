package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormAcceptBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class GrabOrderData(val order: GrabOrder) : BaseData<OrderFormAcceptBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getGrabOrder(body: GrabOrderBody) {
        api(Api.getApi().getGrabOrder(body)).build()
    }

    override fun onSucceedRequest(data: OrderFormAcceptBean, what: Int) {
        order.getGrabOrderRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        if (flag!=-6)
            Toast.Tips(msg)
        order.getGrabOrderError(flag)
    }

    interface GrabOrder {
        fun getGrabOrderRequest(data: OrderFormAcceptBean)
        fun getGrabOrderError(code:Int)
    }
}