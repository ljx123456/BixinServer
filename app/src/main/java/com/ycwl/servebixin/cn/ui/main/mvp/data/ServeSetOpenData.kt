package com.ycwl.servebixin.cn.ui.main.mvp.data

import android.util.Log
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeSetOpenBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ServeSetOpenData(val serve: ServeSetOpen) : BaseData<EditUserBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getServeSetOpen(body: ServeSetOpenBody) {
        api(Api.getApi().getServeSetOpen(body)).build()
    }

    override fun onSucceedRequest(data: EditUserBean, what: Int) {
        serve.getServeSetOpenRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
//        Log.e("测试flag",":"+flag.toString())
//        Log.e("测试what",":"+what.toString())
        if (flag!=-1340)
            Toast.Tips(msg)
        serve.getServeSetOpenError(flag)
    }

    interface ServeSetOpen {
        fun getServeSetOpenRequest(data: EditUserBean)
        fun getServeSetOpenError(flag: Int)
    }
}