package com.ycwl.servebixin.cn.ui.yue.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.YueServeBean

interface YueServeView:BaseView{
    fun getYueServeRequest(data:YueServeBean)
    fun getYueServeError()
}