package com.ycwl.servebixin.cn.ui.set.mvp.data

import com.ycwl.servebixin.cn.ui.set.mvp.bean.LogoutBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.SetNotiBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class SetNotiData(val set:SetNoti): BaseData<LogoutBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getSetNoti(body:SetNotiBody) {
        api(Api.getApi().getSetNoti(body)).build()
    }

    override fun onSucceedRequest(data: LogoutBean, what: Int) {
        set.getSetNotiRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        set.getSetNotiError()
    }

    interface SetNoti {
        fun getSetNotiRequest(data: LogoutBean)
        fun getSetNotiError()
    }
}