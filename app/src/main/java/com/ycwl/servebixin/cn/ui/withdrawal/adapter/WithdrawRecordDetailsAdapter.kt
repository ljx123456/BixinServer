package com.ycwl.servebixin.cn.ui.withdrawal.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawRecordListBean

class WithdrawRecordDetailsAdapter(records:MutableList<WithdrawRecordListBean.DataBean>) :BaseQuickAdapter<WithdrawRecordListBean.DataBean,BaseViewHolder>(R.layout.item_withdraw_record_details,records){
    override fun convert(helper: BaseViewHolder, item: WithdrawRecordListBean.DataBean) {
        var time=item.applyTime.substring(5,item.applyTime.length-3)
        helper.setText(R.id.tv_withdraw_record_money,"${item.recodePrice}")
                .setText(R.id.tv_withdraw_record_time,time)
        if (item.drawCashType==1){
            helper.setText(R.id.tv_withdraw_record_type,"提现到-支付宝")
        }else{
            helper.setText(R.id.tv_withdraw_record_type,"提现到-微信")
        }
    }

}