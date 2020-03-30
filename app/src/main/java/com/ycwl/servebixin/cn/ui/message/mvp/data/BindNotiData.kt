package com.ycwl.servebixin.cn.ui.message.mvp.data

import com.ycwl.servebixin.cn.ui.message.mvp.bean.BindNotiBean
import com.ycwl.servebixin.cn.ui.message.mvp.body.BindNotiBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BindNotiData(val bind: BindNoti) : BaseData<BindNotiBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getReadNoti(body: BindNotiBody) {
        api(Api.getApi().getBindNoti(body)).build()
    }

    override fun onSucceedRequest(data: BindNotiBean, what: Int) {
        bind.getBindNotiRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        bind.getBindNotiError()
    }

    interface BindNoti {
        fun getBindNotiRequest(data: BindNotiBean)
        fun getBindNotiError()
    }
}