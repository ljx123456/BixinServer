package com.ycwl.servebixin.cn.ui.broker.adapter

import android.widget.Switch
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class BrokerKTVAdapter(list: MutableList<BrokerKTVBean.DataBean>):BaseQuickAdapter<BrokerKTVBean.DataBean,BaseViewHolder>(R.layout.item_broker_ktv,list){
    override fun convert(helper: BaseViewHolder, item: BrokerKTVBean.DataBean) {
//        if (item.enableField==0){
//            helper.getView<Switch>(R.id.brokerKTVSwitch).isChecked=false
//        }else{
//            helper.getView<Switch>(R.id.brokerKTVSwitch).isChecked=true
//        }
        ImageLoad.setUserHead(item.businessImg,helper.getView(R.id.brokerKTVImg) as RoundedImageView)
        helper.getView<Switch>(R.id.brokerKTVSwitch).isChecked = item.enableField==1
        helper.setText(R.id.brokerKTVName,item.businessName)
                .setText(R.id.brokerKTVAddress,item.businessAddress)
                .addOnClickListener(R.id.brokerKTVSwitch)
                .addOnClickListener(R.id.brokerKTVDel)

    }
}