package com.ycwl.servebixin.cn.ui.main.dialog

import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.ActivityUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseDialogFragment
import com.ycwl.servebixin.cn.utils.intent.intentUtils

import kotlinx.android.synthetic.main.dialog_login_other.*

class OtherLoginDialog: BaseDialogFragment(){
    override fun setLayoutID(): Int = R.layout.dialog_login_other

    override fun isFullScreen(): Boolean =true

    override fun setLayoutData() {
        this.isCancelable=false
    }

    override fun clickListener() {
        Click.viewClick(yes).subscribe {
            ActivityUtils.finishAllActivities()
            intentUtils.intentLogin()
        }
    }

}