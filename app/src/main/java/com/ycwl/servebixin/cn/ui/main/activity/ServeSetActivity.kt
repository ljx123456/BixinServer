package com.ycwl.servebixin.cn.ui.main.activity

import android.content.DialogInterface
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Switch
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeSetBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeSetOpenBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.ServeSetPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeSetView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_serve_set.*
import kotlinx.android.synthetic.main.layout_title.*

class ServeSetActivity:BaseActivity(),ServeSetView{
    override fun getServeSetOpenError(flag:Int) {
        if (switchSinger.isChecked){
            switchSinger.isChecked=false
        }else{
            switchSinger.isChecked=true
        }

       if (flag==-1340){
           ShowDialog.showCustomDialogs(mContext, "若需开启接单服务\n请先完善服务设置", "去完善", "取消", object : DialogInterface.OnClickListener {
               override fun onClick(dialog: DialogInterface, which: Int) {
                   when (which) {
                       DialogInterface.BUTTON_POSITIVE -> {
                           dialog.dismiss()
                           user.setUserSkillID(userSkillId.toString())
                           user.setSkillTypeID(skillTypeId.toString())
                           intentUtils.intentServeDetailsSet()

                       }
                       DialogInterface.BUTTON_NEGATIVE -> {
                           dialog.dismiss()
                       }
                   }
               }
           })
       }
    }


    private val presenter by lazy { ServeSetPresenter(this,this,this) }
    private var isPerfectData=0
    private var userSkillId=0
    private var skillTypeId=0
    private var flag:Boolean=true
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_serve_set

    override fun setActivityTitle() {
        titleText.text="服务设置"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {

    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
    }

    override fun getServeSetRequest(data: ServeSetBean) {
        isPerfectData=data.data.isPerfectData
        when(data.data.userSkillDtos.size){
            1->{
                layout_singer.visibility=View.VISIBLE
                layout_swimming.visibility=View.GONE
                layout_mountain.visibility=View.GONE
                tv_singer_title.text=data.data.userSkillDtos[0].skillName
                userSkillId=data.data.userSkillDtos[0].userSkillId
                skillTypeId=data.data.userSkillDtos[0].skillTypeId
                if (data.data.userSkillDtos[0].openState==1){
                    tv_singer_content.text="打开开关以开启接单服务，轻点进行服务设置"
                    switchSinger.isClickable=true
                }else{
                    tv_singer_content.text="服务暂未开放"
                    switchSinger.isClickable=false
                }

                if (data.data.userSkillDtos[0].state==0)
                    switchSinger.isChecked=false
                else
                    switchSinger.isChecked=true
            }
            2->{
                layout_singer.visibility=View.VISIBLE
                layout_swimming.visibility=View.VISIBLE
                layout_mountain.visibility=View.GONE
                tv_singer_title.text=data.data.userSkillDtos[0].skillName
                userSkillId=data.data.userSkillDtos[0].userSkillId
                skillTypeId=data.data.userSkillDtos[0].skillTypeId
                if (data.data.userSkillDtos[0].openState==1){
                    tv_singer_content.text="打开开关以开启接单服务，轻点进行服务设置"
                    switchSinger.isClickable=true
                }else{
                    tv_singer_content.text="服务暂未开放"
                    switchSinger.isClickable=false
                }

                if (data.data.userSkillDtos[0].state==0)
                    switchSinger.isChecked=false
                else
                    switchSinger.isChecked=true


                tv_swimming_title.text=data.data.userSkillDtos[1].skillName
                if (data.data.userSkillDtos[1].openState==1){
                    tv_swimming_content.text="打开开关以开启接单服务，轻点进行服务设置"
                    switchSwimming.isClickable=true
                }else{
                    tv_swimming_content.text="服务暂未开放"
                    switchSwimming.isClickable=false
                }

                if (data.data.userSkillDtos[1].state==0)
                    switchSwimming.isChecked=false
                else
                    switchSwimming.isChecked=true
            }
            3->{
                layout_singer.visibility=View.VISIBLE
                layout_swimming.visibility=View.VISIBLE
                layout_mountain.visibility=View.VISIBLE

                tv_singer_title.text=data.data.userSkillDtos[0].skillName
                userSkillId=data.data.userSkillDtos[0].userSkillId
                skillTypeId=data.data.userSkillDtos[0].skillTypeId
                if (data.data.userSkillDtos[0].openState==1){
                    tv_singer_content.text="打开开关以开启接单服务，轻点进行服务设置"
                    switchSinger.isClickable=true
                }else{
                    tv_singer_content.text="服务暂未开放"
                    switchSinger.isClickable=false
                }

                if (data.data.userSkillDtos[0].state==0)
                    switchSinger.isChecked=false
                else
                    switchSinger.isChecked=true


                tv_swimming_title.text=data.data.userSkillDtos[1].skillName
                if (data.data.userSkillDtos[1].openState==1){
                    tv_swimming_content.text="打开开关以开启接单服务，轻点进行服务设置"
                    switchSwimming.isClickable=true
                }else{
                    tv_swimming_content.text="服务暂未开放"
                    switchSwimming.isClickable=false
                }

                if (data.data.userSkillDtos[1].state==0)
                    switchSwimming.isChecked=false
                else
                    switchSwimming.isChecked=true


                tv_mountain_title.text=data.data.userSkillDtos[2].skillName
                if (data.data.userSkillDtos[2].openState==1){
                    tv_mountain_content.text="打开开关以开启接单服务，轻点进行服务设置"
                    switchMountain.isClickable=true
                }else{
                    tv_mountain_content.text="服务暂未开放"
                    switchMountain.isClickable=false
                }

                if (data.data.userSkillDtos[2].state==0)
                    switchMountain.isChecked=false
                else
                    switchMountain.isChecked=true
            }
        }
//        switchSinger.isChecked
        switchSinger.setOnClickListener {
            if (data.data.isPerfectData==1){
                if (switchSinger.isChecked){
//                    Toast.Tips("打开")
                    if (user.getRealname()==1) {
                        presenter.getServeSetOpen(ServeSetOpenBody(data.data.userSkillDtos[0].userSkillId,data.data.userSkillDtos[0].skillTypeId,1))
                    }else if (user.getRealname()==-1){
                        switchSinger.isChecked=false
                        ShowDialog.showCustomDialogs(this,"如需开启服务\n请先完成实名认证!","去认证","取消",object :DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> {
                                        dialog.dismiss()
                                        intentUtils.intentRealnameName()
                                        finish()
                                    }
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        dialog.dismiss()
                                    }
                                }
                            }
                        })
                    }else if (user.getRealname()==2){
                        switchSinger.isChecked=false
                        ShowDialog.showCustomDialogs(this,"实名认证正在审核中","去查看","取消",object :DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> {
                                        dialog.dismiss()
                                        intentUtils.intentRealnameDetails()
                                    }
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        dialog.dismiss()
                                    }
                                }
                            }
                        })
                    }else{
                        switchSinger.isChecked=false
                        ShowDialog.showCustomDialogs(this,"如需开启服务\n请先完成实名认证!","去认证","取消",object :DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> {
                                        dialog.dismiss()
                                        intentUtils.intentRealnameDetails()
                                    }
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        dialog.dismiss()
                                    }
                                }
                            }
                        })
                    }

                }else{
                    presenter.getServeSetOpen(ServeSetOpenBody(data.data.userSkillDtos[0].userSkillId,data.data.userSkillDtos[0].skillTypeId,0))
                }
            }else{
                if (switchSinger.isChecked){
                    switchSinger.isChecked=false
                }else{
                    switchSinger.isChecked=true
                }
                Toast.Tips("请先完善资料")
            }


        }
