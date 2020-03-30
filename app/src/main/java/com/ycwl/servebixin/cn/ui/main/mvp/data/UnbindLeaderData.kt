package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeDetailsBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class UnbindLeaderData(val unbind: UnbindLeader) : BaseData<EditUserBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getUnbindLeader(body: ServeDetailsBody) {
        api(Api.getApi().getUnbindLeader(body)).build()
    }

    override fun onSucceedRequest(data: EditUserBean, what: Int) {
        unbind.getUnbindLeaderRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        unbind.getUnbindLeaderError()
    }

    interface UnbindLeader {
        fun getUnbindLeaderRequest(data: EditUserBean)
        fun getUnbindLeaderError()
    }
}