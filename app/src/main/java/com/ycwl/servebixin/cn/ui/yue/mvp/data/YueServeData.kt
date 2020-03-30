package com.ycwl.servebixin.cn.ui.yue.mvp.data

import com.ycwl.servebixin.cn.ui.yue.mvp.bean.YueServeBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.YueServeBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class YueServeData(val serve: YueServe) : BaseData<YueServeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getYueServe(body:YueServeBody) {
        api(Api.getApi().getYueServe(body)).build()
    }

    override fun onSucceedRequest(data: YueServeBean, what: Int) {
        serve.getYueServeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        serve.getYueServeError()
    }

    interface YueServe {
        fun getYueServeRequest(data: YueServeBean)
        fun getYueServeError()
    }
}