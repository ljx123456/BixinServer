package com.ycwl.servebixin.cn.ui.login.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.bean.CheckNameBean

interface RegisterDataView :BaseView{
    fun getRegisterDataRequest(data: ByCodeBean)
    fun setCheckNameRequest(data: CheckNameBean)
}