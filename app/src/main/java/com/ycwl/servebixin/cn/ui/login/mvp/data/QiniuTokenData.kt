package com.ycwl.servebixin.cn.ui.login.mvp.data



import com.ycwl.servebixin.cn.ui.login.mvp.bean.QiniuTokenBean

import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo

class QiniuTokenData(val qiniutoken: QiniuToken) : BaseData<QiniuTokenBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getQiniuToken() {
        api(Api.getApi().getQiniuToken()).build()
    }

    override fun onSucceedRequest(data: QiniuTokenBean, what: Int) {
        qiniutoken.getQiniuTokenRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        qiniutoken.getQiniuTokenError()
    }

    interface QiniuToken {
        fun getQiniuTokenRequest(data: QiniuTokenBean)
        fun getQiniuTokenError()
    }
}