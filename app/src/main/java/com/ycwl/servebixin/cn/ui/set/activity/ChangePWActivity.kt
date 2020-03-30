package com.ycwl.servebixin.cn.ui.set.activity

import android.content.DialogInterface
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.set.mvp.body.ChangePWBody
import com.ycwl.servebixin.cn.ui.set.mvp.presenter.ChangePWPresenter
import com.ycwl.servebixin.cn.ui.set.mvp.view.ChangePWView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.layout_title.*

class ChangePWActivity:BaseActivity(), ChangePWView{
    private var flag=true
    private val presenter by lazy { ChangePWPresenter(this,this,this) }

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_reset_password

    override fun setActivityTitle() {
        titleText.setText("设置新密码")
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
    }

    override fun initActivityData() {
        resetPwdNext.text="确认修改"
        resetPassword.transformationMethod= PasswordTransformationMethod.getInstance()
        resetPwdHint.setImageResource(R.mipmap.ic_eye_off)
        tv_reset_password_phone.text= user.getUserPhone()
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(resetPwdHint).subscribe {
            if (flag){
                resetPassword.transformationMethod= HideReturnsTransformationMethod.getInstance()
                resetPwdHint.setImageResource(R.mipmap.ic_eye_on)
                flag=false
            }else{
                resetPassword.transformationMethod= PasswordTransformationMethod.getInstance()
                resetPwdHint.setImageResource(R.mipmap.ic_eye_off)
                flag=true
            }
        }

        resetPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!=null&&s.toString().length>0)
                    resetPwdTips.visibility= View.VISIBLE
                else
                    resetPwdTips.visibility= View.GONE

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
            presenter.getChangePW(ChangePWBody(resetPassword.text.toString()))
        }
    }

    override fun getChangePWRequest() {
        ShowDialog.showCustomDialog(this,"密码修改成功,请重新登录","确定",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                intentUtils.intentLogin()
                finish()
            }
        })
//        intentUtils.intentLogin()
//        finish()
    }
}