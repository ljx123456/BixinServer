package com.ycwl.servebixin.cn.ui.set.activity

import android.webkit.WebView
import android.webkit.WebViewClient
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import kotlinx.android.synthetic.main.activity_apply_broker_question.*
import kotlinx.android.synthetic.main.layout_title.*

class ExplainActivity:BaseActivity(){
    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int = R.layout.activity_apply_broker_question

    override fun setActivityTitle() {
        titleText.text="帮助中心"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        wv_broker.webViewClient=object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                wv_broker.loadUrl(url)
                return true
            }
        }
        wv_broker.loadUrl("http://service.bixinyule.com/service_help")
        wv_broker.getSettings().setUseWideViewPort(true);//将图片调整到适合webView的大小
        wv_broker.getSettings().setLoadWithOverviewMode(true);//缩放至屏幕大小
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (wv_broker.canGoBack()){
            wv_broker.goBack()
        }else{
            finish()
        }
    }

}