package com.ycwl.servebixin.cn.ui.set.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.set.mvp.bean.BlackListBean

interface BlackListView:BaseView{
    fun getBlackListRequest(data:BlackListBean)
    fun getBlackListError()

    fun getDelBlackRequest()
}