package com.ycwl.servebixin.cn.ui.withdrawal.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.qqtheme.framework.picker.DatePicker
import cn.yoyo.com.utils.utils.Click
import com.chad.library.adapter.base.BaseQuickAdapter
import com.pp.wsy.bosom.app.utils.pickers.pickerUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.ui.withdrawal.adapter.IncomeRecordAdapter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeRecordListBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.NewIncomeRecordListBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.IncomeRecordListBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.IncomeRecordPresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.IncomeRecordView
import kotlinx.android.synthetic.main.activity_income_record.*
import kotlinx.android.synthetic.main.layout_error_network.*
import kotlinx.android.synthetic.main.layout_none_data.*
import kotlinx.android.synthetic.main.layout_title.*
import java.text.SimpleDateFormat
import java.util.*

class IncomeRecordActivity :BaseActivity(),IncomeRecordView{


    private val presenter by lazy { IncomeRecordPresenter(this,this,this) }
    private var spd= SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

    private var records=ArrayList<NewIncomeRecordListBean>()
    private var map=HashMap<String,ArrayList<IncomeRecordListBean.DataBean>>()
    private var list:ArrayList<IncomeRecordListBean.DataBean>?=null
    private var strs=ArrayList<String>()
    private var adapter: IncomeRecordAdapter?=null
    private var isfresh=false

    private var pageIndex = 1
    private val pageSize = 10
    private var profitType=""
    private var recodeRole=""
    private var time=""

    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int = R.layout.activity_income_record

    override fun setActivityTitle() {
        titleText.text="收益明细"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        popView = LayoutInflater.from(mContext).inflate(R.layout.pop_income_record, null)
        time=spd.format(Date())
        pop = PopupWindowHelper(popView)
        if (months>=10){
            tv_record_month.text="${years}-${months}"
        }else{
            tv_record_month.text="${years}-0${months}"
        }

        presenter.getIncomeRecord(IncomeRecordListBody(time,profitType,recodeRole,pageIndex.toString(),pageSize.toString()))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }

        Click.viewClick(tv_income_record_title1).subscribe {
            pop.showAsDropDown(layout_income_title)
        }
        Click.viewClick(tv_income_record_title2).subscribe {
            pop.showAsDropDown(layout_income_title)
        }

        Click.viewClick(tv_income_record_title3).subscribe {
            pop.showAsDropDown(layout_income_title)
        }
        swip_income_record.setOnRefreshListener {
            isfresh=true
            pageIndex = 1
            presenter.getIncomeRecord(IncomeRecordListBody(time,profitType,recodeRole,pageIndex.toString(),pageSize.toString()))
        }

        Click.viewClick(popView.findViewById(R.id.pop_income_record_bg)).subscribe { pop.dismiss() }

