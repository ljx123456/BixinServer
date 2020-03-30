package com.ycwl.servebixin.cn.ui.withdrawal.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.AuthInfoBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.BindWithdrawTypeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawTypeBean

interface WithdrawTypeView :BaseView{
    fun getWithdrawTypeRequest(data:WithdrawTypeBean)
    fun getWithdrawTypeError()

    fun getAlipayInfo(data: AuthInfoBean)

    fun getBindAlipay(data: BindWithdrawTypeBean)

    fun getUnbindType()

}