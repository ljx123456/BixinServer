package com.ycwl.servebixin.cn.ui.withdrawal.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.login.mvp.body.SendCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.body.ValidationCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.ResetPwdCodePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.ResetPwdCodeView
import com.ycwl.servebixin.cn.ui.login.utils.CodeTime
import com.ycwl.servebixin.cn.ui.login.utils.PhoneCode
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_set_withdraw_pwd_code.*
import kotlinx.android.synthetic.main.layout_title.*

class WithdrawResetPwdCodeActivity:BaseActivity(), ResetPwdCodeView{

    private val codeTime = CodeTime()
    private val presenter by lazy { ResetPwdCodePresenter(this, this, this) }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_set_withdraw_pwd_code

    override fun setActivityTitle() {
        titleText.setText("重置提现密码")
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
    }

    override fun initActivityData() {
        presenter.getSendCode(SendCodeBody(user.getUserPhone(), user.getUserToken(),9))
        codeTime.codeCountTimer(set_withdraw_password_sendAgainCode)
        tv_set_withdraw_password_code_phone.text="请输入"+ user.getUserPhone()+"收到的验证码以重置提现密码"
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        pc_set_withdraw_password_code.setOnInputListener(object : PhoneCode.OnInputListener{
            override fun onSucess(code: String?) {
                presenter.getValidationCode(ValidationCodeBody(user.getUserPhone(),pc_set_withdraw_password_code.phoneCode,9))
            }

            override fun onInput() {
            }
        })
        Click.viewClick(set_withdraw_password_sendAgainCode).subscribe{
            presenter.getSendCode(SendCodeBody(user.getUserPhone(), user.getUserToken(),9))
        }
    }

    override fun getSendCodeRequest() {
        codeTime.codeCountTimer(set_withdraw_password_sendAgainCode)
    }

    override fun getValidationCodeRequest() {
        intentUtils.intentWithdrawResetPwd()
    }

}