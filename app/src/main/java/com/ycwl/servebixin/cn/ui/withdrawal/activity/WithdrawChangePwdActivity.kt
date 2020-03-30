package com.ycwl.servebixin.cn.ui.withdrawal.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawChangePwdBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.WithdrawChangePwdPresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawChangePwdView
import com.ycwl.servebixin.cn.ui.withdrawal.utils.PasswordCode
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_set_withdraw_pwd_again.*
import kotlinx.android.synthetic.main.layout_title.*

class WithdrawChangePwdActivity:BaseActivity(),WithdrawChangePwdView{

    private val presenter by lazy { WithdrawChangePwdPresenter(this,this,this) }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int  =R.layout.activity_set_withdraw_pwd_again

    override fun setActivityTitle() {
        titleText.text="修改提现密码"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        tv_set_withdraw_password_again_title.text="请再次输入新密码，并保证两次输入一致"
        set_withdraw_password_again.isEnabled=false
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        pc_set_withdraw_password_again.setOnInputListener(object : PasswordCode.OnInputListener{
            override fun onSucess(code: String) {
                set_withdraw_password_again.isEnabled=true

            }

            override fun onInput() {

            }
        })
        Click.viewClick(set_withdraw_password_again).subscribe {
            if (intent.getStringExtra("pwd").equals(pc_set_withdraw_password_again.passwordCode)){
                presenter.getWithdrawChangePwd(WithdrawChangePwdBody(pc_set_withdraw_password_again.passwordCode))
                pc_set_withdraw_password_again.clean()
            }else{
                intentUtils.intentWithdrawSetPwd("错误")
                pc_set_withdraw_password_again.clean()
                finish()
            }

        }
    }

    override fun getWithdrawChangePwdAgainRequest() {
        Toast.Tips("提现密码修改成功")
        intentUtils.intentWithdrawSet()
    }

}