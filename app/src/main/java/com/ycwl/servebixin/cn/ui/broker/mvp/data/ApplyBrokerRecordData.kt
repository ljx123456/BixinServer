package com.ycwl.servebixin.cn.ui.broker.mvp.data

import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerRecordBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ApplyBrokerRecordData (val upload: ApplyBrokerRecord) : BaseData<ApplyBrokerRecordBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getApplyBrokerRecord() {
        api(Api.getApi().getApplyBrokerRecord()).build()
    }

    override fun onSucceedRequest(data: ApplyBrokerRecordBean, what: Int) {
        upload.getApplyBrokerRecordRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        upload.getApplyBrokerRecordError(flag)
    }

    interface ApplyBrokerRecord {
        fun getApplyBrokerRecordRequest(data: ApplyBrokerRecordBean)
        fun getApplyBrokerRecordError(data:Int)
    }
}