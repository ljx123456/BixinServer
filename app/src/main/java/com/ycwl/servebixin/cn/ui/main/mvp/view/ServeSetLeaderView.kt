package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchLeaderBean

interface ServeSetLeaderView:BaseView{
    fun getSearchLeaderRequest(data:SearchLeaderBean)
    fun getSearchLeaderError()
    fun getBindLeaderRequest(data:EditUserBean)
}