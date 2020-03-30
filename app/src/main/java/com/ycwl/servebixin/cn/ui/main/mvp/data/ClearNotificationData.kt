package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.ClearNotificationBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ClearNotificationData(val clear: ClearNotification) : BaseData<ClearNotificationBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getClearNotification() {
        api(Api.getApi().getClearNotification()).build()
    }

    override fun onSucceedRequest(data:ClearNotificationBean, what: Int) {
        clear.getClearNotificationRequest()
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
//        Log.e("测试flag",":"+flag.toString())
//        Log.e("测试what",":"+what.toString())
        Toast.Tips(msg)
        clear.getClearNotificationError()
    }

    interface ClearNotification {
        fun getClearNotificationRequest()
        fun getClearNotificationError()
    }
}