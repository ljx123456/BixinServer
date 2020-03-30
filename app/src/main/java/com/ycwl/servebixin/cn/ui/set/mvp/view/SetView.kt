package com.ycwl.servebixin.cn.ui.set.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.set.mvp.bean.LogoutBean

interface SetView:BaseView{
    fun getLogoutRequest(data:LogoutBean)
}