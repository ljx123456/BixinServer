package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.RealnameDetailsBean

interface RealnameDetailsView:BaseView{
    fun getDetailsRequest(data:RealnameDetailsBean)
    fun getDetailsError()
}