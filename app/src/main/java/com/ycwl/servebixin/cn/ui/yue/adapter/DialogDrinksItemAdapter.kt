package com.ycwl.servebixin.cn.ui.yue.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.ExpListBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class DialogDrinksItemAdapter(map:List<ExpListBean.DrinkBean>) : BaseQuickAdapter<ExpListBean.DrinkBean, BaseViewHolder>(R.layout.item_yue_drinks,map){
    override fun convert(helper: BaseViewHolder, item: ExpListBean.DrinkBean) {
        ImageLoad.setUserHead(item.drinkImage,helper.getView(R.id.drinksImage) as RoundedImageView)
        helper.setText(R.id.drinksMoney,"¥:"+item.drinkMoney)
                .setText(R.id.drinksName,item.drinkName)
                .setText(R.id.drinksNum,item.drinkNum)
                .addOnClickListener(R.id.drinksAdd)
                .addOnClickListener(R.id.drinksSub)


    }

}