package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormDetailsBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormDetailsBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OrderFormDetailsData(val order: OrderFormDetails) : BaseData<OrderFormDetailsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getOrderFormDetails(body: OrderFormDetailsBody) {
        api(Api.getApi().getOrderFormDetails(body)).build()
    }

    override fun onSucceedRequest(data: OrderFormDetailsBean, what: Int) {
        order.getOrderFormDetailsRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        order.getOrderFormDetailsError(flag)
    }

    interface OrderFormDetails {
        fun getOrderFormDetailsRequest(data: OrderFormDetailsBean)
        fun getOrderFormDetailsError(code:Int)
    }
}