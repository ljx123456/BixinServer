package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchKTVBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.SearchKTVBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class SearchKTVData(val search: SearchKTV) : BaseData<SearchKTVBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getSearchKTV(body: SearchKTVBody) {
        api(Api.getApi().getSearchKTV(body)).build()
    }

    override fun onSucceedRequest(data: SearchKTVBean, what: Int) {
        search.getSearchKTVRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        search.getSearchKTVError()
    }

    interface SearchKTV {
        fun getSearchKTVRequest(data: SearchKTVBean)
        fun getSearchKTVError()
    }
}