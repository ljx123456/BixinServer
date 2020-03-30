package com.ycwl.servebixin.cn.ui.main.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import cn.yoyo.com.utils.utils.Click
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.main.adapter.FansAdapter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.FansBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.FansBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.FansPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.FansView
import com.ycwl.servebixin.cn.utils.layoutUtils
import kotlinx.android.synthetic.main.activity_fans.*
import kotlinx.android.synthetic.main.layout_error_network.*
import kotlinx.android.synthetic.main.layout_title.*

class FansActivity:BaseActivity(),FansView{

    private val presenter by lazy { FansPresenter(this,this,this) }
    private var pageIndex=1
    private var pageSize="10"
    private var adapter:FansAdapter?=null

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int =R.layout.activity_fans

    override fun setActivityTitle() {
        titleText.text="我的粉丝"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        showLoading()
        pageIndex=1
        presenter.getFans(FansBody(pageIndex,pageSize))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe {
            finish()
        }
    }

    override fun getFansRequest(data: FansBean) {
        try {
            dismissLoading()
        }catch (e:Exception){
            e.printStackTrace()
        }
        errorLayout.visibility=View.GONE
        fansList.visibility=View.VISIBLE
        if (data.data!=null){
            if(pageIndex==1) {
                if (adapter != null) {
                    adapter!!.setNewData(data.data)
                } else {
                    adapter = FansAdapter(data.data)
                    var manager = LinearLayoutManager(this)
                    manager.orientation = LinearLayout.VERTICAL
                    fansList.layoutManager = manager
                    fansList.adapter = adapter
                    adapter!!.setEmptyView(layoutUtils.getNoneFans())
                }
            }else{
                adapter!!.addData(data.data)
            }
            if (adapter != null) {
//            serveListAdapter!!.disableLoadMoreIfNotFullPage()
                adapter!!.setPreLoadNumber(2)
            }
            adapter!!.setOnLoadMoreListener(object :BaseQuickAdapter.RequestLoadMoreListener{
                override fun onLoadMoreRequested() {
                    pageIndex=pageIndex+1
                    presenter.getFans(FansBody(pageIndex,pageSize))
                }
            },fansList)

            if (data.data.size<10){
                if (pageIndex>1||data.data.size>0){
                    adapter!!.loadMoreEnd()
//                notiAdapter.addFooterView(layoutUtils.getNoMore())
                }else{
                    adapter!!.loadMoreComplete()
                }
            }else{
                adapter!!.loadMoreComplete()
            }
        }
    }

    override fun getFansError() {
        try {
            dismissLoading()
        }catch (e:Exception){
            e.printStackTrace()
        }

        errorLayout.visibility=View.VISIBLE
        fansList.visibility=View.GONE
        Click.viewClick(errorLayout).subscribe {
            showLoading()
            presenter.getFans(FansBody(pageIndex,pageSize))
        }
    }
}