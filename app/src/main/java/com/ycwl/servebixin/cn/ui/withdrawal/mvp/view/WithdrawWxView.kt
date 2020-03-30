package com.ycwl.servebixin.cn.ui.withdrawal.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.BindWithdrawTypeBean

interface WithdrawWxView:BaseView{
    fun getBindWXRequest(data:BindWithdrawTypeBean)
}