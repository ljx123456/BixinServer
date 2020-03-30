package com.ycwl.servebixin.cn.ui.withdrawal.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawRecordListBean

interface WithdrawRecordView : BaseView{
    fun getWithdrawRecordRequest(data:WithdrawRecordListBean)
    fun getWithdrawRecordError()
}