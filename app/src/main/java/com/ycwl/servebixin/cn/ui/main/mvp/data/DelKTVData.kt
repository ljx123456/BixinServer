package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.DelKTVBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class DelKTVData(val del: DelKTV) : BaseData<EditUserBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getDelKTV(body: DelKTVBody) {
        api(Api.getApi().getDelKTV(body)).build()
    }

    override fun onSucceedRequest(data:EditUserBean, what: Int) {
        del.getDelKTVRequest()
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
//        Log.e("测试flag",":"+flag.toString())
//        Log.e("测试what",":"+what.toString())
        Toast.Tips(msg)
        del.getDelKTVError()
    }

    interface DelKTV {
        fun getDelKTVRequest()
        fun getDelKTVError()
    }
}