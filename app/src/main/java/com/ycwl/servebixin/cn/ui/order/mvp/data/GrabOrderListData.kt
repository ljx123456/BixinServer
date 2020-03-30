package com.ycwl.servebixin.cn.ui.order.mvp.data

import com.ycwl.servebixin.cn.ui.order.mvp.bean.GrabOrderListBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderListBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class GrabOrderListData(val order: GrabOrderList) : BaseData<GrabOrderListBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getGrabOrderList(body: GrabOrderListBody) {
        api(Api.getApi().getGrabOrderList(body)).build()
    }

    override fun onSucceedRequest(data: GrabOrderListBean, what: Int) {
        order.getGrabOrderListRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        if(flag!=-6&&flag!=-1320)
            Toast.Tips(msg)
        order.getGrabOrderListError(flag)
    }

    interface GrabOrderList {
        fun getGrabOrderListRequest(data: GrabOrderListBean)
        fun getGrabOrderListError(code:Int)
    }
}