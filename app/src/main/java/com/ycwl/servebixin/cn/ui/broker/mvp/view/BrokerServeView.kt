package com.ycwl.servebixin.cn.ui.broker.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerServeBean

interface BrokerServeView :BaseView{
    fun getBrokerServeRequest(data:BrokerServeBean)
}