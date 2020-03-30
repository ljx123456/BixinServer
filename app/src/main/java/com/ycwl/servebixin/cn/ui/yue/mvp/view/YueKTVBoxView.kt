package com.ycwl.servebixin.cn.ui.yue.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.BaoFangBean

interface YueKTVBoxView :BaseView{
    fun getBoxRequest(data:BaoFangBean)
}