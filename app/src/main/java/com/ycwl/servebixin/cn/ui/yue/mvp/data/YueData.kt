package com.ycwl.servebixin.cn.ui.yue.mvp.data

import com.ycwl.servebixin.cn.ui.yue.mvp.bean.YueBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.YueBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class YueData(val order: OrderCode) : BaseData<YueBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getDrinks(body: YueBody) {
        api(Api.getApi().getOrderCode(body)).build()
    }

    override fun onSucceedRequest(data: YueBean, what: Int) {
        order.getOrderCodeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        order.getOrderCodeError()
    }

    interface OrderCode {
        fun getOrderCodeRequest(data: YueBean)
        fun getOrderCodeError()
    }
}