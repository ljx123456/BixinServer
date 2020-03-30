package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.UploadCardBean

interface RealnameCardView:BaseView{
    fun getRealnameCardRequest(data:UploadCardBean)
    fun getRealnameCardError()
}