package com.ycwl.servebixin.cn.ui.broker.mvp.data

import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerAddKTVBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerAddKTVBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BrokerAddKTVData(val broker: BrokerAddKTV) : BaseData<BrokerAddKTVBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBrokerAddKTV(body:BrokerAddKTVBody) {
        api(Api.getApi().getBrokerAddKTV(body)).build()
    }

    override fun onSucceedRequest(data: BrokerAddKTVBean, what: Int) {
        broker.getBrokerRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        broker.getBrokerError()
    }

    interface BrokerAddKTV{
        fun getBrokerRequest(data: BrokerAddKTVBean)
        fun getBrokerError()
    }
}