package com.ycwl.servebixin.cn.ui.main.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import cn.jpush.android.api.JPushInterface
import cn.jpush.android.api.TagAliasCallback
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.event.NotificationClickEvent
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.eventbus.EventBus
import cn.jpush.im.api.BasicCallback
import cn.yoyo.com.utils.utils.Click
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.pp.wsy.bosom.app.ui.login.mvp.presenter.SplashPresenter
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.AppProject
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.GreenDaoHelper
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.jpush.MiuiUtils
import com.ycwl.servebixin.cn.ui.main.base.BaseActivity
import com.ycwl.servebixin.cn.ui.main.dialog.VersionUpdatingDialog
import com.ycwl.servebixin.cn.ui.main.fragment.MessageFragment
import com.ycwl.servebixin.cn.ui.main.fragment.OrderFragment
import com.ycwl.servebixin.cn.ui.main.fragment.PersonFragment
import com.ycwl.servebixin.cn.ui.main.location.LocationUtils
import com.ycwl.servebixin.cn.ui.main.mvp.bean.UpdateBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.LocationBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.UpdateBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.UpdateCityBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.LocationPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.UpdateCityPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.LocationView
import com.ycwl.servebixin.cn.ui.main.mvp.view.SplashView
import com.ycwl.servebixin.cn.ui.main.mvp.view.UpdateCityView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.permissions.UserPermissions
import com.ycwl.servebixin.cn.utils.permissions.UserPermissions.userLocation
import com.ycwl.servebixin.cn.utils.utils.Toast
import jiguang.chat.activity.ChatActivity
import jiguang.chat.application.JGApplication
import jiguang.chat.entity.Event
import jiguang.chat.entity.EventType
import jiguang.chat.entity.NotificationClickEventReceiver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),SplashView, VersionUpdatingDialog.VersionUpdatingCallBack,LocationUtils.Location , LocationView,UpdateCityView{
    override fun getUpdateCityRequest() {//更新用户城市成功
        updateCityFlag=false
    }

    override fun getUpdateCityError() {

    }

    override fun getLocationRequest() {

    }
//    override fun requestPermissionsFaceError() {
//        if (flag||AppProject().getFlag()){
//
//        }
//    }
//
//    override fun getLocationRequest() {
//
//    }

    override fun openNoWorkLayout(): Boolean =false

    private val updataPresenter by lazy { SplashPresenter(this, this, this) }
    private val updatingdialog = VersionUpdatingDialog()
    private val locationPresenter by lazy { LocationPresenter(this, this, this) }
    private var locationUtils=LocationUtils(this)
    private val updateCityPresenter by lazy { UpdateCityPresenter(this,this,this) }

    private var tvs= arrayOfNulls<TextView>(3)
    private var ivs= arrayOfNulls<ImageView>(3)
    private var dbs_nor= arrayOf(R.mipmap.message_nor,R.mipmap.order_nor,R.mipmap.myself_nor)
    private var dbs_sel= arrayOf(R.mipmap.message_sel,R.mipmap.order_sel,R.mipmap.myself_sel)
    private var fragments= arrayOfNulls<Fragment>(3)
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private var now_index=0
//    private var locationUtils=LocationUtils(this)
    private var flag=false
    private var flag2=false
    private var updateCityFlag=true

//    private val  MSG_SET_ALIAS = 1001
    companion object {
     val  MSG_SET_ALIAS = 1001
    }
    private val mAliasCallback= object :TagAliasCallback{
        override fun gotResult(p0: Int, p1: String?, p2: MutableSet<String>?) {
            when(p0){
                0->{
                    Log.e("测试","别名设置成功")
                    mHandler.removeCallbacksAndMessages(null)
                }
                6002->{
                    Log.e("测试","别名延时设置")
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60)
                }
                else ->{
                    Log.e("测试","别名设置p0"+p0.toString())
                }
            }
        }
    }
    private val mHandler : Handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                MSG_SET_ALIAS ->{
                    Log.e("测试","收到消息")
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAlias(applicationContext,
                            user.getUserUserID(),
                            mAliasCallback)

                }
                else -> {

                }
            }
        }
    }

    private var alias=""


