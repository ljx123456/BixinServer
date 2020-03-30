package com.ycwl.servebixin.cn.ui.broker.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.mylhyl.acp.Acp
import com.mylhyl.acp.AcpListener
import com.mylhyl.acp.AcpOptions
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.broker.adapter.BrokerServeAdapter
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerServeBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerServeBody
import com.ycwl.servebixin.cn.ui.broker.mvp.presenter.BrokerServePresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.view.BrokerServeView
import kotlinx.android.synthetic.main.activity_broker_serve.*
import kotlinx.android.synthetic.main.layout_none_data.*
import kotlinx.android.synthetic.main.layout_title.*

class BrokerServeActivity:BaseActivity(),BrokerServeView{

    private var pageIndex: Int = 1
    private var pageSize: Int = 10
    private lateinit var adapter:BrokerServeAdapter

    private val presenter by lazy { BrokerServePresenter(this,this,this) }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_broker_serve

    override fun setActivityTitle() {
        titleText.text="我的约玩达人"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        swip_broker_serve.isRefreshing=false
        presenter.getBrokerServe(BrokerServeBody(pageIndex.toString(),pageSize.toString()))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        swip_broker_serve.setOnRefreshListener {
            pageIndex = 1
            presenter.getBrokerServe(BrokerServeBody(pageIndex.toString(),pageSize.toString()))
        }
    }

    override fun getBrokerServeRequest(data: BrokerServeBean) {
        swip_broker_serve.isRefreshing=false
        if (data!=null&&data.data!=null&&data.data.size>0) {
            swip_broker_serve.visibility=View.VISIBLE
            noDataLayout.visibility=View.GONE
            if (pageIndex == 1) {
                adapter = BrokerServeAdapter(data.data)
                var manager = LinearLayoutManager(this)
                manager.orientation = LinearLayout.VERTICAL
                recy_broker_serve.layoutManager = manager
                recy_broker_serve.adapter = adapter
            }else{
                adapter.addData(data.data)
            }

            adapter.setOnItemChildClickListener { adapter, view, position ->
                CallPhone(this.adapter.data[position].phone)
            }

            adapter.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener{
                override fun onLoadMoreRequested() {
                    pageIndex=pageIndex+1
                    presenter.getBrokerServe(BrokerServeBody(pageIndex.toString(),pageSize.toString()))
                }
            },recy_broker_serve)

            if (data.data.size < pageSize) {
                adapter!!.loadMoreEnd()
            } else {
                adapter!!.loadMoreComplete()
            }
        }else{
            if (pageIndex>1){
                adapter!!.loadMoreEnd()
            }else{
                swip_broker_serve.visibility=View.GONE
                noDataLayout.visibility=View.VISIBLE
            }
        }
    }

     fun CallPhone(phoneNum: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Acp.getInstance(application).request(AcpOptions.Builder()
                    .setPermissions(Manifest.permission.CALL_PHONE)
                    .build(), object : AcpListener {
                override fun onGranted() {
                    val intent = Intent(Intent.ACTION_DIAL)
                    val data = Uri.parse("tel:$phoneNum")
                    intent.data = data
                    startActivity(intent)
                }

                override fun onDenied(permissions: List<String>) {
                    ToastUtils.showShort("获取系统权限失败，请手动开启")
                }
            })
        } else {
            val intent = Intent(Intent.ACTION_DIAL)
            val data = Uri.parse("tel:$phoneNum")
            intent.data = data
            startActivity(intent)
        }
    }
}