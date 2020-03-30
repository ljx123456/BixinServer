package com.ycwl.servebixin.cn.ui.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.main.mvp.bean.FansBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class FansAdapter(fans:MutableList<FansBean.DataBean>):BaseQuickAdapter<FansBean.DataBean,BaseViewHolder>(R.layout.item_fans,fans){
    override fun convert(helper: BaseViewHolder, item: FansBean.DataBean) {
        val image=helper.getView<RoundedImageView>(R.id.fansImg)
        ImageLoad.setUserHead(item.avatar,image)
        var sex=""
        if (item.sex==1){
            sex="男"
        }else{
            sex="女"
        }
        helper.setText(R.id.fansName,item.nickname)
                .setText(R.id.fansContent,"${sex} ${item.age}岁 ${item.constellation}")
    }
}