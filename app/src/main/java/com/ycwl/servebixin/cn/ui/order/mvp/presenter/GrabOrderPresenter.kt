package com.ycwl.servebixin.cn.ui.order.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormAcceptBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderBody
import com.ycwl.servebixin.cn.ui.order.mvp.data.GrabOrderData
import com.ycwl.servebixin.cn.ui.order.mvp.view.GrabOrderView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class GrabOrderPresenter(owner: LifecycleOwner, val view: GrabOrderView, val mContext: Context) : BasePresenter(owner, view, mContext), GrabOrderData.GrabOrder {

    private val order=GrabOrderData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        order.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getGrabOrderRequest(data: OrderFormAcceptBean) {
        view.dismissLoading(mContext)
        view.getGrabOrderRequest(data)
    }

    override fun getGrabOrderError(code:Int) {
        view.dismissLoading(mContext)
        view.getGrabOrderError(code)
    }

    fun getGrabOrder(body:GrabOrderBody){
        view.showLoading(mContext)
        order.getGrabOrder(body)
    }
}