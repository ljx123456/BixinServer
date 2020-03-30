package com.ycwl.servebixin.cn.ui.set.mvp.data

import com.ycwl.servebixin.cn.ui.set.mvp.bean.NotiStateBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class NotiStateData(val state:NotiState): BaseData<NotiStateBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getNotiState() {
        api(Api.getApi().getNotiState()).build()
    }

    override fun onSucceedRequest(data: NotiStateBean, what: Int) {
        state.getNotiStateRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        state.getNotiStateError()
    }

    interface NotiState {
        fun getNotiStateRequest(data: NotiStateBean)
        fun getNotiStateError()
    }
}