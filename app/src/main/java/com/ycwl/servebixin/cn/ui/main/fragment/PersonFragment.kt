package com.ycwl.servebixin.cn.ui.main.fragment

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import cn.jpush.android.api.JPushInterface
import cn.jpush.android.api.TagAliasCallback
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.model.UserInfo
import cn.jpush.im.api.BasicCallback
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.makeramen.roundedimageview.RoundedDrawable
import com.tbruyelle.rxpermissions2.RxPermissions

import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.AppProject
import com.ycwl.servebixin.cn.base.BaseFragment
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.PicturesUtils
import com.ycwl.servebixin.cn.db.TagsUtils
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.image.ImageInfo
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.main.location.LocationUtils
import com.ycwl.servebixin.cn.ui.main.mvp.body.LocationBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.LocationPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.PersonPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.LocationView
import com.ycwl.servebixin.cn.ui.main.mvp.view.PersonView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.IncomePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.IncomeView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.image.ImageLoad
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.permissions.UserPermissions
import com.ycwl.servebixin.cn.utils.permissions.UserPermissions.userLocation
import com.ycwl.servebixin.cn.utils.utils.FileUtils
import com.ycwl.servebixin.cn.utils.utils.SpHelp
import com.ycwl.servebixin.cn.utils.utils.Toast

