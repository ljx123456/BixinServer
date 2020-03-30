package com.ycwl.servebixin.cn.ui.order.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormAcceptBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormClockBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormRefuseBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormRefuseReasonsBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormAcceptBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormClockBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormRefuseBody
import com.ycwl.servebixin.cn.ui.order.mvp.data.OrderFormAcceptData
import com.ycwl.servebixin.cn.ui.order.mvp.data.OrderFormClockData
import com.ycwl.servebixin.cn.ui.order.mvp.data.OrderFormRefuseData
import com.ycwl.servebixin.cn.ui.order.mvp.data.OrderFormRefuseReasonsData
import com.ycwl.servebixin.cn.ui.order.mvp.view.OrderFormMakeView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class OrderFormMakePresenter(owner: LifecycleOwner, val view: OrderFormMakeView, val mContext: Context) : BasePresenter(owner, view, mContext)
        ,OrderFormAcceptData.OrderFormAccept,OrderFormClockData.OrderFormClock,OrderFormRefuseReasonsData.OrderFormRefuseReasons,OrderFormRefuseData.OrderFormRefuse{

    private val orderAccept=OrderFormAcceptData(this)
    private val orderClock=OrderFormClockData(this)
    private val orderRefuseReason=OrderFormRefuseReasonsData(this)
    private val orderRefuse=OrderFormRefuseData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
//        orderAccept.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getOrderFormAcceptRequest(data: OrderFormAcceptBean) {
        view.dismissLoading(mContext)
        view.getAcceptRequest(data)
    }

    override fun getOrderFormAcceptError(code:Int) {
        view.dismissLoading(mContext)
        view.getAcceptError(code)
    }

    override fun getOrderFormClockRequest(data: OrderFormClockBean) {
        view.dismissLoading(mContext)
        view.getClockRequest(data)
    }

    override fun getOrderFormClockError(flag: Int) {
        view.dismissLoading(mContext)
        view.getClockError(flag)

    }

    override fun getOrderFormRefuseReasonsRequest(data: OrderFormRefuseReasonsBean) {
        view.dismissLoading(mContext)
        view.getRefuseReasonsRequest(data)
    }

    override fun getOrderFormRefuseReasonsError() {
        view.dismissLoading(mContext)
    }

    override fun getOrderFormRefuseRequest(data: OrderFormRefuseBean) {
        view.dismissLoading(mContext)
        view.getRefuseRequest(data)
    }

    override fun getOrderFormRefuseError() {
        view.dismissLoading(mContext)
    }

    //接单
    fun getAccept(body:OrderFormAcceptBody){
        view.showLoading(mContext)
        orderAccept.getOrderFormAccept(body)
    }

    //打卡
    fun getClock(body:OrderFormClockBody){
        view.showLoading(mContext)
        orderClock.getOrderFormClock(body)
    }

    //拒绝理由
    fun getRefuseReason(){
        view.showLoading(mContext)
        orderRefuseReason.getOrderFormRefuseReasons()
    }

    //拒绝
    fun getRefuse(body:OrderFormRefuseBody){
        view.showLoading(mContext)
        orderRefuse.getOrderFormRefuse(body)
    }
}