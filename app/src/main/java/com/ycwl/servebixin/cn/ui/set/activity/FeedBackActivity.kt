package com.ycwl.servebixin.cn.ui.set.activity

import android.text.Editable
import android.text.TextWatcher
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.ToastUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.set.mvp.body.FeedBackBody
import com.ycwl.servebixin.cn.ui.set.mvp.presenter.FeedBackPresenter
import com.ycwl.servebixin.cn.ui.set.mvp.view.FeedBackView
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_opinion_feedback.*
import kotlinx.android.synthetic.main.layout_title.*

class FeedBackActivity:BaseActivity(),TextWatcher,FeedBackView{

    private val presenter by lazy { FeedBackPresenter(this,this,this) }

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_opinion_feedback

    override fun setActivityTitle() {
        titleText.text="意见反馈"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {

    }

    override fun clickListener() {
        opinion_feedback_context.addTextChangedListener(this)
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(opinion_feedback_ok).subscribe {
            if (opinion_feedback_context.text.toString().length > 0) {
                presenter.getFeedBack(FeedBackBody(opinion_feedback_context.text.toString()))
            } else {
                ToastUtils.showShort("请输入反馈内容")
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s!!.length >= 500) {
            ToastUtils.showShort("当前输入内容不能超过500字")
        }
        opinion_feedback_num.setText("${s!!.length}/500字")
    }

    override fun getFeedBackRequest() {
        Toast.Tips("感谢您的反馈")
        finish()
    }
}