//    //高德定位
//    //声明AMapLocationClient类对象
//    var mLocationClient: AMapLocationClient? = null
//    //声明AMapLocationClientOption对象
//    var mLocationOption: AMapLocationClientOption? = null
//    //异步获取定位结果
//    var mLocationListener: AMapLocationListener = AMapLocationListener { amapLocation ->
//        if (amapLocation != null) {
//            if (amapLocation.errorCode == 0) {
//                //解析定位结果
//
//
//            }else {
//                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                Log.e("AmapError","location Error, ErrCode:"
//                        + amapLocation.getErrorCode() + ", errInfo:"
//                        + amapLocation.getErrorInfo())
//            }
//        }
//    }


    override fun onSavedInstanceState(bundle: Bundle?) {
        super.onSavedInstanceState(bundle)
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = window.decorView
            val option = ( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            decorView.systemUiVisibility = option
            window.navigationBarColor = Color.TRANSPARENT
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    //更新接口
    override fun getUpdateRequest(data: UpdateBean) {
        updatingdialog.setDialogContent(data, this)
        updatingdialog.show(supportFragmentManager, "")
//        updatingdialog.isCancelable=false
    }
    override fun getUpdateError(code: Int, message: String) {
        if (code!=-1301)
            Toast.Tips(message)
    }

    override fun enterInto() {

    }

//    override fun requestPermissionsFaceSucceed(context: Context, what: Int) {
//        //获取定位
////        citypresenter.getCityList()
//
//        locationUtils.getLocation()
//    }

    override fun getLocationSuccess() {
//        if (!"".equals(getCity())) {
//            for (i in cityListBean.data.indices) {
//                if (cityListBean.data.get(i).cityName.equals(getCity())) {
//                    setCityID("${cityListBean.data.get(i).cityId}")
//                }
//            }
//        } else {
//
//        }
        Log.e("测试","定位一次")
        locationPresenter.getLocation(LocationBody(user.getlng(),user.getlat()))
        if (updateCityFlag){
            Log.e("测试","更新用户城市")
            updateCityPresenter.getUpdateCity(UpdateCityBody(user.getlng(),user.getlat()))
        }
    }

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int =R.layout.activity_main

    override fun setActivityTitle() {

    }

    override fun initActivityData() {
//        var userDB = GreenDaoHelper.getDaoSessions().userDBDao
//        if (userDB != null) {
//            var data = userDB.loadAll()
//            if (data != null && data.size >= 1 && data.get(0).token != null) {
//                LogUtils.a("极光别名", user.getUserUserID())
//                JPushInterface.setAlias(this, 1, user.getUserUserID())
//                JMessageClient.login(user.getUserUserID(), user.getUserIM(), object : BasicCallback() {
//                    override fun gotResult(p0: Int, p1: String?) {
//                        LogUtils.a("极光聊天", p1)
//                    }
//                })
//                JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_SOUND or JMessageClient.FLAG_NOTIFY_WITH_VIBRATE)
//                //注册Notification点击的接收器
//                NotificationClickEventReceiver(applicationContext)
//            }else{
//                intentUtils.intentLogin()
//                finish()
//            }
//        }else{
//            intentUtils.intentLogin()
//            finish()
//        }

        alias=user.getUserID()
//        调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias))
//        LogUtils.a("极光别名", user.getUserUserID())
//        JPushInterface.setAlias(this,user.getUserUserID(),object : TagAliasCallback {
//            override fun gotResult(p0: Int, p1: String?, p2: MutableSet<String>?) {
//                Log.e("测试别名设置main",p0.toString())
//                if (p0==6002){
//                    var handler=Handler()
//                    handler.postDelayed(object :Runnable{
//                        override fun run() {
//                            JPushInterface.setAlias(this@MainActivity,user.getUserUserID(),object : TagAliasCallback {
//                                override fun gotResult(p0: Int, p1: String?, p2: MutableSet<String>?) {
//                                    Log.e("测试别名设置main",p0.toString())
////                                    if (p0==6002){
////                                        var handler=Handler()
////                                        handler.postDelayed(object :Runnable{
////                                            override fun run() {
////
////                                            }
////                                        },1000*10)
////                                    }
//                                }
//                            })
//                            handler.removeCallbacksAndMessages(null)
//                        }
//                    },1000*60)
//                }
//            }
//        })
//        JMessageClient.login(user.getUserUserID(), user.getUserIM(), object : BasicCallback() {
//            override fun gotResult(p0: Int, p1: String?) {
//                LogUtils.a("极光聊天", p1)
//            }
//        })
//        JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_SOUND or JMessageClient.FLAG_NOTIFY_WITH_VIBRATE)
//        //注册Notification点击的接收器
//        NotificationClickEventReceiver(applicationContext)
        Thread(object:Runnable {
            override fun run() {
                if (!MiuiUtils.isNotificationEnabled(this@MainActivity)) {
                    ShowDialog.showCustomDialog(this@MainActivity, "提示信息", "为了更好的使用本产品,请到设置中心打开对应通知设置", "去设置", "取消", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    if (MiuiUtils.isMIUI()) {
                                        MiuiUtils.goPermissionSettings(mContext)
                                    } else {
                                        val localIntent = Intent()
                                        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        if (Build.VERSION.SDK_INT >= 9) {
                                            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS")
                                            localIntent.setData(Uri.fromParts("package", this@MainActivity.packageName, null))
                                        } else if (Build.VERSION.SDK_INT <= 8) {
                                            localIntent.setAction(Intent.ACTION_VIEW)

                                            localIntent.setClassName("com.android.settings",
                                                    "com.android.settings.InstalledAppDetails")
                                            localIntent.putExtra("com.android.settings.ApplicationPkgName",
                                                    this@MainActivity.packageName)
                                        }
                                        startActivity(localIntent)
                                    }
                                    dialog!!.dismiss()
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog!!.dismiss()
                                }
                            }
                        }
                    })

                }
            }
        })
        updataPresenter.getUpdata(UpdateBody(1, AppUtils.getAppVersionCode()))
