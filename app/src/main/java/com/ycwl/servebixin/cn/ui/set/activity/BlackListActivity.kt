package com.ycwl.servebixin.cn.ui.set.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.set.adapter.BlackListAdapter
import com.ycwl.servebixin.cn.ui.set.mvp.bean.BlackListBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.BlackListBody
import com.ycwl.servebixin.cn.ui.set.mvp.body.DelBlackBody
import com.ycwl.servebixin.cn.ui.set.mvp.presenter.BlackListPresenter
import com.ycwl.servebixin.cn.ui.set.mvp.view.BlackListView
import kotlinx.android.synthetic.main.activity_set_black.*
import kotlinx.android.synthetic.main.layout_error_network.*
import kotlinx.android.synthetic.main.layout_title.*

class BlackListActivity:BaseActivity(),BlackListView{

    private val presenter by lazy { BlackListPresenter(this,this,this) }
    private val lng = user.getlng()
    private val lat = user.getlat()
    private val pageSize = 10
    private var pageIndex = 1
    private var position=0
    private lateinit var blackListAdapter: BlackListAdapter

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int =R.layout.activity_set_black

    override fun setActivityTitle() {
        titleText.text="黑名单"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        presenter.getBlackList(BlackListBody(lng,lat,pageIndex,pageSize))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        black_list_refesh.setOnRefreshListener {
            pageIndex = 1
            presenter.getBlackList(BlackListBody(lng, lat, pageIndex, pageSize))
        }
    }

    override fun getBlackListRequest(data: BlackListBean) {
        errorLayout.visibility = View.GONE
        black_list_refesh.isRefreshing = false
        if (pageIndex == 1) {
            blackListAdapter = BlackListAdapter(data.data)
            var manager = LinearLayoutManager(mContext)
            manager.orientation = LinearLayoutManager.VERTICAL
            black_list.layoutManager = manager
            black_list.adapter = blackListAdapter
        } else {
            blackListAdapter!!.addData(data.data)
        }
        blackListAdapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                pageIndex = pageIndex + 1
                presenter.getBlackList(BlackListBody(lng, lat, pageIndex, pageSize))
            }
        }, black_list)
        if (blackListAdapter != null) {
            blackListAdapter!!.disableLoadMoreIfNotFullPage()
            blackListAdapter!!.setPreLoadNumber(2)
        }
        blackListAdapter.setOnItemChildClickListener { adapter, view, position ->
            this.position=position
            presenter.getDelBlack(DelBlackBody(blackListAdapter.data.get(position).userId))
        }
    }

    override fun getBlackListError() {
        black_list_refesh.isRefreshing = false
        errorLayout.visibility = View.VISIBLE
        Click.viewClick(errorLayout).subscribe {
            pageIndex = 1
            presenter.getBlackList(BlackListBody(lng, lat, pageIndex, pageSize))
        }
    }

    override fun getDelBlackRequest() {
        blackListAdapter!!.remove(position)
    }
}