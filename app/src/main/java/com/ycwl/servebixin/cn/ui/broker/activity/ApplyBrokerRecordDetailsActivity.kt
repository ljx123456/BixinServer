package com.ycwl.servebixin.cn.ui.broker.activity

import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerRecordDetailsBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.ApplyBrokerRecordDetailsBody
import com.ycwl.servebixin.cn.ui.broker.mvp.presenter.ApplyBrokerRecordDetailsPresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.view.ApplyBrokerRecordDetailsView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_apply_broker_record_details.*
import kotlinx.android.synthetic.main.layout_title.*

class ApplyBrokerRecordDetailsActivity:BaseActivity(),ApplyBrokerRecordDetailsView{

    private val presenter by lazy { ApplyBrokerRecordDetailsPresenter(this,this,this) }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_apply_broker_record_details

    override fun setActivityTitle() {
        titleText.text="经纪人审核"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        apply_details_view2.visibility=View.GONE
        apply_details_time3.visibility=View.GONE
        apply_details_img3.visibility=View.GONE
        apply_details_content3.visibility=View.GONE
        apply_details_des3.visibility=View.GONE
        btnApplyAgain.visibility=View.GONE
        presenter.getApplyBrokerRecordDetails(ApplyBrokerRecordDetailsBody(intent.getIntExtra("id",0)))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(btnApplyAgain).subscribe {
            intentUtils.intentApplyBrokerUpload()
//            finish()
        }
    }

    override fun getApplyBrokerRecordDetailsRequest(data: ApplyBrokerRecordDetailsBean) {
        var split1=data.data.createTime.split(" ")
        if (split1.size==2){
            apply_details_time1.text=split1[0]+"\n"+split1[1]
        }
        var split2=data.data.createTime.split(" ")
        if (split2.size==2){
            apply_details_time2.text=split2[0]+"\n"+split2[1]
        }
        when (data.data.auditState){
           0 ->{
               apply_details_view2.visibility=View.VISIBLE
               apply_details_time3.visibility=View.VISIBLE
               apply_details_img3.visibility=View.VISIBLE
               apply_details_content3.visibility=View.VISIBLE
               apply_details_des3.visibility=View.VISIBLE
               btnApplyAgain.visibility=View.VISIBLE
                var split=data.data.updateTime.split(" ")
               if (split.size==2){
                   apply_details_time3.text=split[0]+"\n"+split[1]
               }
               apply_details_des3.text=data.data.description
           }
            2 ->{
                apply_details_view2.visibility=View.GONE
                apply_details_time3.visibility=View.GONE
                apply_details_img3.visibility=View.GONE
                apply_details_content3.visibility=View.GONE
                apply_details_des3.visibility=View.GONE
            }
            1->{
                apply_details_view2.visibility= View.VISIBLE
                apply_details_time3.visibility= View.VISIBLE
                apply_details_img3.visibility= View.VISIBLE
                apply_details_content3.visibility= View.VISIBLE
                apply_details_des3.visibility= View.GONE
                btnApplyAgain.visibility= View.GONE
                apply_details_img3.setImageResource(R.mipmap.button_check_green)
                apply_details_content3.text="审核通过"
                var split=data.data.updateTime.split(" ")
                if (split.size==2){
                    apply_details_time3.text=split[0]+"\n"+split[1]
                }
            }
        }
    }
}