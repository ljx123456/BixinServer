package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.AuthInfoBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class AuthInfoData(val info: AuthInfo) : BaseData<AuthInfoBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getAuthInfo() {
        api(Api.getApi().getAuthInfo()).build()
    }

    override fun onSucceedRequest(data: AuthInfoBean, what: Int) {
        info.getAuthInfoRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        info.getAuthInfoError()
    }

    interface AuthInfo{
        fun getAuthInfoRequest(data: AuthInfoBean)
        fun getAuthInfoError()
    }
}