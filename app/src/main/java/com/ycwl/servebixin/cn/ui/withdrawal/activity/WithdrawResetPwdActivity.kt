package com.ycwl.servebixin.cn.ui.withdrawal.activity

import android.content.Intent
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.withdrawal.utils.PasswordCode
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_withdraw_set_pwd.*
import kotlinx.android.synthetic.main.layout_title.*

class WithdrawResetPwdActivity :BaseActivity(){
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_withdraw_set_pwd

    override fun setActivityTitle() {
        titleText.text="重置提现密码"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        tv_withdraw_set_pwd_title.text="请设置您的新提现密码，用于余额提现"
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        pc_withdraw_set_password.setOnInputListener(object : PasswordCode.OnInputListener{
            override fun onSucess(code: String?) {
                intentUtils.intentWithdrawResetPwdAgain(pc_withdraw_set_password.passwordCode)
                pc_withdraw_set_password.clean()
            }

            override fun onInput() {

            }

        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent!=null&&intent.getStringExtra("tips")!=null&&!intent.getStringExtra("tips").equals("")&&intent.getStringExtra("tips").equals("错误")){
            tv_withdraw_set_password_tips.visibility= View.VISIBLE
        }else{
            tv_withdraw_set_password_tips.visibility= View.GONE
        }
    }
}