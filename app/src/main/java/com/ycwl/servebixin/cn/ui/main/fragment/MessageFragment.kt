package com.ycwl.servebixin.cn.ui.main.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.model.UserInfo
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.ycwl.servebixin.cn.JMessage.ConversationListFragment

import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseFragment
import com.ycwl.servebixin.cn.ui.login.adapter.FragmentAdapter
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.MessagePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.MessageView
import com.ycwl.servebixin.cn.ui.message.fragment.NotificationFragment
import com.ycwl.servebixin.cn.ui.message.mvp.body.NotificationBody
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment: BaseFragment(),MessageView {
    override fun getClearNotificationRequest() {
        (fragments[messageLayout.currentItem] as NotificationFragment).clear()
    }

    override fun getClearNotificationError() {

    }

    private var isCreated=false
    private var fragments = ArrayList<Fragment>()
    private val presenter by lazy { MessagePresenter(this,this,activity!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCreated=true
    }

    override fun setLayoutTitle() {

    }

    override fun initFragmentData() {
        var titles = ArrayList<String>()
        titles.add("通知")
        titles.add("聊天")


        titles.forEach {
            messageTab.addTab(messageTab.newTab().setText(it))
        }
        fragments.add(NotificationFragment())
        fragments.add(ConversationListFragment())
        mFragmentAdapter = FragmentAdapter(childFragmentManager, fragments, titles)
        messageLayout.adapter = mFragmentAdapter
        messageTab.setupWithViewPager(messageLayout)
        messageTab.setTabsFromPagerAdapter(mFragmentAdapter)
        messageLayout.setOffscreenPageLimit(1)
    }

    override fun setFragmentListener() {
        Click.viewClick(tv_clear).subscribe {
            ShowDialog.showCustomDialogs(activity!!,"是否清空所有通知信息？","清空","取消",object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
//                            presenter.getDelServices(DelServicesBody("${data.data.listId}"))
                            try {
                                if(fragments[messageLayout.currentItem] is NotificationFragment){
                                    presenter.clearNotification()
                                }else if (fragments[messageLayout.currentItem] is ConversationListFragment){
                                    if (JMessageClient.getConversationList()!=null&& JMessageClient.getConversationList().size>0) {
                                        JMessageClient.getConversationList().forEach {
//                                            var user=it.targetInfo as UserInfo
//                                            JMessageClient.deleteSingleConversation(user.userName,user.appKey)
                                            if (JMessageClient.deleteSingleConversation((it.getTargetInfo() as UserInfo).userName, (it.getTargetInfo() as UserInfo).appKey)){
                                                Log.e("测试","删除成功")
                                            }else{
                                                Log.e("测试","删除失败")
                                            }
                                        }
                                        (fragments[messageLayout.currentItem] as ConversationListFragment).delConversation()

                                    }
                                }
                            }catch (e: java.lang.Exception){
                                e.printStackTrace()
                            }
                            dialog.dismiss()
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }

            })
        }

    }

    override fun layoutID(): Int = R.layout.fragment_message

    override fun openEventBus(): Boolean = false
    var mFragmentAdapter: FragmentAdapter? = null
//    override fun getActivityLayout(): Int = R.layout.fragment_message

//    override fun setActivityTitle() {
//        titleText.setText("消息中心")
//        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
//    }

//    override fun initActivityData() {
//
//    }
//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        if (!isCreated) {
//            return
//        }
//
//        if (isVisibleToUser) {
//            Log.e("测试", "进来了")
//            try {
//                if (fragments[messageLayout.currentItem] is NotificationFragment){
//                    (fragments[messageLayout.currentItem] as NotificationFragment).init()
//                }else if (fragments[messageLayout.currentItem] is ConversationListFragment){
//                    (fragments[messageLayout.currentItem] as ConversationListFragment).init()
//                }
//            }catch (e:Exception){
//                e.printStackTrace()
//            }
//
//
////            bannerPresenter.getBanner()
//        }
//
//    }
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden){
            LogUtils.a("hidden","测试2")
            try {
                if (fragments[messageLayout.currentItem] is NotificationFragment){
                    (fragments[messageLayout.currentItem] as NotificationFragment).init()
                }else if (fragments[messageLayout.currentItem] is ConversationListFragment){
                    (fragments[messageLayout.currentItem] as ConversationListFragment).init()
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    }



//
//    override fun clickListener() {
//        Click.viewClick(titleLeft).subscribe { finish() }
//    }
}