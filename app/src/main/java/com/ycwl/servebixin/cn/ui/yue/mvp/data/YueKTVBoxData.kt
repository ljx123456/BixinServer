package com.ycwl.servebixin.cn.ui.yue.mvp.data

import com.ycwl.servebixin.cn.ui.yue.mvp.bean.BaoFangBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.BaoFangBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class YueKTVBoxData(val ktv: YueKTVBox) : BaseData<BaoFangBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getYueKTVBox(body:BaoFangBody) {
        api(Api.getApi().getYueKTVBox(body)).build()
    }

    override fun onSucceedRequest(data: BaoFangBean, what: Int) {
        ktv.getYueKTVBoxRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        ktv.getYueKTVBoxError()
    }

    interface YueKTVBox {
        fun getYueKTVBoxRequest(data: BaoFangBean)
        fun getYueKTVBoxError()
    }
}