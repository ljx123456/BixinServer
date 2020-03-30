package com.ycwl.servebixin.cn.ui.set.mvp.data

import com.ycwl.servebixin.cn.ui.set.mvp.bean.BlackListBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.BlackListBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class BlackListData(val black:BlackList): BaseData<BlackListBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getBlackList(body: BlackListBody) {
        api(Api.getApi().getBlackList(body)).build()
    }

    override fun onSucceedRequest(data: BlackListBean, what: Int) {
        black.getBlackListRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        black.getBlackListError()
    }

    interface BlackList {
        fun getBlackListRequest(data: BlackListBean)
        fun getBlackListError()
    }
}