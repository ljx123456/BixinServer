package com.ycwl.servebixin.cn.base

import android.view.View
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.event.LoginStateChangeEvent
import com.blankj.utilcode.util.ActivityUtils
import com.ycwl.servebixin.cn.db.*
import com.ycwl.servebixin.cn.ui.main.dialog.OtherLoginDialog
import com.ycwl.servebixin.cn.utils.dialog.LoadDialog
import jiguang.chat.utils.FileHelper
import jiguang.chat.utils.SharePreferenceManager
import org.greenrobot.eventbus.EventBus


open abstract class BaseFragment : FatherFragment() {


    override fun onCreateFragment(contentView: View?) {
        openActivityEventBus()
        setLayoutTitle()
        initFragmentData()
        setFragmentListener()
//        //注册sdk的event用于接收各种event事件
//        JMessageClient.registerEventReceiver(this)
    }

    protected abstract fun openEventBus(): Boolean

    protected abstract fun setLayoutTitle()

    protected abstract fun initFragmentData()

    protected abstract fun setFragmentListener()

    fun show() {
        LoadDialog.show(mContext)
    }

    fun dismiss() {
        LoadDialog.dismiss(mContext)
    }

    private fun openActivityEventBus() {
        if (openEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    private fun closeActivityEventBus() {
        if (openEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        closeActivityEventBus()
//        JMessageClient.unRegisterEventReceiver(this)
    }

//    fun onEventMainThread(event: LoginStateChangeEvent) {
//        val reason = event.reason
//        val myInfo = event.myInfo
//        if (myInfo != null) {
//            val path: String
//            val avatar = myInfo.avatarFile
//            if (avatar != null && avatar.exists()) {
//                path = avatar.absolutePath
//            } else {
//                path = FileHelper.getUserAvatarPath(myInfo.userName)
//            }
//            SharePreferenceManager.setCachedUsername(myInfo.userName)
//            SharePreferenceManager.setCachedAvatarPath(path)
//            //            JMessageClient.logout();
//            var dialog = OtherLoginDialog()
//            dialog.show(activity!!.supportFragmentManager, "")
//            DBUtils.DelUser()
//            DBUtils.delMerchat()
//            ServeUtils.deleteALLServe()
//            DrinkUtils.deleteALLDrinks()
//            PicturesUtils.deleteAllPictures()
//            TagsUtils.deleteAllTags()
//            DBUtils.DelVideo()
////        JPushInterface.deleteAlias(this,1)
////            ActivityUtils.finishAllActivities()
//            user.setOccupation("")
//            user.setOccupationID("")
//            user.setUserSkillID("")
//            user.setSkillTypeID("")
////            OrderServeUtils.deleteAllOrder()
////                    JMessageClient.logout()
////            user.setNum("0")
////            user.setOrderNo("")
//        }
//    }


}
