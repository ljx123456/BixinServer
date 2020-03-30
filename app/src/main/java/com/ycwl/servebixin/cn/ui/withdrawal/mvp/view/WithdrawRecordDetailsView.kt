package com.ycwl.servebixin.cn.ui.withdrawal.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawRecordDetailsBean

interface WithdrawRecordDetailsView : BaseView{
    fun getRecordDetailsRequest(data:WithdrawRecordDetailsBean)
}