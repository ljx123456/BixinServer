package com.ycwl.servebixin.cn.ui.yue.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinkDetailsBean

interface DrinkDetailsView : BaseView {
    fun getDrinkDetailsRequest(data: DrinkDetailsBean)
}