package com.ycwl.servebixin.cn.ui.main.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.adapter.OccupationAdapter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.OccupationBean
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.OccupationPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.OccupationView
import kotlinx.android.synthetic.main.activity_occupation.*
import kotlinx.android.synthetic.main.layout_title.*

class OccupationActivity:BaseActivity(),OccupationView{

    private val presenter by lazy { OccupationPresenter(this,this,this) }
    private var list= ArrayList<OccupationBean.DataBean.UserOccupationsBean>()
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_occupation

    override fun setActivityTitle() {
        titleText.text="职业"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        presenter.getOccupation()
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        occupationListView.setOnItemClickListener { parent, view, position, id ->
//            Toast.Tips(list[position].occupationName)
            user.setOccupation(list[position].occupationName)
            user.setOccupationID(list[position].occupationId.toString())
            finish()
        }
    }

    override fun getOccupationRequest(data: OccupationBean) {
        list=data.data.userOccupations
        val adapter = OccupationAdapter(list,this)
        occupationListView.adapter=adapter
    }

}