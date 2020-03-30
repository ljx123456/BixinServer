package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class IncomeData (val income: Income) : BaseData<IncomeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getIncome() {
        api(Api.getApi().getIncome()).build()
    }

    override fun onSucceedRequest(data: IncomeBean, what: Int) {
        income.getIncomeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        if (flag!=-6007)
            Toast.Tips(msg)
        income.getIncomeError(flag)
    }

    interface Income {
        fun getIncomeRequest(data: IncomeBean)
        fun getIncomeError(data:Int)
    }
}