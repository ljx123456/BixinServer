package com.ycwl.servebixin.cn.ui.set.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.main.mvp.bean.RealnameDetailsBean
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.RealnameDetailsPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.RealnameDetailsView
import kotlinx.android.synthetic.main.activity_realname.*
import kotlinx.android.synthetic.main.layout_title.*

class AccountDetailsActivity:BaseActivity(),RealnameDetailsView{

    private val presenter by lazy { RealnameDetailsPresenter(this,this,this) }

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int =R.layout.activity_realname

    override fun setActivityTitle() {
        titleText.text="实名认证"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        presenter.getDetails()
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
    }

    override fun getDetailsRequest(data: RealnameDetailsBean) {
        if (data.data!=null&&data.data.auditState==1){
            name.text=data.data.realName
            idcard.text=data.data.idCardNo
        }
    }

    override fun getDetailsError() {

    }
}