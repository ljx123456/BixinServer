package com.ycwl.servebixin.cn.ui.order.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.order.mvp.bean.GrabOrderListBean
import com.ycwl.servebixin.cn.ui.order.utils.TimeUtils

class GrabOrderListAdapter(list:MutableList<GrabOrderListBean.DataBean>):BaseQuickAdapter<GrabOrderListBean.DataBean,BaseViewHolder>(R.layout.item_order_form_list,list),TimeUtils.TimeUtilCallBack{
    override fun finishTime() {
        callBack!!.finishGrab()
    }

    private var callBack:CallBack?=null

    override fun convert(helper: BaseViewHolder, item: GrabOrderListBean.DataBean) {
        //抢单状态：0 等待抢单，1 等待开始，2 进行中，3 结束，4 订单取消，5 抢单超时，6 被抢单
        helper.setText(R.id.tv_order_form_list_type,item.skillTypeName)
                .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                .addOnClickListener(R.id.btn_order_form_list_2)
                .addOnClickListener(R.id.btn_order_form_list_3)
                .addOnClickListener(R.id.btn_order_form_list_4)


        when(item.inviteState){
            0->{
                helper.setVisible(R.id.layout_order_form_list_bottom,true)
                        .setVisible(R.id.btn_order_form_list_1,false)
                        .setVisible(R.id.btn_order_form_list_2,true)
                        .setVisible(R.id.btn_order_form_list_3,false)
                        .setVisible(R.id.btn_order_form_list_4,true)
                        .setVisible(R.id.btn_order_form_list_5,false)
                        .setText(R.id.btn_order_form_list_4,"抢单")
                        .setText(R.id.tv_order_form_list_state,"等待抢单")
                        .setVisible(R.id.layout_order_form_list_content3,false)
                var timeView = helper.getView<TextView>(R.id.tv_order_form_list_state_tips)
                if (item.waitGrabOrderTime!=null&&item.waitGrabOrderTime>0) {
                    var timeutils = TimeUtils()
                    timeutils.setCallBack(this)
                    timeutils.setEndTimer(item.waitGrabOrderTime.toLong())
                    timeutils.codeCountTimerOrder("（", "后失效）", timeView)
                }else{
                    timeView.text=" "
                }
                if (item.reserveStartTime!=null&&item.reserveStartTime!=""){
                    helper.setText(R.id.tv_order_form_list_content2_title,"预约时间")
                            .setText(R.id.tv_order_form_list_content2_tips,item.reserveStartTime)
                }else{
                    helper.setText(R.id.tv_order_form_list_content2_title,"下单时间")
                            .setText(R.id.tv_order_form_list_content2_tips,item.createTime)
                }
            }
            1->{
                helper.setVisible(R.id.layout_order_form_list_bottom,true)
                        .setVisible(R.id.btn_order_form_list_1,false)
                        .setVisible(R.id.btn_order_form_list_2,true)
                        .setVisible(R.id.btn_order_form_list_3,true)

                        .setVisible(R.id.btn_order_form_list_5,false)
                        .setText(R.id.tv_order_form_list_state,"等待开始")
                        .setVisible(R.id.layout_order_form_list_content3,false)
                if (item.reserveStartTime!=null&&item.reserveStartTime!=""){
                    helper.setText(R.id.tv_order_form_list_content2_title,"预约时间")
                            .setText(R.id.tv_order_form_list_content2_tips,item.reserveStartTime)
                }else{
                    helper.setText(R.id.tv_order_form_list_content2_title,"下单时间")
                            .setText(R.id.tv_order_form_list_content2_tips,item.createTime)
                }
                if (item.isPoinListService==0){
                    helper.setVisible(R.id.btn_order_form_list_4,false)
                }else{
                    helper.setVisible(R.id.btn_order_form_list_4,true)
                            .setText(R.id.btn_order_form_list_4,"酒水点单")
                }
                if (item.type==0){
                    helper.setText(R.id.tv_order_form_list_state_tips,item.acceptTime+" (抢单)")
                }else{
                    helper.setText(R.id.tv_order_form_list_state_tips,"用户指定派单")
                }

            }
            2->{
                helper.setVisible(R.id.layout_order_form_list_bottom,true)
                        .setVisible(R.id.btn_order_form_list_1,false)
                        .setVisible(R.id.btn_order_form_list_2,true)
                        .setVisible(R.id.btn_order_form_list_3,true)
                        .setVisible(R.id.btn_order_form_list_5,false)
                        .setText(R.id.tv_order_form_list_state,"进行中")
                        .setText(R.id.tv_order_form_list_content2_title,"开始时间")
                        .setText(R.id.tv_order_form_list_content2_tips,item.startTime)
                        .setVisible(R.id.layout_order_form_list_content3,true)
                        .setText(R.id.tv_order_form_list_content3_title,"结束时间")
                        .setText(R.id.tv_order_form_list_content3_tips,"正在约玩中")

                if (item.isPoinListService==0){
                    helper.setVisible(R.id.btn_order_form_list_4,false)
                }else{
                    helper.setVisible(R.id.btn_order_form_list_4,true)
                            .setText(R.id.btn_order_form_list_4,"酒水点单")
                }
                if (item.type==0){
                    helper.setText(R.id.tv_order_form_list_state_tips,item.acceptTime+" (抢单)")
                }else{
                    helper.setText(R.id.tv_order_form_list_state_tips,"用户指定派单")
                }
            }//抢单状态：0 等待抢单，1 等待开始，2 进行中，3 结束，4 订单取消，5 抢单超时，6 被抢单
            3->{
                helper.setVisible(R.id.layout_order_form_list_bottom,true)
                        .setVisible(R.id.btn_order_form_list_1,false)
                        .setVisible(R.id.btn_order_form_list_2,false)
                        .setVisible(R.id.btn_order_form_list_3,true)
                        .setVisible(R.id.btn_order_form_list_4,false)
                        .setVisible(R.id.btn_order_form_list_5,false)
                        .setText(R.id.tv_order_form_list_state,"约玩结束")
                        .setText(R.id.tv_order_form_list_content2_title,"开始时间")
                        .setText(R.id.tv_order_form_list_content2_tips,item.startTime)
                        .setVisible(R.id.layout_order_form_list_content3,true)
                        .setText(R.id.tv_order_form_list_content3_title,"结束时间")
                        .setText(R.id.tv_order_form_list_content3_tips,item.updateTime)

                if (item.type==0){
                    helper.setText(R.id.tv_order_form_list_state_tips,item.acceptTime+" (抢单)")
                }else{
                    helper.setText(R.id.tv_order_form_list_state_tips,"用户指定派单")
                }

            }
            4->{
                helper.setVisible(R.id.layout_order_form_list_bottom,true)
                        .setVisible(R.id.btn_order_form_list_1,false)
                        .setVisible(R.id.btn_order_form_list_2,false)
                        .setVisible(R.id.btn_order_form_list_3,true)
                        .setVisible(R.id.btn_order_form_list_4,false)
                        .setVisible(R.id.btn_order_form_list_5,false)
                        .setText(R.id.tv_order_form_list_state,"订单取消")
                        .setText(R.id.tv_order_form_list_state_tips,"用户主动取消")
                        .setVisible(R.id.layout_order_form_list_content3,true)
                        .setText(R.id.tv_order_form_list_content3_title,"取消时间")
                        .setText(R.id.tv_order_form_list_content3_tips,item.updateTime)
                if (item.reserveStartTime!=null&&item.reserveStartTime!=""){
                    helper.setText(R.id.tv_order_form_list_content2_title,"预约时间")
                            .setText(R.id.tv_order_form_list_content2_tips,item.reserveStartTime)
                }else{
                    helper.setText(R.id.tv_order_form_list_content2_title,"下单时间")
                            .setText(R.id.tv_order_form_list_content2_tips,item.createTime)
                }

            }
            5->{
                helper.setVisible(R.id.layout_order_form_list_bottom,false)
                        .setVisible(R.id.btn_order_form_list_1,false)
                        .setVisible(R.id.btn_order_form_list_2,false)
                        .setVisible(R.id.btn_order_form_list_3,false)
                        .setVisible(R.id.btn_order_form_list_4,false)
                        .setVisible(R.id.btn_order_form_list_5,false)
                        .setText(R.id.tv_order_form_list_state,"抢单超时")
                        .setText(R.id.tv_order_form_list_state_tips," ")
                        .setVisible(R.id.layout_order_form_list_content3,false)
                if (item.reserveStartTime!=null&&item.reserveStartTime!=""){
                    helper.setText(R.id.tv_order_form_list_content2_title,"预约时间")
                            .setText(R.id.tv_order_form_list_content2_tips,item.reserveStartTime)
                }else{
                    helper.setText(R.id.tv_order_form_list_content2_title,"下单时间")
                            .setText(R.id.tv_order_form_list_content2_tips,item.createTime)
                }

            }
            6->{
                helper.setVisible(R.id.layout_order_form_list_bottom,false)
                        .setVisible(R.id.btn_order_form_list_1,false)
                        .setVisible(R.id.btn_order_form_list_2,false)
                        .setVisible(R.id.btn_order_form_list_3,false)
                        .setVisible(R.id.btn_order_form_list_4,false)
                        .setVisible(R.id.btn_order_form_list_5,false)
                        .setText(R.id.tv_order_form_list_state,"被抢单")
                        .setText(R.id.tv_order_form_list_state_tips," ")
                        .setVisible(R.id.layout_order_form_list_content3,false)
                if (item.reserveStartTime!=null&&item.reserveStartTime!=""){
                    helper.setText(R.id.tv_order_form_list_content2_title,"预约时间")
                            .setText(R.id.tv_order_form_list_content2_tips,item.reserveStartTime)
                }else{
                    helper.setText(R.id.tv_order_form_list_content2_title,"下单时间")
                            .setText(R.id.tv_order_form_list_content2_tips,item.createTime)
                }
            }
        }

    }

    public fun setCallBack(callBack: CallBack){
        this.callBack=callBack
    }

    interface CallBack{
        fun finishGrab()
    }

}