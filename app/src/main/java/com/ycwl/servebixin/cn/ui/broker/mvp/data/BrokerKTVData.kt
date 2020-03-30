package com.ycwl.servebixin.cn.ui.broker.mvp.data

import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BrokerKTVData(val broker: BrokerKTV) : BaseData<BrokerKTVBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBrokerKTV() {
        api(Api.getApi().getBrokerKTV()).build()
    }

    override fun onSucceedRequest(data: BrokerKTVBean, what: Int) {
        broker.getBrokerKTVRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        broker.getBrokerKTVError()
    }

    interface BrokerKTV {
        fun getBrokerKTVRequest(data: BrokerKTVBean)
        fun getBrokerKTVError()
    }
}