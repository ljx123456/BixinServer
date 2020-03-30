package com.ycwl.servebixin.cn.ui.withdrawal.mvp.data

import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.BindWithdrawTypeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.BindAlipayTypeBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class WithdrawWxData(val set: BindWxType) : BaseData<BindWithdrawTypeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBindWxType(body: BindAlipayTypeBody) {
        api(Api.getApi().getBindWxType(body)).build()
    }

    override fun onSucceedRequest(data: BindWithdrawTypeBean, what: Int) {
        set.getBindWxTypeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        set.getBindWxTypeError()
    }

    interface BindWxType {
        fun getBindWxTypeRequest(data: BindWithdrawTypeBean)
        fun getBindWxTypeError()
    }
}