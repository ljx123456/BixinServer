package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderDrinkBody
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class GrabOrderDrinkData(val order: GrabOrderDrink) : BaseData<DrinksBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getGrabOrderDrink(body: GrabOrderDrinkBody) {
        api(Api.getApi().getGrabOrderDrink(body)).build()
    }

    override fun onSucceedRequest(data: DrinksBean, what: Int) {
        order.getGrabOrderDrinkRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        order.getGrabOrderDrinkError()
    }

    interface GrabOrderDrink {
        fun getGrabOrderDrinkRequest(data: DrinksBean)
        fun getGrabOrderDrinkError()
    }
}