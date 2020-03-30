package com.ycwl.servebixin.cn.ui.broker.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerServeBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class BrokerServeAdapter(serves:MutableList<BrokerServeBean.DataBean>):BaseQuickAdapter<BrokerServeBean.DataBean,BaseViewHolder>(R.layout.item_broker_serve,serves){
    override fun convert(helper: BaseViewHolder, item: BrokerServeBean.DataBean) {
        ImageLoad.setUserHead(item.avatar,helper.getView(R.id.brokerServeImg) as RoundedImageView)
        var sex=""
        if (item.sex==1){
            sex="男"
        }else if (item.sex==2){
            sex="女"
        }
        helper.setText(R.id.brokerServeName,item.nickname)
                .setText(R.id.brokerServeContent,sex+" "+"${item.age}岁 "+item.constellation)
                .setText(R.id.brokerServePhone,item.phone)
                .addOnClickListener(R.id.brokerServePhone)

    }

}