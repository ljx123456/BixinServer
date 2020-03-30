package com.ycwl.servebixin.cn.ui.withdrawal.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.withdrawal.adapter.IncomeRecordDetailsListAdapter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeRecordDetailsBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.IncomeRecordDetailsBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.IncomeRecordDetailsPresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.IncomeRecordDetailsView
import kotlinx.android.synthetic.main.activity_income_record_details.*
import kotlinx.android.synthetic.main.layout_title.*

class IncomeRecordDetailsActivity :BaseActivity(),IncomeRecordDetailsView{

    private val presenter by lazy { IncomeRecordDetailsPresenter(this,this,this) }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_income_record_details

    override fun setActivityTitle() {
        titleText.text="收益详情"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        presenter.getIncomeRecordDetails(IncomeRecordDetailsBody(intent.getIntExtra("id",0)))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
    }

    override fun getIncomeRecordDetailsRequest(data: IncomeRecordDetailsBean) {
        incomeRecordID.text=data.data.orderNo
        when(data.data.recodeRole){
            2 -> incomeRecordRole.text="约玩达人"
            3 -> incomeRecordRole.text="达人经纪人"
        }
//        进账类型：0 服务费，1 酒水提成，2 包房主人，5 达人接单分成，7 退单收益，8 包房归属人奖励， 9 达人接单奖励，3、4、6、8、9为其他
        //       profitType: 0 服务费，1 酒水提成，2 包房主人，3 推荐达人，4 服务赔偿，5 经纪人奖励， 6 推荐用户
        when(data.data.profitType){
            0-> incomeRecordType.text="服务分成"
            1-> incomeRecordType.text="酒水分成"
            2-> incomeRecordType.text="酒水分成"
            5-> incomeRecordType.text="达人接单分成"
            7-> incomeRecordType.text="退单"
            else ->incomeRecordType.text="其他"
        }

        if (data.data.profitType==3||data.data.profitType==6){
            layout_incomeRecordKTV.visibility=View.GONE
            layout_incomeRecordRoom.visibility=View.GONE
            layout_incomeRecordAddress.visibility=View.GONE
        }else{
            layout_incomeRecordKTV.visibility=View.VISIBLE
            layout_incomeRecordRoom.visibility=View.VISIBLE
            layout_incomeRecordAddress.visibility=View.VISIBLE
            incomeRecordKTV.text=data.data.businessName
            incomeRecordRoom.text=data.data.boxTypeName+" "+data.data.boxName
            incomeRecordAddress.text=data.data.businessAddress
        }
        incomeRecordTime.text=data.data.createTime
        incomeRecordTips.text=data.data.recodeDescribe

        if (data.data.profitType==1||data.data.profitType==2){
            layout_incomeRecordDrink.visibility=View.VISIBLE
            incomeRecordDrink.text="${data.data.wineCountPrice}元"
        }else{
            layout_incomeRecordDrink.visibility=View.GONE
        }
        incomeRecordMine.text="${data.data.recodePrice}元"

        if (data.data.profitType==3||data.data.profitType==5||data.data.profitType==8){
            layout_incomeRecordList.visibility=View.VISIBLE
            if (data.data.profitType==8){
                incomeRecordListTitle.text = "达人接单列表"
            }else {
                incomeRecordListTitle.text = "达人列表"
            }
            if (data.data.users!=null&&data.data.users.size>0) {
                var adapter = IncomeRecordDetailsListAdapter(data.data.users)
                var manager = LinearLayoutManager(mContext)
                manager.orientation = LinearLayout.VERTICAL
                incomeRecordList.layoutManager = manager
                incomeRecordList.adapter = adapter
            }
        }else if (data.data.profitType==6){
            layout_incomeRecordList.visibility=View.VISIBLE
            incomeRecordListTitle.text="推荐列表"
            if (data.data.users!=null&&data.data.users.size>0) {
                var adapter = IncomeRecordDetailsListAdapter(data.data.users)
                var manager = LinearLayoutManager(mContext)
                manager.orientation = LinearLayout.VERTICAL
                incomeRecordList.layoutManager = manager
                incomeRecordList.adapter = adapter
            }
        }else{
            layout_incomeRecordList.visibility=View.GONE
        }


    }

}