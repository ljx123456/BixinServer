package com.ycwl.servebixin.cn.ui.main.mvp.view

import com.ycwl.servebixin.cn.base.BaseView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.UpdateBean

/**
 * Created by Administrator on 2018/11/7 0007.
 */
interface SplashView : BaseView {
    fun getUpdateRequest(data: UpdateBean)
    fun getUpdateError(code:Int,message:String)
}