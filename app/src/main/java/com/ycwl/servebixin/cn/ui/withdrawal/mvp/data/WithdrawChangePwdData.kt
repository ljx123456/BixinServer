package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.SetWithdrawPwdBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawChangePwdBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class WithdrawChangePwdData(val set: WithdrawChangePwd) : BaseData<SetWithdrawPwdBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getWithdrawChangePwd(body: WithdrawChangePwdBody) {
        api(Api.getApi().getWithdrawChangePwd(body)).build()
    }

    override fun onSucceedRequest(data: SetWithdrawPwdBean, what: Int) {
        set.getWithdrawChangePwdRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        set.getWithdrawChangePwdError()
    }

    interface WithdrawChangePwd {
        fun getWithdrawChangePwdRequest(data: SetWithdrawPwdBean)
        fun getWithdrawChangePwdError()
    }
}