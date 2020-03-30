package com.ycwl.servebixin.cn.ui.withdrawal.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeRecordListBean

class IncomeRecordDetailsAdapter(records:MutableList<IncomeRecordListBean.DataBean>):BaseQuickAdapter<IncomeRecordListBean.DataBean,BaseViewHolder>(R.layout.item_income_record,records){
    override fun convert(helper: BaseViewHolder, item: IncomeRecordListBean.DataBean) {
        //        进账类型：0 服务费，1 酒水提成，2 包房主人，5 达人接单分成，7 退单收益，3、4、6为其他
//       profitType: 0 服务费，1 酒水提成，2 包房主人，3 推荐达人，4 服务赔偿，5 经纪人奖励， 6 推荐用户
        when(item.profitType){
            0 -> helper.setText(R.id.item_income_record_title,"服务分成")
            1->helper.setText(R.id.item_income_record_title,"酒水分成")
            2->helper.setText(R.id.item_income_record_title,"酒水分成")
            3->helper.setText(R.id.item_income_record_title,"其他")
            4->helper.setText(R.id.item_income_record_title,"其他")
            5->helper.setText(R.id.item_income_record_title,"达人接单分成")
            6->helper.setText(R.id.item_income_record_title,"其他")
            7->helper.setText(R.id.item_income_record_title,"其他")
        }
        if (item.profitType==1){
            helper.setVisible(R.id.item_income_record_drink,true)
                    .setText(R.id.item_income_record_drink,"酒水总额：${item.wineCountPrice}")
        }else{
            helper.setVisible(R.id.item_income_record_drink,false)
        }
        helper.setText(R.id.item_income_record_ktv,item.businessName)
                .setText(R.id.item_income_record_time,item.createTime)
                .setText(R.id.item_income_record_money,"+${item.recodePrice}")

        when(item.recodeRole){
            2->helper.setText(R.id.item_income_record_type,"约玩达人收益")
            3->helper.setText(R.id.item_income_record_type,"经纪人收益")
        }
    }

}