package com.ycwl.servebixin.cn.ui.order.activity

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.callback.GetUserInfoCallback
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.api.model.UserInfo
import cn.jpush.im.android.eventbus.EventBus
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.AppProject
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.DrinkUtils
import com.ycwl.servebixin.cn.db.ServeUtils
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.location.LocationUtils
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.ui.order.adapter.RefuseReasonsAdapter
import com.ycwl.servebixin.cn.ui.order.dialog.SelectMapDialog
import com.ycwl.servebixin.cn.ui.order.mvp.bean.*
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormAcceptBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormClockBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormDetailsBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormRefuseBody
import com.ycwl.servebixin.cn.ui.order.mvp.presenter.OrderFormDetailsPresenter
import com.ycwl.servebixin.cn.ui.order.mvp.presenter.OrderFormMakePresenter
import com.ycwl.servebixin.cn.ui.order.mvp.view.OrderFormDetailsView
import com.ycwl.servebixin.cn.ui.order.mvp.view.OrderFormMakeView
import com.ycwl.servebixin.cn.ui.order.utils.TimeUtils
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.permissions.UserPermissions
import com.ycwl.servebixin.cn.utils.utils.Toast
import com.ycwl.servebixin.cn.view.MyRecyclerView
import jiguang.chat.activity.ChatActivity
import jiguang.chat.application.JGApplication
import jiguang.chat.entity.Event
import jiguang.chat.entity.EventType
import kotlinx.android.synthetic.main.activity_order_form_details.*
import java.io.File
import java.net.URISyntaxException

class OrderFormDetailsActivity :BaseActivity(),OrderFormDetailsView, OrderFormMakeView,LocationUtils.Location ,TimeUtils.TimeUtilCallBack,SelectMapDialog.SelectMapDialogFace{


    override fun gaodeBtn() {
        if (isPackageInstalled("com.autonavi.minimap")){
            var intent= Intent()
            intent.action= Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            var uri=Uri.parse("amapuri://route/plan/?did=BGVIS2&dlat=" + lat
                    + "&dlon=" + lng + "&dname=${name}&dev=0&t=0")
            intent.data=uri
            startActivity(intent)
        }else{
            Toast.Tips("请安装高德地图")
        }
    }

    override fun baiduBtn() {
        if (isPackageInstalled("com.baidu.BaiduMap")){
            try {
            var pareUrl = "baidumap://map/direction?region=" +
                    "&destination=" + lat + "," + lng + "&coord_type=bd09ll&src=andr.bixinyule.ServeBixin"
              var intent = Intent.getIntent(pareUrl)
               startActivity(intent)
              } catch (e: URISyntaxException) {
                  e.printStackTrace()
              }
        }else{
            Toast.Tips("请安装百度地图")
        }
    }

    override fun getDetailsError(code: Int) {
        if (code==-6){
            presenter.getOrderFormDetails(OrderFormDetailsBody(orderNum))
        }
    }

    override fun finishTime() {
        presenter.getOrderFormDetails(OrderFormDetailsBody(orderNum))
    }


    override fun getLocationSuccess() {
        location.stopLocation()
        orderMakePresenter.getClock(OrderFormClockBody(serviceNo,user.getlng(),user.getlat()))

    }

    override fun getAcceptRequest(data: OrderFormAcceptBean) {
        Toast.Tips("接单成功")
        presenter.getOrderFormDetails(OrderFormDetailsBody(orderNum))
    }

    override fun getAcceptError(code: Int) {
        if (code==-6){
            presenter.getOrderFormDetails(OrderFormDetailsBody(orderNum))
        }
    }

    override fun getClockRequest(data: OrderFormClockBean) {
        Toast.Tips("打卡成功")
        presenter.getOrderFormDetails(OrderFormDetailsBody(orderNum))
    }

