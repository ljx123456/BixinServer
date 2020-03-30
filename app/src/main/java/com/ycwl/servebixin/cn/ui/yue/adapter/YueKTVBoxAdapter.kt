package com.ycwl.servebixin.cn.ui.yue.adapter

import android.graphics.Color
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.BaoFangTypeBean

class YueKTVBoxAdapter(list:MutableList<BaoFangTypeBean>):BaseQuickAdapter<BaoFangTypeBean,BaseViewHolder>(R.layout.item_yue_ktv_box,list){
    override fun convert(helper: BaseViewHolder, item: BaoFangTypeBean) {
        helper.setText(R.id.item_yue_ktv_box_name,item.boxTypeName)
        if (item.isFlag){
            helper.setBackgroundColor(R.id.item_yue_ktv_box_name,Color.parseColor("#ffffff"))
                    .setTextColor(R.id.item_yue_ktv_box_name,Color.parseColor("#535252"))
        }else{
            helper.setBackgroundColor(R.id.item_yue_ktv_box_name,Color.parseColor("#ECF0F4"))
                    .setTextColor(R.id.item_yue_ktv_box_name,Color.parseColor("#ff8b8b8b"))
        }

    }

}