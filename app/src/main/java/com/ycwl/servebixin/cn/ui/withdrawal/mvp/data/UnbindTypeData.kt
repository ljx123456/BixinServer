package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.UnbindTypeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.UnbindTypeBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class UnbindTypeData(val unbind: UnbindType) : BaseData<UnbindTypeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getUnbindType(body: UnbindTypeBody) {
        api(Api.getApi().getUnbindType(body)).build()
    }

    override fun onSucceedRequest(data: UnbindTypeBean, what: Int) {
        unbind.getUnbindTypeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        unbind.getUnbindTypeError()
    }

    interface UnbindType{
        fun getUnbindTypeRequest(data: UnbindTypeBean)
        fun getUnbindTypeError()
    }
}