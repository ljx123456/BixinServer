package com.ycwl.servebixin.cn.ui.message.activity

import android.os.Handler
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.message.mvp.bean.FeedBackDetailsBean
import com.ycwl.servebixin.cn.ui.message.mvp.body.FeedBackDetailsBody
import com.ycwl.servebixin.cn.ui.message.mvp.view.FeedBackDetailsView
import com.ycwl.servebixin.cn.ui.message.mvp.presenter.FeedBackDetailsPresenter
import kotlinx.android.synthetic.main.activity_feedback_details.*
import kotlinx.android.synthetic.main.layout_title.*

class FeedBackDetailsActivity:BaseActivity(),FeedBackDetailsView{

    private var id=""
    private val presenter by lazy { FeedBackDetailsPresenter(this,this,this) }

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int =R.layout.activity_feedback_details

    override fun setActivityTitle() {
        titleText.text="处理结果"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        if (intent.getStringExtra("id")!=null&&intent.getStringExtra("id")!=""){
            id=intent.getStringExtra("id")
            presenter.getFeedBackDetails(FeedBackDetailsBody(id))
//            showLoading()
        }

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }

    }

    override fun getFeedBackDetailsRequest(data: FeedBackDetailsBean) {
//        dismissLoading()
//        layout_content.visibility= View.VISIBLE
//        errorLayout.visibility= View.GONE
        tv_time.text=data.data.createTime
        tv_details.text=data.data.content
        tv_result.text=data.data.handleRes

    }

    override fun getFeedBackDetailsError() {
//        try {
//            var h= Handler()
//            h.postDelayed(object :Runnable{
//                override fun run() {
//                    dismissLoading()
//                    layout_content.visibility= View.GONE
//                    errorLayout.visibility= View.VISIBLE
////                    Log.e("点击","dismiss了")
//                    h.removeCallbacksAndMessages(null)
//                }
//
//            },1000)
//        }catch (e:Exception){
//            e.printStackTrace()
//        }
//        Click.viewClick(anewClick).subscribe {
//            showLoading()
//            presenter.getFeedBackDetails(FeedBackDetailsBody(id))
//        }

    }
}