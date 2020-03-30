package com.ycwl.servebixin.cn.base

import android.view.View
import com.ycwl.servebixin.cn.utils.utils.FastDoubleClick

/**
 * Created by Administrator on 2018/1/26 0026.
 */
interface BaseClickListener : View.OnClickListener {

    override fun onClick(v: View?) {
        if (!FastDoubleClick.isFastDoubleClick()) viewClick(v!!)
    }

    fun viewClick(v: View)
}