package com.ycwl.servebixin.cn.ui.broker.activity

import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.broker.adapter.ApplyBrokerRecordAdapter
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerRecordBean
import com.ycwl.servebixin.cn.ui.broker.mvp.presenter.ApplyBrokerRecordPresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.view.ApplyBrokerRecordView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_apply_broker_record.*
import kotlinx.android.synthetic.main.layout_title.*

class ApplyBrokerRecordActivity :BaseActivity(),ApplyBrokerRecordView{

    private val presenter by lazy { ApplyBrokerRecordPresenter(this,this,this) }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_apply_broker_record

    override fun setActivityTitle() {
        titleText.text="达人经纪人"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRight.setImageResource(R.mipmap.button_doubt)

    }

    override fun initActivityData() {

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish()  }
        Click.viewClick(titleRight).subscribe { intentUtils.intentApplyBrokerQuestion() }

    }

    override fun getRecordRequest(data: ApplyBrokerRecordBean) {
        var adapter =ApplyBrokerRecordAdapter(data.data)
        var manager=LinearLayoutManager(this)
        manager.orientation=LinearLayout.VERTICAL
        recy_apply_record.layoutManager=manager
        recy_apply_record.adapter=adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            intentUtils.intentApplyBrokerRecordDetails(data.data[position].auditId)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getApplyBrokerRecord()
    }

}