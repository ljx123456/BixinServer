package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormAcceptBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormAcceptBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OrderFormAcceptData(val order: OrderFormAccept) : BaseData<OrderFormAcceptBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getOrderFormAccept(body: OrderFormAcceptBody) {
        api(Api.getApi().getOrderFormAccept(body)).build()
    }

    override fun onSucceedRequest(data: OrderFormAcceptBean, what: Int) {
        order.getOrderFormAcceptRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        if (flag!=-6)
            Toast.Tips(msg)
        order.getOrderFormAcceptError(flag)
    }

    interface OrderFormAccept {
        fun getOrderFormAcceptRequest(data: OrderFormAcceptBean)
        fun getOrderFormAcceptError(code:Int)
    }
}