package com.ycwl.servebixin.cn.ui.login.mvp.data


import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.ByPwdBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ByPwdData(val bypwd: ByPwd) : BaseData<ByCodeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getByPwd(body: ByPwdBody) {
        api(Api.getApi().getByPwd(body)).build()
    }

    override fun onSucceedRequest(data: ByCodeBean, what: Int) {
        bypwd.getByPwdRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        bypwd.getByPwdError()
    }

    interface ByPwd {
        fun getByPwdRequest(data: ByCodeBean)
        fun getByPwdError()
    }
}