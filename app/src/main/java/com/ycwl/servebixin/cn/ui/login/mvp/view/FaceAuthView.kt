package com.ycwl.servebixin.cn.ui.login.mvp.view

import com.ycwl.servebixin.cn.base.BaseView

interface FaceAuthView :BaseView{
    fun getSendFaceRequest()
    fun getSendFaceError()
}