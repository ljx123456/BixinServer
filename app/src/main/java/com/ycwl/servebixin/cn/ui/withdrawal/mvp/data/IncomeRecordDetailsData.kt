package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeRecordDetailsBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.IncomeRecordDetailsBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class IncomeRecordDetailsData(val income: IncomeRecordDetails) : BaseData<IncomeRecordDetailsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getIncomeRecordDetails(body:IncomeRecordDetailsBody) {
        api(Api.getApi().getIncomeRecordDetails(body)).build()
    }

    override fun onSucceedRequest(data: IncomeRecordDetailsBean, what: Int) {
        income.getIncomeRecordDetailsRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        income.getIncomeRecordDetailsError(flag)
    }

    interface IncomeRecordDetails {
        fun getIncomeRecordDetailsRequest(data: IncomeRecordDetailsBean)
        fun getIncomeRecordDetailsError(data:Int)
    }
}