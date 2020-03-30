package com.ycwl.servebixin.cn.ui.broker.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerRecordDetailsBean

interface ApplyBrokerRecordDetailsView :BaseView{
    fun getApplyBrokerRecordDetailsRequest(data : ApplyBrokerRecordDetailsBean)
}