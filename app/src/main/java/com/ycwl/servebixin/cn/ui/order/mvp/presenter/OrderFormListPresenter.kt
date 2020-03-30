package com.ycwl.servebixin.cn.ui.order.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.order.mvp.bean.GrabOrderListBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormListBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderListBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormListBody
import com.ycwl.servebixin.cn.ui.order.mvp.data.GrabOrderListData
import com.ycwl.servebixin.cn.ui.order.mvp.data.OrderFormListData
import com.ycwl.servebixin.cn.ui.order.mvp.view.OrderFormListView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class OrderFormListPresenter(owner: LifecycleOwner, val view: OrderFormListView, val mContext: Context) : BasePresenter(owner, view, mContext), OrderFormListData.OrderFormList, GrabOrderListData.GrabOrderList {
    override fun getGrabOrderListRequest(data: GrabOrderListBean) {
//        view.dismissLoading(mContext)
        view.getGrabOrderListRequest(data)
    }

    override fun getGrabOrderListError(code:Int) {
//        view.dismissLoading(mContext)
        view.getGrabOrderListError(code)
    }

    private val order=OrderFormListData(this)
    private val grab=GrabOrderListData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        order.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getOrderFormListRequest(data: OrderFormListBean) {
//        view.dismissLoading(mContext)
        view.getOrderFormListRequest(data)
    }

    override fun getOrderFormListError(code:Int){
//        view.dismissLoading(mContext)
        view.getOrderFormListError(code)
    }

    fun getOrderFormList(body:OrderFormListBody){
//        view.showLoading(mContext)
        order.getOrderFormList(body)
    }

    fun getGrabOrderList(body:GrabOrderListBody){
//        view.showLoading(mContext)
        grab.getGrabOrderList(body)
    }

}