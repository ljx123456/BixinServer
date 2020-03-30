package com.ycwl.servebixin.cn.ui.message.mvp.data

import com.ycwl.servebixin.cn.ui.message.mvp.bean.ReadNotiBean
import com.ycwl.servebixin.cn.ui.message.mvp.body.ReadNotiBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ReadNotiData(val read: ReadNoti) : BaseData<ReadNotiBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getReadNoti(body: ReadNotiBody) {
        api(Api.getApi().getReadNoti(body)).build()
    }

    override fun onSucceedRequest(data: ReadNotiBean, what: Int) {
        read.getReadNotiRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        read.getReadNotiError()
    }

    interface ReadNoti {
        fun getReadNotiRequest(data: ReadNotiBean)
        fun getReadNotiError()
    }
}