package com.ycwl.servebixin.cn.ui.message.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.message.mvp.bean.FeedBackDetailsBean

interface FeedBackDetailsView:BaseView{
    fun getFeedBackDetailsRequest(data:FeedBackDetailsBean)
    fun getFeedBackDetailsError()
}