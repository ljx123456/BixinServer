package com.ycwl.servebixin.cn.base

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.blankj.utilcode.util.NetworkUtils
import kotlinx.android.synthetic.main.layout_error_network.*
import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.TypedArray
import android.view.WindowManager
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R

/**
 * Created by Administrator on 2017/12/18 0018.
 */
abstract class BaseAppActivity : AppCompatActivity() {


    protected val mContext by lazy { this }

    private fun fixOrientation(): Boolean {
        try {
            val field = Activity::class.java!!.getDeclaredField("mActivityInfo")
            field.setAccessible(true)
            val o = field.get(this) as ActivityInfo
            o.screenOrientation = -1
            field.setAccessible(false)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    private fun isTranslucentOrFloating(): Boolean {
        var isTranslucentOrFloating = false
        try {
            val styleableRes = Class.forName("com.android.internal.R\$styleable").getField("Window").get(null) as IntArray
            val ta = obtainStyledAttributes(styleableRes)
            val m = ActivityInfo::class.java.getMethod("isTranslucentOrFloating", TypedArray::class.java)
            m.isAccessible = true
            isTranslucentOrFloating = m.invoke(null, ta) as Boolean
            m.isAccessible = false
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return isTranslucentOrFloating
    }

    override fun onCreate(bundle: Bundle?) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            var result = fixOrientation()
        }


        super.onCreate(bundle)
        if ("vivo".equals(android.os.Build.BRAND) || "oppo".equals(android.os.Build.BRAND)) {
            var window = getWindow()
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }
        initLayoutData(bundle)
    }

    private fun initLayoutData(bundle: Bundle?) {
        if (openNoWorkLayout()) setLayout(bundle) else onStartActivity(bundle)
    }


    private fun setLayout(bundle: Bundle?) {
        if (NetworkUtils.isConnected()) onStartActivity(bundle) else setNoWorkLayoutView(bundle)
    }


    private fun setNoWorkLayoutView(bundle: Bundle?) {
        setContentView(R.layout.layout_error_network)
        errorLayout.visibility = View.VISIBLE
        Click.viewClick(errorLayout).subscribe { if (NetworkUtils.isConnected()) onStartActivity(bundle) }
    }


    protected abstract fun onStartActivity(bundle: Bundle?)

    protected open fun openNoWorkLayout(): Boolean = true


}