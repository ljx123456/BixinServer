package com.ycwl.servebixin.cn.ui.broker.mvp.data

import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerUploadBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.ApplyBrokerUploadBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ApplyBrokerUploadData(val upload: ApplyBrokerUpload) : BaseData<ApplyBrokerUploadBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getApplyBrokerUpload(body:ApplyBrokerUploadBody) {
        api(Api.getApi().getApplyBrokerUpload(body)).build()
    }

    override fun onSucceedRequest(data: ApplyBrokerUploadBean, what: Int) {
        upload.getApplyBrokerUploadRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        upload.getApplyBrokerUploadError(flag)
    }

    interface ApplyBrokerUpload {
        fun getApplyBrokerUploadRequest(data: ApplyBrokerUploadBean)
        fun getApplyBrokerUploadError(data:Int)
    }
}