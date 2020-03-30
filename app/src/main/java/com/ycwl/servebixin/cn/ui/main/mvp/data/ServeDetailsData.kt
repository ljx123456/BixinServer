package com.ycwl.servebixin.cn.ui.main.mvp.data

import android.util.Log
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeDetailsBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeDetailsBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ServeDetailsData(val serve: ServeDetails) : BaseData<ServeDetailsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getServeDetails(body: ServeDetailsBody) {
        api(Api.getApi().getServeDetails(body)).build()
    }

    override fun onSucceedRequest(data:ServeDetailsBean, what: Int) {
        serve.getServeSetOpenRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
//        Log.e("测试flag",":"+flag.toString())
//        Log.e("测试what",":"+what.toString())
        Toast.Tips(msg)
        serve.getServeSetOpenError()
    }

    interface ServeDetails {
        fun getServeSetOpenRequest(data: ServeDetailsBean)
        fun getServeSetOpenError()
    }
}