package com.ycwl.servebixin.cn.ui.withdrawal.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeRecordListBean

interface IncomeRecordView :BaseView{
    fun getIncomeRecordRequest(data :IncomeRecordListBean)
    fun getIncomeRecordError()
}