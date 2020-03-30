package com.ycwl.servebixin.cn.ui.order.adapter

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormListBean
import com.ycwl.servebixin.cn.ui.order.utils.TimeUtils

class OrderFormListAdapter(val type:String,orders:MutableList<OrderFormListBean.DataBean>):BaseQuickAdapter<OrderFormListBean.DataBean,BaseViewHolder>(R.layout.item_order_form_list,orders),TimeUtils.TimeUtilCallBack{

    private var callBack:CallBack?=null

    override fun finishTime() {
        callBack!!.finish()
    }

    override fun convert(helper: BaseViewHolder, item: OrderFormListBean.DataBean) {
        //服务状态：1 未接单，2 支付失败，3已接单/未付款，4 已付款，5 到场，6 开始服务，7 结束服务，8 超时，9 用户取消，10 服务人员拒绝，12 邀约已满
        //待确认对应 1；进行中对应 2,3,4,5,6；已完成对应 7


        when(item.serviceState){
            1 ->{
                var timeView = helper.getView<TextView>(R.id.tv_order_form_list_state_tips)
                if (item.acceptTime!=null&&item.acceptTime>0) {
                    var timeutils = TimeUtils()
                    timeutils.setCallBack(this)
                    timeutils.setEndTimer(item.acceptTime.toLong())
                    timeutils.codeCountTimerOrder("（", "后失效）", timeView)
                }else{
                    timeView.visibility=View.GONE
                }
                   helper.setVisible(R.id.btn_order_form_list_4,true)
                           .setVisible(R.id.btn_order_form_list_1,true)
                           .setVisible(R.id.btn_order_form_list_2,true)
                           .setVisible(R.id.btn_order_form_list_3,true)
                           .setVisible(R.id.btn_order_form_list_5,false)

                   helper.setText(R.id.tv_order_form_list_type,item.skillName)
                           .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                           .setText(R.id.tv_order_form_list_state,"未确认")
                           .setText(R.id.btn_order_form_list_4,"接受订单")
                           .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                           .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                           .setText(R.id.tv_order_form_list_content2_title,"到场时间：")
                           .setText(R.id.tv_order_form_list_content2_tips,item.estimateArriveTime)
                           .setText(R.id.tv_order_form_list_content3_title,"距离：")
                           .setVisible(R.id.layout_order_form_list_content3,true)
                           .setVisible(R.id.tv_order_form_list_type_tips,false)
                   var station=""
                   if (item.isStationing==0){
                       station="（足迹外订单）"
                   }else{
                       station="（足迹内订单）"
                   }
//                   var distance=""
//                   if (item.distance.toDouble()<1000){
//                       distance="距约玩场地约${item.distance}m"
//                   }else{
//                       var s=(item.distance.toDouble()/1000).toString()
//                       distance="距约玩场地约${s}m"
//                   }
                    helper.setText(R.id.tv_order_form_list_content3_tips,"距约玩场地约${item.distance}"+station)

            }
            2->{
                var timeView = helper.getView<TextView>(R.id.tv_order_form_list_state_tips)
                if (item.waitPayServiceTime!=null&&item.waitPayServiceTime>0) {
                    var timeutils = TimeUtils()
                    timeutils.setCallBack(this)
                    timeutils.setEndTimer(item.waitPayServiceTime.toLong())
                    timeutils.codeCountTimerOrder("（", "后失效）", timeView)
                }else{
                    timeView.visibility=View.GONE
                }

                   helper.setVisible(R.id.btn_order_form_list_1,false)
                           .setVisible(R.id.btn_order_form_list_4,false)
                           .setVisible(R.id.btn_order_form_list_2,true)
                           .setVisible(R.id.btn_order_form_list_3,true)
                           .setVisible(R.id.btn_order_form_list_5,true)


                   helper.setText(R.id.tv_order_form_list_type,item.skillName)
                           .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                           .setText(R.id.tv_order_form_list_state,"等待用户付款")
                           .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                           .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                           .setText(R.id.tv_order_form_list_content2_title,"到场时间：")
                           .setText(R.id.tv_order_form_list_content2_tips,item.estimateArriveTime)
                           .setText(R.id.tv_order_form_list_content3_title,"距离：")
                           .setVisible(R.id.layout_order_form_list_content3,true)
                           .setVisible(R.id.tv_order_form_list_type_tips,false)
                   var station=""
                   if (item.isStationing==0){
                       station="（足迹外订单）"
                   }else{
                       station="（足迹内订单）"
                   }
//                   var distance=""
//                   if (item.distance.toDouble()<1000){
//                       distance="距约玩场地约${item.distance}m"
//                   }else{
//                       var s=(item.distance.toDouble()/1000).toString()
//                       distance="距约玩场地约${s}m"
//                   }
                   helper.setText(R.id.tv_order_form_list_content3_tips,"距约玩场地约${item.distance}"+station)
            }
            3->{
                var timeView = helper.getView<TextView>(R.id.tv_order_form_list_state_tips)
                if (item.waitPayServiceTime!=null&&item.waitPayServiceTime>0) {
                    var timeutils = TimeUtils()
                    timeutils.setCallBack(this)
                    timeutils.setEndTimer(item.waitPayServiceTime.toLong())
                    timeutils.codeCountTimerOrder("（", "后失效）", timeView)
                }else{
                    timeView.visibility=View.GONE
                }
                   helper.setVisible(R.id.btn_order_form_list_1,false)
                           .setVisible(R.id.btn_order_form_list_4,false)
                           .setVisible(R.id.btn_order_form_list_2,true)
                           .setVisible(R.id.btn_order_form_list_3,true)
                           .setVisible(R.id.btn_order_form_list_5,true)

                   helper.setText(R.id.tv_order_form_list_type,item.skillName)
                           .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                           .setText(R.id.tv_order_form_list_state,"等待用户付款")
                           .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                           .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                           .setText(R.id.tv_order_form_list_content2_title,"到场时间：")
                           .setText(R.id.tv_order_form_list_content2_tips,item.estimateArriveTime)
                           .setText(R.id.tv_order_form_list_content3_title,"距离：")
                           .setVisible(R.id.layout_order_form_list_content3,true)
                           .setVisible(R.id.tv_order_form_list_type_tips,false)
                   var station=""
                   if (item.isStationing==0){
                       station="（足迹外订单）"
                   }else{
                       station="（足迹内订单）"
                   }
//                   var distance=""
//                   if (item.distance.toDouble()<1000){
//                       distance="距约玩场地约${item.distance}m"
//                   }else{
//                       var s=(item.distance.toDouble()/1000).toString()
//                       distance="距约玩场地约${s}m"
//                   }
                helper.setText(R.id.tv_order_form_list_content3_tips,"距约玩场地约${item.distance}"+station)
            }
            4->{
                        helper.setVisible(R.id.tv_order_form_list_state_tips,false)
                                .setVisible(R.id.btn_order_form_list_4,true)
                                .setText(R.id.btn_order_form_list_4,"打卡")
                                .setVisible(R.id.btn_order_form_list_2,true)
                                .setVisible(R.id.btn_order_form_list_3,true)
                                .setVisible(R.id.btn_order_form_list_1,false)
                                .setVisible(R.id.btn_order_form_list_5,false)


                        helper.setText(R.id.tv_order_form_list_type,item.skillName)
                                .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                                .setText(R.id.tv_order_form_list_state,"未到场")
                                .setVisible(R.id.tv_order_form_list_state_tips,false)
                                .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                                .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                                .setText(R.id.tv_order_form_list_content2_title,"到场时间：")
                                .setText(R.id.tv_order_form_list_content2_tips,item.estimateArriveTime)
                                .setText(R.id.tv_order_form_list_content3_title,"距离：")
                                .setVisible(R.id.layout_order_form_list_content3,true)
                                .setVisible(R.id.tv_order_form_list_type_tips,false)
                        var station=""
                        if (item.isStationing==0){
                            station="（足迹外订单）"
                        }else{
                            station="（足迹内订单）"
                        }
//                        var distance=""
//                        if (item.distance.toDouble()<1000){
//                            distance="距约玩场地约${item.distance}m"
//                        }else{
//                            var s=(item.distance.toDouble()/1000).toString()
//                            distance="距约玩场地约${s}m"
//                        }
                helper.setText(R.id.tv_order_form_list_content3_tips,"距约玩场地约${item.distance}"+station)
            }
            5->{


                helper.setVisible(R.id.tv_order_form_list_state_tips,false)
                        .setVisible(R.id.btn_order_form_list_1,false)
                        .setVisible(R.id.btn_order_form_list_2,false)
                        .setVisible(R.id.btn_order_form_list_3,true)
                        .setVisible(R.id.btn_order_form_list_4,false)
                        .setVisible(R.id.btn_order_form_list_5,false)

                if (item.hasComplaints==0){
                    helper.setVisible(R.id.tv_order_form_list_type_tips,false)
                            .setVisible(R.id.tv_order_form_list_state_tips,false)
                }else{
                    helper.setVisible(R.id.tv_order_form_list_type_tips,false)
                            .setVisible(R.id.tv_order_form_list_state_tips,true)
                            .setText(R.id.tv_order_form_list_state_tips,"(用户投诉)")
                }

                helper.setText(R.id.tv_order_form_list_type,item.skillName)
                        .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                        .setText(R.id.tv_order_form_list_state,"约玩中")
//                        .setVisible(R.id.tv_order_form_list_state_tips,false)
                        .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                        .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                        .setText(R.id.tv_order_form_list_content2_title,"开始时间：")
                        .setText(R.id.tv_order_form_list_content2_tips,item.startTime)
                        .setText(R.id.tv_order_form_list_content3_title,"结束时间：")
                        .setText(R.id.tv_order_form_list_content3_tips,"- -")
                        .setVisible(R.id.layout_order_form_list_content3,true)

            }
            6->{
                if (item.isPoinListService==1){
                    helper.setVisible(R.id.tv_order_form_list_state_tips,false)
                            .setVisible(R.id.btn_order_form_list_4,true)
                            .setText(R.id.btn_order_form_list_4,"酒水点单")
                            .setVisible(R.id.btn_order_form_list_3,true)
                            .setVisible(R.id.btn_order_form_list_2,false)
                            .setVisible(R.id.btn_order_form_list_1,false)
                            .setVisible(R.id.btn_order_form_list_5,false)
                }else{
                    helper.setVisible(R.id.tv_order_form_list_state_tips,false)
                            .setVisible(R.id.btn_order_form_list_1,false)
                            .setVisible(R.id.btn_order_form_list_2,false)
                            .setVisible(R.id.btn_order_form_list_3,true)
                            .setVisible(R.id.btn_order_form_list_4,false)
                            .setVisible(R.id.btn_order_form_list_5,false)
                }
                if (item.hasComplaints==0){
                    helper.setVisible(R.id.tv_order_form_list_type_tips,false)
                            .setVisible(R.id.tv_order_form_list_state_tips,false)
                }else{
                    helper.setVisible(R.id.tv_order_form_list_type_tips,false)
                            .setVisible(R.id.tv_order_form_list_state_tips,true)
                            .setText(R.id.tv_order_form_list_state_tips,"(用户投诉)")
                }

                        helper.setText(R.id.tv_order_form_list_type,item.skillName)
                                .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                                .setText(R.id.tv_order_form_list_state,"约玩中")
//                                .setVisible(R.id.tv_order_form_list_state_tips,false)
                                .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                                .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                                .setText(R.id.tv_order_form_list_content2_title,"开始时间：")
                                .setText(R.id.tv_order_form_list_content2_tips,item.startTime)
                                .setText(R.id.tv_order_form_list_content3_title,"结束时间：")
                                .setText(R.id.tv_order_form_list_content3_tips,"- -")
                                .setVisible(R.id.layout_order_form_list_content3,true)

            }
            7 ->{
                        helper.setVisible(R.id.tv_order_form_list_state_tips,false)
                                .setVisible(R.id.btn_order_form_list_1,false)
                                .setVisible(R.id.btn_order_form_list_2,false)
                                .setVisible(R.id.btn_order_form_list_3,true)
                                .setVisible(R.id.btn_order_form_list_4,false)
                                .setVisible(R.id.btn_order_form_list_5,false)
                if (item.hasComplaints==0){
                    helper.setVisible(R.id.tv_order_form_list_type_tips,false)
                            .setVisible(R.id.tv_order_form_list_state_tips,false)
                }else{
                    helper.setVisible(R.id.tv_order_form_list_type_tips,false)
                            .setVisible(R.id.tv_order_form_list_state_tips,true)
                            .setText(R.id.tv_order_form_list_state_tips,"(用户投诉)")
                }


                        helper.setText(R.id.tv_order_form_list_type,item.skillName)
                                .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                                .setText(R.id.tv_order_form_list_state,"约玩结束")
                                .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                                .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                                .setText(R.id.tv_order_form_list_content2_title,"开始时间：")
                                .setText(R.id.tv_order_form_list_content2_tips,item.startTime)
                                .setText(R.id.tv_order_form_list_content3_title,"结束时间：")
                                .setText(R.id.tv_order_form_list_content3_tips,item.endTime)
                                .setVisible(R.id.layout_order_form_list_content3,true)
                    }
            8 ->{
                        helper.setVisible(R.id.btn_order_form_list_1,false)
                                .setVisible(R.id.btn_order_form_list_2,false)
                                .setVisible(R.id.btn_order_form_list_3,true)
                                .setVisible(R.id.btn_order_form_list_4,false)
                                .setVisible(R.id.btn_order_form_list_5,false)

                        helper.setText(R.id.tv_order_form_list_type,item.skillName)
                                .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                                .setText(R.id.tv_order_form_list_state,"订单取消")
                                .setVisible(R.id.tv_order_form_list_state_tips,true)
                                .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                                .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                                .setText(R.id.tv_order_form_list_content2_title,"超时时间：")
                                .setText(R.id.tv_order_form_list_content2_tips,item.cancelTime)
                                .setVisible(R.id.layout_order_form_list_content3,false)
                                .setVisible(R.id.tv_order_form_list_type_tips,false)
                if (item.description!=null&&item.description!=""){
                    helper.setText(R.id.tv_order_form_list_state_tips,"（${item.description}）")
                }
                    }
            9->{
                        if (item.walletRecodeId>0){
                            helper.setVisible(R.id.btn_order_form_list_4, true)
                                    .setText(R.id.btn_order_form_list_4, "查看补贴")
                                    .setVisible(R.id.btn_order_form_list_3, true)
                                    .setVisible(R.id.btn_order_form_list_2, false)
                                    .setVisible(R.id.btn_order_form_list_1, false)
                                    .setVisible(R.id.btn_order_form_list_5,false)

                            helper.setText(R.id.tv_order_form_list_type, item.skillName)
                                    .setText(R.id.tv_order_form_list_baofang, item.boxTypeName + "  ${item.boxName}")
                                    .setText(R.id.tv_order_form_list_state, "订单取消")
                                    .setVisible(R.id.tv_order_form_list_state_tips,true)
                                    .setText(R.id.tv_order_form_list_state_tips,"（用户取消）")
                                    .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                                    .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                                    .setText(R.id.tv_order_form_list_content2_title,"取消时间：")
                                    .setText(R.id.tv_order_form_list_content2_tips,item.cancelTime)
                                    .setVisible(R.id.layout_order_form_list_content3,false)
                                    .setVisible(R.id.tv_order_form_list_type_tips,false)

                        }else {
                            helper.setVisible(R.id.btn_order_form_list_1,false)
                                    .setVisible(R.id.btn_order_form_list_2,false)
                                    .setVisible(R.id.btn_order_form_list_3,true)
                                    .setVisible(R.id.btn_order_form_list_4,false)
                                    .setVisible(R.id.btn_order_form_list_5,false)


                            helper.setText(R.id.tv_order_form_list_type,item.skillName)
                                    .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                                    .setText(R.id.tv_order_form_list_state,"订单取消")
                                    .setVisible(R.id.tv_order_form_list_state_tips,true)
                                    .setText(R.id.tv_order_form_list_state_tips,"（用户取消）")
                                    .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                                    .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                                    .setText(R.id.tv_order_form_list_content2_title,"取消时间：")
                                    .setText(R.id.tv_order_form_list_content2_tips,item.cancelTime)
                                    .setVisible(R.id.layout_order_form_list_content3,false)
                                    .setVisible(R.id.tv_order_form_list_type_tips,false)

                        }
                    }
            10->{
                        helper.setVisible(R.id.btn_order_form_list_1,false)
                                .setVisible(R.id.btn_order_form_list_2,false)
                                .setVisible(R.id.btn_order_form_list_3,true)
                                .setVisible(R.id.btn_order_form_list_4,false)
                                .setVisible(R.id.btn_order_form_list_5,false)

                        helper.setText(R.id.tv_order_form_list_type,item.skillName)
                                .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                                .setText(R.id.tv_order_form_list_state,"订单取消")
                                .setVisible(R.id.tv_order_form_list_state_tips,true)
                                .setText(R.id.tv_order_form_list_state_tips,"（主动拒绝）")
                                .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                                .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                                .setText(R.id.tv_order_form_list_content2_title,"取消时间：")
                                .setText(R.id.tv_order_form_list_content2_tips,item.cancelTime)
                                .setText(R.id.tv_order_form_list_content3_title,"拒绝备注：")
                                .setText(R.id.tv_order_form_list_content3_tips,item.description)
                                .setVisible(R.id.layout_order_form_list_content3,true)
                                .setVisible(R.id.tv_order_form_list_type_tips,false)
                    }
            12->{
                        helper.setVisible(R.id.btn_order_form_list_1,false)
                                .setVisible(R.id.btn_order_form_list_2,false)
                                .setVisible(R.id.btn_order_form_list_3,true)
                                .setVisible(R.id.btn_order_form_list_4,false)
                                .setVisible(R.id.btn_order_form_list_5,false)


                        helper.setText(R.id.tv_order_form_list_type,item.skillName)
                                .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                                .setText(R.id.tv_order_form_list_state,"订单取消")
                                .setVisible(R.id.tv_order_form_list_state_tips,true)
                                .setText(R.id.tv_order_form_list_state_tips,"（预约已满）")
                                .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                                .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                                .setText(R.id.tv_order_form_list_content2_title,"取消时间：")
                                .setText(R.id.tv_order_form_list_content2_tips,item.updateTime)
                                .setVisible(R.id.layout_order_form_list_content3,false)
                                .setVisible(R.id.tv_order_form_list_type_tips,false)
            }
            20->{


                helper.setVisible(R.id.tv_order_form_list_state_tips,false)
                        .setVisible(R.id.btn_order_form_list_1,false)
                        .setVisible(R.id.btn_order_form_list_2,false)
                        .setVisible(R.id.btn_order_form_list_3,true)
                        .setVisible(R.id.btn_order_form_list_4,false)
                        .setVisible(R.id.btn_order_form_list_5,false)

                if (item.hasComplaints==0){
                    helper.setVisible(R.id.tv_order_form_list_type_tips,false)
                            .setVisible(R.id.tv_order_form_list_state_tips,false)
                }else{
                    helper.setVisible(R.id.tv_order_form_list_type_tips,false)
                            .setVisible(R.id.tv_order_form_list_state_tips,true)
                            .setText(R.id.tv_order_form_list_state_tips,"(用户投诉)")
                }

                helper.setText(R.id.tv_order_form_list_type,item.skillName)
                        .setText(R.id.tv_order_form_list_baofang,item.boxTypeName+"  ${item.boxName}")
                        .setText(R.id.tv_order_form_list_state,"约玩中")
//                        .setVisible(R.id.tv_order_form_list_state_tips,false)
                        .setText(R.id.tv_order_form_list_content1_title,"约玩场地：")
                        .setText(R.id.tv_order_form_list_content1_tips,item.businessName)
                        .setText(R.id.tv_order_form_list_content2_title,"开始时间：")
                        .setText(R.id.tv_order_form_list_content2_tips,item.startTime)
                        .setText(R.id.tv_order_form_list_content3_title,"结束时间：")
                        .setText(R.id.tv_order_form_list_content3_tips,"- -")
                        .setVisible(R.id.layout_order_form_list_content3,true)

            }

       }
        helper.addOnClickListener(R.id.btn_order_form_list_1)
                .addOnClickListener(R.id.btn_order_form_list_2)
                .addOnClickListener(R.id.btn_order_form_list_3)
                .addOnClickListener(R.id.btn_order_form_list_4)


    }


    public fun setCallBack(callBack: CallBack){
        this.callBack=callBack
    }

    interface CallBack{
        fun finish()
    }
}