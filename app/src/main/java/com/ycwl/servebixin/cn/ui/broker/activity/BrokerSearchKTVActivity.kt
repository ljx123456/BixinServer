package com.ycwl.servebixin.cn.ui.broker.activity

import android.content.DialogInterface
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerAddKTVBean
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerSearchKTVBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerAddKTVBody
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerSearchKTVBody
import com.ycwl.servebixin.cn.ui.broker.mvp.presenter.BrokerSearchKTVPresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.view.BrokerSearchKTVView
import com.ycwl.servebixin.cn.ui.main.adapter.SearchKTVAdapter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchKTVBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.BindKTVBody
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_serve_set_ktv.*
import kotlinx.android.synthetic.main.layout_title.*

class BrokerSearchKTVActivity :BaseActivity(),BrokerSearchKTVView{

    private val presenter by lazy { BrokerSearchKTVPresenter(this,this,this) }
    private lateinit var mAdapter:SearchKTVAdapter


    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_serve_set_ktv

    override fun setActivityTitle() {
        titleText.text="添加KTV"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        tv_serve_set_ktv_tips.text="您可以通过搜索KTV进行添加，添加后需要进行审核"
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(tv_serve_set_ktv_search).subscribe {
            if (edt_serve_set_ktv.text!=null&&!edt_serve_set_ktv.text.toString().equals("")){
                presenter.getSearch(BrokerSearchKTVBody(edt_serve_set_ktv.text.toString()))
            }else{
                Toast.Tips("请输入KTV名称")
            }
        }
    }

    override fun getSearchRequest(data: SearchKTVBean) {
        if (data!=null&&data.data!=null&&data.data.size>0) {
            layout_serve_set_ktv_search.visibility= View.VISIBLE
            layout_serve_set_ktv_search_none.visibility= View.GONE
            mAdapter = SearchKTVAdapter(data.data)
            var manager = LinearLayoutManager(mContext)
            manager.orientation = LinearLayout.VERTICAL
            recy_serve_set_ktv.layoutManager=manager
            recy_serve_set_ktv.adapter=mAdapter
            mAdapter.setOnItemChildClickListener { adapter, view, position ->
                //                Toast.Tips("点击了")

                ShowDialog.showCustomDialogNoTitle(this,"是否确认添加该KTV？ 添加后需商家审核，请尽快联系商家","确认", "取消",object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                dialog.dismiss()
                                presenter.getAdd(BrokerAddKTVBody(mAdapter.data[position].businessId.toString()))
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog.dismiss()
                            }
                        }
                    }
                })
            }
        }else{
            layout_serve_set_ktv_search.visibility= View.GONE
            layout_serve_set_ktv_search_none.visibility= View.VISIBLE
        }
    }

    override fun getSearchError() {
        layout_serve_set_ktv_search.visibility= View.GONE
        layout_serve_set_ktv_search_none.visibility= View.VISIBLE
    }

    override fun getAddRequest(data: BrokerAddKTVBean) {
        Toast.Tips("添加成功")
        finish()
    }

    override fun getAddError() {

    }

}