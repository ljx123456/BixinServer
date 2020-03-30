package com.ycwl.servebixin.cn.ui.set.mvp.data

import com.ycwl.servebixin.cn.ui.set.mvp.bean.LogoutBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.FeedBackBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class FeedBackData(val opinion:FeedBack): BaseData<LogoutBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getFeedBack(body: FeedBackBody) {
        api(Api.getApi().getFeedBack(body)).build()
    }

    override fun onSucceedRequest(data: LogoutBean, what: Int) {
        opinion.getFeedBackRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        opinion.getFeedBackError()
    }

    interface FeedBack {
        fun getFeedBackRequest(data: LogoutBean)
        fun getFeedBackError()
    }
}