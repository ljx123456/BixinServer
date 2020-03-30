package com.ycwl.servebixin.cn.ui.broker.mvp.data

import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerSearchKTVBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerSearchKTVBody
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchKTVBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BrokerSearchKTVData(val broker: BrokerSearchKTV) : BaseData<SearchKTVBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBrokerSearchKTV(body: BrokerSearchKTVBody) {
        api(Api.getApi().getBrokerSearchKTV(body)).build()
    }

    override fun onSucceedRequest(data: SearchKTVBean, what: Int) {
        broker.getBrokerSearchRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        broker.getBrokerSearchError()
    }

    interface BrokerSearchKTV{
        fun getBrokerSearchRequest(data: SearchKTVBean)
        fun getBrokerSearchError()
    }
}