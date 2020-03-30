package com.ycwl.servebixin.cn.ui.order.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.order.mvp.bean.ReasonsBean

class RefuseReasonsAdapter(reasons:MutableList<ReasonsBean>):BaseQuickAdapter<ReasonsBean,BaseViewHolder>(R.layout.item_refuse_pop,reasons){
    override fun convert(helper: BaseViewHolder, item: ReasonsBean) {
        helper.setText(R.id.item_pop_content,item.description)
                .setChecked(R.id.item_pop_check,item.isFlag)


    }
}