package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawOldPwdBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class WithdrawOldPwdData(val change: WithdrawOldPwd) : BaseData<EditUserBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getWithdrawOldPwd(body: WithdrawOldPwdBody) {
        api(Api.getApi().getWithdrawOldPwd(body)).build()
    }

    override fun onSucceedRequest(data: EditUserBean, what: Int) {
        change.getWithdrawOldPwdRequest()
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        change.getWithdrawOldPwdError(msg)
    }

    interface WithdrawOldPwd {
        fun getWithdrawOldPwdRequest()
        fun getWithdrawOldPwdError(msg: String)
    }
}