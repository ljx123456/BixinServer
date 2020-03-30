package com.ycwl.servebixin.cn.ui.broker.mvp.data

import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVDelBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerKTVDelBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BrokerKTVDelData(val broker: BrokerKTVDel) : BaseData<BrokerKTVDelBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBrokerKTVDel(body:BrokerKTVDelBody) {
        api(Api.getApi().getBrokerKTVDel(body)).build()
    }

    override fun onSucceedRequest(data:BrokerKTVDelBean, what: Int) {
        broker.getBrokerKTVDelRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        broker.getBrokerKTVDelError()
    }

    interface BrokerKTVDel {
        fun getBrokerKTVDelRequest(data: BrokerKTVDelBean)
        fun getBrokerKTVDelError()
    }
}