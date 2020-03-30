package com.ycwl.servebixin.cn.ui.broker.activity

import android.content.DialogInterface
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Switch
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.broker.adapter.BrokerKTVAdapter
import com.ycwl.servebixin.cn.ui.broker.adapter.BrokerKTVApplyAdapter
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVBean
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVDelBean
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVOpenBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerKTVDelBody
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerKTVOpenBody
import com.ycwl.servebixin.cn.ui.broker.mvp.presenter.BrokerKTVPresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.view.BrokerKTVView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_broker_ktv.*
import kotlinx.android.synthetic.main.layout_title.*

class BrokerKTVActivity : BaseActivity(),BrokerKTVView{

    private val presenter by lazy { BrokerKTVPresenter(this,this,this) }
    private var ktvApplys=ArrayList<BrokerKTVBean.DataBean>()
    private var ktvs=ArrayList<BrokerKTVBean.DataBean>()
    private lateinit var adapterKTV:BrokerKTVAdapter
    private lateinit var adapterKTVApply:BrokerKTVApplyAdapter
    private lateinit var switch :Switch
    private var isOpen=false
    private var position=0

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_broker_ktv

    override fun setActivityTitle() {
        titleText.text="我的约玩场地"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRight.visibility=View.GONE
        titleRight.setImageResource(R.mipmap.add_s)
    }

    override fun initActivityData() {
        layout_broker_ktv_have.visibility=View.GONE
        layout_broker_ktv_none.visibility=View.GONE
        titleRight.visibility=View.GONE

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(titleRight).subscribe {
            intentUtils.intentBrokerSearchKTV()
        }
        Click.viewClick(tv_broker_add_ktv).subscribe {
            intentUtils.intentBrokerSearchKTV()
        }
    }

    override fun getBrokerKTVRequest(data: BrokerKTVBean) {
        ktvs.clear()
        ktvApplys.clear()
        if (data.data.size>0){
            layout_broker_ktv_have.visibility=View.VISIBLE
            layout_broker_ktv_none.visibility=View.GONE
            titleRight.visibility=View.VISIBLE
            data.data.forEach {
                if (it.auditState==2){
                    ktvs.add(it)
                }else{
                    ktvApplys.add(it)
                }
            }
            if (ktvs.size>0) {
                adapterKTV = BrokerKTVAdapter(ktvs)
                var manager=LinearLayoutManager(this)
                manager.orientation=LinearLayout.VERTICAL
                recy_broker_ktv.layoutManager=manager
                recy_broker_ktv.adapter=adapterKTV
                adapterKTV.setOnItemChildClickListener { adapter, view, position ->
                    when(view.id){
                        R.id.brokerKTVSwitch ->{
                            switch=(view.parent as RelativeLayout).findViewById(R.id.brokerKTVSwitch)
                            if (switch.isChecked){
                                isOpen=true
                                presenter.getBrokerKTVOpen(BrokerKTVOpenBody(adapterKTV.data[position].fieldId.toString(),"1"))
                            }else{
                                isOpen=false
                                presenter.getBrokerKTVOpen(BrokerKTVOpenBody(adapterKTV.data[position].fieldId.toString(),"0"))
                            }
                        }
                        R.id.brokerKTVDel ->{

                            ShowDialog.showCustomDialogNoTitle(this,"是否确认删除该KTV？ 再次添加需要重新审核","确认", "取消",object : DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface, which: Int) {
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            dialog.dismiss()
                                            this@BrokerKTVActivity.position=position
                                            presenter.getBrokerKTVDel(BrokerKTVDelBody(adapterKTV.data[position].fieldId.toString()))
                                        }
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                            dialog.dismiss()
                                        }
                                    }
                                }
                            })

                        }
                    }
                }
            }

            if (ktvApplys.size>0){
                recy_broker_ktv_apply.visibility=View.VISIBLE
                adapterKTVApply= BrokerKTVApplyAdapter((ktvApplys))
                var manager=LinearLayoutManager(this)
                manager.orientation=LinearLayout.VERTICAL
                recy_broker_ktv_apply.layoutManager=manager
                recy_broker_ktv_apply.adapter=adapterKTVApply
            }else{
                recy_broker_ktv_apply.visibility=View.GONE
            }


        }else{
            titleRight.visibility=View.GONE
            layout_broker_ktv_have.visibility=View.GONE
            layout_broker_ktv_none.visibility=View.VISIBLE
        }
    }

    override fun getBrokerKTVError() {

    }

    override fun getBrokerKTVOpenRequest(data: BrokerKTVOpenBean) {
        switch.isChecked=isOpen
    }

    override fun getBrokerKTVOpenError() {
        switch.isChecked=!isOpen
    }

    override fun getBrokerKTVDelRequest(data: BrokerKTVDelBean) {
        adapterKTV.remove(position)
//        adapterKTV.notifyDataSetChanged()
    }

    override fun getBrokerKTVDelError() {

    }

    override fun onResume() {
        super.onResume()
        presenter.getBrokerKTV()
    }
}