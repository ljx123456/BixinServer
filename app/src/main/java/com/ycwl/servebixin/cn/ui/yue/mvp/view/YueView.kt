package com.ycwl.servebixin.cn.ui.yue.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.YueBean

interface YueView :BaseView{
    fun getOrderCodeRequest(data:YueBean)
}