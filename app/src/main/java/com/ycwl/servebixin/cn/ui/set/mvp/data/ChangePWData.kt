package com.ycwl.servebixin.cn.ui.set.mvp.data

import com.ycwl.servebixin.cn.ui.set.mvp.bean.ChangePWBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.ChangePWBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ChangePWData(val resetpwd: ChangePW) : BaseData<ChangePWBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getChangePW(body: ChangePWBody) {
        api(Api.getApi().getChangePW(body)).build()
    }

    override fun onSucceedRequest(data: ChangePWBean, what: Int) {
        resetpwd.getChangePWRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        resetpwd.getChangePWError()
    }

    interface ChangePW {
        fun getChangePWRequest(data: ChangePWBean)
        fun getChangePWError()
    }
}