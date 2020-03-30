package com.ycwl.servebixin.cn.ui.yue.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.BaoFangListBean

class YueKTVBoxDetailsAdapter(list: MutableList<BaoFangListBean>):BaseQuickAdapter<BaoFangListBean,BaseViewHolder>(R.layout.item_yue_ktv_box_details,list){
    override fun convert(helper: BaseViewHolder, item: BaoFangListBean) {
        helper.setText(R.id.item_yue_ktv_box_details_name,item.boxName)
        if (item.isFlag){
            helper.setChecked(R.id.item_yue_ktv_box_details_check,true)
        }else{
            helper.setChecked(R.id.item_yue_ktv_box_details_check,false)
        }
    }

}