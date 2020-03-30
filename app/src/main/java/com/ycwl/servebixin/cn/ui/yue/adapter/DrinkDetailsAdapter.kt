package com.ycwl.servebixin.cn.ui.yue.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinkDetailsBean


class DrinkDetailsAdapter(data: MutableList<DrinkDetailsBean.DataBean.WineDetailsInfoBean>) : BaseQuickAdapter<DrinkDetailsBean.DataBean.WineDetailsInfoBean, BaseViewHolder>(R.layout.item_drink_details, data) {
    override fun convert(helper: BaseViewHolder, item: DrinkDetailsBean.DataBean.WineDetailsInfoBean) {
        var s=""
        if (item.businessWineSpecifications!=null)
            s=item.businessWineSpecifications
        helper.setText(R.id.drinkName, item.wineName)
                .setText(R.id.drinkMoney, "x${item.wineNum}")
                .setText(R.id.drinkContent,s+"/"+item.businessWineUnit)
    }
}