package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.OpenAllBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OpenAllData(val open: OpenAll) : BaseData<EditUserBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getOpenAll(body: OpenAllBody) {
        api(Api.getApi().getOpenAll(body)).build()
    }

    override fun onSucceedRequest(data: EditUserBean, what: Int) {
        open.getOpenAllRequest()
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
//        Log.e("测试flag",":"+flag.toString())
//        Log.e("测试what",":"+what.toString())
        Toast.Tips(msg)
        open.getOpenAllError()
    }

    interface OpenAll {
        fun getOpenAllRequest()
        fun getOpenAllError()
    }
}