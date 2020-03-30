package com.ycwl.servebixin.cn.ui.set.activity

import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.PersonPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.PersonView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.layout_title.*

class AccountActivity:BaseActivity(),PersonView{

    private val presenter by lazy { PersonPresenter(this,this,this) }
    private var state=-1

    override fun openEventBus(): Boolean=false

    override fun getActivityLayout(): Int =R.layout.activity_account

    override fun setActivityTitle() {
        titleText.text="账号与安全"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(layout_change_pw).subscribe {
            intentUtils.intentChangePWCode()
        }
    }

    override fun getPersonDataRequest(data: ByCodeBean) {
        if (data.data!=null&&data.data.serviceIDCardAuditState!=null){
            state=data.data.serviceIDCardAuditState
            when(state){
                -1->{//未提交
                    tv_realname_need_open.visibility=View.VISIBLE
                    tv_realname_need_open.text="待认证"
                    tv_realname_opened.visibility=View.GONE
                    tv_realname.visibility=View.GONE
                }
                0->{//未通过
                    tv_realname_need_open.visibility=View.VISIBLE
                    tv_realname_need_open.text="审核未通过"
                    tv_realname_opened.visibility=View.GONE
                    tv_realname.visibility=View.GONE
                }
                1->{//通过
                    tv_realname_need_open.visibility=View.GONE
                    tv_realname_opened.visibility=View.GONE
                    tv_realname.visibility=View.VISIBLE
                }
                2->{//审核中
                    tv_realname_need_open.visibility=View.GONE
                    tv_realname_opened.visibility=View.VISIBLE
                    tv_realname.visibility=View.GONE
                }
            }
            Click.viewClick(layout_account_realname).subscribe {
                if (state==1){
                    intentUtils.intentAccountDetails()
                }else if (state==-1){
                    intentUtils.intentRealnameName()
                } else{
                    intentUtils.intentRealnameDetails()
                }
            }
        }
    }

    override fun getPersonDataError() {

    }

    override fun onResume() {
        super.onResume()
        presenter.getPersonData()
    }

}