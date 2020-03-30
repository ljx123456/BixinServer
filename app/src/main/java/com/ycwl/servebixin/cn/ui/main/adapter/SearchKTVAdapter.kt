package com.ycwl.servebixin.cn.ui.main.adapter

import android.graphics.Color
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchKTVBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class SearchKTVAdapter(ktvs:MutableList<SearchKTVBean.DataBean>):BaseQuickAdapter<SearchKTVBean.DataBean,BaseViewHolder>(R.layout.item_serve_set_ktv,ktvs){
    override fun convert(helper: BaseViewHolder, item: SearchKTVBean.DataBean) {
        ImageLoad.setUserHead(item.businessImg,helper.getView(R.id.serveSetKTVImg) as RoundedImageView)
        helper.setText(R.id.tv_serve_set_ktv_name,item.businessName)
                .setText(R.id.tv_serve_set_ktv_content,item.businessAddress)
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