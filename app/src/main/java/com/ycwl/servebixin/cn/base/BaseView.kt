package com.ycwl.servebixin.cn.base

import android.content.Context
import com.ycwl.servebixin.cn.utils.dialog.LoadDialog

/**
 * Created by Administrator on 2017/12/18 0018.
 */
interface BaseView {

    fun showLoading(context: Context) {
        LoadDialog.show(context)
    }

    fun dismissLoading(context: Context) {
        LoadDialog.dismiss(context)
    }


}