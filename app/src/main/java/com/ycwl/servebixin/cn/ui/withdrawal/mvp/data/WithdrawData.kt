package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class WithdrawData(val type: Withdraw) : BaseData<WithdrawBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getWithdraw(body:WithdrawBody) {
        api(Api.getApi().getWithdraw(body)).build()
    }

    override fun onSucceedRequest(data: WithdrawBean, what: Int) {
        type.getWithdrawRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        type.getWithdrawError()
    }

    interface Withdraw{
        fun getWithdrawRequest(data: WithdrawBean)
        fun getWithdrawError()
    }
}