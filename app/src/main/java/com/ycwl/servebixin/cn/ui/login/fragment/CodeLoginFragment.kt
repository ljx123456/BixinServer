package com.ycwl.servebixin.cn.ui.login.fragment

import android.text.Editable
import android.text.TextWatcher
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseFragment
import com.ycwl.servebixin.cn.ui.login.mvp.bean.SendCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.SendCodeBody
import com.ycwl.servebixin.cn.ui.login.mvp.data.SendCodeData
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.SendCodePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.SendCodeView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.fragment_login_code.*

class CodeLoginFragment:BaseFragment(),SendCodeView{

    override fun openEventBus(): Boolean =false
    private val presenter by lazy { SendCodePresenter(this,this,activity!!) }

    override fun setLayoutTitle() {

    }

    override fun initFragmentData() {
    }

    override fun setFragmentListener() {
        Click.viewClick(sendCode).subscribe {
            presenter.getCode(SendCodeBody(codePhone.text.toString(), "", 0))
        }
        codePhone.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s!!.toString().length==11)
                    sendCode.isEnabled=true
                else
                    sendCode.isEnabled=false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    override fun layoutID(): Int = R.layout.fragment_login_code



    override fun getSendCodeError() {

    }

    override fun getSendCodeRequest() {
        intentUtils.intentLoginCode(codePhone.text.toString())
    }


}