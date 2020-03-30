package com.ycwl.servebixin.cn.ui.yue.mvp.data

import com.ycwl.servebixin.cn.ui.yue.mvp.bean.KTVBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.YueKTVBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class YueKTVData(val ktv: YueKTV) : BaseData<KTVBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getYueKTV(body:YueKTVBody) {
        api(Api.getApi().getYueKTV(body)).build()
    }

    override fun onSucceedRequest(data: KTVBean, what: Int) {
        ktv.getYueKTVRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        ktv.getYueKTVError()
    }

    interface YueKTV {
        fun getYueKTVRequest(data: KTVBean)
        fun getYueKTVError()
    }
}