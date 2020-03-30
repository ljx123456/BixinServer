package com.ycwl.servebixin.cn.ui.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseFragment
import com.ycwl.servebixin.cn.ui.login.adapter.FragmentAdapter
import com.ycwl.servebixin.cn.ui.order.fragment.OrderFormFragment
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : BaseFragment(){
    var mFragmentAdapter: FragmentAdapter? = null
    override fun openEventBus(): Boolean = false

    override fun setLayoutTitle() {

    }

    override fun initFragmentData() {
        var titles = ArrayList<String>()
        titles.add("订单")
        titles.add("抢单")

        var fragments = ArrayList<Fragment>()
        titles.forEach {
            orderTab.addTab(orderTab.newTab().setText(it))
            fragments.add(OrderFormFragment(it))
        }

        mFragmentAdapter = FragmentAdapter(activity!!.getSupportFragmentManager(), fragments, titles)
        orderPager.adapter = mFragmentAdapter
        orderTab.setupWithViewPager(orderPager)
        orderTab.setTabsFromPagerAdapter(mFragmentAdapter)
        orderPager.setOffscreenPageLimit(0)
    }

    override fun setFragmentListener() {

    }

    override fun layoutID(): Int = R.layout.fragment_order

//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
//        if (!hidden){
//            var titles = ArrayList<String>()
//            titles.add("订单")
//            titles.add("抢单")
//
//            var fragments = ArrayList<Fragment>()
//            titles.forEach {
//                orderTab.addTab(orderTab.newTab().setText(it))
//                fragments.add(OrderFormFragment(it))
//            }
//            var fragmentManager=activity!!.getSupportFragmentManager()
//            var fragmentTransaction=fragmentManager.beginTransaction()
//
//            mFragmentAdapter = FragmentAdapter(fragmentManager, fragments, titles)
//            orderPager.adapter = mFragmentAdapter
//            orderTab.setupWithViewPager(orderPager)
//            orderTab.setTabsFromPagerAdapter(mFragmentAdapter)
//            orderPager.setOffscreenPageLimit(0)
//        }
//    }


}