package com.ycwl.servebixin.cn.ui.order.fragment

import android.annotation.SuppressLint
import android.renderscript.Short3
import android.support.v4.app.Fragment
import com.blankj.utilcode.util.LogUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseFragment
import com.ycwl.servebixin.cn.ui.login.adapter.FragmentAdapter
import kotlinx.android.synthetic.main.fragment_order_form.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@SuppressLint("ValidFragment")
class OrderFormFragment(val type: String): BaseFragment(){
    var mFragmentAdapter: FragmentAdapter? = null
    var fragments = ArrayList<Fragment>()
    override fun openEventBus(): Boolean = true

    override fun setLayoutTitle() {

    }

    override fun initFragmentData() {
        var titles = ArrayList<String>()
        titles.add("全部")
        titles.add("待确认")
        titles.add("进行中")
        titles.add("已完成")



        titles.forEach {
            orderFormTab.addTab(orderFormTab.newTab().setText(it))
            fragments.add(OrderFormListFragment(type,it))

        }
        mFragmentAdapter = FragmentAdapter(childFragmentManager, fragments, titles)
        orderFormPager.adapter = mFragmentAdapter
        orderFormTab.setupWithViewPager(orderFormPager)
        orderFormTab.setTabsFromPagerAdapter(mFragmentAdapter)
        orderFormPager.setOffscreenPageLimit(0)
    }

    override fun setFragmentListener() {

    }

    override fun layoutID(): Int =R.layout.fragment_order_form

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public fun getEvent(s:String){
        LogUtils.a("消息:"+s)
        (fragments[orderFormPager.currentItem] as OrderFormListFragment).init()
    }
}