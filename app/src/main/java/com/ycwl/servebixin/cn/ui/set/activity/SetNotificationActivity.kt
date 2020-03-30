package com.ycwl.servebixin.cn.ui.set.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.set.mvp.bean.NotiStateBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.SetNotiBody
import com.ycwl.servebixin.cn.ui.set.mvp.presenter.SetNotiPresenter
import com.ycwl.servebixin.cn.ui.set.mvp.view.SetNotiView
import kotlinx.android.synthetic.main.activity_set_noti.*
import kotlinx.android.synthetic.main.layout_title.*

class SetNotificationActivity:BaseActivity(),SetNotiView{

    private val presenter by lazy { SetNotiPresenter(this,this,this) }
    private var isNoti=false
    private var isTalk=false

    override fun getNotiStateRequest(data: NotiStateBean) {
        if (data.data.messageNotify==0){
            switch_talk.isChecked=false
        }else{
            switch_talk.isChecked=true
        }

        if (data.data.systemNotify==0){
            switch_noti.isChecked=false
        }else{
            switch_noti.isChecked=true
        }
    }

    override fun getSetNotiRequest() {
//        if (isNoti){
//            switch_noti.isChecked=true
//            isNoti=false
//        }
//        if (isTalk){
//            switch_talk.isChecked=true
//            isTalk=false
//        }
    }

    override fun getSetNotiRequestError() {
        if (isNoti){
            switch_noti.isChecked=false
            isNoti=false
        }
        if (isTalk){
            switch_talk.isChecked=false
            isTalk=false
        }
    }

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int =R.layout.activity_set_noti

    override fun setActivityTitle() {
        titleText.text="通知设置"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        presenter.getNotiState()
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(switch_talk).subscribe {
            isTalk=true
            if (switch_talk.isChecked){
                presenter.getSetNoti(SetNotiBody("1","1"))
            }else{
                presenter.getSetNoti(SetNotiBody("1","0"))
            }
        }
        Click.viewClick(switch_noti).subscribe {
            isNoti=true
            if (switch_noti.isChecked){
                presenter.getSetNoti(SetNotiBody("2","1"))
            }else{
                presenter.getSetNoti(SetNotiBody("2","0"))
            }
        }
    }
}