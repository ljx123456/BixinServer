package com.ycwl.servebixin.cn.ui.login.mvp.data

import com.ycwl.servebixin.cn.ui.login.mvp.bean.CheckNameBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.CheckNameBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast


class CheckNameData(val check: CheckName) : BaseData<CheckNameBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getCheckName(body: CheckNameBody) {
        api(Api.getApi().getCheckName(body)).build()
    }

    override fun onSucceedRequest(data: CheckNameBean, what: Int) {
        check.getCheckNameRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        check.getCheckNameError()
    }

    interface CheckName {
        fun getCheckNameRequest(data: CheckNameBean)
        fun getCheckNameError()
    }
}