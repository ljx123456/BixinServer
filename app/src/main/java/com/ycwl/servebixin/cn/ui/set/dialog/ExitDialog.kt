package com.ycwl.servebixin.cn.ui.set.dialog

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_exit.*

@SuppressLint("ValidFragment")
class ExitDialog(val exit: Exit) : BaseDialogFragment() {
    override fun isFullScreen(): Boolean = true

    override fun clickListener() {
        Click.viewClick(exitOK).subscribe { exit.OkExit() }
        Click.viewClick(exitOver).subscribe { dismiss() }

    }

    override fun setLayoutData() {

    }

    override fun setLayoutID(): Int = R.layout.dialog_exit
    interface Exit {
        fun OkExit()

    }
}