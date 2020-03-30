package com.ycwl.servebixin.cn.ui.withdrawal.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawRecordDetailsBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawRecordDetailsBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.WithdrawRecordDetailsPresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawRecordDetailsView
import kotlinx.android.synthetic.main.activity_withdraw_record_details.*
import kotlinx.android.synthetic.main.layout_title.*

class WithdrawRecordDetailsActivity:BaseActivity(), WithdrawRecordDetailsView{

    private val presenter by lazy { WithdrawRecordDetailsPresenter(this,this,this) }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_withdraw_record_details

    override fun setActivityTitle() {
        titleText.text="提现详情"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        presenter.getWithdrawRecordDetails(WithdrawRecordDetailsBody(intent.getIntExtra("id",0)))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
    }

    override fun getRecordDetailsRequest(data: WithdrawRecordDetailsBean) {
        withdrawRecordID.text=data.data.orderNo
        withdrawRecordMoney.text="${data.data.recodePrice}"
        withdrawRecordFee.text="${data.data.servicePrice}"
        withdrawRecordStartTime.text=data.data.applyTime
        withdrawRecordEndTime.text=data.data.arrivalAccountTime
        if (data.data.drawCashType==1){
            withdrawRecordCount.text=data.data.drawCashAccount+"（支付宝）"
        }else{
            withdrawRecordCount.text=data.data.drawCashAccount+"（微信）"
        }

    }

}