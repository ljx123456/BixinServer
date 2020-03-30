package com.ycwl.servebixin.cn.ui.broker.mvp.data

import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerServeBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerServeBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BrokerServeData(val broker: BrokerServe) : BaseData<BrokerServeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBrokerServe(body:BrokerServeBody) {
        api(Api.getApi().getBrokerServe(body)).build()
    }

    override fun onSucceedRequest(data: BrokerServeBean, what: Int) {
        broker.getBrokerServeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        broker.getBrokerServeError()
    }

    interface BrokerServe {
        fun getBrokerServeRequest(data: BrokerServeBean)
        fun getBrokerServeError()
    }
}