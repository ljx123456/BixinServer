package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.BindKTVBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BindKTVData(val bind: BindKTV) : BaseData<EditUserBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBindKTV(body: BindKTVBody) {
        api(Api.getApi().getBindKTV(body)).build()
    }

    override fun onSucceedRequest(data: EditUserBean, what: Int) {
        bind.getBindKTVRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        bind.getBindKTVError()
    }

    interface BindKTV {
        fun getBindKTVRequest(data: EditUserBean)
        fun getBindKTVError()
    }
}