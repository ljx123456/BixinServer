package com.ycwl.servebixin.cn.ui.message.activity

import android.webkit.WebView
import android.webkit.WebViewClient
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import kotlinx.android.synthetic.main.activity_apply_broker_question.*
import kotlinx.android.synthetic.main.layout_title.*

class ActivityHtmlActivity:BaseActivity(){
    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int = R.layout.activity_apply_broker_question

    override fun setActivityTitle() {
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        var id=intent.getStringExtra("messageId")
        var type = intent.getIntExtra("messageType", 1)
//        外网示例：http://service.bixinyule.com/service/manage/message/?token=1m+iiWM+nr4EPdXjxTKc2CkD3ayF3ktxab8KLwjLvXm89UqRr1WBAZB17vHSCNM=&messageId=1&type=1&port=2
//        本地测试示例：http://192.168.1.222/service/manage/message/?token=1m+iiWM+nr1UonePtxab8KLwjLvXm89EUOrYBiJtb4RCDUqRr1WBAZB17vHSCNM=&messageId=1&type=1&port=2
        var url="http://service.bixinyule.com/service/manage/message/?token=${user.getUserToken()}&messageId=${id}&type=${type}&port=2"
        wv_broker.webViewClient=object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                wv_broker.loadUrl(url)
                return true
            }
        }

        wv_broker.loadUrl(url)
        wv_broker.settings.javaScriptEnabled = true
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
