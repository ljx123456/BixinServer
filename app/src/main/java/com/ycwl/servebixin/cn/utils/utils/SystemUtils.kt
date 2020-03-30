package com.ycwl.servebixin.cn.utils.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import com.blankj.utilcode.util.ActivityUtils
import com.ycwl.servebixin.cn.base.BaseActivity


/**
 * Created by Administrator on 2018/5/14 0014.
 */
object SystemUtils {


    //全屏
    fun activityFullScreen(activity: BaseActivity) {
        activity.window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
//        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }

    /**
     * 判断当前连接是否为wifi
     *
     * @param context
     * 上下文
     * @return true wifi false 非wifi
     */
    fun isWifiEnabled(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected)
                return networkInfo.type == ConnectivityManager.TYPE_WIFI
        }
        return false
    }


    //判断是否首次安装
    @SuppressLint("ApplySharedPref")
    fun isFirstStart(context: Context): Boolean {
        val preferences = context.getSharedPreferences("SHARE_APP_TAG", 0)
        val isFirst = preferences.getBoolean("FIRSTStart", true)
        return if (isFirst) {
            preferences.edit().putBoolean("FIRSTStart", false).commit()
            true
        } else {
            false
        }
    }

    //重启APP
    fun restartApp(context: Context) {
        Handler().postDelayed({
            ActivityUtils.finishAllActivities()
//            ActivityUtils.startActivity(SplashActivity::class.java)
            android.os.Process.killProcess(android.os.Process.myPid())
        }, 1000)

    }

}