package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.NearByKTVBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchKTVBean

interface ServeSetKTVView:BaseView{
    fun getSearchKTVRequest(data: SearchKTVBean)
    fun getSearchKTVError()
    fun getBindKTVRequest(data:EditUserBean)

    fun getNearByKTVRequest(data: NearByKTVBean)
}