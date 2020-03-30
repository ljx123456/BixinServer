package com.ycwl.servebixin.cn.ui.broker.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class BrokerKTVApplyAdapter(list:MutableList<BrokerKTVBean.DataBean>):BaseQuickAdapter<BrokerKTVBean.DataBean,BaseViewHolder>(R.layout.item_broker_ktv_apply,list){
    override fun convert(helper: BaseViewHolder, item: BrokerKTVBean.DataBean) {
        ImageLoad.setUserHead(item.businessImg,helper.getView(R.id.brokerKTVApplyImg) as RoundedImageView)
        when(item.auditState){
            1->{
                helper.setText(R.id.brokerKTVApplyState,"KTV审核中")
                        .setVisible(R.id.brokerKTVApplyTips,false)
            }
            0->{
                helper.setText(R.id.brokerKTVApplyState,"审核未通过")
                        .setVisible(R.id.brokerKTVApplyTips,true)
            }

        }
        helper.setText(R.id.brokerKTVApplyName,item.businessName)
                .setText(R.id.brokerKTVApplyTime,item.createTime)
                .setText(R.id.brokerKTVApplyAddress,item.businessAddress)
    }

}