package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchLeaderBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.SearchLeaderBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class SearchLeaderData(val search: SearchLeader) : BaseData<SearchLeaderBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getSearchLeader(body:SearchLeaderBody) {
        api(Api.getApi().getSearchLeader(body)).build()
    }

    override fun onSucceedRequest(data: SearchLeaderBean, what: Int) {
        search.getSearchLeaderRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        search.getSearchLeaderError()
    }

    interface SearchLeader {
        fun getSearchLeaderRequest(data: SearchLeaderBean)
        fun getSearchLeaderError()
    }
}