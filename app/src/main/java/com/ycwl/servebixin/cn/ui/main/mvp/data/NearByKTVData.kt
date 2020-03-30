package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.NearByKTVBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.NearByKTVBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class NearByKTVData(val search: NearByKTV) : BaseData<NearByKTVBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getNearByKTV(body: NearByKTVBody) {
        api(Api.getApi().getNearByKTV(body)).build()
    }

    override fun onSucceedRequest(data: NearByKTVBean, what: Int) {
        search.getNearByKTVRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        search.getNearByKTVError()
    }

    interface NearByKTV {
        fun getNearByKTVRequest(data: NearByKTVBean)
        fun getNearByKTVError()
    }
}