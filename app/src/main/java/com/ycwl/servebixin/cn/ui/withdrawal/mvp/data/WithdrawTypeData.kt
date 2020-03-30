package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawTypeBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class WithdrawTypeData(val type: WithdrawType) : BaseData<WithdrawTypeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getWithdrawType() {
        api(Api.getApi().getWithdrawType()).build()
    }

    override fun onSucceedRequest(data: WithdrawTypeBean, what: Int) {
        type.getWithdrawTypeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        type.getWithdrawTypeError()
    }

    interface WithdrawType{
        fun getWithdrawTypeRequest(data: WithdrawTypeBean)
        fun getWithdrawTypeError()
    }
}