    override fun getClockError(code: Int) {
        if (code==-5011){
            ShowDialog.showCustomDialog(this,"此时间段不支持打卡\n请在约玩开始前10分钟内内打卡","确认",object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                    presenter.getOrderFormDetails(OrderFormDetailsBody(orderNum))
                }
            })
        }else if (code==-5012){
            ShowDialog.showCustomDialog(this,"不在打卡范围内\n请在目的地范围100米内打卡","确认",object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                    presenter.getOrderFormDetails(OrderFormDetailsBody(orderNum))
                }
            })
        }

    }

    override fun getRefuseReasonsRequest(data: OrderFormRefuseReasonsBean) {
        if (data!=null&&data.data.size>0){
            var list=ArrayList<ReasonsBean>()
            data.data.forEach {
                list.add(ReasonsBean(it.code,it.description,false))
            }
            var adapter= RefuseReasonsAdapter(list)
            var manager= LinearLayoutManager(this)
            manager.orientation= LinearLayout.VERTICAL
            popView.findViewById<MyRecyclerView>(R.id.recy_pop).layoutManager=manager
            popView.findViewById<MyRecyclerView>(R.id.recy_pop).adapter=adapter
            adapter.setOnItemClickListener { mAdapter, view, position ->
                adapter.data[position].isFlag=true
                reasonCode=adapter.data[position].code
                for (i in  adapter.data.indices){
                    if (i!=position){
                        adapter.data[i].isFlag=false
                    }
                }
                adapter.notifyDataSetChanged()
            }
            pop.showAtLocation(window.decorView, Gravity.FILL,0,0)
        }

    }

    override fun getRefuseRequest(data: OrderFormRefuseBean) {
        pop.dismiss()
        presenter.getOrderFormDetails(OrderFormDetailsBody(orderNum))
    }

    private val presenter by lazy { OrderFormDetailsPresenter(this,this,this) }
    private val orderMakePresenter by lazy { OrderFormMakePresenter(this,this,this) }
    private var orderNum=""
    private var reasonCode=0
    private var serviceNo=""
    private var lat=""
    private var lng=""
    private var name=""
    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View
    private lateinit var dialog: SelectMapDialog
    private lateinit var location:LocationUtils
    private var timeutils = TimeUtils()
    private var flag=true

    override fun onSavedInstanceState(bundle: Bundle?) {
        super.onSavedInstanceState(bundle)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = window.decorView
            val option = ( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            decorView.systemUiVisibility = option
            window.navigationBarColor = Color.TRANSPARENT
            window.statusBarColor = Color.TRANSPARENT
        }
    }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_order_form_details

    override fun setActivityTitle() {

    }

    override fun initActivityData() {
        orderNum=intent.getStringExtra("orderNum")

        timeutils.setCallBack(this)
        presenter.getOrderFormDetails(OrderFormDetailsBody(orderNum))
        popView = LayoutInflater.from(mContext).inflate(R.layout.pop_refuse_reason, null)
        pop = PopupWindowHelper(popView)
        dialog= SelectMapDialog(this)
        dialog.setDialogFace(this)
    }

    override fun clickListener() {
        Click.viewClick(orderFormDetailsBack).subscribe {
            finish()
        }
        Click.viewClick(popView.findViewById(R.id.layout_bg)).subscribe { pop.dismiss() }
        Click.viewClick(popView.findViewById(R.id.pop_close)).subscribe { pop.dismiss() }
        Click.viewClick(popView.findViewById(R.id.pop_sure)).subscribe {
            orderMakePresenter.getRefuse(OrderFormRefuseBody(orderNum,reasonCode))
        }
    }

    override fun getDetailsRequest(data: OrderFormDetailsBean) {
        orderFormDetailsBtn1.visibility=View.GONE
        orderFormDetailsBtn2.visibility=View.GONE
        orderFormDetailsBtn3.visibility=View.GONE
        orderFormDetailsBtn4.visibility=View.GONE
        if (data!=null&&data.data!=null){

            //服务状态：1 未接单，2 支付失败，3已接单/未付款，4 已付款，5 到场，6 开始服务，7 结束服务，8 超时，9 用户取消，10 服务人员拒绝，11服务繁忙，12 邀约已满
            when(data.data.serviceState){
                1->{
//                    timeutils.onDestroy()
                    orderFormDetailsStateTips.visibility=View.VISIBLE
                    timeutils.setEndTimer(data.data.acceptTime.toLong())
                    timeutils.codeCountTimerOrder("(","后失效)",orderFormDetailsStateTips)
                    orderFormDetailsStartTimeTitle.text="到场时间："
                    orderFormDetailsStartTime.text=data.data.estimateArriveTime
                    orderFormDetailsEndTime.text="距约玩场地约${data.data.distance}"
                    orderFormDetailsBtn1.visibility=View.VISIBLE
                    orderFormDetailsBtn2.visibility=View.VISIBLE
                    orderFormDetailsBtn3.visibility=View.VISIBLE
                    orderFormDetailsBtn4.visibility=View.VISIBLE

                }
                2->{
//                    timeutils.onDestroy()
                    orderFormDetailsRate1.setTextColor(Color.parseColor("#FFFFFF"))
                    orderFormDetailsRate1.setBackgroundResource(R.drawable.tv_order_form_details_default_bg)
                    orderFormDetailsRateText1.setTextColor(Color.parseColor("#80ffffff"))
                    orderFormDetailsRateLine2.setBackgroundResource(R.drawable.tv_order_form_line)
                    orderFormDetailsRate2.setTextColor(Color.parseColor("#FEA984"))
                    orderFormDetailsRate2.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                    orderFormDetailsRateText2.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsState.text="等待用户付款"
                    orderFormDetailsStateTips.visibility=View.VISIBLE
                    timeutils.setEndTimer(data.data.waitPayServiceTime.toLong())
                    timeutils.codeCountTimerOrder("(","后失效)",orderFormDetailsStateTips)
                    orderFormDetailsStartTimeTitle.text="到场时间："
                    orderFormDetailsStartTime.text=data.data.estimateArriveTime
                    orderFormDetailsEndTime.text="距约玩场地约${data.data.distance}"
                    orderFormDetailsBtn2.visibility=View.VISIBLE
                    orderFormDetailsBtn3.visibility=View.VISIBLE
                    orderFormDetailsBtn4.visibility=View.VISIBLE
                    orderFormDetailsBtn4.isEnabled=false
                    orderFormDetailsBtn4.text="已接单"

                }
                3->{
//                    timeutils.onDestroy()
                    orderFormDetailsRate1.setTextColor(Color.parseColor("#FFFFFF"))
                    orderFormDetailsRate1.setBackgroundResource(R.drawable.tv_order_form_details_default_bg)
                    orderFormDetailsRateText1.setTextColor(Color.parseColor("#80ffffff"))
                    orderFormDetailsRateLine2.setBackgroundResource(R.drawable.tv_order_form_line)
                    orderFormDetailsRate2.setTextColor(Color.parseColor("#FEA984"))
                    orderFormDetailsRate2.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                    orderFormDetailsRateText2.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsState.text="等待用户付款"
                    orderFormDetailsStateTips.visibility=View.VISIBLE
                    timeutils.setEndTimer(data.data.waitPayServiceTime.toLong())
                    timeutils.codeCountTimerOrder("(","后失效)",orderFormDetailsStateTips)
                    orderFormDetailsStartTimeTitle.text="到场时间："
                    orderFormDetailsStartTime.text=data.data.estimateArriveTime
                    orderFormDetailsEndTime.text="距约玩场地约${data.data.distance}"
                    orderFormDetailsBtn2.visibility=View.VISIBLE
                    orderFormDetailsBtn3.visibility=View.VISIBLE
                    orderFormDetailsBtn4.visibility=View.VISIBLE
                    orderFormDetailsBtn4.isEnabled=false
                    orderFormDetailsBtn4.text="已接单"

                }
                4->{
                    orderFormDetailsRate1.setTextColor(Color.parseColor("#FFFFFF"))
                    orderFormDetailsRate1.setBackgroundResource(R.drawable.tv_order_form_details_default_bg)
                    orderFormDetailsRateText1.setTextColor(Color.parseColor("#80ffffff"))
                    orderFormDetailsRateLine2.setBackgroundResource(R.drawable.tv_order_form_line)
                    orderFormDetailsRate2.setTextColor(Color.parseColor("#FEA984"))
                    orderFormDetailsRate2.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                    orderFormDetailsRateText2.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsState.text="未到场"
                    orderFormDetailsStateTips.visibility=View.GONE
                    orderFormDetailsStartTimeTitle.text="到场时间："
                    orderFormDetailsStartTime.text=data.data.estimateArriveTime
                    orderFormDetailsEndTime.text="距约玩场地约${data.data.distance}"
                    orderFormDetailsBtn2.visibility=View.VISIBLE
                    orderFormDetailsBtn3.visibility=View.VISIBLE
                    orderFormDetailsBtn4.visibility=View.VISIBLE
                    orderFormDetailsBtn4.text="打卡"

                }

                5 ->{
                    orderFormDetailsRate1.setTextColor(Color.parseColor("#FFFFFF"))
                    orderFormDetailsRate1.setBackgroundResource(R.drawable.tv_order_form_details_default_bg)
                    orderFormDetailsRateText1.setTextColor(Color.parseColor("#80ffffff"))
                    orderFormDetailsRateLine2.setBackgroundResource(R.drawable.tv_order_form_line)
//                    orderFormDetailsRate2.setTextColor(Color.parseColor("#FEA984"))
//                    orderFormDetailsRate2.setBackgroundResource(R.drawable.tv_order_form_details_bg)
//                    orderFormDetailsRateText2.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsRateLine3.setBackgroundResource(R.drawable.tv_order_form_line)
                    orderFormDetailsRate3.setTextColor(Color.parseColor("#FEA984"))
                    orderFormDetailsRate3.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                    orderFormDetailsRateText3.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsState.text="进行中"
                    orderFormDetailsStateTips.visibility=View.GONE
                    orderFormDetailsStartTimeTitle.text="开始时间："
                    orderFormDetailsStartTime.text=data.data.startTime
                    orderFormDetailsEndTimeTitle.text="结束时间："
                    orderFormDetailsEndTime.text="正在约玩中"

                    orderFormDetailsBtn3.visibility=View.VISIBLE
                    orderFormDetailsBtn4.visibility=View.GONE

                    if (data.data.hasComplaints==0){
                        orderFormDetailsTipsLayout.visibility=View.GONE
                    }else{
                        orderFormDetailsTipsLayout.visibility=View.VISIBLE
                        orderFormDetailsTips.text="用户投诉"
                    }


                }
                6 ->{
                    orderFormDetailsRate1.setTextColor(Color.parseColor("#FFFFFF"))
                    orderFormDetailsRate1.setBackgroundResource(R.drawable.tv_order_form_details_default_bg)
                    orderFormDetailsRateText1.setTextColor(Color.parseColor("#80ffffff"))
                    orderFormDetailsRateLine2.setBackgroundResource(R.drawable.tv_order_form_line)
//                    orderFormDetailsRate2.setTextColor(Color.parseColor("#FEA984"))
//                    orderFormDetailsRate2.setBackgroundResource(R.drawable.tv_order_form_details_bg)
//                    orderFormDetailsRateText2.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsRateLine3.setBackgroundResource(R.drawable.tv_order_form_line)
                    orderFormDetailsRate3.setTextColor(Color.parseColor("#FEA984"))
                    orderFormDetailsRate3.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                    orderFormDetailsRateText3.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsState.text="进行中"
                    orderFormDetailsStateTips.visibility=View.GONE
                    orderFormDetailsStartTimeTitle.text="开始时间："
                    orderFormDetailsStartTime.text=data.data.startTime
                    orderFormDetailsEndTimeTitle.text="结束时间："
                    orderFormDetailsEndTime.text="正在约玩中"

                    orderFormDetailsBtn3.visibility=View.VISIBLE
                    if (data.data.isPoinListService==0){
                        orderFormDetailsBtn4.visibility=View.GONE
                    }else {
                        orderFormDetailsBtn4.visibility = View.VISIBLE
                        orderFormDetailsBtn4.text = "酒水点单"
                    }
                    if (data.data.hasComplaints==0){
                        orderFormDetailsTipsLayout.visibility=View.GONE
                    }else{
                        orderFormDetailsTipsLayout.visibility=View.VISIBLE
                        orderFormDetailsTips.text="用户投诉"
                    }


                }
                7->{
                    orderFormDetailsRate1.setTextColor(Color.parseColor("#FFFFFF"))
                    orderFormDetailsRate1.setBackgroundResource(R.drawable.tv_order_form_details_default_bg)
                    orderFormDetailsRateText1.setTextColor(Color.parseColor("#80ffffff"))
                    orderFormDetailsRateLine2.setBackgroundResource(R.drawable.tv_order_form_line)
//                    orderFormDetailsRate2.setTextColor(Color.parseColor("#FEA984"))
//                    orderFormDetailsRate2.setBackgroundResource(R.drawable.tv_order_form_details_bg)
//                    orderFormDetailsRateText2.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsRateLine3.setBackgroundResource(R.drawable.tv_order_form_line)
//                    orderFormDetailsRate3.setTextColor(Color.parseColor("#FEA984"))
//                    orderFormDetailsRate3.setBackgroundResource(R.drawable.tv_order_form_details_bg)
//                    orderFormDetailsRateText3.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsRateLine4.setBackgroundResource(R.drawable.tv_order_form_line)
                    orderFormDetailsRate4.setTextColor(Color.parseColor("#FEA984"))
                    orderFormDetailsRate4.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                    orderFormDetailsRateText4.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsState.text="已完成"
                    orderFormDetailsStateTips.visibility=View.GONE
                    if (data.data.hasComplaints==0){
                        orderFormDetailsTipsLayout.visibility=View.GONE
                    }else{
                        orderFormDetailsTipsLayout.visibility=View.VISIBLE
                        orderFormDetailsTips.text="用户投诉"
                    }

                    orderFormDetailsStartTimeTitle.text="开始时间："
                    orderFormDetailsStartTime.text=data.data.startTime
                    orderFormDetailsEndTimeTitle.text="结束时间："
                    orderFormDetailsEndTime.text=data.data.endTime

                    orderFormDetailsBtn3.visibility=View.VISIBLE


                }
                //服务状态：1 未接单，2 支付失败，3已接单/未付款，4 已付款，5 到场，6 开始服务，7 结束服务，8 超时，9 用户取消，10 服务人员拒绝，12 邀约已满
                8->{
                    orderFormDetailsStateTips.visibility=View.GONE
                    orderFormDetailsRateLayout1.visibility=View.GONE
                    orderFormDetailsRateLayout2.visibility=View.VISIBLE
                    if (data.data.description!=null&&data.data.description!=""){
                        orderFormDetailsState.text = data.data.description
                    }else {
                        orderFormDetailsState.text = "确认超时"
                    }
                    orderFormDetailsEndTimeLayout.visibility=View.GONE
                    orderFormDetailsStartTimeTitle.text="取消时间："
                    orderFormDetailsStartTime.text=data.data.updateTime

                    orderFormDetailsBtn3.visibility=View.VISIBLE


                }

                9->{
                    if (data.data.startTime!=null){
                        orderFormDetailsRateLayout1.visibility=View.VISIBLE
                        orderFormDetailsRateLayout2.visibility=View.GONE
                        orderFormDetailsRate1.setTextColor(Color.parseColor("#FFFFFF"))
                        orderFormDetailsRate1.setBackgroundResource(R.drawable.tv_order_form_details_default_bg)
                        orderFormDetailsRateText1.setTextColor(Color.parseColor("#80ffffff"))
                        orderFormDetailsRateLine2.setBackgroundResource(R.drawable.tv_order_form_line)
//                    orderFormDetailsRate2.setTextColor(Color.parseColor("#FEA984"))
//                    orderFormDetailsRate2.setBackgroundResource(R.drawable.tv_order_form_details_bg)
//                    orderFormDetailsRateText2.setTextColor(Color.parseColor("#ffffff"))
                        orderFormDetailsRateLine3.setBackgroundResource(R.drawable.tv_order_form_line)
//                    orderFormDetailsRate3.setTextColor(Color.parseColor("#FEA984"))
//                    orderFormDetailsRate3.setBackgroundResource(R.drawable.tv_order_form_details_bg)
//                    orderFormDetailsRateText3.setTextColor(Color.parseColor("#ffffff"))
                        orderFormDetailsRateLine4.setBackgroundResource(R.drawable.tv_order_form_line)
                        orderFormDetailsRate4.setTextColor(Color.parseColor("#FEA984"))
                        orderFormDetailsRate4.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                        orderFormDetailsRateText4.setTextColor(Color.parseColor("#ffffff"))

                        orderFormDetailsStartTimeLayout.visibility=View.VISIBLE
                        orderFormDetailsStartTimeTitle.text="开始时间："
                        orderFormDetailsStartTime.text=data.data.startTime
                        if (data.data.walletRecodeId>0){
                            orderFormDetailsBtn4.visibility=View.VISIBLE
                            orderFormDetailsBtn4.text="查看补贴"

                        }else{
                            orderFormDetailsBtn4.visibility=View.GONE
                        }

                    }else{
                        orderFormDetailsRateLayout1.visibility=View.GONE
                        orderFormDetailsRateLayout2.visibility=View.VISIBLE
                        orderFormDetailsStartTimeLayout.visibility=View.GONE
                    }

                    orderFormDetailsState.text="用户取消"
                    orderFormDetailsEndTimeTitle.text="取消时间："
                    orderFormDetailsEndTime.text=data.data.cancelTime
                    orderFormDetailsStateTips.visibility=View.GONE
                    orderFormDetailsBtn3.visibility=View.VISIBLE
                }

                10->{
                    orderFormDetailsStateTips.visibility=View.GONE
                    orderFormDetailsRateLayout1.visibility=View.GONE
                    orderFormDetailsRateLayout2.visibility=View.VISIBLE
                    orderFormDetailsStartTimeLayout.visibility=View.GONE
                    orderFormDetailsState.text="拒绝邀请"
                    orderFormDetailsEndTimeTitle.text="取消时间："
                    orderFormDetailsEndTime.text=data.data.cancelTime

                    orderFormDetailsTipsLayout.visibility=View.VISIBLE
                    orderFormDetailsTips.setTextColor(Color.parseColor("#ff535252"))
                    orderFormDetailsTips.text=data.data.description

                    orderFormDetailsBtn3.visibility=View.VISIBLE


                }
                12->{
                    orderFormDetailsStateTips.visibility=View.GONE
                    orderFormDetailsRateLayout1.visibility=View.GONE
                    orderFormDetailsRateLayout2.visibility=View.VISIBLE
                    orderFormDetailsStartTimeLayout.visibility=View.GONE
                    orderFormDetailsState.text="邀请已满"
                    orderFormDetailsEndTimeTitle.text="取消时间："
                    orderFormDetailsEndTime.text=data.data.updateTime


                    orderFormDetailsBtn3.visibility=View.VISIBLE

                }
                20->{
                    orderFormDetailsRate1.setTextColor(Color.parseColor("#FFFFFF"))
                    orderFormDetailsRate1.setBackgroundResource(R.drawable.tv_order_form_details_default_bg)
                    orderFormDetailsRateText1.setTextColor(Color.parseColor("#80ffffff"))
                    orderFormDetailsRateLine2.setBackgroundResource(R.drawable.tv_order_form_line)
//                    orderFormDetailsRate2.setTextColor(Color.parseColor("#FEA984"))
//                    orderFormDetailsRate2.setBackgroundResource(R.drawable.tv_order_form_details_bg)
//                    orderFormDetailsRateText2.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsRateLine3.setBackgroundResource(R.drawable.tv_order_form_line)
                    orderFormDetailsRate3.setTextColor(Color.parseColor("#FEA984"))
                    orderFormDetailsRate3.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                    orderFormDetailsRateText3.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsState.text="进行中"
                    orderFormDetailsStateTips.visibility=View.GONE
                    orderFormDetailsStartTimeTitle.text="开始时间："
                    orderFormDetailsStartTime.text=data.data.startTime
                    orderFormDetailsEndTimeTitle.text="结束时间："
                    orderFormDetailsEndTime.text="正在约玩中"

                    orderFormDetailsBtn3.visibility=View.VISIBLE
                    orderFormDetailsBtn4.visibility=View.GONE

                    if (data.data.hasComplaints==0){
                        orderFormDetailsTipsLayout.visibility=View.GONE
                    }else{
                        orderFormDetailsTipsLayout.visibility=View.VISIBLE
                        orderFormDetailsTips.text="用户投诉"
                    }


                }
            }

            orderFormDetailsName.text=data.data.skillName
            orderFormDetailsMoney.text="${data.data.orderTotalPrice}元"
            orderFormDetailsOrderTime.text=data.data.createTime
            orderFormDetailsKTV.text=data.data.businessName
            orderFormDetailsAddress.text=data.data.address
            orderFormDetailsBoxType.text=data.data.boxTypeName
            orderFormDetailsBoxNum.text=data.data.boxName
            orderFormDetailsOrderType.text= if(data.data.isStationing==0)  "足迹外订单" else "足迹内订单"
            orderFormDetailsOrderNum.text=data.data.orderServiceNo

            Click.viewClick(orderFormDetailsBtn1).subscribe {
                // 拒绝
                ShowDialog.showCustomDialogNoTitle(this,"是否确认拒绝本次约玩？","确认","取消",object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                dialog.dismiss()
                                orderMakePresenter.getRefuseReason()
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog.dismiss()
                            }
                        }
                    }
                })
            }
            Click.viewClick(orderFormDetailsBtn2).subscribe {
                // 导航
                name=data.data.businessName
                lat=data.data.latitude.toString()
                lng=data.data.longitude.toString()
                dialog.showDialog()
            }
            Click.viewClick(orderFormDetailsBtn3).subscribe {
                LogUtils.a("测试",data.data.userId)
                if (flag) {
                    flag=false
                    JMessageClient.getUserInfo(data.data.userId, "4b24613280bd2c477bab4989", object : GetUserInfoCallback() {
                        override fun gotResult(p0: Int, p1: String, p2: UserInfo) {
                            LogUtils.a("测试", "进来了${p1}")
                            if (p0 == 0) {
                                LogUtils.a("测试", "进来了")
                                flag=true
                                var mUserInfo = p2
                                var intent = Intent(this@OrderFormDetailsActivity, ChatActivity::class.java)
                                //创建会话
                                intent.putExtra("targetId", mUserInfo.getUserName())
                                intent.putExtra("targetAppKey", mUserInfo.getAppKey())
                                var notename = mUserInfo.getNotename()
                                if (TextUtils.isEmpty(notename)) {
                                    notename = mUserInfo.getNickname()
                                    if (TextUtils.isEmpty(notename)) {
                                        notename = mUserInfo.getUserName()
                                    }
                                }
                                intent.putExtra("conv_title", notename)
                                var conv: Conversation? = JMessageClient.getSingleConversation(mUserInfo.getUserName(), mUserInfo.getAppKey())
                                //如果会话为空，使用EventBus通知会话列表添加新会话
                                if (conv == null) {
                                    conv = Conversation.createSingleConversation(mUserInfo.getUserName(), mUserInfo.getAppKey())
                                    EventBus.getDefault().post(Event.Builder()
                                            .setType(EventType.createConversation)
                                            .setConversation(conv)
                                            .build())
                                }
                                startActivity(intent)
                            }
                        }
                    })
                }else{
                    Toast.Tips("聊天正在创建中。。。")
                }
            }

            Click.viewClick(orderFormDetailsBtn4).subscribe {
                when(data.data.serviceState){
                    1->{
                        // 接单
                        orderMakePresenter.getAccept(OrderFormAcceptBody(data.data.orderServiceNo))
                    }
                    4->{
                        // 打卡
                        serviceNo=data.data.orderServiceNo

                        checkLocation()
                    }
                    6->{
                        //点单
                        DBUtils.delMerchat()//删除服务地址
                        DrinkUtils.deleteALLDrinks()//删除所有酒水
                        ServeUtils.deleteALLServe()//删除所有服务人员
                        intentUtils.intentOrderFormDrinks(data.data.orderServiceNo)
                    }
                    9->{
                        // 查看补贴
                        intentUtils.intentIncomeRecordDetails(data.data.walletRecodeId)
                    }
                }
            }

        }
    }

    //判断包名是否存在
    fun isPackageInstalled(packageName: String): Boolean {
        return File("/data/data/$packageName").exists()
    }

    override fun onDestroy() {
        super.onDestroy()
//        location.stopLocation()
    }

    fun checkLocation(){
        var rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).subscribe { aBoolean ->
            if (aBoolean) {
                location = LocationUtils(this)
                location.getLocation()
            }
            else{
                if(!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    ShowDialog.showCustomDialog(this, "打卡失败", "无法获取你位置信息，请立即前往设置", "去设置", "暂不", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
                                    UserPermissions.gotoSet(this@OrderFormDetailsActivity)
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog.dismiss()
                                }
                            }
                        }
                    })
                }else{
                    ShowDialog.showCustomDialog(this, "打卡失败", "无法获取你位置信息，请立即开启", "去开启", "暂不", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
                                    checkLocation()
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog.dismiss()
                                }
                            }
                        }
                    })
                }
            }
        }
    }
}