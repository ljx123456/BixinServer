package com.ycwl.servebixin.cn.ui.message.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.message.mvp.bean.NotificationBean

class NotificationAdapter(list:MutableList<NotificationBean.DataBean>):BaseQuickAdapter<NotificationBean.DataBean,BaseViewHolder>(R.layout.item_notification,list){
    override fun convert(helper: BaseViewHolder, item: NotificationBean.DataBean) {
        helper.setText(R.id.tv_noti_title,item.title)
                .setText(R.id.tv_noti_time,item.createTime)
                .setText(R.id.tv_noti_content,item.content)
        if (item.isRead==0){
            helper.getView<TextView>(R.id.tv_noti_dot).visibility=View.VISIBLE
        }else{
            helper.getView<TextView>(R.id.tv_noti_dot).visibility=View.INVISIBLE
        }

        if (item.type==10||item.type==11||item.type==14||item.type==15||item.type==16||item.type==17||item.type==18||item.type==21){
            helper.setVisible(R.id.iv_noti_content,true)
        }else{
            helper.setVisible(R.id.iv_noti_content,false)
        }
//        helper.getView<TextView>(R.id.tv_noti_dot)
    }
}