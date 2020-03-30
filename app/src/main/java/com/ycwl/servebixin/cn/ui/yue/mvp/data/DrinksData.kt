package com.ycwl.servebixin.cn.ui.yue.mvp.data

import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.DrinksBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class DrinksData(val drinks: Drinks) : BaseData<DrinksBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getDrinks(body: DrinksBody) {
        api(Api.getApi().getDrinks(body)).build()
    }

    override fun onSucceedRequest(data: DrinksBean, what: Int) {
        drinks.getDrinksRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        drinks.getDrinksError()
    }

    interface Drinks {
        fun getDrinksRequest(data: DrinksBean)
        fun getDrinksError()
    }
}