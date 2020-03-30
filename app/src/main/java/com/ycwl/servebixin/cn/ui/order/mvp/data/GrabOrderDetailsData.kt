package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.GrabOrderDetailsBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderDetailsBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class GrabOrderDetailsData(val order: GrabOrderDetails) : BaseData<GrabOrderDetailsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getGrabOrderDetails(body: GrabOrderDetailsBody) {
        api(Api.getApi().getGrabOrderDetails(body)).build()
    }

    override fun onSucceedRequest(data: GrabOrderDetailsBean, what: Int) {
        order.getGrabOrderDetailsRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        order.getGrabOrderDetailsError(flag)
    }

    interface GrabOrderDetails {
        fun getGrabOrderDetailsRequest(data: GrabOrderDetailsBean)
        fun getGrabOrderDetailsError(code:Int)
    }
}