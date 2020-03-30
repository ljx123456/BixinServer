package com.ycwl.servebixin.cn.base

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import cn.jpush.android.api.BasicPushNotificationBuilder
import cn.jpush.android.api.JPushInterface
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.event.NotificationClickEvent
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.eventbus.EventBus
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseApplication
import com.ycwl.servebixin.cn.base.BaseContext
import com.ycwl.servebixin.cn.db.GreenDaoHelper.initDatabase
import com.ycwl.servebixin.cn.qiniu.SendAppFile.initConfiguration

import com.ycwl.servebixin.cn.utils.utils.SpHelp.initSharedPreferences
import io.reactivex.plugins.RxJavaPlugins
import jiguang.chat.activity.ChatActivity
import jiguang.chat.application.JGApplication
import jiguang.chat.entity.Event
import jiguang.chat.entity.EventType


/**
 * Created by Administrator on 2017/12/18 0018.
 */
class AppProject : BaseApplication() {

    private var flag=false

    override fun initCreate() {
        //context初始化
        BaseContext.initContext(this)
        //RxJava异常处理
        RxJavaPlugins.setErrorHandler {
            Log.e("测试","onRxJavaErrorHandler ---->: $it")
        }
        //初始化数据库
        initDatabase(this)
        //数据初始化
        initSharedPreferences()

        //初始化极光
        initJPush(this)

        //注册到微信
        regToWx()

        //初始化友盟
        initUMeng()
        flag=false

        //初始化bugly
        initBugly()
    }

    fun getFlag():Boolean{
        return this.flag
    }

    fun setFlag(flag:Boolean){
        this.flag=flag
    }

    fun initJPush(context: Context) {
        JPushInterface.init(context)
        setStyleBasic(context)
        JPushInterface.setDebugMode(true)
        JMessageClient.setDebugMode(true)
        JMessageClient.init(context,false)
        JMessageClient.registerEventReceiver(this)
//        setTag(context)
    }

    fun regToWx(){
        val APP_ID = "wx962d0b8ab1bac6ef"
        var api:IWXAPI = WXAPIFactory.createWXAPI(this, APP_ID, true)
        api.registerApp(APP_ID)
    }

    fun initUMeng(){
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5ce3ff350cafb21233000605")
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }

    fun initBugly(){
        CrashReport.initCrashReport(getApplicationContext(), "fd7dc4bcd9", true)
    }

    /**
     * 激光推送设置通知提示方式 - 基础属性
     */
    private fun setStyleBasic(context: Context) {
//        val builder = BasicPushNotificationBuilder(context)
//        builder.statusBarDrawable = R.mipmap.ic_launcher
//        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
//        builder.notificationDefaults = Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS
//        JPushInterface.setPushNotificationBuilder(1, builder)

        val builder = BasicPushNotificationBuilder(context)
        builder.statusBarDrawable = R.mipmap.ic_app
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL or Notification.FLAG_SHOW_LIGHTS  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = (Notification.DEFAULT_SOUND
                or Notification.DEFAULT_VIBRATE
                or Notification.DEFAULT_LIGHTS)  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder)

    }

    override fun initLibrary() {
        //七牛云初始化
        initConfiguration()
    }

    override fun initDataSave() {

    }



    public fun onEvent(event: NotificationClickEvent){
        var mUserInfo=event.message.fromUser
        var intent= Intent()
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

}