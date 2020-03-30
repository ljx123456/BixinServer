package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.CompleteDataDetailsBean

interface CompleteDataDetailsView:BaseView{
    fun getCompleteDataDetailsRequest(data:CompleteDataDetailsBean)
    fun getCompleteDataDetailError()
}