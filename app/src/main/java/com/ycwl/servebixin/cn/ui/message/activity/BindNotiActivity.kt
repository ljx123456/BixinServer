package com.ycwl.servebixin.cn.ui.message.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.message.mvp.bean.BindNotiBean
import com.ycwl.servebixin.cn.ui.message.mvp.body.BindNotiBody
import com.ycwl.servebixin.cn.ui.message.mvp.presenter.BindNotiPresenter
import com.ycwl.servebixin.cn.ui.message.mvp.view.BindNotiView
import com.ycwl.servebixin.cn.utils.image.ImageLoad
import kotlinx.android.synthetic.main.activity_noti_bind.*
import kotlinx.android.synthetic.main.layout_title.*

class BindNotiActivity:BaseActivity(),BindNotiView{

    private val presenter by lazy { BindNotiPresenter(this,this,this) }

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_noti_bind
    override fun setActivityTitle() {
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        presenter.getBindNoti(BindNotiBody(intent.getStringExtra("id")))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
    }

    override fun getBindNotiRequest(data: BindNotiBean) {
        //服务领班关系状态：0 解绑，1 绑定
        if (data.data.serviceLeader==0){
            titleText.text="达人解绑通知"
            tv_noti_bind_time_title.text="解绑时间"
            tv_noti_bind_content_title.text="解绑达人"
        }else{
            titleText.text="达人绑定通知"
            tv_noti_bind_time_title.text="绑定时间"
            tv_noti_bind_content_title.text="绑定达人"
        }
        tv_noti_bind_time.text=data.data.time
        ImageLoad.setUserHead(data.data.avatar,notiBindImg)
        notiBindName.text=data.data.nickname
        notiBindPhone.text=data.data.phone
        var sex=""
        if (data.data.sex==1){
            sex="男"
        }else{
            sex="女"
        }

        notiBindContent.text=sex+" ${data.data.age}岁 "+data.data.constellation

    }
}