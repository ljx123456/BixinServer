package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean

interface PersonView :BaseView{
    fun getPersonDataRequest(data: ByCodeBean)
    fun getPersonDataError()
}