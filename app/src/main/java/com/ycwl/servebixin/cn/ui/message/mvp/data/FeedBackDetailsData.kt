package com.ycwl.servebixin.cn.ui.message.mvp.data

import com.ycwl.servebixin.cn.ui.message.mvp.bean.FeedBackDetailsBean
import com.ycwl.servebixin.cn.ui.message.mvp.body.FeedBackDetailsBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class FeedBackDetailsData(val details: FeedBackDetails) : BaseData<FeedBackDetailsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getFeedBackDetails(body: FeedBackDetailsBody) {
        api(Api.getApi().getFeedBackDetails(body)).build()
    }

    override fun onSucceedRequest(data: FeedBackDetailsBean, what: Int) {
        details.getFeedBackDetailsRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        details.getFeedBackDetailsError()
    }

    interface FeedBackDetails {
        fun getFeedBackDetailsRequest(data: FeedBackDetailsBean)
        fun getFeedBackDetailsError()
    }
}