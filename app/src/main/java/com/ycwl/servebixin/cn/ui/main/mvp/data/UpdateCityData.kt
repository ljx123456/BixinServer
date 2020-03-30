package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.blankj.utilcode.util.NetworkUtils
import com.ycwl.servebixin.cn.ui.main.mvp.bean.UpdateCityBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.UpdateCityBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast


class UpdateCityData(val update: UpdateCity) : BaseData<UpdateCityBean>() {
    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        if (NetworkUtils.isConnected()) {
            Toast.Tips(msg)
        }
        update.getUpdateCityError(flag,msg)
    }

    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getUpdateCity(body: UpdateCityBody) {
        api(Api.getApi().getUpdateCity(body)).build()
    }

    override fun onSucceedRequest(data: UpdateCityBean, what: Int) {
        update.getUpdateCityRequest(data)
    }


    interface UpdateCity {
        fun getUpdateCityRequest(data: UpdateCityBean)
        fun getUpdateCityError(code:Int,msg:String)
    }
}