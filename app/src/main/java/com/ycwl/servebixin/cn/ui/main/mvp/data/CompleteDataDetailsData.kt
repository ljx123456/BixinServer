package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.CompleteDataDetailsBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class CompleteDataDetailsData(val details: Details) : BaseData<CompleteDataDetailsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getCompleteDetailsData() {
        api(Api.getApi().getCompleteDetailsData()).build()
    }

    override fun onSucceedRequest(data: CompleteDataDetailsBean, what: Int) {
        details.getDetailsDataRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        details.getDetailsDataError()
    }

    interface Details {
        fun getDetailsDataRequest(data: CompleteDataDetailsBean)
        fun getDetailsDataError()
    }
}