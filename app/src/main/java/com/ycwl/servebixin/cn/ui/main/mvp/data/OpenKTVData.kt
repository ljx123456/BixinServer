package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.OpenKTVBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OpenKTVData(val open: OpenKTV) : BaseData<EditUserBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getOpenKTV(body: OpenKTVBody) {
        api(Api.getApi().getOpenKTV(body)).build()
    }

    override fun onSucceedRequest(data:EditUserBean, what: Int) {
        open.getOpenKTVRequest()
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
//        Log.e("测试flag",":"+flag.toString())
//        Log.e("测试what",":"+what.toString())
        Toast.Tips(msg)
        open.getOpenKTVError()
    }

    interface OpenKTV {
        fun getOpenKTVRequest()
        fun getOpenKTVError()
    }
}