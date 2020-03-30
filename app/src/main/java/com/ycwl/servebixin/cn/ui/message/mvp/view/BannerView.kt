package com.ycwl.servebixin.cn.ui.message.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.message.mvp.bean.BannerBean

interface BannerView:BaseView{
    fun getBannerRequest(data:BannerBean)
}