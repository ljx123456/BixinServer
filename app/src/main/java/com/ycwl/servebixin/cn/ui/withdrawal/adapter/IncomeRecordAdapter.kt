package com.ycwl.servebixin.cn.ui.withdrawal.adapter

import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.NewIncomeRecordListBean
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.view.MyRecyclerView

class IncomeRecordAdapter(records:MutableList<NewIncomeRecordListBean>):BaseQuickAdapter<NewIncomeRecordListBean,BaseViewHolder>(R.layout.item_withdraw_record,records){
    override fun convert(helper: BaseViewHolder, item: NewIncomeRecordListBean) {
        helper.setText(R.id.tv_withdraw_record_month,item.month)
                .addOnClickListener(R.id.tv_withdraw_record_month)
        var itemAdapter=IncomeRecordDetailsAdapter(item.list)
        val list=helper.getView<MyRecyclerView>(R.id.recy_withdraw_record_details)
        var manager= LinearLayoutManager(mContext)
        manager.orientation=LinearLayout.VERTICAL
        list.layoutManager=manager
        list.adapter=itemAdapter
        itemAdapter.setOnItemClickListener { adapter, view, position ->
            intentUtils.intentIncomeRecordDetails(itemAdapter.data[position].walletRecodeId)
        }
    }

}