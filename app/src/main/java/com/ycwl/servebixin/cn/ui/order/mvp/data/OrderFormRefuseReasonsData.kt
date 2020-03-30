package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormRefuseReasonsBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormRefuseReasonsBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OrderFormRefuseReasonsData(val order: OrderFormRefuseReasons) : BaseData<OrderFormRefuseReasonsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getOrderFormRefuseReasons() {
        api(Api.getApi().getOrderFormRefuseReasons()).build()
    }

    override fun onSucceedRequest(data: OrderFormRefuseReasonsBean, what: Int) {
        order.getOrderFormRefuseReasonsRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        order.getOrderFormRefuseReasonsError()
    }

    interface OrderFormRefuseReasons {
        fun getOrderFormRefuseReasonsRequest(data: OrderFormRefuseReasonsBean)
        fun getOrderFormRefuseReasonsError()
    }
}