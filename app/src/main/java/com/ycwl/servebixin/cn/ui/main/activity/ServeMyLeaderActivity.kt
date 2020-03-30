package com.ycwl.servebixin.cn.ui.main.activity

import android.content.DialogInterface
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeDetailsBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.ServeMyLeaderPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeMyLeaderView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.image.ImageLoad
import kotlinx.android.synthetic.main.activity_serve_my_leader.*
import kotlinx.android.synthetic.main.layout_title.*

class ServeMyLeaderActivity:BaseActivity(),ServeMyLeaderView{

    private val presenter by lazy { ServeMyLeaderPresenter(this,this,this) }
    override fun getUnbindLeaderRequest(data: EditUserBean) {
        finish()
    }

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_serve_my_leader

    override fun setActivityTitle() {
        titleText.text="我的经纪人"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        ImageLoad.setUserHead(intent.getStringExtra("avatar"),serveMyLeaderImg)
        tv_serve_my_leader_name.text=intent.getStringExtra("name")
        var sex=""
        if (intent.getIntExtra("sex",-1)==1){
            sex="男"
        }else{
            sex="女"
        }
        tv_serve_my_leader_content.text=sex+" "+intent.getStringExtra("age")+" "+intent.getStringExtra("constellation")
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }

        //解绑
//        Click.viewClick(tv_serve_my_leader_unbind).subscribe {
//            ShowDialog.showCustomDialogNoTitle(this, "是否确认解绑该经纪人？", "确定", "取消", object : DialogInterface.OnClickListener {
//                override fun onClick(dialog: DialogInterface, which: Int) {
//                    when (which) {
//                        DialogInterface.BUTTON_POSITIVE -> {
//                            dialog.dismiss()
//                            presenter.getUnbindLeader(ServeDetailsBody(user.getUserSkillID().toInt(), user.getSkillTypeID().toInt()))
//                        }
//                        DialogInterface.BUTTON_NEGATIVE -> {
//                            dialog.dismiss()
//                        }
//                    }
//                }
//            })
//        }
    }

}