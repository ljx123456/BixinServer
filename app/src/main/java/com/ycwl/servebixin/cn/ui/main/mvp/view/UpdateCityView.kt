package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
interface UpdateCityView: BaseView {
    fun getUpdateCityRequest()
    fun getUpdateCityError()
}