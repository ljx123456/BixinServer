package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.TagBean

interface TagsView : BaseView{
    fun getTagsRequest(data: TagBean)
}