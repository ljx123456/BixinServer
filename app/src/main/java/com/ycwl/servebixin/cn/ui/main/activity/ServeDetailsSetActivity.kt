package com.ycwl.servebixin.cn.ui.main.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.adapter.ServeDetailsKTVAdapter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeDetailsBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.OpenAllBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeDetailsBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.ServeDetailsPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeDetailsView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_serve_details_set.*
import kotlinx.android.synthetic.main.layout_title.*

class ServeDetailsSetActivity:BaseActivity(),ServeDetailsView{
    override fun getOpenAllRequest() {

    }


    override fun getopenKTVRequest() {

    }

    override fun delKTVRequest() {

    }

    override fun getServeDetailsRequest(data: ServeDetailsBean) {
        if (data.data.userSkillPrice<=0){
            tv_serve_detail_set_price.text="未设置"
            tv_serve_detail_set_price.setTextColor(Color.parseColor("#ff8b8b8b"))
        }else{
            tv_serve_detail_set_price.text=data.data.userSkillPrice.toString()+"元"
            tv_serve_detail_set_price.setTextColor(Color.parseColor("#535252"))
        }
        if (data.data.leader==null||data.data.leader.nickname==null){
            tv_serve_detail_set_leader.text="未设置"
            tv_serve_detail_set_leader.setTextColor(Color.parseColor("#ff8b8b8b"))
        }else{
            tv_serve_detail_set_leader.text=data.data.leader.nickname
            tv_serve_detail_set_leader.setTextColor(Color.parseColor("#535252"))
        }

        if (data.data.isOut==1){
            switchOpenAll.isChecked=false
        }else{
            switchOpenAll.isChecked=true
        }

        if (data.data.businesss==null||data.data.businesss.size==0){
            haveKTV.visibility=View.GONE
            addKTV.visibility=View.VISIBLE
            tv_editKTV.visibility=View.GONE
        }else{
            haveKTV.visibility=View.VISIBLE
            addKTV.visibility=View.GONE
            tv_editKTV.visibility=View.VISIBLE
            adapter= ServeDetailsKTVAdapter(this,presenter)
            adapter.updateList(data.data.businesss)
            var manager = LinearLayoutManager(mContext)
            manager.orientation = LinearLayout.VERTICAL
            recy_haveKTV.layoutManager=manager
            recy_haveKTV.adapter=adapter
        }
        Click.viewClick(layout_serve_detail_set_price).subscribe {
            intentUtils.intentServeSetPrice(data.data.skillPriceUp)
        }

        Click.viewClick(layout_serve_detail_set_leader).subscribe {
            if (data.data.leader==null||data.data.leader.nickname==null) {
                intentUtils.intentServeSetLeader()
            }else{
                intentUtils.intentServeMyLeader(data.data.leader.avatar,data.data.leader.nickname,data.data.leader.sex,data.data.leader.age.toString(),data.data.leader.constellation)
            }
        }

        Click.viewClick(addKTV).subscribe {
            intentUtils.intentServeSetKTV()
        }

        Click.viewClick(layout_add).subscribe {
            intentUtils.intentServeSetKTV()
        }

        Click.viewClick(tv_editKTV).subscribe {
            if (tv_editKTV.text.equals("编辑")){
                tv_editKTV.text="保存"
                tv_editKTV.setTextColor(Color.parseColor("#84CEFE"))
                adapter.edit(true)
                adapter.notifyDataSetChanged()
            }else{
                tv_editKTV.text="编辑"
                tv_editKTV.setTextColor(Color.parseColor("#535252"))
                adapter.edit(false)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun getServeDetailsError() {

    }

    private var userSkillId=-2
    private var skillTypeId=-2
    private val presenter by lazy { ServeDetailsPresenter(this,this,this) }
    private lateinit var adapter:ServeDetailsKTVAdapter

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int =R.layout.activity_serve_details_set

    override fun setActivityTitle() {
        titleText.text="服务设置"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        switchOpenAll.setOnClickListener {
            if (switchOpenAll.isChecked){
                presenter.openAll(OpenAllBody(userSkillId,skillTypeId,0))
            }else{
                presenter.openAll(OpenAllBody(userSkillId,skillTypeId,1))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        userSkillId=user.getUserSkillID().toInt()
        skillTypeId=user.getSkillTypeID().toInt()
        presenter.getServeDetails(ServeDetailsBody(userSkillId,skillTypeId))
    }
}