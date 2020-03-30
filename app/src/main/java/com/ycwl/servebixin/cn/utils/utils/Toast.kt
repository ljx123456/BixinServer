package com.ycwl.servebixin.cn.utils.utils

import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils
import com.ycwl.servebixin.cn.base.BaseContext.getContext
import com.ycwl.servebixin.cn.R


/**
 * Created by Administrator on 2018/2/1 0001.
 */
object Toast {

    fun Tips(msg: String) {
        ToastUtils.setGravity(Gravity.CENTER, 0, 150)
        ToastUtils.setBgColor(getContext().resources.getColor(R.color.toastBg))
        ToastUtils.setMsgColor(getContext().resources.getColor(R.color.colorPrimary))
        ToastUtils.showShort(msg)
    }

    fun LongTips(msg: String) {
        ToastUtils.setGravity(Gravity.CENTER, 0, 150)
        ToastUtils.setBgColor(getContext().resources.getColor(R.color.toastBg))
        ToastUtils.setMsgColor(getContext().resources.getColor(R.color.colorPrimary))
        ToastUtils.showLong(msg)
    }
}