package com.ycwl.servebixin.cn.ui.main.activity

import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.PicturesUtils
import com.ycwl.servebixin.cn.db.TagsUtils
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.PersonPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.PersonView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_complete_data.*
import kotlinx.android.synthetic.main.layout_title.*
import java.lang.Exception

class CompleteDataActivity :BaseActivity(),PersonView{

    private val presenter by lazy { PersonPresenter(this,this,this) }
    override fun getPersonDataRequest(data: ByCodeBean) {
//        val user= DBUtils.getUser()
        DBUtils.setUser(ByCodeBean.DataBean(data.data.tokenCreateTime,data.data.token,data.data.identity,data.data.rongToken,data.data.phone,data.data.nickname,data.data.sex,data.data.birthday,
                data.data.constellation,data.data.avatar,data.data.occupationName,data.data.isPerfectData,data.data.leaderIDCardAuditState,data.data.dataAuditState,data.data.isEnableSkill,data.data.serviceIDCardAuditState,
                data.data.orderNum, data.data.fansNum,data.data.followNum,data.data.bixinId,data.data.userId, user.getUserIM(),data.data.age,data.data.videos,data.data.tags))
        if (data.data.videos!=null&&data.data.videos.size>0){
            PicturesUtils.deleteAllPictures()
            if (data.data.videos[0].type==1){
                DBUtils.setVideo(data.data.videos[0])
            }else{
                DBUtils.DelVideo()
                PicturesUtils.addPicture(data.data.videos[0])
            }

            for (i in data.data.videos.indices){
                if (i!=0){
                    PicturesUtils.addPicture(data.data.videos[i])
                }
            }
        }else{
            try {
                DBUtils.DelVideo()
                PicturesUtils.deleteAllPictures()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        if (data.data.tags!=null&&data.data.tags.size>0){
            TagsUtils.deleteAllTags()
            data.data.tags.forEach {
                TagsUtils.addTag(it)
            }
        }else{
            TagsUtils.deleteAllTags()
        }
        if (data.data.isPerfectData==0) {
            tv_data_need_complete.visibility = View.VISIBLE
            tv_date_completed.visibility=View.GONE
        }else{
            tv_data_need_complete.visibility = View.GONE
            tv_date_completed.visibility=View.VISIBLE
        }


        when(data.data.dataAuditState){
            0 ->{
                tv_date_completed.text="审核失败"
                tv_data_need_complete.visibility = View.GONE
                tv_date_completed.visibility=View.VISIBLE
//                layout_complete_data.isEnabled=true
            }
            2 ->{
                tv_date_completed.text="等待审核"
                tv_data_need_complete.visibility = View.GONE
                tv_date_completed.visibility=View.VISIBLE
//                layout_complete_data.isEnabled=false
            }
            1 ->{
                tv_date_completed.text="审核通过"
                tv_data_need_complete.visibility = View.GONE
                tv_date_completed.visibility=View.VISIBLE
//                layout_complete_data.isEnabled=false
            }

            -1 ->{
                tv_data_need_complete.visibility = View.VISIBLE
                tv_date_completed.visibility=View.GONE
            }

        }

//        when(data.data.serviceIDCardAuditState){
//            0 ->{
//                tv_realname_opened.text="审核失败"
//                tv_realname_need_open.visibility = View.GONE
//                tv_realname_opened.visibility=View.VISIBLE
////                layout_complete_realname.isEnabled=true
//            }
//            2 ->{
//                tv_realname_opened.text="等待审核"
//                tv_realname_need_open.visibility = View.GONE
//                tv_realname_opened.visibility=View.VISIBLE
////                layout_complete_realname.isEnabled=true
//            }
//            1 ->{
//                tv_realname_opened.text="审核通过"
//                tv_realname_need_open.visibility = View.GONE
//                tv_realname_opened.visibility=View.VISIBLE
////                layout_complete_realname.isEnabled=false
//            }
//            -1->{
//                tv_realname_need_open.visibility = View.VISIBLE
//                tv_realname_opened.visibility=View.GONE
//            }
//
//        }

//        if (data.data.isEnableSkill==0){
//            tv_skill_need_open.visibility=View.VISIBLE
//            tv_skill_opened.visibility=View.GONE
//        }else{
//            tv_skill_need_open.visibility=View.GONE
//            tv_skill_opened.visibility=View.VISIBLE
//        }

        Click.viewClick(layout_complete_data).subscribe {
            if (data.data.dataAuditState==-1) {
//                intentUtils.intentCompleteData()
                intentUtils.intentEditUser("1")
            } else{
                intentUtils.intentCompleteDataDetails()
            }

        }
//        Click.viewClick(layout_complete_realname).subscribe {
//            if (data.data.serviceIDCardAuditState==-1){
//                intentUtils.intentRealnameName()
//            }else{
//                intentUtils.intentRealnameDetails()
//            }
//
//        }
//        Click.viewClick(layout_complete_skill).subscribe {
//            //            intentUtils.intentServeSet()
//        }

    }

    override fun getPersonDataError() {

    }

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_complete_data

    override fun setActivityTitle() {
        titleText.text="完善资料"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
    }

    override fun initActivityData() {
//        presenter.getPersonData()

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }

    }

    override fun onResume() {
        super.onResume()
        presenter.getPersonData()
    }

}