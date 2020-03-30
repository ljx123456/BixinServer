package com.ycwl.servebixin.cn.ui.withdrawal.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import cn.qqtheme.framework.picker.DatePicker
import cn.yoyo.com.utils.utils.Click
import com.chad.library.adapter.base.BaseQuickAdapter
import com.pp.wsy.bosom.app.utils.pickers.pickerUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.ui.withdrawal.adapter.WithdrawRecordAdapter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.NewWithdrawRecordListBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawRecordListBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.IncomeRecordListBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawRecordListBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.WithdrawRecordPresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawRecordView
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_withdraw_record.*
import kotlinx.android.synthetic.main.layout_error_network.*
import kotlinx.android.synthetic.main.layout_none_data.*
import kotlinx.android.synthetic.main.layout_title.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class WithdrawRecordActivity :BaseActivity(),WithdrawRecordView{
    private val presenter by lazy { WithdrawRecordPresenter(this,this,this) }
    private var spd=SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    private var records=ArrayList<NewWithdrawRecordListBean>()
    private var map=HashMap<String,ArrayList<WithdrawRecordListBean.DataBean>>()
    private var list:ArrayList<WithdrawRecordListBean.DataBean>?=null
    private var strs=ArrayList<String>()
    private var time=""

    private var pageIndex = 1
    private val pageSize = 10
    private var adapter:WithdrawRecordAdapter?=null
    private var isfresh=false


    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_withdraw_record

    override fun setActivityTitle() {
        titleText.text="提现记录"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        time=spd.format(Date())
        presenter.getWithdrawRecord(WithdrawRecordListBody(time,pageIndex,pageSize))
    }

    override fun clickListener() {

        Click.viewClick(titleLeft).subscribe { finish() }
        swip_withdraw_record.setOnRefreshListener {
            isfresh=true
            pageIndex = 1
            presenter.getWithdrawRecord(WithdrawRecordListBody(time,pageIndex,pageSize))
        }
    }

    override fun getWithdrawRecordRequest(data: WithdrawRecordListBean) {
        swip_withdraw_record.isRefreshing = false
//        errorLayout.visibility=View.GONE
        if (isfresh){
            strs.clear()
            isfresh=false
        }
        if (data!=null&&data.data!=null&&data.data.size>0){
            swip_withdraw_record.visibility=View.VISIBLE
            noDataLayout.visibility=View.GONE
//            strs.clear()
            records.clear()
            data.data.forEach {
                var month=it.applyTime.substring(0,7)
                if (strs!=null&&strs.size>0){
                    if (!strs.contains(month)){
                        map.put(strs[strs.size-1],list!!)
                        list=ArrayList<WithdrawRecordListBean.DataBean>()
                        strs.add(month)
                        list!!.add(it)
                    }else{
                        list!!.add(it)
                    }
                }else{
                    list=ArrayList<WithdrawRecordListBean.DataBean>()
                    strs.add(month)
                    list!!.add(it)
                }
            }

            map.put(strs[strs.size-1],list!!)

            strs.forEach {
                records.add(NewWithdrawRecordListBean(it,map.get(it)!!))
            }

            if (pageIndex==1){
                adapter= WithdrawRecordAdapter(records)
                var manager = LinearLayoutManager(mContext)
                manager.orientation = LinearLayout.VERTICAL
                recy_withdraw_record.layoutManager=manager
                recy_withdraw_record.adapter=adapter
                adapter!!.notifyDataSetChanged()
            }else{
                if (adapter!!.data.containsAll(records)){

                }else {
                    adapter!!.addData(records)
                    adapter!!.notifyDataSetChanged()
                }
            }
            adapter!!.setOnItemChildClickListener { adapter, view, position ->
                getTime()
            }

            adapter!!.setOnLoadMoreListener(object :BaseQuickAdapter.RequestLoadMoreListener{
                override fun onLoadMoreRequested() {
                    pageIndex=pageIndex+1
                    presenter.getWithdrawRecord(WithdrawRecordListBody(time,pageIndex,pageSize))

                }

            },recy_withdraw_record)

            if (adapter != null) {
                adapter!!.disableLoadMoreIfNotFullPage()
                adapter!!.setPreLoadNumber(1)
            }
            if (data.data.size<pageSize){
                adapter!!.loadMoreEnd()
            }
        }else{
            if (adapter!=null) {
                adapter!!.loadMoreEnd()
                adapter!!.notifyDataSetChanged()
            }else{
                swip_withdraw_record.visibility=View.GONE
                noDataLayout.visibility=View.VISIBLE
            }
        }

    }

    override fun getWithdrawRecordError() {
        swip_withdraw_record.isRefreshing=false
//        errorLayout.visibility=View.VISIBLE
    }

    /**
     * 设置生日
     */
    val c = Calendar.getInstance()//可以对每个时间域单独修改
    var years = c.get(Calendar.YEAR)
    var months = c.get(Calendar.MONTH) + 1
    var dates = c.get(Calendar.DATE)
    fun getTime() {
        var picker = DatePicker(this,1)
        pickerUtils.showMonthPicker(picker)
        picker.setSelectedItem(years, months)
        picker.setBackgroundColor(Color.parseColor("#FAFAFA"))
        picker.setTopBackgroundColor(Color.parseColor("#ffffff"))
        picker.setLabelTextColor(Color.parseColor("#999999"))
        picker.setTitleText("")
        picker.setTitleTextColor(Color.parseColor("#333333"))
        picker.setTitleTextSize(16)
        picker.setOnDatePickListener(cn.qqtheme.framework.picker.DatePicker.OnYearMonthPickListener { year, month ->
            years = year.toInt()
            months = month.toInt()
            time="$year-$month-01"+" 00:00:00"
            isfresh=true
            pageIndex=1
            presenter.getWithdrawRecord(WithdrawRecordListBody(time,pageIndex,pageSize))
        })
        picker.show()

    }

}