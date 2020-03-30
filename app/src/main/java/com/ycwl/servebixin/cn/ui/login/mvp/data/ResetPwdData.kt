package com.ycwl.servebixin.cn.ui.login.mvp.data


import com.ycwl.servebixin.cn.ui.login.mvp.bean.ResetPwdBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.ResetPwdBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ResetPwdData(val resetpwd: ResetPwd) : BaseData<ResetPwdBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getResetPwd(body: ResetPwdBody) {
        api(Api.getApi().getResetPwd(body)).build()
    }

    override fun onSucceedRequest(data: ResetPwdBean, what: Int) {
        resetpwd.getResetPwdRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        resetpwd.getResetPwdError()
    }

    interface ResetPwd {
        fun getResetPwdRequest(data: ResetPwdBean)
        fun getResetPwdError()
    }
}