package com.ycwl.servebixin.cn.ui.order.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
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
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseFragment
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.DrinkUtils
import com.ycwl.servebixin.cn.db.ServeUtils.deleteALLServe
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.location.LocationUtils
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.ui.order.adapter.GrabOrderListAdapter
import com.ycwl.servebixin.cn.ui.order.adapter.OrderFormListAdapter
import com.ycwl.servebixin.cn.ui.order.adapter.RefuseReasonsAdapter
import com.ycwl.servebixin.cn.ui.order.dialog.SelectMapDialog
import com.ycwl.servebixin.cn.ui.order.mvp.bean.*
import com.ycwl.servebixin.cn.ui.order.mvp.body.*
import com.ycwl.servebixin.cn.ui.order.mvp.presenter.GrabOrderPresenter
import com.ycwl.servebixin.cn.ui.order.mvp.presenter.OrderFormListPresenter
import com.ycwl.servebixin.cn.ui.order.mvp.presenter.OrderFormMakePresenter
import com.ycwl.servebixin.cn.ui.order.mvp.view.GrabOrderView
import com.ycwl.servebixin.cn.ui.order.mvp.view.OrderFormListView
import com.ycwl.servebixin.cn.ui.order.mvp.view.OrderFormMakeView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.permissions.UserPermissions
import com.ycwl.servebixin.cn.utils.utils.Toast
import com.ycwl.servebixin.cn.view.MyRecyclerView
import jiguang.chat.activity.ChatActivity
import jiguang.chat.application.JGApplication
import jiguang.chat.entity.Event
import jiguang.chat.entity.EventType
import kotlinx.android.synthetic.main.fragment_order_form_list.*
import kotlinx.android.synthetic.main.layout_error_network.*
import kotlinx.android.synthetic.main.layout_none_data.*
import kotlinx.android.synthetic.main.pop_refuse_reason.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.greendao.DbUtils
import java.io.File
import java.net.URISyntaxException
import java.nio.file.Files.exists



@SuppressLint("ValidFragment")
class OrderFormListFragment(val type:String,val it: String): BaseFragment(),OrderFormListView,OrderFormMakeView,GrabOrderView,LocationUtils.Location,OrderFormListAdapter.CallBack,GrabOrderListAdapter.CallBack,SelectMapDialog.SelectMapDialogFace{
    override fun gaodeBtn() {
        if (isPackageInstalled("com.autonavi.minimap")){
            var intent=Intent()
            intent.action=Intent.ACTION_VIEW
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

    override fun getOrderFormListError(code: Int) {
        if (code==-6) {
//            orderPageIndex = 1
//            LogUtils.a("订单列表"+orderState,"报错-6")
            orderFormPresenter.getOrderFormList(OrderFormListBody(orderState, orderPageIndex, orderPageSize))
        }else if (code==-1320){
            swip_order_form.isRefreshing = false
            errorLayout.visibility = View.GONE
            noDataLayout.visibility=View.VISIBLE
        }
        else{
            swip_order_form.isRefreshing = false
            errorLayout.visibility = View.VISIBLE
            Click.viewClick(errorLayout).subscribe {
                orderPageIndex = 1
                orderFormPresenter.getOrderFormList(OrderFormListBody(orderState, orderPageIndex, orderPageSize))
            }
        }
    }

    override fun getGrabOrderListError(code: Int) {
        if (code==-6) {
//            grabPageIndex = 1

            orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState, grabPageIndex, grabPageSize))
        }else if (code==-1320){
            swip_order_form.isRefreshing = false
            errorLayout.visibility = View.GONE
            noDataLayout.visibility=View.VISIBLE
        }
        else{
            swip_order_form.isRefreshing = false
            errorLayout.visibility = View.VISIBLE
            Click.viewClick(errorLayout).subscribe {
                grabPageIndex = 1
                orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState, grabPageIndex, grabPageSize))
            }
        }

    }

    override fun finishGrab() {
//        grabPageIndex=1
        orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState,grabPageIndex,grabPageSize))
    }

    override fun finish() {
//        orderPageIndex=1
        orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
    }

    override fun getLocationSuccess() {
        locationUtils.stopLocation()
        orderMakePresenter.getClock(OrderFormClockBody(orderNo,user.getlng(),user.getlat()))
    }


    private val orderFormPresenter by lazy { OrderFormListPresenter(this,this,activity!!) }
    private val orderMakePresenter by lazy { OrderFormMakePresenter(this,this,activity!!) }
    private val grabOrderPresenter by lazy { GrabOrderPresenter(this,this,activity!!) }

    private var orderPageIndex=1
    private val orderPageSize=10
    private var orderState="-1"
    private var orderNo=""
    private var position=0
    private var reasonCode=0
    private var orderFormAdapter:OrderFormListAdapter?=null

    private var grabPageIndex=1
    private val grabPageSize=10
    private var grabState="-1"
    private var grabOrderListAdapter:GrabOrderListAdapter?=null
    private var grabPosition=0
    private var inviteId=""
    private var isCreated = false
    private var name=""

    private lateinit var locationUtils:LocationUtils

    private lateinit var dialog: SelectMapDialog
    private var lat=""
    private var lng=""

    private var flag1=true
    private var flag2=true


    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCreated=true
