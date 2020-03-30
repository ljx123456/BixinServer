package com.ycwl.servebixin.cn.ui.login.mvp.view

import com.ycwl.servebixin.cn.base.BaseView

interface ResetPwdCodeView : BaseView{
    fun getSendCodeRequest()
    fun getValidationCodeRequest()
}