        Click.viewClick(popView.findViewById(R.id.pop_income_record_title)).subscribe {
            popView.findViewById<TextView>(R.id.pop_income_record_title).setTextColor(Color.parseColor("#535252"))
            popView.findViewById<TextView>(R.id.pop_income_record_type1).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_type2).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_type3).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_role1).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_role2).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_role3).setTextColor(Color.parseColor("#8B8B8B"))
            profitType=""
            recodeRole=""
        }

        Click.viewClick(popView.findViewById(R.id.pop_income_record_type1)).subscribe {
            popView.findViewById<TextView>(R.id.pop_income_record_title).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_type1).setTextColor(Color.parseColor("#535252"))
            popView.findViewById<TextView>(R.id.pop_income_record_type2).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_type3).setTextColor(Color.parseColor("#8B8B8B"))
            profitType="1"
        }

        Click.viewClick(popView.findViewById(R.id.pop_income_record_type2)).subscribe {
            popView.findViewById<TextView>(R.id.pop_income_record_title).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_type2).setTextColor(Color.parseColor("#535252"))
            popView.findViewById<TextView>(R.id.pop_income_record_type1).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_type3).setTextColor(Color.parseColor("#8B8B8B"))
            profitType="2"
        }

        Click.viewClick(popView.findViewById(R.id.pop_income_record_type3)).subscribe {
            popView.findViewById<TextView>(R.id.pop_income_record_title).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_type3).setTextColor(Color.parseColor("#535252"))
            popView.findViewById<TextView>(R.id.pop_income_record_type2).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_type1).setTextColor(Color.parseColor("#8B8B8B"))
            profitType=""
        }

        Click.viewClick(popView.findViewById(R.id.pop_income_record_role1)).subscribe {
            popView.findViewById<TextView>(R.id.pop_income_record_title).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_role1).setTextColor(Color.parseColor("#535252"))
            popView.findViewById<TextView>(R.id.pop_income_record_role2).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_role3).setTextColor(Color.parseColor("#8B8B8B"))
            recodeRole="3"
        }

        Click.viewClick(popView.findViewById(R.id.pop_income_record_role2)).subscribe {
            popView.findViewById<TextView>(R.id.pop_income_record_title).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_role2).setTextColor(Color.parseColor("#535252"))
            popView.findViewById<TextView>(R.id.pop_income_record_role1).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_role3).setTextColor(Color.parseColor("#8B8B8B"))
            recodeRole="2"
        }

        Click.viewClick(popView.findViewById(R.id.pop_income_record_role3)).subscribe {
            popView.findViewById<TextView>(R.id.pop_income_record_title).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_role3).setTextColor(Color.parseColor("#535252"))
            popView.findViewById<TextView>(R.id.pop_income_record_role2).setTextColor(Color.parseColor("#8B8B8B"))
            popView.findViewById<TextView>(R.id.pop_income_record_role1).setTextColor(Color.parseColor("#8B8B8B"))
            recodeRole=""
        }

        Click.viewClick(popView.findViewById(R.id.pop_income_record_sure)).subscribe {
           when(profitType){
               ""  -> tv_income_record_title2.text="全部分类"
               "1" -> tv_income_record_title2.text="服务收益"
               "2" -> tv_income_record_title2.text="酒水收益"
           }
            when(recodeRole){
                ""  -> tv_income_record_title3.text="全部角色"
                "3" -> tv_income_record_title3.text="达人经纪人"
                "2" -> tv_income_record_title3.text="约玩达人"
            }
            isfresh=true
            pop.dismiss()
            pageIndex=1
            presenter.getIncomeRecord(IncomeRecordListBody(time,profitType,recodeRole,pageIndex.toString(),pageSize.toString()))
        }


    }

    override fun getIncomeRecordRequest(data: IncomeRecordListBean) {
        swip_income_record.isRefreshing = false
//        errorLayout.visibility=View.GONE
        if (isfresh){
            strs.clear()
            isfresh=false
        }

        if (data!=null&&data.data!=null&&data.data.size>0){
//            strs.clear()
            swip_income_record.visibility=View.VISIBLE
            noDataLayout.visibility=View.GONE
            tv_record_month.visibility=View.GONE
            records.clear()
            data.data.forEach {
                var month=it.createTime.substring(0,7)
                if (strs!=null&&strs.size>0){
                    if (!strs.contains(month)){
                        map.put(strs[strs.size-1],list!!)
                        list=ArrayList<IncomeRecordListBean.DataBean>()
                        strs.add(month)
                        list!!.add(it)
                    }else{
                        list!!.add(it)
                    }
                }else{
                    list=ArrayList<IncomeRecordListBean.DataBean>()
                    strs.add(month)
                    list!!.add(it)
                }
            }

            map.put(strs[strs.size-1],list!!)

            strs.forEach {
                records.add(NewIncomeRecordListBean(it,map.get(it)!!))
            }

            if (pageIndex==1){
                adapter= IncomeRecordAdapter(records)
                var manager = LinearLayoutManager(mContext)
                manager.orientation = LinearLayout.VERTICAL
                recy_income_record.layoutManager=manager
                recy_income_record.adapter=adapter
                adapter!!.notifyDataSetChanged()
            }else{
                if (adapter!!.data.containsAll(records)){

                }else {
                    adapter!!.addData(records)
                    adapter!!.notifyDataSetChanged()
                }
            }

            adapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener{
                override fun onLoadMoreRequested() {
                    pageIndex=pageIndex+1
                    presenter.getIncomeRecord(IncomeRecordListBody(time,profitType,recodeRole,pageIndex.toString(),pageSize.toString()))

                }

            },recy_income_record)
            adapter!!.setOnItemChildClickListener { adapter, view, position ->
                getTime()
            }

            if (adapter != null) {
//                adapter!!.disableLoadMoreIfNotFullPage()
                adapter!!.setPreLoadNumber(1)
            }
            if (data.data.size<pageSize){
                adapter!!.loadMoreEnd()
            }else{
                adapter!!.loadMoreComplete()
            }
        }else{
            if (pageIndex>1){
                adapter!!.loadMoreEnd()
            }else {
                swip_income_record.visibility=View.GONE
                noDataLayout.visibility=View.VISIBLE
                tv_record_month.visibility=View.VISIBLE
                Click.viewClick(tv_record_month).subscribe { getTime() }
            }

        }

    }

    override fun getIncomeRecordError() {
        swip_income_record.isRefreshing=false
//        errorLayout.visibility=View.VISIBLE
//        Click.viewClick(errorLayout).subscribe {
//            presenter.getIncomeRecord(IncomeRecordListBody(time,profitType,recodeRole,pageIndex.toString(),pageSize.toString()))
//        }
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
            tv_record_month.text="$year-$month"
            isfresh=true
            pageIndex=1
            presenter.getIncomeRecord(IncomeRecordListBody(time,profitType,recodeRole,pageIndex.toString(),pageSize.toString()))
        })
        picker.show()

    }

}