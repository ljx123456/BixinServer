package com.ycwl.servebixin.cn.ui.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.main.mvp.bean.NearByKTVBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad
import java.math.BigDecimal

class NearByKTVAdapter(ktvs:MutableList<NearByKTVBean.DataBean>): BaseQuickAdapter<NearByKTVBean.DataBean, BaseViewHolder>(R.layout.item_serve_set_ktv,ktvs){
    override fun convert(helper: BaseViewHolder, item: NearByKTVBean.DataBean) {
        ImageLoad.setUserHead(item.businessImg,helper.getView(R.id.serveSetKTVImg) as RoundedImageView)
        helper.setText(R.id.tv_serve_set_ktv_name,item.businessName)
        if (item.distance.compareTo(BigDecimal(1000))==-1){
            helper.setText(R.id.tv_serve_set_ktv_content,item.businessAddress+"（${item.distance.setScale(1,BigDecimal.ROUND_HALF_UP)}m）")
        }else{
            helper.setText(R.id.tv_serve_set_ktv_content,item.businessAddress+"（${(item.distance.divide(BigDecimal(1000))).setScale(1,BigDecimal.ROUND_HALF_UP)}km）")
        }

        if (item.isAdd==0){
            helper.setVisible(R.id.tv_serve_set_ktv_bind,true)
                    .setVisible(R.id.tv_serve_set_ktv_binded,false)
                    .addOnClickListener(R.id.tv_serve_set_ktv_bind)
        }else{
            helper.setVisible(R.id.tv_serve_set_ktv_bind,false)
                    .setVisible(R.id.tv_serve_set_ktv_binded,true)
        }

    }

}