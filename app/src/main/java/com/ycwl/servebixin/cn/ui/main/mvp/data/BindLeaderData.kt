package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.BindLeaderBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BindLeaderData(val bind: BindLeader) : BaseData<EditUserBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBindLeader(body: BindLeaderBody) {
        api(Api.getApi().getBindLeader(body)).build()
    }

    override fun onSucceedRequest(data: EditUserBean, what: Int) {
        bind.getBindLeaderRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        bind.getBindLeaderError()
    }

    interface BindLeader {
        fun getBindLeaderRequest(data: EditUserBean)
        fun getBindLeaderError()
    }
}