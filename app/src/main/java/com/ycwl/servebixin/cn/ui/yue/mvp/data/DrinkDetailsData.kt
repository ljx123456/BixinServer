package com.ycwl.servebixin.cn.ui.yue.mvp.data

import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinkDetailsBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.DrinkDetailsBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast


class DrinkDetailsData(val drinkdetails: DrinkDetails) : BaseData<DrinkDetailsBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getDrinkDetails(body: DrinkDetailsBody) {
        api(Api.getApi().getDrinkDetails(body)).build()
    }

    override fun onSucceedRequest(data: DrinkDetailsBean, what: Int) {
        drinkdetails.getDrinkDetailsRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        if (flag!=-4)
            Toast.Tips(msg)
        drinkdetails.getDrinkDetailsError()
    }

    interface DrinkDetails {
        fun getDrinkDetailsRequest(data: DrinkDetailsBean)
        fun getDrinkDetailsError()
    }
}