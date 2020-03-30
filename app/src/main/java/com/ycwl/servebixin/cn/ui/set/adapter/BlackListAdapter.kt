package com.ycwl.servebixin.cn.ui.set.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.set.mvp.bean.BlackListBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class BlackListAdapter(list:MutableList<BlackListBean.DataBean>):BaseQuickAdapter<BlackListBean.DataBean,BaseViewHolder>(R.layout.item_black_list, list){
    override fun convert(helper: BaseViewHolder, item: BlackListBean.DataBean) {
        ImageLoad.setUserHead(item.avatar,helper.getView<RoundedImageView>(R.id.blackItemImg))
        var sex=""
        if (item.sex==1){
            sex="男"
        }else{
            sex="女"
        }
        helper.setText(R.id.blackItemName,item.nickname)
                .setText(R.id.blackItemContent,sex+" ${item.age}岁 "+item.constellation)
                .addOnClickListener(R.id.blackItemDel)

    }
}