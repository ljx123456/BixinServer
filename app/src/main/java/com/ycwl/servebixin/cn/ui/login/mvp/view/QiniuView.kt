package com.ycwl.servebixin.cn.ui.login.mvp.view

import com.ycwl.servebixin.cn.base.BaseView

interface QiniuView: BaseView {
    fun sendSucceedImage(fileUrlList: ArrayList<String>)
    fun sendFileErrorImage()
}