package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeSetPriceBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeSetPriceBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ServeSetPriceData(val serve: ServeSetPrice) : BaseData<ServeSetPriceBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getServeSetPrice(body:ServeSetPriceBody) {
        api(Api.getApi().getServeSetPrice(body)).build()
    }

    override fun onSucceedRequest(data: ServeSetPriceBean, what: Int) {
        serve.getServeSetPriceRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        serve.getServeSetPriceError()
    }

    interface ServeSetPrice {
        fun getServeSetPriceRequest(data: ServeSetPriceBean)
        fun getServeSetPriceError()
    }
}