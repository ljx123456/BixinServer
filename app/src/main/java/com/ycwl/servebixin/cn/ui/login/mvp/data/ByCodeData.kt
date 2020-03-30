package com.ycwl.servebixin.cn.ui.login.mvp.data

import android.util.Log

import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.ByCodeBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ByCodeData(val bycode: ByCode) : BaseData<ByCodeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun byCode(body: ByCodeBody) {
        api(Api.getApi().getByCode(body)).build()
    }

    override fun onSucceedRequest(data: ByCodeBean, what: Int) {
        bycode.getByCodeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        bycode.getByCodeError()
    }

    interface ByCode {
        fun getByCodeRequest(data: ByCodeBean)
        fun getByCodeError()
    }
}