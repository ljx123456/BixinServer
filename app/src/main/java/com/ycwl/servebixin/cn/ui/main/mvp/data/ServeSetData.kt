package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeSetBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ServeSetData(val serve: ServeSet) : BaseData<ServeSetBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getServeSet() {
        api(Api.getApi().getServeSet()).build()
    }

    override fun onSucceedRequest(data: ServeSetBean, what: Int) {
        serve.getServeSetRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        serve.getServeSetError()
    }

    interface ServeSet {
        fun getServeSetRequest(data: ServeSetBean)
        fun getServeSetError()
    }
}