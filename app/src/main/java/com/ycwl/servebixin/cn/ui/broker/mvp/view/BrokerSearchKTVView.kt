package com.ycwl.servebixin.cn.ui.broker.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerAddKTVBean
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerSearchKTVBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchKTVBean

interface BrokerSearchKTVView :BaseView{
    fun getSearchRequest(data: SearchKTVBean)
    fun getSearchError()

    fun getAddRequest(data:BrokerAddKTVBean)
    fun getAddError()
}