package com.ycwl.servebixin.cn.ui.broker.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVBean
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVDelBean
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVOpenBean

interface BrokerKTVView : BaseView{

    fun getBrokerKTVRequest(data:BrokerKTVBean)
    fun getBrokerKTVError()

    fun getBrokerKTVOpenRequest(data: BrokerKTVOpenBean)
    fun getBrokerKTVOpenError()

    fun getBrokerKTVDelRequest(data: BrokerKTVDelBean)
    fun getBrokerKTVDelError()

}