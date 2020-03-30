package com.ycwl.servebixin.cn.ui.broker.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerRecordBean

interface ApplyBrokerRecordView :BaseView{
    fun getRecordRequest(data :ApplyBrokerRecordBean)
}