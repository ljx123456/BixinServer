package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormClockBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormClockBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OrderFormClockData(val order: OrderFormClock) : BaseData<OrderFormClockBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getOrderFormClock(body: OrderFormClockBody) {
        api(Api.getApi().getOrderFormClock(body)).build()
    }

    override fun onSucceedRequest(data: OrderFormClockBean, what: Int) {
        order.getOrderFormClockRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        if (flag!=-5011&&flag!=-5012)
            Toast.Tips(msg)
        order.getOrderFormClockError(flag)
    }

    interface OrderFormClock {
        fun getOrderFormClockRequest(data: OrderFormClockBean)
        fun getOrderFormClockError(flag: Int)
    }
}