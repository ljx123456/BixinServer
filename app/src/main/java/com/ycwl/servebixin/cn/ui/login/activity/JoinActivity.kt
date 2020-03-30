package com.ycwl.servebixin.cn.ui.login.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import kotlinx.android.synthetic.main.layout_title.*

/**
 * 加入我们
 */
class JoinActivity : BaseActivity() {
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_join

    override fun setActivityTitle() {
    }

    override fun initActivityData() {
        titleLeft.setImageResource(R.mipmap.ic_left)
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
    }
}