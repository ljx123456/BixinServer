package com.ycwl.servebixin.cn.ui.message.activity

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import kotlinx.android.synthetic.main.activity_apply_broker_question.*
import kotlinx.android.synthetic.main.layout_title.*

class BannerActivity: BaseActivity(){
    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int = R.layout.activity_apply_broker_question

    override fun setActivityTitle() {
//        titleText.text="用户说明"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        titleText.text=intent.getStringExtra("title")
        val url=intent.getStringExtra("url")
        wv_broker.webViewClient=object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                wv_broker.loadUrl(url)
                return true
            }
        }
        wv_broker.loadUrl(url)
        wv_broker.getSettings().setUseWideViewPort(true);//将图片调整到适合webView的大小
        wv_broker.getSettings().setLoadWithOverviewMode(true);//缩放至屏幕大小
        wv_broker.getSettings().cacheMode=WebSettings.LOAD_NO_CACHE
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (wv_broker!=null) {
            if (wv_broker.canGoBack()) {
                wv_broker.goBack()
            } else {
                finish()
            }
        }
    }

}