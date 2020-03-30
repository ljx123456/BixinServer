package com.ycwl.servebixin.cn.ui.set.mvp.data

import com.ycwl.servebixin.cn.ui.set.mvp.bean.LogoutBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class LogoutData(val logout:Logout): BaseData<LogoutBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getLogout() {
        api(Api.getApi().getLogout()).build()
    }

    override fun onSucceedRequest(data: LogoutBean, what: Int) {
        logout.getLogoutRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        logout.getLogoutError()
    }

    interface Logout {
        fun getLogoutRequest(data: LogoutBean)
        fun getLogoutError()
    }
}