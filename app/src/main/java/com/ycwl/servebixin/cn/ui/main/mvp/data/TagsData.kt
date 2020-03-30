package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.TagBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class TagsData (val tags: Tags) : BaseData<TagBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getTags() {
        api(Api.getApi().getTags()).build()
    }

    override fun onSucceedRequest(data: TagBean, what: Int) {
        tags.getTagsRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        tags.getTagsError()
    }

    interface Tags {
        fun getTagsRequest(data: TagBean)
        fun getTagsError()
    }
}