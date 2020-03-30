package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeDetailsBean

interface ServeDetailsView:BaseView{
    fun getServeDetailsRequest(data: ServeDetailsBean)
    fun getServeDetailsError()
    fun delKTVRequest()
    fun getopenKTVRequest()
    fun getOpenAllRequest()
}