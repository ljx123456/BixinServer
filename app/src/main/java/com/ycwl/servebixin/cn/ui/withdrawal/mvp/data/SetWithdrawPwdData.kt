package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.SetWithdrawPwdBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.SetWithdrawPwdBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class SetWithdrawPwdData(val set: SetWithdrawPwd) : BaseData<SetWithdrawPwdBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getSetWithdrawPwd(body: SetWithdrawPwdBody) {
        api(Api.getApi().getSetWithdrawPwd(body)).build()
    }

    override fun onSucceedRequest(data: SetWithdrawPwdBean, what: Int) {
        set.getSetWithdrawPwdRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        set.getSetWithdrawPwdError()
    }

    interface SetWithdrawPwd {
        fun getSetWithdrawPwdRequest(data: SetWithdrawPwdBean)
        fun getSetWithdrawPwdError()
    }
}