package com.ycwl.servebixin.cn.ui.message.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.message.mvp.bean.BindNotiBean

interface BindNotiView:BaseView{
    fun getBindNotiRequest(data:BindNotiBean)
}