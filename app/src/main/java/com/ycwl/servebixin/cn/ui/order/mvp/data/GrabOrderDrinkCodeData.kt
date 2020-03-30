package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderDrinkCodeBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderDrinkCodeBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class GrabOrderDrinkCodeData(val order: GrabOrderDrinkCode) : BaseData<OrderDrinkCodeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getGrabOrderDrinkCode(body: GrabOrderDrinkCodeBody) {
        api(Api.getApi().getGrabOrderDrinkCode(body)).build()
    }

    override fun onSucceedRequest(data: OrderDrinkCodeBean, what: Int) {
        order.getGrabOrderDrinkCodeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        order.getGrabOrderDrinkCodeError()
    }

    interface GrabOrderDrinkCode {
        fun getGrabOrderDrinkCodeRequest(data: OrderDrinkCodeBean)
        fun getGrabOrderDrinkCodeError()
    }
}