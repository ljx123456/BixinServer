package com.ycwl.servebixin.cn.ui.login.activity

import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.jakewharton.rxbinding2.widget.RxTextView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.ACache
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_set_password.*
import kotlinx.android.synthetic.main.layout_title.*

/**
 * 设置密码
 */
class SetPasswordActivity : BaseActivity() {

    private var flag=true

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_set_password

    override fun setActivityTitle() {
        titleText.text="设置密码"
//        titleLeft.setImageResource(R.mipmap.ic_left)
    }

    override fun initActivityData() {
        registerPassword.transformationMethod= PasswordTransformationMethod.getInstance()
        pwdHint.setImageResource(R.mipmap.ic_eye_off)
    }

    override fun clickListener() {
//        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(pwdHint).subscribe {
            if (flag){
                registerPassword.transformationMethod=HideReturnsTransformationMethod.getInstance()
                pwdHint.setImageResource(R.mipmap.ic_eye_on)
                flag=false
            }else{
                registerPassword.transformationMethod= PasswordTransformationMethod.getInstance()
                pwdHint.setImageResource(R.mipmap.ic_eye_off)
                flag=true
            }
        }

        registerPassword.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s!=null&&s.toString().length>0){
                    setPwdTips.visibility=View.VISIBLE
                }else{
                    setPwdTips.visibility=View.GONE
                }
                if (s!=null&&s.toString().length>=6&&s.toString().length<=12){
                    setPwdNext.isEnabled=true
                }else{
                    setPwdNext.isEnabled=false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        Click.viewClick(setPwdNext).subscribe {
            var mCache = ACache.get(this)
            mCache.put("password" ,registerPassword.text.toString())
            intentUtils.intentRegisterData()
        }

    }
}