//        switchSinger.setOnCheckedChangeListener { buttonView, isChecked ->
//
//
//        }

//        switchSwimming.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (data.data.isPerfectData==1) {
//                if (isChecked) {
//                    presenter.getServeSetOpen(ServeSetOpenBody(data.data.userSkillDtos[1].userSkillId, data.data.userSkillDtos[1].skillTypeId, 1))
//                } else {
//                    presenter.getServeSetOpen(ServeSetOpenBody(data.data.userSkillDtos[1].userSkillId, data.data.userSkillDtos[1].skillTypeId, 0))
//                }
//            }else{
//                ShowDialog.showCustomDialogs(mContext, "若需开启接单服务\n请先完善服务设置", "去完善", "取消", object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface, which: Int) {
//                        when (which) {
//                            DialogInterface.BUTTON_POSITIVE -> {
//                                dialog.dismiss()
////                                intentUtils.intentServeDetailsSet()
//                            }
//                            DialogInterface.BUTTON_NEGATIVE -> {
//                                dialog.dismiss()
//                            }
//                        }
//                    }
//                })
//            }
//        }

//        switchMountain.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (data.data.isPerfectData==1) {
//                if (isChecked) {
//                    presenter.getServeSetOpen(ServeSetOpenBody(data.data.userSkillDtos[2].userSkillId, data.data.userSkillDtos[2].skillTypeId, 1))
//                } else {
//                    presenter.getServeSetOpen(ServeSetOpenBody(data.data.userSkillDtos[2].userSkillId, data.data.userSkillDtos[2].skillTypeId, 0))
//                }
//            }else{
//                ShowDialog.showCustomDialogs(mContext, "若需开启接单服务\n请先完善服务设置", "去完善", "取消", object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface, which: Int) {
//                        when (which) {
//                            DialogInterface.BUTTON_POSITIVE -> {
//                                dialog.dismiss()
//
//
//                            }
//                            DialogInterface.BUTTON_NEGATIVE -> {
//                                dialog.dismiss()
//                            }
//                        }
//                    }
//                })
//            }
//        }

        Click.viewClick(tv_singer_content).subscribe {
            user.setUserSkillID(userSkillId.toString())
            user.setSkillTypeID(skillTypeId.toString())
            intentUtils.intentServeDetailsSet()
        }
//        Click.viewClick(layout_swimming).subscribe {
////            intentUtils.intentServeDetailsSet()
//        }
//        Click.viewClick(layout_mountain).subscribe {
////            intentUtils.intentServeDetailsSet()
//        }
    }

    override fun getServeSetOpenRequest(data: EditUserBean) {

    }

    override fun onResume() {
        super.onResume()
        presenter.getServeSet()
    }

}