package com.ycwl.servebixin.cn.ui.withdrawal.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawOldPwdBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.WithdrawOldPwdPresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawOldPwdView
import com.ycwl.servebixin.cn.ui.withdrawal.utils.PasswordCode
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_withdraw_old_pwd.*
import kotlinx.android.synthetic.main.layout_title.*

class WithdrawOldPwdActivity :BaseActivity(),WithdrawOldPwdView{


    private val presenter by lazy { WithdrawOldPwdPresenter(this,this,this) }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int =R.layout.activity_withdraw_old_pwd
    override fun setActivityTitle() {
        titleText.text="修改提现密码"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
       pc_withdraw_old_pwd.setOnInputListener(object :PasswordCode.OnInputListener{
           override fun onSucess(code: String) {
                presenter.getWithdrawOldPwd(WithdrawOldPwdBody(pc_withdraw_old_pwd.passwordCode))
               pc_withdraw_old_pwd.clean()
           }

           override fun onInput() {

           }
       })
    }

    override fun clickListener() {
       Click.viewClick(titleLeft).subscribe { finish() }
    }

    override fun getChangePwdRequest() {
        intentUtils.intentWithdrawSetPwd()
    }

    override fun getChangePwdError(msg:String) {
        Toast.Tips(msg)
    }

}