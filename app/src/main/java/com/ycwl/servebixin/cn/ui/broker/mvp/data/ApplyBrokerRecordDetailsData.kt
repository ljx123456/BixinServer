package com.ycwl.servebixin.cn.ui.broker.mvp.data

import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerRecordDetailsBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.ApplyBrokerRecordDetailsBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ApplyBrokerRecordDetailsData(val deatils: ApplyBrokerRecordDetails) : BaseData<ApplyBrokerRecordDetailsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getApplyBrokerRecordDetails(body:ApplyBrokerRecordDetailsBody) {
        api(Api.getApi().getApplyBrokerRecordDetails(body)).build()
    }

    override fun onSucceedRequest(data: ApplyBrokerRecordDetailsBean, what: Int) {
        deatils.getApplyBrokerRecordDetailsRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        deatils.getApplyBrokerRecordDetailsError(flag)
    }

    interface ApplyBrokerRecordDetails {
        fun getApplyBrokerRecordDetailsRequest(data: ApplyBrokerRecordDetailsBean)
        fun getApplyBrokerRecordDetailsError(data:Int)
    }
}