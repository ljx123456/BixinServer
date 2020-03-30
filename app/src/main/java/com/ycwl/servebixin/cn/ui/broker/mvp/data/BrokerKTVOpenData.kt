package com.ycwl.servebixin.cn.ui.broker.mvp.data

import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVOpenBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerKTVOpenBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BrokerKTVOpenData(val broker: BrokerKTVOpen) : BaseData<BrokerKTVOpenBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBrokerKTVOpen(body:BrokerKTVOpenBody) {
        api(Api.getApi().getBrokerKTVOpen(body)).build()
    }

    override fun onSucceedRequest(data: BrokerKTVOpenBean, what: Int) {
        broker.getBrokerKTVOpenRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        broker.getBrokerKTVOpenError()
    }

    interface BrokerKTVOpen {
        fun getBrokerKTVOpenRequest(data: BrokerKTVOpenBean)
        fun getBrokerKTVOpenError()
    }
}