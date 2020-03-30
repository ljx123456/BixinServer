package com.ycwl.servebixin.cn.ui.withdrawal.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeBean

interface IncomeView : BaseView{
    fun getIncomeRequest(data:IncomeBean)
    fun getIncomeError(data: Int)
}