package com.ycwl.servebixin.cn.ui.withdrawal.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeRecordDetailsBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class IncomeRecordDetailsListAdapter(datas:MutableList<IncomeRecordDetailsBean.DataBean.UsersBean>) :BaseQuickAdapter<IncomeRecordDetailsBean.DataBean.UsersBean,BaseViewHolder>(R.layout.item_income_record_list_details,datas){
    override fun convert(helper: BaseViewHolder, item: IncomeRecordDetailsBean.DataBean.UsersBean) {
        ImageLoad.setUserHead(item.avatar,helper.getView(R.id.incomeRecordListImg) as RoundedImageView)
        helper.setText(R.id.incomeRecordListName,item.nickname)
        var sex=""
        if (item.age==1){
            sex="男"
        }else{
            sex="女"
        }
        helper.setText(R.id.incomeRecordListContent,sex+" "+"${item.age}岁"+" "+item.constellation)
    }

}