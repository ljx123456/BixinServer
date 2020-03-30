package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeSetBean

interface ServeSetView: BaseView {
    fun getServeSetRequest(data: ServeSetBean)
    fun getServeSetOpenRequest(data: EditUserBean)
    fun getServeSetOpenError(flag:Int)
}