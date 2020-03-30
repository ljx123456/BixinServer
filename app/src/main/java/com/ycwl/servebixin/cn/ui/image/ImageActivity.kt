package com.ycwl.servebixin.cn.ui.image

import android.graphics.Color
import android.support.v4.view.ViewPager
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.utils.utils.SystemUtils.activityFullScreen
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.layout_title.*


class ImageActivity : BaseActivity(), ViewPager.OnPageChangeListener {
    private var index = 0
    private var delete = false

    private lateinit var images: ArrayList<ImageBrowseInfo>
    private lateinit var adapter: ImageBrowseAdapter


    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_image

    override fun setActivityTitle() {
        activityFullScreen(this)
        getIntentVariable()
    }

    override fun initActivityData() {
        setImageBrowseAdapter()
        setTextViewIndex()
//        title_layout.setBackgroundColor(Color.parseColor("#000000"))
//        titleLeft.setImageResource(R.mipmap.nav_button_back_white_nor)
    }

    //接收传递的值
    private fun getIntentVariable() {
        index = intent.getIntExtra("index", 0)
        delete = intent.getBooleanExtra("delete", false)
        images = intent.getSerializableExtra("images") as ArrayList<ImageBrowseInfo>
    }

    //设置选中的文本内容
    private fun setTextViewIndex() {
        textIndex.text = (index + 1).toString() + "/" + images.size
    }


    //初始化适配器
    private fun setImageBrowseAdapter() {
        adapter = ImageBrowseAdapter(images)
        imagePager.adapter = adapter
        imagePager.currentItem = index
    }

    override fun clickListener() {
//        Click.viewClick(titleLeft).subscribe { finish() }
        imagePager.setOnPageChangeListener(this)

    }

    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }


    override fun onPageSelected(position: Int) {
        textIndex.text = (position + 1).toString() + "/" + images.size
    }
}