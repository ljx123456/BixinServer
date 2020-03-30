package com.ycwl.servebixin.cn.ui.login.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean

interface ByPwdView: BaseView {
    fun getByPwdRequest(data: ByCodeBean)
}