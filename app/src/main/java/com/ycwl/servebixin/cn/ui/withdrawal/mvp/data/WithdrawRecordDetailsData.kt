package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawRecordDetailsBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawRecordDetailsBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class WithdrawRecordDetailsData(val details: WithdrawRecordDetails) : BaseData<WithdrawRecordDetailsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getWithdrawRecordDetails(body:WithdrawRecordDetailsBody) {
        api(Api.getApi().getWithdrawRecordDetails(body)).build()
    }

    override fun onSucceedRequest(data: WithdrawRecordDetailsBean, what: Int) {
        details.getWithdrawRecordDetailsRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        details.getWithdrawRecordDetailsError()
    }

    interface WithdrawRecordDetails {
        fun getWithdrawRecordDetailsRequest(data: WithdrawRecordDetailsBean)
        fun getWithdrawRecordDetailsError()
    }
}