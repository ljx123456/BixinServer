package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawRecordListBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawRecordListBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class WithdrawRecordData(val set: WithdrawRecord) : BaseData<WithdrawRecordListBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getWithdrawRecord(body: WithdrawRecordListBody) {
        api(Api.getApi().getWithdrawRecord(body)).build()
    }

    override fun onSucceedRequest(data: WithdrawRecordListBean, what: Int) {
        set.getWithdrawRecordRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        set.getWithdrawRecordError()
    }

    interface WithdrawRecord {
        fun getWithdrawRecordRequest(data: WithdrawRecordListBean)
        fun getWithdrawRecordError()
    }
}