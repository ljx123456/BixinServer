package com.ycwl.servebixin.cn.ui.login.activity

import android.text.Editable
import android.text.TextWatcher
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.login.mvp.body.SendCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.SendCodePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.SendCodeView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_reset_password_phone.*
import kotlinx.android.synthetic.main.layout_title.*

class ResetPasswordPhoneActivity : BaseActivity(),SendCodeView{

    private val presenter by lazy { SendCodePresenter(this,this,this) }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int  = R.layout.activity_reset_password_phone

    override fun setActivityTitle() {
        titleText.text="找回密码"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
    }

    override fun initActivityData() {

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }

        resetpwd_codePhone.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s!=null&&s.toString().length==11)
                    resetpwd_sendCode.isEnabled=true
                else
                    resetpwd_sendCode.isEnabled=false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        Click.viewClick(resetpwd_sendCode).subscribe{
            presenter.getCode(SendCodeBody(resetpwd_codePhone.text.toString(),null,1))
        }
    }

    override fun getSendCodeRequest() {
        intentUtils.intentResetPwdCode(resetpwd_codePhone.text.toString())
    }

    override fun getSendCodeError() {

    }

}