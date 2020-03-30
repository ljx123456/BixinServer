package com.ycwl.servebixin.cn.ui.set.activity

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
import kotlinx.android.synthetic.main.activity_reset_password_code.*
import kotlinx.android.synthetic.main.layout_title.*

class ChangePWCodeActivity:BaseActivity(), ResetPwdCodeView{
    private val codeTime = CodeTime()
    private val presenter by lazy { ResetPwdCodePresenter(this, this, this) }

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_reset_password_code

    override fun setActivityTitle() {
        titleText.setText("修改密码")
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
    }

    override fun initActivityData() {
        codeTime.codeCountTimer(reset_password_sendAgainCode)
        tv_reset_password_code_phone.text=user.getUserPhone()
        presenter.getSendCode(SendCodeBody(user.getUserPhone(),user.getUserToken(),6))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        pc_reset_password.setOnInputListener(object : PhoneCode.OnInputListener{
            override fun onSucess(code: String?) {
                presenter.getValidationCode(ValidationCodeBody(tv_reset_password_code_phone.text.toString(),pc_reset_password.phoneCode,6))
                pc_reset_password.clean()
            }

            override fun onInput() {
            }
        })
        Click.viewClick(reset_password_sendAgainCode).subscribe{
            presenter.getSendCode(SendCodeBody(tv_reset_password_code_phone.text.toString(),user.getUserToken(),6))
        }
    }

    override fun getSendCodeRequest() {
        codeTime.codeCountTimer(reset_password_sendAgainCode)
    }

    override fun getValidationCodeRequest() {
        intentUtils.intentChangePW()
    }
}