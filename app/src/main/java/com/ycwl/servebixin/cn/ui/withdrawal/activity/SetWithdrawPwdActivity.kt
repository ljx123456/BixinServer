package com.ycwl.servebixin.cn.ui.withdrawal.activity

import android.content.Intent
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.login.utils.PhoneCode
import com.ycwl.servebixin.cn.ui.withdrawal.utils.PasswordCode
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_set_withdraw_pwd.*
import kotlinx.android.synthetic.main.layout_title.*

class SetWithdrawPwdActivity : BaseActivity(){
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_set_withdraw_pwd

    override fun setActivityTitle() {
        titleText.text="设置提现密码"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {


    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        pc_set_withdraw_password.setOnInputListener(object : PasswordCode.OnInputListener{
            override fun onSucess(code: String?) {
                intentUtils.intentSetWithdrawPwdAgain(pc_set_withdraw_password.passwordCode)
                pc_set_withdraw_password.clean()
            }

            override fun onInput() {
            }
        })
    }




    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent!=null&&intent.getStringExtra("tips")!=null&&!intent.getStringExtra("tips").equals("")&&intent.getStringExtra("tips").equals("错误")){
            tv_set_withdraw_password_tips.visibility=View.VISIBLE
        }else{
            tv_set_withdraw_password_tips.visibility=View.GONE
        }
    }

}