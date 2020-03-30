package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.SetWithdrawPwdBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawChangePwdBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class WithdrawResetPwdData(val set: WithdrawResetPwd) : BaseData<SetWithdrawPwdBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getWithdrawResetPwd(body: WithdrawChangePwdBody) {
        api(Api.getApi().getWithdrawResetPwd(body)).build()
    }

    override fun onSucceedRequest(data: SetWithdrawPwdBean, what: Int) {
        set.getWithdrawResetPwdRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        set.getWithdrawResetPwdError()
    }

    interface WithdrawResetPwd {
        fun getWithdrawResetPwdRequest(data: SetWithdrawPwdBean)
        fun getWithdrawResetPwdError()
    }
}