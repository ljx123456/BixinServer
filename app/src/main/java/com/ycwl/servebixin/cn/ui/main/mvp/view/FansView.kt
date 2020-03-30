package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.FansBean

interface FansView:BaseView{
    fun getFansRequest(data:FansBean)
    fun getFansError()
}