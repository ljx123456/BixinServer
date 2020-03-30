package com.ycwl.servebixin.cn.ui.yue.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.KTVBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class YueKTVAdapter(ktvs:MutableList<KTVBean.DataBean>):BaseQuickAdapter<KTVBean.DataBean,BaseViewHolder>(R.layout.item_yue_ktv,ktvs){
    override fun convert(helper: BaseViewHolder, item: KTVBean.DataBean) {
        ImageLoad.setImage(item.businessImg,helper.getView(R.id.item_yue_ktv_img) as RoundedImageView)
        helper.setText(R.id.item_yue_ktv_name,item.businessName)
                .setText(R.id.item_yue_ktv_address,item.businessAddress)
    }

}