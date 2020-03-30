package com.ycwl.servebixin.cn.ui.message.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseFragment
import com.ycwl.servebixin.cn.ui.image.ImageBannerInfo
import com.ycwl.servebixin.cn.ui.image.ImageInfo
import com.ycwl.servebixin.cn.ui.message.adapter.NotificationAdapter
import com.ycwl.servebixin.cn.ui.message.mvp.bean.BannerBean
import com.ycwl.servebixin.cn.ui.message.mvp.bean.NotificationBean
import com.ycwl.servebixin.cn.ui.message.mvp.body.NotificationBody
import com.ycwl.servebixin.cn.ui.message.mvp.body.ReadNotiBody
import com.ycwl.servebixin.cn.ui.message.mvp.presenter.BannerPresenter
import com.ycwl.servebixin.cn.ui.message.mvp.presenter.NotificationPresenter
import com.ycwl.servebixin.cn.ui.message.mvp.view.BannerView
import com.ycwl.servebixin.cn.ui.message.mvp.view.NotificationView
import com.ycwl.servebixin.cn.ui.message.util.BannerUtils
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.layout_error_network.*
import kotlinx.android.synthetic.main.layout_none_data.*
import kotlinx.android.synthetic.main.layout_none_message.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class NotificationFragment:BaseFragment(),NotificationView,BannerView{
    override fun getBannerRequest(data: BannerBean) {
        if (data!=null&&data.data!=null&&data.data.size>0){
            notiBanner.visibility=View.VISIBLE
            var imagelist = ArrayList<ImageBannerInfo>()
            data.data.forEach {
                imagelist.add(ImageBannerInfo(it.image,true,0,it.url,it.name,it.description))
            }
            BannerUtils.setBanner(notiBanner,imagelist)
        }

    }

    override fun getReadNotiRequest() {
        // 类型：10 服务端订单，11 达人审核，12 资料审核，13 经纪人审核，14 系统，商家通知，15 领班关系，16 收益，17 提现,18 实名审核，19 举报，20 建议，21 商家通知
        notiAdapter!!.data[position].isRead=1
        notiAdapter!!.notifyDataSetChanged()
        when (type) {
            10 -> {
                if (serveOrderType==1){
                    intentUtils.intentOrderFormDetails(orderNo)
                }else{
                    intentUtils.intentGrabOrderDetails(inviteID)
                }
            }
            11 ->{
                intentUtils.intentCompleteDataDetails()
            }
            14-> intentUtils.intentActivityHtml(messageId,messageType)
//            13 -> intentUtils.intentApplyBrokerRecordDetails(auditId)
            15 -> intentUtils.intentBindNoti(id)
            16 -> intentUtils.intentIncomeRecordDetails(walletRecodeId)
            17 -> intentUtils.intentWithdrawRecordDetails(walletRecodeId)
            18 -> intentUtils.intentSetAccount()

            20 -> intentUtils.intentFeedBackDetails(feedbackId)

            21-> intentUtils.intentActivityHtml(messageId,messageType)
//                3 -> intentDiscount()
//                4 -> intentWine()
//                5 -> intentOrderDetils(info.orderNo)
        }
    }

    private val presenter by lazy { NotificationPresenter(this,this,activity!!) }
    private val bannerPresenter by lazy { BannerPresenter(this,this,activity!!) }
    private var pageIndex: Int = 1
    private var pageSize: Int = 10
    private var position=0
    private var type=0
    private var id=""
    private var orderNo=""
    private var inviteID=""
    private var serveOrderType=1
    private var auditId=0
    private var walletRecodeId=0
    private var feedbackId=""
    private lateinit var notiAdapter: NotificationAdapter
    private var isCreated = false
    private var messageId=""
    private var messageType=1

    override fun openEventBus(): Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCreated=true
    }

    override fun setLayoutTitle() {

    }

    override fun initFragmentData() {
        notiRefresh.isRefreshing = true
        presenter.getNotification(NotificationBody(pageIndex, pageSize))
        bannerPresenter.getBanner()
    }

    override fun setFragmentListener() {
        notiRefresh.setOnRefreshListener {
            pageIndex = 1
            presenter.getNotification(NotificationBody(pageIndex, pageSize))
        }
        Click.viewClick(errorLayout).subscribe {
            pageIndex = 1
            presenter.getNotification(NotificationBody(pageIndex, pageSize))
        }
    }

    override fun layoutID(): Int = R.layout.fragment_notification

    override fun getNotificationRequest(data: NotificationBean) {
        errorLayout.visibility = View.GONE
        notiRefresh.isRefreshing = false
        if (data!=null&&data.data!=null&&data.data.size>0) {
            notiRefresh.visibility=View.VISIBLE
            noMessageLayout.visibility=View.GONE
            if (pageIndex == 1) {
                notiAdapter = NotificationAdapter(data.data)
                var manager = LinearLayoutManager(mContext)
                manager.orientation = LinearLayout.VERTICAL
                notiList.layoutManager = manager
                notiList.adapter = notiAdapter
            } else {
                notiAdapter.addData(data.data)
            }
            notiAdapter.setOnLoadMoreListener(object :BaseQuickAdapter.RequestLoadMoreListener{
                override fun onLoadMoreRequested() {
                    pageIndex=pageIndex+1
                    presenter.getNotification(NotificationBody(pageIndex, pageSize))
                }
            },notiList)
            notiAdapter.setOnItemClickListener { adapter, view, position ->
                var info = notiAdapter.data.get(position)
                /**退款详情
                 *   0 -> intentRefundMerchant(refundadapter.data.get(position).refundId)
                else -> intentRefundServe(refundadapter.data.get(position).refundId)
                 */
                type=info.type
                this.position=position

                // 类型：10 服务端订单，11 达人审核，12 资料审核，13 经纪人审核，14 系统，15 领班关系，16 收益，17 提现，18 实名审核，19 举报，20 建议，21 商家通知
                when (info.type) {
                    10 -> {
                        serveOrderType=info.serviceOrderType
                        if (serveOrderType==1){
                            orderNo=info.orderNo
                        }else{
                            inviteID=info.inviteId.toString()
                        }
                    }
//                    13 -> auditId=info.auditId
                    14 ->{
                        messageId=info.messageId
                        messageType=info.messageType
                    }
                    15 -> id=info.id
                    16 -> walletRecodeId=info.walletRecodeId
                    17 -> walletRecodeId=info.walletRecodeId

                    20-> feedbackId=info.feedBackId

                    21 ->{
                        messageId=info.messageId
                        messageType=info.messageType
                    }
//                3 -> intentDiscount()
//                4 -> intentWine()
//                5 -> intentOrderDetils(info.orderNo)
                }
                presenter.getReadNoti(ReadNotiBody(info.id))
            }

            if (data.data.size < pageSize) {
                notiAdapter!!.loadMoreEnd()
            } else {
                notiAdapter!!.loadMoreComplete()
            }
        }else{

                if (pageIndex>1){
                    notiAdapter!!.loadMoreEnd()
                }else{
                    notiRefresh.visibility=View.GONE
                    noMessageLayout.visibility=View.VISIBLE
//                    errorLayout.visibility = View.VISIBLE
                }

        }
    }

    override fun getNotificationError() {
        notiRefresh.isRefreshing = false
        errorLayout.visibility = View.VISIBLE
        Click.viewClick(errorLayout).subscribe {
            presenter.getNotification(NotificationBody(pageIndex, pageSize))
            bannerPresenter.getBanner()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isCreated) {
            return
        }

        if (isVisibleToUser) {
            pageIndex = 1
            presenter.getNotification(NotificationBody(pageIndex, pageSize))
//            bannerPresenter.getBanner()
        }

    }

    fun init(){
        pageIndex = 1
        presenter.getNotification(NotificationBody(pageIndex, pageSize))
    }
//
//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
////        if (!hidden){
//            pageIndex = 1
//            presenter.getNotification(NotificationBody(pageIndex, pageSize))
////        }
//
//    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public fun getEvent(s:String){
        LogUtils.a("消息:"+s)
        pageIndex = 1
        presenter.getNotification(NotificationBody(pageIndex, pageSize))
    }

    fun clear(){
        notiRefresh.visibility=View.GONE
        noMessageLayout.visibility=View.VISIBLE
    }
}