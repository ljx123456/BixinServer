package com.ycwl.servebixin.cn.ui.login.mvp.view

import com.ycwl.servebixin.cn.base.BaseView

interface SendCodeView:BaseView{
    fun getSendCodeRequest()
    fun getSendCodeError()
}