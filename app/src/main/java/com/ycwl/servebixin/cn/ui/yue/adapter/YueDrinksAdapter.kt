package com.ycwl.servebixin.cn.ui.yue.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.ExpListBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class YueDrinksAdapter(list: MutableList<ExpListBean.DrinkBean>) : BaseQuickAdapter<ExpListBean.DrinkBean, BaseViewHolder>(R.layout.item_yue_drinks, list) {

    override fun convert(helper: BaseViewHolder, item: ExpListBean.DrinkBean) {
//        var sub = helper.getView<ImageView>(R.id.drinksSub)
//        var add = helper.getView<ImageView>(R.id.drinksAdd)
//        var num = helper.getView<TextView>(R.id.drinksNum)
        ImageLoad.setImage(item.drinkImage, helper.getView(R.id.drinksImage) as RoundedImageView)
        helper.setText(R.id.drinksName, item.drinkName)
                .setText(R.id.drinksContext, item.drinkText)
                .setText(R.id.drinksMoney, "¥ ${item.drinkMoney}")
                .addOnClickListener(R.id.drinksSub)
                .addOnClickListener(R.id.drinksAdd)
                .setText(R.id.drinksNum, "${item.drinkNum}")
    }
}