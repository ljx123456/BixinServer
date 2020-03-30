package com.ycwl.servebixin.cn.ui.withdrawal.activity

import android.content.DialogInterface
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_withdraw_set.*
import kotlinx.android.synthetic.main.layout_title.*

class WithdrawSetActivity :BaseActivity(){
    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int = R.layout.activity_withdraw_set

    override fun setActivityTitle() {
        titleText.text="提现设置"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(withdrawSetType).subscribe {
            intentUtils.intentWithdrawType("1")
        }
        Click.viewClick(withdrawSetPwd).subscribe {
            intentUtils.intentWithdrawOldPwd()
        }
        Click.viewClick(forgetWithdrawSetPwd).subscribe {
            ShowDialog.showCustomDialogNoTitle(this,"继续操作将为当前登录账号 发送一条验证码以便您重置密码","继续", "取消",object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            dialog.dismiss()
                            intentUtils.intentWithdrawResetPwdCode()
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }
            })
        }
    }

}