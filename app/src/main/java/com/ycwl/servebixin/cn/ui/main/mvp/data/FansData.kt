package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.FansBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.FansBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class FansData(val person: Fans) : BaseData<FansBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getFansData(body:FansBody) {
        api(Api.getApi().getFansData(body)).build()
    }

    override fun onSucceedRequest(data: FansBean, what: Int) {
        person.getFansDataRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        person.getFansDataError()
    }

    interface Fans {
        fun getFansDataRequest(data: FansBean)
        fun getFansDataError()
    }
}