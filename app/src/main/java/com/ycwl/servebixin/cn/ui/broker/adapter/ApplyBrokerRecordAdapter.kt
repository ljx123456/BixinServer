package com.ycwl.servebixin.cn.ui.broker.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerRecordBean

class ApplyBrokerRecordAdapter(records:MutableList<ApplyBrokerRecordBean.DataBean>) :BaseQuickAdapter<ApplyBrokerRecordBean.DataBean,BaseViewHolder>(R.layout.item_apply_broker_record,records){
    override fun convert(helper: BaseViewHolder, item: ApplyBrokerRecordBean.DataBean) {
        if (item.auditState==0){
            helper.setText(R.id.tv_apply_broker_record_title,"申请失败")
                    .setTextColor(R.id.tv_apply_broker_record_title,Color.parseColor("#FF6464"))
                    .setText(R.id.tv_apply_broker_record_time,item.updateTime)
                    .setVisible(R.id.tv_apply_broker_record_tips,false)
                    .setText(R.id.tv_apply_broker_record_tips,"24小时后自动删除")
        }else if (item.auditState==2){
            helper.setText(R.id.tv_apply_broker_record_title,"资料审核中")
                    .setText(R.id.tv_apply_broker_record_time,item.updateTime)
                    .setTextColor(R.id.tv_apply_broker_record_title,Color.parseColor("#535252"))
                    .setVisible(R.id.tv_apply_broker_record_tips,false)
        }
    }

}