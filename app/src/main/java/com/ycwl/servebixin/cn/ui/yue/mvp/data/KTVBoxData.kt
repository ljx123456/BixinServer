package com.ycwl.servebixin.cn.ui.yue.mvp.data

import com.ycwl.servebixin.cn.ui.yue.mvp.bean.KTVBoxBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.KTVBoxBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class KTVBoxData(val ktv: KTVBox) : BaseData<KTVBoxBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getKTVBox(body: KTVBoxBody) {
        api(Api.getApi().getKTVBox(body)).build()
    }

    override fun onSucceedRequest(data: KTVBoxBean, what: Int) {
        ktv.getKTVBoxRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        ktv.getKTVBoxError()
    }

    interface KTVBox {
        fun getKTVBoxRequest(data: KTVBoxBean)
        fun getKTVBoxError()
    }
}