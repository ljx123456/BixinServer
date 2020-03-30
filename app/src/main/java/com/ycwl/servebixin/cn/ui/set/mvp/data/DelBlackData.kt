package com.ycwl.servebixin.cn.ui.set.mvp.data

import com.ycwl.servebixin.cn.ui.set.mvp.bean.LogoutBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.DelBlackBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class DelBlackData(val del:DelBlack): BaseData<LogoutBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getDelBlack(body: DelBlackBody) {
        api(Api.getApi().getDelBlack(body)).build()
    }

    override fun onSucceedRequest(data: LogoutBean, what: Int) {
        del.getDelBlackRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        del.getDelBlackError()
    }

    interface DelBlack {
        fun getDelBlackRequest(data: LogoutBean)
        fun getDelBlackError()
    }
}