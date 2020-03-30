package com.ycwl.servebixin.cn.ui.order.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.callback.GetUserInfoCallback
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.api.model.UserInfo
import cn.jpush.im.android.eventbus.EventBus
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.AppUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.DrinkUtils
import com.ycwl.servebixin.cn.db.ServeUtils
import com.ycwl.servebixin.cn.ui.order.dialog.SelectMapDialog
import com.ycwl.servebixin.cn.ui.order.mvp.bean.GrabOrderDetailsBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormAcceptBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderDetailsBody
import com.ycwl.servebixin.cn.ui.order.mvp.presenter.GrabOrderDetailsPresenter
import com.ycwl.servebixin.cn.ui.order.mvp.presenter.GrabOrderPresenter
import com.ycwl.servebixin.cn.ui.order.mvp.view.GrabOrderDetailsView
import com.ycwl.servebixin.cn.ui.order.mvp.view.GrabOrderView
import com.ycwl.servebixin.cn.ui.order.utils.TimeUtils
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import jiguang.chat.activity.ChatActivity
import jiguang.chat.entity.Event
import jiguang.chat.entity.EventType
import kotlinx.android.synthetic.main.activity_grab_order_details.*
import java.io.File
import java.net.URISyntaxException

class GrabOrderDetailsActivity:BaseActivity(),GrabOrderDetailsView, GrabOrderView ,TimeUtils.TimeUtilCallBack,SelectMapDialog.SelectMapDialogFace{


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
            Toast.Tips("请安装高德导航")
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

    override fun getGrabOrderDetailsError(code: Int) {
        if (code==-6) {
            presenter.getGrabOrderDetails(GrabOrderDetailsBody(inviteId))
        }
    }

    override fun finishTime() {
        presenter.getGrabOrderDetails(GrabOrderDetailsBody(inviteId))
    }

    override fun getGrabOrderRequest(data: OrderFormAcceptBean) {
        presenter.getGrabOrderDetails(GrabOrderDetailsBody(inviteId))
    }

    override fun getGrabOrderError(code: Int) {
        if (code==-6){
            presenter.getGrabOrderDetails(GrabOrderDetailsBody(inviteId))
        }
    }

    private val presenter by lazy { GrabOrderDetailsPresenter(this,this,this) }
    private val grabOrderPresenter by lazy { GrabOrderPresenter(this,this,this) }
    private var inviteId=""
    private lateinit var dialog: SelectMapDialog
    private var lat=""
    private var lng=""
    private var name=""
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
    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int =R.layout.activity_grab_order_details

    override fun setActivityTitle() {

    }

    override fun initActivityData() {
        inviteId=intent.getStringExtra("inviteId")
        presenter.getGrabOrderDetails(GrabOrderDetailsBody(inviteId))
        dialog= SelectMapDialog(this)
        dialog.setDialogFace(this)
    }

    override fun clickListener() {
         Click.viewClick(grabOrderDetailsBack).subscribe {
            finish()
        }
    }

