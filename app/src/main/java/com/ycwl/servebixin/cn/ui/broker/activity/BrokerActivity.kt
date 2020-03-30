package com.ycwl.servebixin.cn.ui.broker.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerBean
import com.ycwl.servebixin.cn.ui.broker.mvp.presenter.BrokerPresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.view.BrokerView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_broker.*
import kotlinx.android.synthetic.main.layout_title.*


class BrokerActivity :BaseActivity(),BrokerView {


    private val presenter by lazy{ BrokerPresenter(this,this,this) }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int =R.layout.activity_broker

    override fun setActivityTitle() {
        titleText.text="达人经纪人"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRight.setImageResource(R.mipmap.button_doubt)
    }

    override fun initActivityData() {

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(titleRight).subscribe {
            intentUtils.intentApplyBrokerQuestion()
        }
        Click.viewClick(layout_broker_ktv).subscribe {
            intentUtils.intentBrokerKTV()
        }
        Click.viewClick(layout_broker_serve).subscribe {
            intentUtils.intentBrokerServe()
        }
    }

    override fun getBrokerRequest(data: BrokerBean) {
        tv_broker_serve_num.text="${data.data.myServiceNum}人"
    }

    override fun onResume() {
        super.onResume()
        presenter.getBroker()
    }

}