//        userLocation(this, this)
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        tvs[0] = findViewById<TextView>(R.id.tv_person_msg)
        tvs[1] = findViewById<TextView>(R.id.tv_person_order)
        tvs[2] = findViewById<TextView>(R.id.tv_person_myself)
        ivs[0] = findViewById<ImageView>(R.id.iv_person_msg)
        ivs[1] = findViewById<ImageView>(R.id.iv_person_order)
        ivs[2] = findViewById<ImageView>(R.id.iv_person_myself)
        fragments[2] = PersonFragment()
        fragmentTransaction.add(R.id.main_layout, fragments[2]!!)
        tvs[2]!!.setTextColor(Color.parseColor("#FFFE849B"))
        ivs[2]!!.setImageResource(dbs_sel[2])
        fragmentTransaction.commit()
        now_index = 2



    }

   public fun onEvent(event:NotificationClickEvent){
        var mUserInfo=event.message.fromUser
        var intent=Intent()
        intent.setClass(this, ChatActivity::class.java)
        //创建会话
        intent.putExtra(JGApplication.TARGET_ID, mUserInfo.getUserName())
        intent.putExtra(JGApplication.TARGET_APP_KEY, mUserInfo.getAppKey())
        var notename = mUserInfo.getNotename()
        if (TextUtils.isEmpty(notename)) {
            notename = mUserInfo.getNickname()
            if (TextUtils.isEmpty(notename)) {
                notename = mUserInfo.getUserName()
            }
        }
        intent.putExtra(JGApplication.CONV_TITLE, notename)
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

    //初始化高德
//    fun initGPS(){
//        //初始化定位
//        mLocationClient = AMapLocationClient(applicationContext)
//        //设置定位回调监听
//        mLocationClient!!.setLocationListener(mLocationListener)
//        //初始化AMapLocationClientOption对象
//        mLocationOption = AMapLocationClientOption()
//        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
//        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
//        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
//        mLocationOption!!.setInterval((1000 * 60).toLong())
//        //关闭缓存机制
//        mLocationOption!!.setLocationCacheEnable(false)
//        //给定位客户端对象设置定位参数
//        mLocationClient!!.setLocationOption(mLocationOption)
//        //启动定位
//        mLocationClient!!.startLocation()
//    }

    override fun clickListener() {
        Click.viewClick(layout_person_msg).subscribe {
            choice(0)
            initialize(0)
            choiceFragment(0)
        }

        Click.viewClick(layout_person_order).subscribe {
            choice(1)
            initialize(1)
            choiceFragment(1)
        }

        Click.viewClick(layout_person_myself).subscribe {
            choice(2)
            initialize(2)
            choiceFragment(2)
        }

    }


    //恢复默认
    fun initialize(index:Int){
        for (i in tvs.indices){
            if (index!=i){
                tvs[i]!!.setTextColor(Color.parseColor("#ff535252"))
                ivs[i]!!.setImageResource(dbs_nor[i])
            }
        }
    }

    //切换效果

    fun choice(index: Int){
        tvs[index]!!.setTextColor(Color.parseColor("#FFFE849B"))
        ivs[index]!!.setImageResource(dbs_sel[index])
    }

    fun choiceFragment(index: Int){
        var fragmentManager=supportFragmentManager
        var fragmentTransaction=fragmentManager.beginTransaction()
        if (now_index!=index){
            if (fragments[index]==null){
                fragments[index]=fragment(index)
                fragmentTransaction.add(R.id.main_layout,fragments[index]!!)
            }else{
                fragmentTransaction.show(fragments[index]!!)
            }
            fragmentTransaction.hide(fragments[now_index]!!)
            fragmentTransaction.commit()
            now_index=index
        }
    }

    fun fragment(index:Int) :Fragment{
        var fragment:Fragment?=null
        when(index){
            0->
                fragment=MessageFragment()
            1->
                fragment=OrderFragment()
            2->
                fragment=PersonFragment()
        }

        return fragment!!
    }

    override fun onResume() {
        super.onResume()
        ActivityUtils.finishOtherActivities(MainActivity::class.java)
        val rxPermissions = RxPermissions(this)
        if (!flag2) {
            flag2=true
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe { aBoolean ->
                if (aBoolean!!) {
                    var userDB = GreenDaoHelper.getDaoSessions().userDBDao
                    if (userDB != null) {
                        var data = userDB.loadAll()
                        if (data != null && data.size >= 1 && data.get(0).token != null) {

                            JMessageClient.login(user.getUserUserID(), user.getUserIM(), object : BasicCallback() {
                                override fun gotResult(p0: Int, p1: String?) {
                                    if (p0 == 0) {
                                        JMessageClient.logout()
                                        LogUtils.a("极光聊天", p0.toString())
                                        Handler().postDelayed(object : Runnable {
                                            override fun run() {
                                                JMessageClient.login(user.getUserUserID(), user.getUserIM(), object : BasicCallback() {
                                                    override fun gotResult(p0: Int, p1: String?) {
                                                        if (p0 == 0) {
                                                            LogUtils.a("极光聊天", p0.toString())
                                                            Log.e("测试", "登陆")
                                                        }
                                                    }
                                                })

                                            }
                                        }, 500)

                                    }
                                }
                            })
                            JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_SOUND or JMessageClient.FLAG_NOTIFY_WITH_VIBRATE)
                            //注册Notification点击的接收器
                            NotificationClickEventReceiver(applicationContext)

                        } else {
                            intentUtils.intentLogin()
                            finish()
                        }
                    } else {
                        intentUtils.intentLogin()
                        finish()
                    }
                } else {
//                    ShowDialog.showCustomDialogNoTitle(this, "由于无法获取设备信息的权限,应用无法运行，请前往设置中心应用权限页设置", "去设置", "取消", object : DialogInterface.OnClickListener {
//                        override fun onClick(dialog: DialogInterface, which: Int) {
//                            when (which) {
//                                DialogInterface.BUTTON_POSITIVE -> {
//                                    dialog.dismiss()
//                                    flag2=false
//                                    UserPermissions.gotoSet(this@MainActivity)
//                                }
//                                DialogInterface.BUTTON_NEGATIVE -> {
//                                    dialog.dismiss()
//                                }
//                            }
//                        }
//                    })
//                    return@subscribe
                }
            }
        }
//        var rxPermissions = RxPermissions(context as Activity)
        if (!flag) {
            flag = true
            val user = DBUtils.getUser()
            if (user.isPerfectData == 1 && user.serviceIDCardAuditState == 1) {
                initLocationPremission()
            }
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if(KeyEvent.KEYCODE_BACK==keyCode)
            return false
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (locationUtils!=null){
            locationUtils.stopLocation()
        }
    }

    fun initLocationPremission(){
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).subscribe { aBoolean ->
            if (aBoolean) {
                //获取定位
                //        citypresenter.getCityList()
                locationUtils.getLocation()
            } else {
                if(!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){
                    ShowDialog.showCustomDialog(this, "定位服务未开启", "未开启定位服务将影响你整个订单系统，建议立即前往设置", "去设置", "暂不", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
                                    flag=false
                                    UserPermissions.gotoSet(this@MainActivity)
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog.dismiss()
                                }
                            }
                        }
                    })
                }else{
                    ShowDialog.showCustomDialog(this, "定位服务未开启", "未开启定位服务将影响你整个订单系统，建议立即开启", "去开启", "暂不", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
                                    initLocationPremission()
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
