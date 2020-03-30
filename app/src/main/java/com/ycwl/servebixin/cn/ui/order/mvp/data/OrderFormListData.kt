package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormListBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormListBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OrderFormListData(val order: OrderFormList) : BaseData<OrderFormListBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getOrderFormList(body: OrderFormListBody) {
        api(Api.getApi().getOrderFormList(body)).build()
    }

    override fun onSucceedRequest(data: OrderFormListBean, what: Int) {
        order.getOrderFormListRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        if(flag!=-6&&flag!=-1320)
            Toast.Tips(msg)
        order.getOrderFormListError(flag)
    }

    interface OrderFormList {
        fun getOrderFormListRequest(data: OrderFormListBean)
        fun getOrderFormListError(code:Int)
    }
}