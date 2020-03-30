package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormDrinkBody
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OrderFormDrinkData(val order: OrderFormDrink) : BaseData<DrinksBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getOrderFormDrink(body: OrderFormDrinkBody) {
        api(Api.getApi().getOrderFormDrink(body)).build()
    }

    override fun onSucceedRequest(data: DrinksBean, what: Int) {
        order.getOrderFormDrinkRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        order.getOrderFormDrinkError()
    }

    interface OrderFormDrink {
        fun getOrderFormDrinkRequest(data: DrinksBean)
        fun getOrderFormDrinkError()
    }
}