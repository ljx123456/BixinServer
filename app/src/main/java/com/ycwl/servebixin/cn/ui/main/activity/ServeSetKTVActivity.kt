package com.ycwl.servebixin.cn.ui.main.activity

import android.Manifest
import android.content.DialogInterface
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import cn.yoyo.com.utils.utils.Click
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.adapter.NearByKTVAdapter
import com.ycwl.servebixin.cn.ui.main.adapter.SearchKTVAdapter
import com.ycwl.servebixin.cn.ui.main.location.LocationUtils
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.NearByKTVBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchKTVBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.BindKTVBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.NearByKTVBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.SearchKTVBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.ServeSetKTVPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeSetKTVView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_serve_set_ktv.*
import kotlinx.android.synthetic.main.layout_title.*

class ServeSetKTVActivity:BaseActivity(),LocationUtils.Location,ServeSetKTVView{
    override fun getLocationSuccess() {
        locationUtils.stopLocation()
        presenter.getNearByKTV(NearByKTVBody(user.getlng(),user.getlat()))
    }

    override fun getNearByKTVRequest(data: NearByKTVBean) {
        if (data!=null&&data.data!=null&&data.data.size>0) {
            layout_serve_set_ktv_search.visibility=View.VISIBLE
            layout_serve_set_ktv_search_none.visibility=View.GONE
            serve_set_ktv_title.text="附近KTV"
            var mAdapter = NearByKTVAdapter(data.data)
            var manager = LinearLayoutManager(mContext)
            manager.orientation = LinearLayout.VERTICAL
            recy_serve_set_ktv.layoutManager=manager
            recy_serve_set_ktv.adapter=mAdapter
            mAdapter.setOnItemChildClickListener { adapter, view, position ->
                //                Toast.Tips("点击了")

                ShowDialog.showCustomDialogNoTitle(this,"是否确认添加该KTV？","确认", "取消",object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                dialog.dismiss()
                                presenter.getBindKTV(BindKTVBody(user.getUserSkillID().toInt(),user.getSkillTypeID().toInt(),mAdapter.data[position].businessId.toString()))
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog.dismiss()
                            }
                        }
                    }
                })
            }
        }else{
            layout_serve_set_ktv_search.visibility=View.GONE
            layout_serve_set_ktv_search_none.visibility=View.GONE
//            layout_serve_set_ktv_search_none.visibility=View.VISIBLE
        }
    }

    private val presenter by lazy { ServeSetKTVPresenter(this,this,this) }
    private lateinit var mAdapter:SearchKTVAdapter
    private lateinit var locationUtils:LocationUtils

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_serve_set_ktv

    override fun setActivityTitle() {
        titleText.text="添加KTV"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        var rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).subscribe { aBoolean ->
            if (aBoolean) {
                locationUtils=LocationUtils(this)
                locationUtils.getLocation()
            }
            else {

            }
        }


    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(tv_serve_set_ktv_search).subscribe {
            if (edt_serve_set_ktv.text!=null&&!edt_serve_set_ktv.text.toString().equals("")){
                presenter.getSearchKTV(SearchKTVBody(edt_serve_set_ktv.text.toString()))
            }else{
                Toast.Tips("请输入KTV名称")
            }
        }
    }

    override fun getSearchKTVRequest(data: SearchKTVBean) {
        if (data!=null&&data.data!=null&&data.data.size>0) {
            layout_serve_set_ktv_search.visibility=View.VISIBLE
            layout_serve_set_ktv_search_none.visibility=View.GONE
            serve_set_ktv_title.text="搜索结果"
            mAdapter = SearchKTVAdapter(data.data)
            var manager = LinearLayoutManager(mContext)
            manager.orientation = LinearLayout.VERTICAL
            recy_serve_set_ktv.layoutManager=manager
            recy_serve_set_ktv.adapter=mAdapter
            mAdapter.setOnItemChildClickListener { adapter, view, position ->
//                Toast.Tips("点击了")

                ShowDialog.showCustomDialogNoTitle(this,"是否确认添加该KTV？","确认", "取消",object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                dialog.dismiss()
                                presenter.getBindKTV(BindKTVBody(user.getUserSkillID().toInt(),user.getSkillTypeID().toInt(),mAdapter.data[position].businessId.toString()))
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog.dismiss()
                            }
                        }
                    }
                })
            }
        }else{
            layout_serve_set_ktv_search.visibility=View.GONE
            layout_serve_set_ktv_search_none.visibility=View.VISIBLE
        }
    }

    override fun getSearchKTVError() {

    }

    override fun getBindKTVRequest(data: EditUserBean) {
        Toast.Tips("添加成功")
        finish()
    }
}