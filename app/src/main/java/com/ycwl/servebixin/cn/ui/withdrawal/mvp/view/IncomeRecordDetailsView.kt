package com.ycwl.servebixin.cn.ui.withdrawal.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeRecordDetailsBean

interface IncomeRecordDetailsView : BaseView{
    fun getIncomeRecordDetailsRequest(data:IncomeRecordDetailsBean)
}