import kotlinx.android.synthetic.main.fragment_person.*
import kotlinx.android.synthetic.main.layout_error_network.*
import retrofit2.http.Url
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class PersonFragment :BaseFragment(),PersonView,IncomeView {
//    override fun requestPermissionsFaceSucceed(context: Context, what: Int) {
//        //获取定位
////        citypresenter.getCityList()
//
//        locationUtils.getLocation()
//    }
//
//    override fun requestPermissionsFaceError() {
//        Log.e("测试","权限请求失败")
//        if (isLocation){
//            if (!isflag2) {
//                isflag2=true
//                Log.e("测试","权限请求弹窗")
////                ShowDialog.showCustomDialog(activity!!, "定位服务未开启", "未开启定位服务将影响你整个订单系统，建议立即前往设置", "去设置", "暂不", object : DialogInterface.OnClickListener {
////                    override fun onClick(dialog: DialogInterface, which: Int) {
////                        when (which) {
////                            DialogInterface.BUTTON_POSITIVE -> {
////                                dialog.dismiss()
////                                UserPermissions.gotoSet(activity!!)
////                            }
////                            DialogInterface.BUTTON_NEGATIVE -> {
////                                dialog.dismiss()
////                            }
////                        }
////                    }
////                })
//            }
//        }
//    }

//    override fun getLocationSuccess() {
//        Log.e("测试","定位一次")
//        locationPresenter.getLocation(LocationBody(user.getlng(),user.getlat()))
//    }
//
//    override fun getLocationRequest() {
//
//    }

    override fun getIncomeRequest(data: IncomeBean) {
        intentUtils.intentIncome()
    }

    override fun getIncomeError(data: Int) {
        if (data==-6007){
            ShowDialog.showCustomDialogNoTitle(activity!!,"要进入收益 请先设置您的提现密码","设置","取消",object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface, which: Int) {

                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            dialog.dismiss()
                            intentUtils.intentSetWithdrawPwdCode()
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }
            })
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        isCreated=true
//
//    }

    override fun setLayoutTitle() {

    }

    override fun initFragmentData() {
//        presenter.getPersonData()
//        init()
        val user=DBUtils.getUser()
//        if (user.isPerfectData==1&&user.serviceIDCardAuditState==1){
//            isLocation=true
//        }else{
//            isLocation=false
//        }
//        app= AppProject()
//        if (!isflag3) {
//            isflag3=true
//            Log.e("测试", "init")
//            userLocation(activity!!, this)
//        }


        if(user.isPerfectData!=1) {
            if (user.dataAuditState == -1 || user.dataAuditState == 0) {
                if (!isflag) {
                    isflag=true
                    ShowDialog.showCustomDialogNoTitle(activity!!, "欢迎加入比心娱乐！\n马上完善资料即可开启接单~", "去完善", "暂不", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {

                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
                                    intentUtils.intentComplete()
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog.dismiss()
                                }
                            }
                        }
                    })
                }
            }
        }else{
            if (user.serviceIDCardAuditState==-1){
                if (!SpHelp.getBoolean("realname")) {
                    SpHelp.putBoolean("realname",true)
                    ShowDialog.showCustomDialogNoTitle(activity!!, "为了享受更好的服务\n请进行实名认证！", "去认证", "取消", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {

                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
                                    intentUtils.intentRealnameName()
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog.dismiss()
                                }
                            }
                        }
                    })
                }
            }
        }
    }

    fun init(){
        val user=DBUtils.getUser()
        val video=DBUtils.getVideo()
        val pictures=PicturesUtils.getPictures()

        if (user.isPerfectData==1){
            layout_person_default.visibility=View.GONE
            layout_person_photo.visibility=View.VISIBLE
            tv_person_complete.visibility=View.GONE
            layout_user_id.visibility=View.VISIBLE
            defaultEdit.isEnabled=true

            if (video!=null&&video.url!=null){
                ImageLoad.setImage(video.url,iv_person)
                iv_person_play.visibility=View.VISIBLE
                Click.viewClick(iv_person_play).subscribe {
                    intentUtils.intentVideo(video.url)
                }
                Click.viewClick(iv_person).subscribe {
                    intentUtils.intentVideo(video.url)
                }
            }else if (pictures!=null&&pictures.size>0){
                ImageLoad.setImage(pictures[0]!!.url,iv_person)
                iv_person_play.visibility=View.GONE
                Click.viewClick(iv_person).subscribe {
                    var imagelist = ArrayList<ImageInfo>()
                    pictures.forEach {
                        imagelist.add(ImageInfo(it.url, false, 2))
                    }
                    intentUtils.intentImage(false, imagelist, 0)
                }
            }

            Click.viewClick(iv_user).subscribe {
                if (pictures!=null&&pictures.size>0) {
                    var imagelist = ArrayList<ImageInfo>()
                    pictures.forEach {
                        imagelist.add(ImageInfo(it.url, false, 2))
                    }
                    intentUtils.intentImage(false, imagelist, 0)
                }
            }

        }else{
            DBUtils.DelVideo()
            PicturesUtils.deleteAllPictures()
            TagsUtils.deleteAllTags()
            layout_person_default.visibility=View.VISIBLE
            layout_person_photo.visibility=View.GONE
            tv_person_complete.visibility=View.VISIBLE
            layout_user_id.visibility=View.GONE
            defaultEdit.isEnabled=false

//            if (!app!!.getFlag()&&user.dataAuditState==0) {
//                ShowDialog.showCustomDialogNoTitle(activity!!, "欢迎加入比心娱乐！\n马上完善资料即可开启接单~", "去完善", "暂不", object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface, which: Int) {
//
//                        when (which) {
//                            DialogInterface.BUTTON_POSITIVE -> {
//                                dialog.dismiss()
//                                intentUtils.intentComplete()
//                            }
//                            DialogInterface.BUTTON_NEGATIVE -> {
//                                app!!.setFlag(true)
//                                dialog.dismiss()
//                            }
//                        }
//                    }
//                })
//            }
        }


        ImageLoad.setUserHead(user.avatar,iv_user)

//        URL(user.avatar).path
//        Pat
        var userInfo=JMessageClient.getMyInfo()
        if (userInfo!=null) {
            userInfo.nickname = user.nickname
            JMessageClient.updateMyInfo(UserInfo.Field.nickname, userInfo, object : BasicCallback() {
                override fun gotResult(p0: Int, p1: String?) {
                    LogUtils.a("极光昵称", p1)
                }
            })
        }
        if (!isflag2) {
            isflag2=true
            val rxPermissions = RxPermissions(activity!!)
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe { aBoolean ->
                if (aBoolean!!) {
                    var file = FileUtils.saveImageFile((iv_user.drawable as RoundedDrawable).toBitmap())
                    JMessageClient.updateUserAvatar(file, object : BasicCallback() {
                        override fun gotResult(p0: Int, p1: String?) {
                            LogUtils.a("极光头像", p1)
                            if (file.exists()) {
                                deletePic(file.absolutePath)
//                            if (file.delete()){
//                                LogUtils.a("删除头像","成功")
//                            }else{
//                                LogUtils.a("删除头像","失败")
//                            }
                            }
                        }
                    })
                } else {
//                    ShowDialog.showCustomDialogNoTitle(activity!!, "由于无法获取设备信息的权限,应用无法运行，请前往设置中心应用权限页设置", "去设置", "取消", object : DialogInterface.OnClickListener {
//                        override fun onClick(dialog: DialogInterface, which: Int) {
//                            when (which) {
//                                DialogInterface.BUTTON_POSITIVE -> {
//                                    dialog.dismiss()
//                                    isflag2=false
//                                    UserPermissions.gotoSet(activity!!)
//                                }
//                                DialogInterface.BUTTON_NEGATIVE -> {
//                                    dialog.dismiss()
//                                }
//                            }
//                        }
//                    })
                }
            }
        }
        tv_user_order_num.text=user.orderNum.toString()
        tv_user_fans_num.text=user.fansNum.toString()
//        tv_user_guan_num.text=user.followNum.toString()
        tv_user_name.text=user.nickname
        if (user.sex==1)
            tv_user_sex.text="男"
        else
            tv_user_sex.text="女"

        tv_user_age.text=user.age.toString()+"岁"
        tv_user_constellation.text=user.constellation
        tv_user_occupationName.text=user.occupationName
        tv_user_id.text="ID:${user.bixinId}"
        var state=DBUtils.getUser().leaderIDCardAuditState
        Click.viewClick(layout_person_broker).subscribe {
            if (user.isPerfectData==1) {
                if (com.ycwl.servebixin.cn.db.user.getIdentity()==3){
                    if (com.ycwl.servebixin.cn.db.user.getRealname()==1) {
                        if (state==-1){
                            ShowDialog.showCustomDialogs(mContext, "由于政策原因，为了更好的提供服务，建议您上传本人手持身份证照片","去上传","取消",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface, which: Int) {
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            dialog.dismiss()
                                            intentUtils.intentApplyBrokerUpload()
                                        }
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                            dialog.dismiss()
                                        }
                                    }
                                }
                            })

                        }else if (state==0){
                            ShowDialog.showCustomDialogs(mContext, "本人手持身份证照片审核失败","去查看","取消",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface, which: Int) {
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            dialog.dismiss()
                                            intentUtils.intentApplyBrokerRecord()
                                        }
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                            dialog.dismiss()
                                        }
                                    }
                                }
                            })

                        }else if (state==2){
                            Toast.Tips("经纪人资料正在审核中,请耐心等待审核结果")
                        }else {
                            intentUtils.intentBroker()
                        }
                    }else if (com.ycwl.servebixin.cn.db.user.getRealname()==-1){
                        ShowDialog.showCustomDialogs(mContext,"由于政策原因，为了更好的提供服务,需要你先完成实名认证","去认证","取消",object :DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> {
                                        dialog.dismiss()
                                        intentUtils.intentRealnameName()
                                    }
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        dialog.dismiss()
                                    }
                                }
                            }
                        })
                    }else if (com.ycwl.servebixin.cn.db.user.getRealname()==2){
//                        Toast.Tips("实名认证正在审核中,请耐心等待审核结果...")
                        ShowDialog.showCustomDialogs(mContext,"实名认证正在审核中","去查看","取消",object :DialogInterface.OnClickListener{
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
                        ShowDialog.showCustomDialogs(mContext,"由于政策原因，为了更好的提供服务,需要你先完成实名认证","去认证","取消",object :DialogInterface.OnClickListener{
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
                    if (state==-1){
                        intentUtils.intentApplyBroker()
                    }else{
                        intentUtils.intentApplyBrokerRecord()
                    }

                }
            }
            else {
                when(user.dataAuditState){
                    -1->{
                        Toast.Tips("请先完善资料")
                    }
                    0->{
                        Toast.Tips("资料审核未通过，请重新提交审核")
                    }
                    2->{
                        Toast.Tips("资料审核中")
                    }
                }

            }

        }

        Click.viewClick(layout_person_ordering).subscribe {
            if (user.isPerfectData==1) {
                if (com.ycwl.servebixin.cn.db.user.getIdentity()==3) {
                    if (com.ycwl.servebixin.cn.db.user.getRealname()==1) {
                        if (state==-1){
                            ShowDialog.showCustomDialogs(mContext, "由于政策原因，为了更好的提供服务，建议您上传本人手持身份证照片","去上传","取消",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface, which: Int) {
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            dialog.dismiss()
                                            intentUtils.intentApplyBrokerUpload()
                                        }
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                            dialog.dismiss()
                                        }
                                    }
                                }
                            })

                        }else if (state==0){
                            ShowDialog.showCustomDialogs(mContext, "本人手持身份证照片审核失败","去查看","取消",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface, which: Int) {
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            dialog.dismiss()
                                            intentUtils.intentApplyBrokerRecord()
                                        }
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                            dialog.dismiss()
                                        }
                                    }
                                }
                            })

                        }else if (state==2){
                            Toast.Tips("经纪人资料正在审核中,请耐心等待审核结果")
                        }else {
                            intentUtils.intentYue()
                        }
                    }else if (com.ycwl.servebixin.cn.db.user.getRealname()==-1){
                        ShowDialog.showCustomDialogs(mContext,"由于政策原因，为了更好的提供服务,需要您先完成实名认证","去认证","取消",object :DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> {
                                        dialog.dismiss()
                                        intentUtils.intentRealnameName()
                                    }
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        dialog.dismiss()
                                    }
                                }
                            }
                        })
                    }else if (com.ycwl.servebixin.cn.db.user.getRealname()==2){
                        ShowDialog.showCustomDialogs(mContext,"实名认证正在审核中","去查看","取消",object :DialogInterface.OnClickListener{
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
                        ShowDialog.showCustomDialogs(mContext,"由于政策原因，为了更好的提供服务,需要您先完成实名认证","去认证","取消",object :DialogInterface.OnClickListener{
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
                    Toast.Tips("请先申请成为达人经纪人")
                }
            }
            else {
                when(user.dataAuditState){
                    -1->{
                        Toast.Tips("请先完善资料")
                    }
                    0->{
                        Toast.Tips("资料审核未通过，请重新提交审核")
                    }
                    2->{
                        Toast.Tips("资料审核中")
                    }
                }

            }

        }
        Click.viewClick(layout_person_service).subscribe {
            if (user.isPerfectData==1) {
                intentUtils.intentServeSet()
            }
            else {
                when(user.dataAuditState){
                    -1->{
                        Toast.Tips("请先完善资料")
                    }
                    0->{
                        Toast.Tips("资料审核未通过，请重新提交审核")
                    }
                    2->{
                        Toast.Tips("资料审核中")
                    }
                }

            }

        }

        LogUtils.a("极光别名", com.ycwl.servebixin.cn.db.user.getUserUserID())
        JPushInterface.setAlias(activity!!, com.ycwl.servebixin.cn.db.user.getUserUserID(),object : TagAliasCallback {
            override fun gotResult(p0: Int, p1: String?, p2: MutableSet<String>?) {
                Log.e("测试别名设置main",p0.toString())
                if (p0==6002){
                    var handler= Handler()
                    handler.postDelayed(object :Runnable{
                        override fun run() {
                            try {
                                JPushInterface.setAlias(activity!!, 1, user.userID)
                            }catch (e:Exception){
                                e.printStackTrace()
                            }
                            handler.removeCallbacksAndMessages(null)
                        }
                    },1000*60)
                }
            }
        })
    }

    fun initFirst(){
        val user=DBUtils.getUser()
        val video=DBUtils.getVideo()
        val pictures=PicturesUtils.getPictures()

        if (user.isPerfectData==1){
            layout_person_default.visibility=View.GONE
            layout_person_photo.visibility=View.VISIBLE
            tv_person_complete.visibility=View.GONE
            layout_user_id.visibility=View.VISIBLE
            defaultEdit.isEnabled=true

            if (video!=null&&video.url!=null){
                ImageLoad.setImage(video.url,iv_person)
                iv_person_play.visibility=View.VISIBLE
                Click.viewClick(iv_person_play).subscribe {
                    intentUtils.intentVideo(video.url)
                }
                Click.viewClick(iv_person).subscribe {
                    intentUtils.intentVideo(video.url)
                }
            }else if (pictures!=null&&pictures.size>0){
                ImageLoad.setImage(pictures[0]!!.url,iv_person)
                iv_person_play.visibility=View.GONE
                Click.viewClick(iv_person).subscribe {
                    var imagelist = ArrayList<ImageInfo>()
                    pictures.forEach {
                        imagelist.add(ImageInfo(it.url, false, 2))
                    }
                    intentUtils.intentImage(false, imagelist, 0)
                }
            }
            Click.viewClick(iv_user).subscribe {
                if (pictures!=null&&pictures.size>0) {
                    var imagelist = ArrayList<ImageInfo>()
                    pictures.forEach {
                        imagelist.add(ImageInfo(it.url, false, 2))
                    }
                    intentUtils.intentImage(false, imagelist, 0)
                }
            }

        }else{
            DBUtils.DelVideo()
            PicturesUtils.deleteAllPictures()
            TagsUtils.deleteAllTags()
            layout_person_default.visibility=View.VISIBLE
            layout_person_photo.visibility=View.GONE
            tv_person_complete.visibility=View.VISIBLE
            layout_user_id.visibility=View.GONE
            defaultEdit.isEnabled=false
//            if (user.dataAuditState==-1||user.dataAuditState==0) {
//                ShowDialog.showCustomDialogNoTitle(activity!!, "欢迎加入比心娱乐！\n马上完善资料即可开启接单~", "去完善", "暂不", object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface, which: Int) {
//
//                        when (which) {
//                            DialogInterface.BUTTON_POSITIVE -> {
//                                dialog.dismiss()
//                                intentUtils.intentComplete()
//                            }
//                            DialogInterface.BUTTON_NEGATIVE -> {
//                                dialog.dismiss()
//                            }
//                        }
//                    }
//                })
//            }
//            if (user.dataAuditState==-1||user.dataAuditState==0) {
//                ShowDialog.showCustomDialog(activity!!, "欢迎加入比心娱乐！\n马上完善资料即可开启接单~", "去完善", object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface, which: Int) {
//                        dialog.dismiss()
//                        intentUtils.intentComplete()
//                    }
//                })
//            }
        }


        ImageLoad.setUserHead(user.avatar,iv_user)

//        URL(user.avatar).path
//        Pat
        var userInfo=JMessageClient.getMyInfo()
        if (userInfo!=null) {
            userInfo.nickname = user.nickname
            JMessageClient.updateMyInfo(UserInfo.Field.nickname, userInfo, object : BasicCallback() {
                override fun gotResult(p0: Int, p1: String?) {
                    LogUtils.a("极光昵称", p1)
                }
            })
        }
        if (!isflag2) {
            isflag2=true
            val rxPermissions = RxPermissions(activity!!)
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe { aBoolean ->
                if (aBoolean!!) {
                    var file = FileUtils.saveImageFile((iv_user.drawable as RoundedDrawable).toBitmap())
                    JMessageClient.updateUserAvatar(file, object : BasicCallback() {
                        override fun gotResult(p0: Int, p1: String?) {
                            LogUtils.a("极光头像", p1)
                            if (file.exists()) {
                                deletePic(file.absolutePath)
//                            if (file.delete()){
//                                LogUtils.a("删除头像","成功")
//                            }else{
////                                file.delete()
//                                LogUtils.a("删除头像","失败")
//                            }
                            }
                        }
                    })
                } else {
//                    ShowDialog.showCustomDialogNoTitle(activity!!, "由于无法获取设备信息的权限,应用无法运行，请前往设置中心应用权限页设置", "去设置", "取消", object : DialogInterface.OnClickListener {
//                        override fun onClick(dialog: DialogInterface, which: Int) {
//                            when (which) {
//                                DialogInterface.BUTTON_POSITIVE -> {
//                                    dialog.dismiss()
//                                    isflag2=false
//                                    UserPermissions.gotoSet(activity!!)
//                                }
//                                DialogInterface.BUTTON_NEGATIVE -> {
//                                    dialog.dismiss()
//                                }
//                            }
//                        }
//                    })
                }
            }
        }

        tv_user_order_num.text=user.orderNum.toString()
        tv_user_fans_num.text=user.fansNum.toString()
//        tv_user_guan_num.text=user.followNum.toString()
        tv_user_name.text=user.nickname
        if (user.sex==1)
            tv_user_sex.text="男"
        else
            tv_user_sex.text="女"

        tv_user_age.text=user.age.toString()+"岁"
        tv_user_constellation.text=user.constellation
        tv_user_occupationName.text=user.occupationName
        tv_user_id.text="ID:${user.bixinId}"
        var state=DBUtils.getUser().leaderIDCardAuditState

        Click.viewClick(layout_person_broker).subscribe {
            if (user.isPerfectData==1) {
                if (com.ycwl.servebixin.cn.db.user.getIdentity()==3){
                    if (com.ycwl.servebixin.cn.db.user.getRealname()==1) {
                        if (state==-1){
                            ShowDialog.showCustomDialogs(mContext, "由于政策原因，为了更好的提供服务，建议您上传本人手持身份证照片","去上传","取消",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface, which: Int) {
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            dialog.dismiss()
                                            intentUtils.intentApplyBrokerUpload()
                                        }
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                            dialog.dismiss()
                                        }
                                    }
                                }
                            })

                        }else if (state==0){
                            ShowDialog.showCustomDialogs(mContext, "本人手持身份证照片审核失败","去查看","取消",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface, which: Int) {
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            dialog.dismiss()
                                            intentUtils.intentApplyBrokerRecord()
                                        }
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                            dialog.dismiss()
                                        }
                                    }
                                }
                            })

                        }else if (state==2){
                            Toast.Tips("经纪人资料正在审核中,请耐心等待审核结果")
                        }else {
                            intentUtils.intentBroker()
                        }
                    }else if (com.ycwl.servebixin.cn.db.user.getRealname()==-1){
                        ShowDialog.showCustomDialogs(mContext,"由于政策原因，为了更好的提供服务,需要您先完成实名认证","去认证","取消",object :DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> {
                                        dialog.dismiss()
                                        intentUtils.intentRealnameName()
                                    }
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        dialog.dismiss()
                                    }
                                }
                            }
                        })
                    }else if (com.ycwl.servebixin.cn.db.user.getRealname()==2){
                        ShowDialog.showCustomDialogs(mContext,"实名认证正在审核中","去查看","取消",object :DialogInterface.OnClickListener{
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
                        ShowDialog.showCustomDialogs(mContext,"由于政策原因，为了更好的提供服务,需要您先完成实名认证","去认证","取消",object :DialogInterface.OnClickListener{
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
                    if (state==-1){
                        intentUtils.intentApplyBroker()
                    }else{
                        intentUtils.intentApplyBrokerRecord()
                    }

                }
            }
            else {
                when(user.dataAuditState){
                    -1->{
                        Toast.Tips("请先完善资料")
                    }
                    0->{
                        Toast.Tips("资料审核未通过，请重新提交审核")
                    }
                    2->{
                        Toast.Tips("资料审核中")
                    }
                }

            }

        }

        Click.viewClick(layout_person_ordering).subscribe {
            if (user.isPerfectData==1) {
                if (com.ycwl.servebixin.cn.db.user.getIdentity()==3) {
                    if (com.ycwl.servebixin.cn.db.user.getRealname()==1) {
                        if (state==-1){
                            ShowDialog.showCustomDialogs(mContext, "由于政策原因，为了更好的提供服务，建议您上传本人手持身份证照片","去上传","取消",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface, which: Int) {
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            dialog.dismiss()
                                            intentUtils.intentApplyBrokerUpload()
                                        }
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                            dialog.dismiss()
                                        }
                                    }
                                }
                            })

                        }else if (state==0){
                            ShowDialog.showCustomDialogs(mContext, "本人手持身份证照片审核失败","去查看","取消",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface, which: Int) {
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            dialog.dismiss()
                                            intentUtils.intentApplyBrokerRecord()
                                        }
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                            dialog.dismiss()
                                        }
                                    }
                                }
                            })

                        }else if (state==2){
                            Toast.Tips("经纪人资料正在审核中,请耐心等待审核结果")
                        }else {
                            intentUtils.intentYue()
                        }
                    }else if (com.ycwl.servebixin.cn.db.user.getRealname()==-1){
                        ShowDialog.showCustomDialogs(mContext,"由于政策原因，为了更好的提供服务,需要您先完成实名认证","去认证","取消",object :DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> {
                                        dialog.dismiss()
                                        intentUtils.intentRealnameName()
                                    }
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        dialog.dismiss()
                                    }
                                }
                            }
                        })
                    }else if (com.ycwl.servebixin.cn.db.user.getRealname()==2){
//                        Toast.Tips("实名认证正在审核中,请耐心等待审核结果...")
                        ShowDialog.showCustomDialogs(mContext,"实名认证正在审核中","去查看","取消",object :DialogInterface.OnClickListener{
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
                        ShowDialog.showCustomDialogs(mContext,"由于政策原因，为了更好的提供服务,需要您先完成实名认证","去认证","取消",object :DialogInterface.OnClickListener{
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
                    Toast.Tips("请先申请成为达人经纪人")
                }
            }
            else {
                when(user.dataAuditState){
                    -1->{
                        Toast.Tips("请先完善资料")
                    }
                    0->{
                        Toast.Tips("资料审核未通过，请重新提交审核")
                    }
                    2->{
                        Toast.Tips("资料审核中")
                    }
                }

            }

        }
        Click.viewClick(layout_person_service).subscribe {
            if (user.isPerfectData==1) {
                intentUtils.intentServeSet()
            }
            else {
                when(user.dataAuditState){
                    -1->{
                        Toast.Tips("请先完善资料")
                    }
                    0->{
                        Toast.Tips("资料审核未通过，请重新提交审核")
                    }
                    2->{
                        Toast.Tips("资料审核中")
                    }
                }

            }

        }
    }

    override fun setFragmentListener() {

        Click.viewClick(tv_person_complete).subscribe {
            intentUtils.intentComplete()
        }

        Click.viewClick(defaultEdit).subscribe {
            intentUtils.intentEditUser()
        }
//        Click.viewClick(mineEdit).subscribe {
//            intentUtils.intentEditUser()
//        }
        Click.viewClick(layout_person_money).subscribe {
            incomePresenter.getIncome()
        }



        Click.viewClick(layout_person_set).subscribe {
            intentUtils.intentSet()
        }

        Click.viewClick(layout_user_id).subscribe {
            intentUtils.intentBiXinIDCode()
        }

        Click.viewClick(fans).subscribe {
            intentUtils.intentFans()
        }

    }

    override fun layoutID(): Int = R.layout.fragment_person

    private val presenter by lazy { PersonPresenter(this, this, activity!!) }
    private val incomePresenter by lazy { IncomePresenter(this,this,activity!!) }
//    private val locationPresenter by lazy { LocationPresenter(this, this, activity!!) }
//    private var locationUtils=LocationUtils(this)
    private var isCreated = false
    private var isflag=false
    private var app:AppProject=AppProject()
    private var isLocation=false
    private var isflag2=false
    private var isflag3=false

    override fun getPersonDataRequest(data: ByCodeBean) {
        errorLayout.visibility=View.GONE
        DBUtils.setUser(ByCodeBean.DataBean(data.data.tokenCreateTime,data.data.token,data.data.identity,data.data.rongToken,data.data.phone,data.data.nickname,data.data.sex,data.data.birthday,
                data.data.constellation,data.data.avatar,data.data.occupationName,data.data.isPerfectData,data.data.leaderIDCardAuditState,data.data.dataAuditState,data.data.isEnableSkill,data.data.serviceIDCardAuditState,
                data.data.orderNum, data.data.fansNum,data.data.followNum,data.data.bixinId,data.data.userId,user.getUserIM(),data.data.age,data.data.videos,data.data.tags))
        if (data.data.videos!=null&&data.data.videos.size>0){
//            if (data.data.videos[0].type==1){
//                DBUtils.setVideo(data.data.videos[0])
//            }else {
//                data.data.videos.forEach {
//                    PicturesUtils.addPicture(it)
//                }
//            }
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
        }
        if (data.data.tags!=null&&data.data.tags.size>0){
            TagsUtils.deleteAllTags()
            data.data.tags.forEach {
                TagsUtils.addTag(it)
            }
        }else{
            TagsUtils.deleteAllTags()
        }
        if (data.data.occupationName==null||data.data.occupationName==""){
            user.setOccupation("")
            user.setOccupationID("")
        }
        init()
    }

    override fun getPersonDataError() {
//        errorLayout.visibility=View.VISIBLE
//        Click.viewClick(errorLayout).subscribe {
//            presenter.getPersonData()
//        }
        initFirst()
    }

    override fun onResume() {
        super.onResume()
        LogUtils.a("resume","测试")
        initFirst()
        presenter.getPersonData()

    }

    override fun openEventBus(): Boolean = false


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden){
            LogUtils.a("hidden","测试2")
            presenter.getPersonData()
        }

    }
    private fun deletePic(path:String){
        if(!TextUtils.isEmpty(path)){
            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val contentResolver = activity!!.getContentResolver()//cutPic.this是一个上下文
            val url =  MediaStore.Images.Media.DATA + "=?"
            //删除图片
            contentResolver.delete(uri, url, arrayOf(path))
        }
    }

//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        if (!isCreated) {
//            return
//        }
//
//        if (isVisibleToUser){
//            LogUtils.a("显示","测试")
//            presenter.getPersonData()
//        }
//    }


}