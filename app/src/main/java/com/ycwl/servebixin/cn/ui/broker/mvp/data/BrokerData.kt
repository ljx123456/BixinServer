package com.ycwl.servebixin.cn.ui.broker.mvp.data

import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BrokerData(val broker: Broker) : BaseData<BrokerBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBroker() {
        api(Api.getApi().getBroker()).build()
    }

    override fun onSucceedRequest(data: BrokerBean, what: Int) {
        broker.getBrokerRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        broker.getBrokerError(flag)
    }

    interface Broker {
        fun getBrokerRequest(data: BrokerBean)
        fun getBrokerError(data:Int)
    }
}