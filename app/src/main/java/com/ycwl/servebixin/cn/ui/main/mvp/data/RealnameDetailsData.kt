package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.RealnameDetailsBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class RealnameDetailsData(val details: Details) : BaseData<RealnameDetailsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getDetailsData() {
        api(Api.getApi().getDetailsData()).build()
    }

    override fun onSucceedRequest(data: RealnameDetailsBean, what: Int) {
        details.getDetailsDataRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        details.getDetailsDataError()
    }

    interface Details {
        fun getDetailsDataRequest(data: RealnameDetailsBean)
        fun getDetailsDataError()
    }
}