    override fun getGrabOrderDetailsRequest(data: GrabOrderDetailsBean) {
        orderFormDetailsBtn1.visibility=View.GONE
        orderFormDetailsBtn2.visibility=View.GONE
        orderFormDetailsBtn3.visibility=View.GONE
        orderFormDetailsBtn4.visibility=View.GONE
        if (data!=null&&data.data!=null){

            orderFormDetailsName.text=data.data.skillTypeName
            orderFormDetailsMoney.text=data.data.boxTypeName+"  ${data.data.boxName}"
            orderFormDetailsOrderTime.text=data.data.createTime
            orderFormDetailsKTV.text=data.data.businessName
            orderFormDetailsAddress.text=data.data.address
            orderFormDetailsOrderNum.text=data.data.orderNo

            var timeutils = TimeUtils()
            timeutils.setCallBack(this)
            //抢单状态：0 等待抢单，1 等待开始，2 进行中，3 结束，4 订单取消，5 抢单超时，6 被抢单
            when(data.data.inviteState){
                0->{
//                    timeutils.onDestroy()
                    orderFormDetailsStateTips.visibility=View.VISIBLE
                    if (data.data.waitGrabOrderTime!=null&&data.data.waitGrabOrderTime>-2) {
                        timeutils.setEndTimer(data.data.waitGrabOrderTime.toLong())
                        timeutils.codeCountTimerOrder("(", "后失效)", orderFormDetailsStateTips)
                    }
                    grabOrderDetailsAcceptLayout.visibility=View.GONE
                    if (data.data.reserveStartTime!=null&&data.data.reserveStartTime!=""){
                        orderFormDetailsStartTimeLayout.visibility=View.VISIBLE
                        orderFormDetailsStartTime.text=data.data.reserveStartTime
                    }else{
                        orderFormDetailsStartTimeLayout.visibility=View.GONE
                    }
                    orderFormDetailsEndTime.text="距约玩场地约${data.data.distance}"
                    orderFormDetailsBtn1.visibility=View.GONE
                    orderFormDetailsBtn2.visibility=View.VISIBLE
                    orderFormDetailsBtn3.visibility=View.GONE
                    orderFormDetailsBtn4.visibility=View.VISIBLE
                    orderFormDetailsBtn4.text="抢单"

                }
                1->{

                    orderFormDetailsRate1.setTextColor(Color.parseColor("#FFFFFF"))
                    orderFormDetailsRate1.setBackgroundResource(R.drawable.tv_order_form_details_default_bg)
                    orderFormDetailsRateText1.setTextColor(Color.parseColor("#80ffffff"))
                    orderFormDetailsRateLine2.setBackgroundResource(R.drawable.tv_order_form_line)
                    orderFormDetailsRate2.setTextColor(Color.parseColor("#84CEFE"))
                    orderFormDetailsRate2.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                    orderFormDetailsRateText2.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsState.text="等待开始"
                    orderFormDetailsStateTips.visibility=View.GONE
                    if (data.data.type==0){
                        grabOrderDetailsAcceptLayout.visibility=View.VISIBLE
                        orderFormDetailsBoxType.text=data.data.acceptTime
                    }else{
                        grabOrderDetailsAcceptLayout.visibility=View.GONE
                    }
                    if (data.data.reserveStartTime!=null&&data.data.reserveStartTime!=""){
                        orderFormDetailsStartTimeLayout.visibility=View.VISIBLE
                        orderFormDetailsStartTime.text=data.data.reserveStartTime
                    }else{
                        orderFormDetailsStartTimeLayout.visibility=View.GONE
                    }


                    orderFormDetailsEndTime.text="距约玩场地约${data.data.distance}"
                    orderFormDetailsBtn2.visibility=View.VISIBLE
                    orderFormDetailsBtn3.visibility=View.VISIBLE
                    if (data.data.isPoinListService=="1"){
                        orderFormDetailsBtn4.visibility=View.VISIBLE
                        orderFormDetailsBtn4.text="酒水点单"
                    }else{
                        orderFormDetailsBtn4.visibility=View.GONE
                    }

                }
                2->{
                    orderFormDetailsRate1.setTextColor(Color.parseColor("#FFFFFF"))
                    orderFormDetailsRate1.setBackgroundResource(R.drawable.tv_order_form_details_default_bg)
                    orderFormDetailsRateText1.setTextColor(Color.parseColor("#80ffffff"))
                    orderFormDetailsRateLine2.setBackgroundResource(R.drawable.tv_order_form_line)
//                    orderFormDetailsRate2.setTextColor(Color.parseColor("#FEA984"))
//                    orderFormDetailsRate2.setBackgroundResource(R.drawable.tv_order_form_details_bg)
//                    orderFormDetailsRateText2.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsRateLine3.setBackgroundResource(R.drawable.tv_order_form_line)
                    orderFormDetailsRate3.setTextColor(Color.parseColor("#84CEFE"))
                    orderFormDetailsRate3.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                    orderFormDetailsRateText3.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsState.text="进行中"
                    orderFormDetailsStateTips.visibility=View.GONE
                    if (data.data.type==0){
                        grabOrderDetailsAcceptLayout.visibility=View.VISIBLE
                        orderFormDetailsBoxType.text=data.data.acceptTime
                    }else{
                        grabOrderDetailsAcceptLayout.visibility=View.GONE
                    }

                    orderFormDetailsStartTimeTitle.text="开始时间："
                    orderFormDetailsStartTime.text=data.data.startTime
                    orderFormDetailsEndTimeTitle.text="结束时间："
                    orderFormDetailsEndTime.text="正在约玩中"
                    orderFormDetailsBtn2.visibility=View.VISIBLE
                    orderFormDetailsBtn3.visibility=View.VISIBLE
                    if (data.data.isPoinListService=="1"){
                        orderFormDetailsBtn4.visibility=View.VISIBLE
                        orderFormDetailsBtn4.text="酒水点单"
                    }else{
                        orderFormDetailsBtn4.visibility=View.GONE
                    }

                }//抢单状态：0 等待抢单，1 等待开始，2 进行中，3 结束，4 订单取消，5 抢单超时，6 被抢单
                3->{
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
                    orderFormDetailsRate4.setTextColor(Color.parseColor("#84CEFE"))
                    orderFormDetailsRate4.setBackgroundResource(R.drawable.tv_order_form_details_bg)
                    orderFormDetailsRateText4.setTextColor(Color.parseColor("#ffffff"))
                    orderFormDetailsState.text="已完成"
                    orderFormDetailsStateTips.visibility=View.GONE
                    if (data.data.type==0){
                        grabOrderDetailsAcceptLayout.visibility=View.VISIBLE
                        orderFormDetailsBoxType.text=data.data.acceptTime
                    }else{
                        grabOrderDetailsAcceptLayout.visibility=View.GONE
                    }

                    orderFormDetailsStartTimeTitle.text="开始时间："
                    orderFormDetailsStartTime.text=data.data.startTime
                    orderFormDetailsEndTimeTitle.text="结束时间："
                    orderFormDetailsEndTime.text=data.data.updateTime
                    orderFormDetailsBtn3.visibility=View.VISIBLE

                }
                4->{
                    orderFormDetailsRateLayout1.visibility=View.GONE
//                    if (data.data.updateTime!=null&&data.data.updateTime!=""){
//
////                        tv_grab_order_state.text="订单取消"
//
//
//                    }else{
//
//
//                    }
                    orderFormDetailsStartTimeTitle.text="取消时间："
                    orderFormDetailsStartTime.text=data.data.updateTime
                    orderFormDetailsState.text="用户取消"
                    orderFormDetailsStateTips.visibility=View.GONE
                    orderFormDetailsEndTimeLayout.visibility=View.GONE
                    orderFormDetailsOrderNoLayout.visibility=View.GONE
                    orderFormDetailsBtn3.visibility=View.VISIBLE
                    if (data.data.type==0){
                        if (data.data.acceptTime!=null&&data.data.acceptTime!="") {
                            grabOrderDetailsAcceptLayout.visibility = View.VISIBLE
                            orderFormDetailsBoxType.text = data.data.acceptTime
                            orderFormDetailsRateLayout2.visibility=View.GONE
                            orderFormDetailsRateLayout3.visibility=View.VISIBLE
                        }else{
                            grabOrderDetailsAcceptLayout.visibility=View.GONE
                            orderFormDetailsRateLayout2.visibility=View.VISIBLE
                            orderFormDetailsRateLayout3.visibility=View.GONE
                            tv_grab_order_state.text="订单取消"
                        }
                    } else{
                        grabOrderDetailsAcceptLayout.visibility=View.GONE
                        orderFormDetailsRateLayout2.visibility=View.VISIBLE
                        orderFormDetailsRateLayout3.visibility=View.GONE
                        tv_grab_order_state.text="订单取消"
                    }

                }
                5->{
                    orderFormDetailsRateLayout1.visibility=View.GONE
                    orderFormDetailsRateLayout2.visibility=View.VISIBLE
                    orderFormDetailsRateLayout3.visibility=View.GONE
                    orderFormDetailsState.text="抢单超时"
                    orderFormDetailsStateTips.visibility=View.GONE
                    grabOrderDetailsAcceptLayout.visibility=View.GONE
                    orderFormDetailsStartTimeLayout.visibility=View.GONE
                    orderFormDetailsEndTimeLayout.visibility=View.GONE
                    orderFormDetailsOrderNoLayout.visibility=View.GONE
                    orderFormDetailsBtnLayout.visibility=View.GONE


                }
                6->{//抢单状态：0 等待抢单，1 等待开始，2 进行中，3 结束，4 订单取消，5 抢单超时，6 被抢单
                    orderFormDetailsRateLayout1.visibility=View.GONE
                    orderFormDetailsRateLayout2.visibility=View.VISIBLE
                    orderFormDetailsRateLayout3.visibility=View.GONE
                    orderFormDetailsState.text="被抢单"
                    orderFormDetailsStateTips.visibility=View.GONE
                    grabOrderDetailsAcceptLayout.visibility=View.GONE
                    orderFormDetailsStartTimeLayout.visibility=View.GONE
                    orderFormDetailsEndTimeLayout.visibility=View.GONE
                    orderFormDetailsOrderNoLayout.visibility=View.GONE
                    orderFormDetailsBtnLayout.visibility=View.GONE

                }
            }

            Click.viewClick(orderFormDetailsBtn2).subscribe {
                //导航
                name=data.data.businessName
                lat=data.data.latitude.toString()
                lng=data.data.longitude.toString()
                dialog.showDialog()
            }
            Click.viewClick(orderFormDetailsBtn3).subscribe {
                if (flag) {
                    flag=false
                    JMessageClient.getUserInfo(data.data.userId.toString(), "4b24613280bd2c477bab4989", object : GetUserInfoCallback() {
                        override fun gotResult(p0: Int, p1: String, p2: UserInfo) {
                            if (p0 == 0) {
                                flag=true
                                var mUserInfo = p2
                                var intent = Intent()
                                intent.setClass(this@GrabOrderDetailsActivity, ChatActivity::class.java)
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
                when(data.data.inviteState){
                    0->{
                        inviteId=data.data.inviteId.toString()
                        grabOrderPresenter.getGrabOrder(GrabOrderBody(data.data.inviteId.toString()))
                    }
                    1->{
                        inviteId=data.data.inviteId.toString()
                        DBUtils.delMerchat()//删除服务地址
                        DrinkUtils.deleteALLDrinks()//删除所有酒水
                        ServeUtils.deleteALLServe()//删除所有服务人员
                        intentUtils.intentGrabOrderDrinks(inviteId)
                    }
                    2->{
                        inviteId=data.data.inviteId.toString()
                        DBUtils.delMerchat()//删除服务地址
                        DrinkUtils.deleteALLDrinks()//删除所有酒水
                        ServeUtils.deleteALLServe()//删除所有服务人员
                        intentUtils.intentGrabOrderDrinks(inviteId)
                    }
                }
            }

        }
    }

    //判断包名是否存在
    fun isPackageInstalled(packageName: String): Boolean {
        return File("/data/data/$packageName").exists()
    }
}