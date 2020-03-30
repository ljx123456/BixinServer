package com.ycwl.servebixin.cn.ui.login.activity

import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.login.mvp.body.ResetPwdBody
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.ResetPwdPresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.ResetPwdView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.dialog_normal_layout.*
import kotlinx.android.synthetic.main.layout_title.*

class ResetPasswordActivity : BaseActivity(), ResetPwdView {

    private var flag=true
    private val presenter by lazy { ResetPwdPresenter(this,this,this) }

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int =R.layout.activity_reset_password

    override fun setActivityTitle() {
        titleText.setText("设置新密码")
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
    }

    override fun initActivityData() {
        resetPassword.transformationMethod= PasswordTransformationMethod.getInstance()
        resetPwdHint.setImageResource(R.mipmap.ic_eye_off)
        tv_reset_password_phone.text=intent.getStringExtra("phone")
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(resetPwdHint).subscribe {
            if (flag){
                resetPassword.transformationMethod=HideReturnsTransformationMethod.getInstance()
                resetPwdHint.setImageResource(R.mipmap.ic_eye_on)
                flag=false
            }else{
                resetPassword.transformationMethod= PasswordTransformationMethod.getInstance()
                resetPwdHint.setImageResource(R.mipmap.ic_eye_off)
                flag=true
            }
        }

        resetPassword.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s!=null&&s.toString().length>0)
                    resetPwdTips.visibility=View.VISIBLE
                else
                    resetPwdTips.visibility=View.GONE

                if (s!=null&&s.toString().length>=6&&s.toString().length<=12)
                    resetPwdNext.isEnabled=true
                else
                    resetPwdNext.isEnabled=false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        Click.viewClick(resetPwdNext).subscribe {
            presenter.getResetPwd(ResetPwdBody(intent.getStringExtra("phone"),resetPassword.text.toString()))
        }
    }

    override fun getResetPwdRequest() {
        intentUtils.intentLogin()
        finish()
    }
}