//        org.greenrobot.eventbus.EventBus.getDefault().register(this)
    }

    override fun openEventBus(): Boolean = false

    override fun setLayoutTitle() {

    }

    override fun initFragmentData() {
        popView = LayoutInflater.from(mContext).inflate(R.layout.pop_refuse_reason, null)
        pop = PopupWindowHelper(popView)

        dialog= SelectMapDialog(activity!!)
        dialog.setDialogFace(this)


        if (type.equals("订单")) {
            when (it) {//订单状态 -1 全部，1 待确认，2 进行中，3 已完成
                "待确认" -> {
                    orderPageIndex = 1
                    orderState = "1"
                }
                "进行中" -> {
                    orderPageIndex = 1
                    orderState = "2"
                }
                "已完成" -> {
                    orderPageIndex = 1
                    orderState = "3"
                }
                "全部" -> {
                    orderPageIndex = 1
                    orderState = "-1"
                }

            }

            swip_order_form.setOnRefreshListener {
                orderPageIndex = 1
                orderFormPresenter.getOrderFormList(OrderFormListBody(orderState, orderPageIndex, orderPageSize))
            }

        } else {
            when (it) {//状态：-1全部订单，1等待抢单，2进行中，3已完成
                "待确认" -> {
                    grabPageIndex = 1
                    grabState = "1"
                }
                "进行中" -> {
                    grabPageIndex = 1
                    grabState = "2"
                }
                "已完成" -> {
                    grabPageIndex = 1
                    grabState = "3"
                }
                "全部" -> {
                    grabPageIndex = 1
                    grabState = "-1"
                }
            }

            orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState, grabPageIndex, grabPageSize))
            swip_order_form.setOnRefreshListener {
                grabPageIndex = 1
                orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState, grabPageIndex, grabPageSize))
            }

        }
    }

    override fun setFragmentListener() {
        Click.viewClick(popView.findViewById(R.id.layout_bg)).subscribe { pop.dismiss() }
        Click.viewClick(popView.findViewById(R.id.pop_close)).subscribe { pop.dismiss() }
        Click.viewClick(popView.findViewById(R.id.pop_sure)).subscribe {
            orderMakePresenter.getRefuse(OrderFormRefuseBody(orderNo,reasonCode))
        }
    }

    override fun layoutID(): Int = R.layout.fragment_order_form_list

    override fun getOrderFormListRequest(data: OrderFormListBean) {
        swip_order_form.isRefreshing=false
        errorLayout.visibility=View.GONE
        if (data!=null&&data.data!=null&&data.data.size>0){
            swip_order_form.visibility=View.VISIBLE
            noDataLayout.visibility=View.GONE
            if (orderPageIndex==1){
                orderFormAdapter= OrderFormListAdapter(it,data.data)
                var manager=LinearLayoutManager(activity!!)
                manager.orientation=LinearLayout.VERTICAL
                recy_order_form.layoutManager=manager
                recy_order_form.adapter=orderFormAdapter
            }else{
                orderFormAdapter!!.addData(data.data)
            }
            orderFormAdapter!!.setCallBack(this)
            orderFormAdapter!!.setOnLoadMoreListener(object :BaseQuickAdapter.RequestLoadMoreListener{
                override fun onLoadMoreRequested() {
                    orderPageIndex=orderPageIndex+1
                    orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
                }
            },recy_order_form)
            orderFormAdapter!!.setOnItemClickListener { adapter, view, position ->
                intentUtils.intentOrderFormDetails(orderFormAdapter!!.data[position].orderServiceNo)
            }
            orderFormAdapter!!.setOnItemChildClickListener { adapter, view, position ->
                //服务状态：1 未接单，2 支付失败，3已接单/未付款，4 已付款，5 到场，6 开始服务，7 结束服务，8 超时，9 用户取消，10 服务人员拒绝，12 邀约已满
                when(view.id){
                    R.id.btn_order_form_list_1 ->{
                        // 拒绝
                        orderNo=orderFormAdapter!!.data[position].orderServiceNo
                        ShowDialog.showCustomDialogNoTitle(activity!!,"是否确认拒绝本次约玩？","确认","取消",object :DialogInterface.OnClickListener{
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
                    R.id.btn_order_form_list_2 ->{
                        //导航
                        name=orderFormAdapter!!.data[position].businessName
                        lat=orderFormAdapter!!.data[position].latitude.toString()
                        lng=orderFormAdapter!!.data[position].longitude.toString()
                        dialog.showDialog()

                    }
                    R.id.btn_order_form_list_3 ->{
                        if (flag1) {
                            flag1=false
                            JMessageClient.getUserInfo(orderFormAdapter!!.data[position].userId.toString(), "4b24613280bd2c477bab4989", object : GetUserInfoCallback() {
                                override fun gotResult(p0: Int, p1: String, p2: UserInfo) {
                                    if (p0 == 0) {
                                        flag1=true
                                        LogUtils.a("测试成功：" + p1)
                                        var mUserInfo = p2
                                        var intent = Intent()
                                        intent.setClass(activity!!, ChatActivity::class.java)
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
                                    } else {
                                        LogUtils.a("测试失败：" + p1)
                                    }
                                }
                            })
                        }else{
                            Toast.Tips("聊天正在创建中。。。")
                        }
                    }
                    R.id.btn_order_form_list_4 ->{
//                        Toast.Tips("点击")
                        when(orderFormAdapter!!.data[position].serviceState){
                            1->{
                                // 接单
                                this.position=position
                                orderNo=orderFormAdapter!!.data[position].orderServiceNo
                                orderMakePresenter.getAccept(OrderFormAcceptBody(orderNo))
                            }
                            4->{
                                // 打卡
                                 this.position=position
                                 orderNo=orderFormAdapter!!.data[position].orderServiceNo

                                checkLocation()
                            }
                            6->{
                                //点单
                                orderNo=orderFormAdapter!!.data[position].orderServiceNo
                                DBUtils.delMerchat()//删除服务地址
                                DrinkUtils.deleteALLDrinks()//删除所有酒水
                                deleteALLServe()//删除所有服务人员
                                intentUtils.intentOrderFormDrinks(orderNo)
                            }
                            9->{//查看补贴
                                intentUtils.intentIncomeRecordDetails(orderFormAdapter!!.data[position].walletRecodeId)
                            }
                        }
                    }
                }
            }
            if (data.data.size<orderPageSize){
                orderFormAdapter!!.loadMoreEnd()
            }else{
                orderFormAdapter!!.loadMoreComplete()
            }

        }else{
//            if (orderFormAdapter!=null){
                if (orderPageIndex>1){
                    orderFormAdapter!!.loadMoreEnd()
                }else{
                    swip_order_form.visibility=View.GONE
                    noDataLayout.visibility=View.VISIBLE
                }

//            }
        }
    }

    override fun getGrabOrderListRequest(data: GrabOrderListBean) {
        swip_order_form.isRefreshing=false
        errorLayout.visibility=View.GONE
        if (data!=null&&data.data!=null&&data.data.size>0){
            swip_order_form.visibility=View.VISIBLE
            noDataLayout.visibility=View.GONE
            if (grabPageIndex==1){
                grabOrderListAdapter= GrabOrderListAdapter(data.data)
                var manager=LinearLayoutManager(activity!!)
                manager.orientation=LinearLayout.VERTICAL
                recy_order_form.layoutManager=manager
                recy_order_form.adapter=grabOrderListAdapter
            }else{
                grabOrderListAdapter!!.addData(data.data)
            }
            grabOrderListAdapter!!.setCallBack(this)
            grabOrderListAdapter!!.setOnLoadMoreListener(object :BaseQuickAdapter.RequestLoadMoreListener{
                override fun onLoadMoreRequested() {
                    grabPageIndex=grabPageIndex+1
                    orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState,grabPageIndex,grabPageSize))
                }
            },recy_order_form)
            grabOrderListAdapter!!.setOnItemClickListener { adapter, view, position ->
                intentUtils.intentGrabOrderDetails(grabOrderListAdapter!!.data[position].inviteId)
            }
            grabOrderListAdapter!!.setOnItemChildClickListener { adapter, view, position ->
                when(view.id){//抢单状态：0 等待抢单，1 等待开始，2 进行中，3 结束，4 订单取消，5 抢单超时，6 被抢单
                    R.id.btn_order_form_list_2->{
                        //导航
                        name=grabOrderListAdapter!!.data[position].businessName
                        lat=grabOrderListAdapter!!.data[position].latitude.toString()
                        lng=grabOrderListAdapter!!.data[position].longitude.toString()
                        dialog.showDialog()
                    }
                    R.id.btn_order_form_list_3->{
                        if (flag2) {
                            flag2=false
                            JMessageClient.getUserInfo(grabOrderListAdapter!!.data[position].userId.toString(), "4b24613280bd2c477bab4989", object : GetUserInfoCallback() {
                                override fun gotResult(p0: Int, p1: String, p2: UserInfo) {
                                    if (p0 == 0) {
                                        flag2=true
                                        var mUserInfo = p2
                                        var intent = Intent()
                                        intent.setClass(this@OrderFormListFragment.activity!!, ChatActivity::class.java)
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
//                                    var conv: Conversation? = JMessageClient.getSingleConversation(mUserInfo.getUserName(), mUserInfo.getAppKey())
//                                    //如果会话为空，使用EventBus通知会话列表添加新会话
//                                    if (conv == null) {
//                                        conv = Conversation.createSingleConversation(mUserInfo.getUserName(), mUserInfo.getAppKey())
//                                        EventBus.getDefault().post(Event.Builder()
//                                                .setType(EventType.createConversation)
//                                                .setConversation(conv)
//                                                .build())
//                                    }
                                        startActivity(intent)
                                    }
                                }
                            })
                        }else{
                            Toast.Tips("聊天正在创建中。。。")
                        }
                    }
                    R.id.btn_order_form_list_4->{
                        when(grabOrderListAdapter!!.data[position].inviteState){
                            0->{
                                grabPosition=position
                                grabOrderPresenter.getGrabOrder(GrabOrderBody(grabOrderListAdapter!!.data[position].inviteId))
                            }
                            1->{
                                inviteId=grabOrderListAdapter!!.data[position].inviteId
                                DBUtils.delMerchat()//删除服务地址
                                DrinkUtils.deleteALLDrinks()//删除所有酒水
                                deleteALLServe()//删除所有服务人员
                                intentUtils.intentGrabOrderDrinks(inviteId)
                            }
                            2->{
                                inviteId=grabOrderListAdapter!!.data[position].inviteId
                                DBUtils.delMerchat()//删除服务地址
                                DrinkUtils.deleteALLDrinks()//删除所有酒水
                                deleteALLServe()//删除所有服务人员
                                intentUtils.intentGrabOrderDrinks(inviteId)
                            }
                        }
                    }
                }
            }

            if (data.data.size<grabPageSize){
                grabOrderListAdapter!!.loadMoreEnd()
            }else{
                grabOrderListAdapter!!.loadMoreComplete()
            }
        }else{
//            if (grabOrderListAdapter!=null){
                if (grabPageIndex>1){
                    grabOrderListAdapter!!.loadMoreEnd()
                }else{
                    swip_order_form.visibility=View.GONE
                    noDataLayout.visibility=View.VISIBLE

                }

//            }
        }
    }

    override fun getGrabOrderRequest(data: OrderFormAcceptBean) {
        Toast.Tips("抢单成功")
//        grabPageIndex=1
        orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState,grabPageIndex,grabPageSize))
    }
    override fun getGrabOrderError(code: Int) {
        if (code==-6){
            orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState,grabPageIndex,grabPageSize))
        }
    }


    override fun getAcceptRequest(data: OrderFormAcceptBean) {
        Toast.Tips("接单成功")
//        orderPageIndex=1
        orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
    }

    override fun getAcceptError(code: Int) {
        if (code==-6){
            orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
        }
    }

    override fun getClockRequest(data: OrderFormClockBean) {
        Toast.Tips("打卡成功")
//        orderPageIndex=1
        orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
    }

    override fun getClockError(code: Int) {
        if (code==-5011){
            ShowDialog.showCustomDialog(activity!!,"此时间段不支持打卡\n请在约玩开始前10分钟内打卡","确认",object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                    orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
                }
            })
        }else if (code==-5012){
            ShowDialog.showCustomDialog(activity!!,"不在打卡范围内\n请在目的地范围100米内打卡","确认",object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                    orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
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
            var adapter=RefuseReasonsAdapter(list)
            var manager=LinearLayoutManager(context!!)
            manager.orientation=LinearLayout.VERTICAL
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
            pop.showAtLocation(recy_order_form,Gravity.FILL,0,0)
        }

    }

    override fun getRefuseRequest(data: OrderFormRefuseBean) {
        pop.dismiss()
//        grabPageIndex=1
        orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
    }

    //判断包名是否存在
    fun isPackageInstalled(packageName: String): Boolean {
        return File("/data/data/$packageName").exists()
    }

    override fun onResume() {
        super.onResume()
        if (type.equals("订单")){
//            orderPageIndex=1
            orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
        }else{
//            grabPageIndex=1
            orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState,grabPageIndex,grabPageSize))
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isCreated) {
            return
        }

        if (userVisibleHint) {
            if (type.equals("订单")){
                orderPageIndex=1
                orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
            }else{
                grabPageIndex=1
                orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState,grabPageIndex,grabPageSize))
            }
        }


    }

    public fun init(){
        if (type.equals("订单")){
            orderPageIndex=1
            orderFormPresenter.getOrderFormList(OrderFormListBody(orderState,orderPageIndex,orderPageSize))
        }else{
            grabPageIndex=1
            orderFormPresenter.getGrabOrderList(GrabOrderListBody(grabState,grabPageIndex,grabPageSize))
        }
    }


    fun checkLocation(){
        var rxPermissions = RxPermissions(context as Activity)
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).subscribe { aBoolean ->
            if (aBoolean) {
                locationUtils=LocationUtils(this)
                locationUtils.getLocation()
            }
            else {
                if(!ActivityCompat.shouldShowRequestPermissionRationale(activity!!, Manifest.permission.ACCESS_COARSE_LOCATION)){
                    ShowDialog.showCustomDialog(activity!!,"打卡失败","无法获取你位置信息，请立即前往设置","去设置","暂不",object :DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
                                    UserPermissions.gotoSet(activity!!)
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog.dismiss()
                                }
                            }
                        }
                    })
                }else{
                    ShowDialog.showCustomDialog(activity!!,"打卡失败","无法获取你位置信息，请立即开启","去开启","暂不",object :DialogInterface.OnClickListener{
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