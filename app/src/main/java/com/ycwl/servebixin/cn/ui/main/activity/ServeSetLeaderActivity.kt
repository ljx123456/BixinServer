package com.ycwl.servebixin.cn.ui.main.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchLeaderBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeSetBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.BindLeaderBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.SearchLeaderBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.ServeSetLeaderPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeSetLeaderView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.image.ImageLoad

import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import com.ycwl.servebixin.cn.utils.utils.UrlParse
import kotlinx.android.synthetic.main.activity_serve_set_leader.*
import kotlinx.android.synthetic.main.layout_title.*

class ServeSetLeaderActivity:BaseActivity(),ServeSetLeaderView{

    override fun getSearchLeaderRequest(data: SearchLeaderBean) {
        if (data!=null&&data.data!=null&&data.data.avatar!=null) {
            layout_serve_set_leader_search_none.visibility = View.GONE
            layout_serve_set_leader_search.visibility = View.VISIBLE
            ImageLoad.setUserHead(data.data.avatar, serveSetLeaderImg)
            tv_serve_set_leader_name.text = data.data.nickname
            var sex = ""
            if (data.data.sex.equals("1")) {
                sex = "男"
            } else {
                sex = "女"
            }
            tv_serve_set_leader_content.text = sex + " " + data.data.age + " " + data.data.constellation

            Click.viewClick(tv_serve_set_leader_bind).subscribe {
                ShowDialog.showCustomDialogNoTitle(this, "是否确认绑定该经纪人？", "确定", "取消", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                dialog.dismiss()
                                presenter.getBindLeader(BindLeaderBody(user.getUserSkillID().toInt(), user.getSkillTypeID().toInt(), data.data.bixinId.toString()))
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog.dismiss()
                            }
                        }
                    }
                })
            }
        }else{
            layout_serve_set_leader_search_none.visibility=View.VISIBLE
            layout_serve_set_leader_search.visibility=View.GONE
        }
    }

    override fun getSearchLeaderError() {

    }

    override fun getBindLeaderRequest(data: EditUserBean) {
        finish()
    }


    private val presenter by lazy { ServeSetLeaderPresenter(this,this,this) }


    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_serve_set_leader

    override fun setActivityTitle() {
        titleText.text="设置经纪人"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRight.visibility=View.VISIBLE
        titleRight.setImageResource(R.mipmap.searchpage_box_qr)
    }

    override fun initActivityData() {
        layout_serve_set_leader_search_none.visibility=View.GONE
        layout_serve_set_leader_search.visibility=View.GONE
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(titleRight).subscribe {
            intentUtils.intentSao(this,"扫描经纪人二维码")
        }
        Click.viewClick(tv_serve_set_leader_search).subscribe {
            if (edt_serve_set_leader.text!=null&&!edt_serve_set_leader.text.toString().equals("")){
                presenter.getSearchLeader(SearchLeaderBody(edt_serve_set_leader.text.toString()))
            }else{
                Toast.Tips("请输入经纪人比心id")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK){
            LogUtils.a("扫描成功" + data!!.extras.getString("codedContent"))
//            http://bixinyule.com?bixinClientType=2&qrcodeType=1&bixinId=6546499&identity=2
            var map= UrlParse.getUrlParams(data!!.extras.getString("codedContent"))
            if (map.containsKey("bixinClientType")){
                if (map["qrcodeType"]!=""&&map["qrcodeType"]=="1"&&map["identity"]=="3"){
                    presenter.getSearchLeader(SearchLeaderBody((map["bixinId"]!!)))
                }else{
                    Toast.Tips("请扫描经纪人二维码")
                }
            }else{
                Toast.Tips("请扫描正确的二维码")
            }
        }
    }

}