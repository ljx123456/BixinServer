package com.ycwl.servebixin.cn.ui.message.mvp.data

import com.ycwl.servebixin.cn.ui.message.mvp.bean.BannerBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BannerData(val banner: Banner) : BaseData<BannerBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBanner() {
        api(Api.getApi().getBanner()).build()
    }

    override fun onSucceedRequest(data: BannerBean, what: Int) {
        banner.getBannerRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        banner.getBannerError()
    }

    interface Banner {
        fun getBannerRequest(data: BannerBean)
        fun getBannerError()
    }
}