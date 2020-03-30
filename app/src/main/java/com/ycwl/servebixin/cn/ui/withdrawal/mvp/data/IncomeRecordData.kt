package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeRecordListBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.IncomeRecordListBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class IncomeRecordData(val set: IncomeRecord) : BaseData<IncomeRecordListBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getIncomeRecord(body: IncomeRecordListBody) {
        api(Api.getApi().getIncomeRecord(body)).build()
    }

    override fun onSucceedRequest(data: IncomeRecordListBean, what: Int) {
        set.getIncomeRecordRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        set.getIncomeRecordError()
    }

    interface IncomeRecord {
        fun getIncomeRecordRequest(data: IncomeRecordListBean)
        fun getIncomeRecordError()
    }
}