package com.ycwl.servebixin.cn.ui.login.mvp.data


import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.RegisterDataBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class RegisterDataData(val registerData: RegisterData ) : BaseData<ByCodeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun register(body: RegisterDataBody) {
        api(Api.getApi().getRegisterData(body)).build()
    }

    override fun onSucceedRequest(data: ByCodeBean, what: Int) {
        registerData.getRegisterDataRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        registerData.getRegisterDataError()
    }

    interface RegisterData {
        fun getRegisterDataRequest(data: ByCodeBean)
        fun getRegisterDataError()
    }
}