package com.ycwl.servebixin.cn.ui.order.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderFormDetailsBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormDetailsBody
import com.ycwl.servebixin.cn.ui.order.mvp.data.OrderFormDetailsData
import com.ycwl.servebixin.cn.ui.order.mvp.view.OrderFormDetailsView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class OrderFormDetailsPresenter(owner: LifecycleOwner, val view: OrderFormDetailsView, val mContext: Context) : BasePresenter(owner, view, mContext), OrderFormDetailsData.OrderFormDetails{

    private val order=OrderFormDetailsData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        order.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getOrderFormDetailsRequest(data: OrderFormDetailsBean) {
        view.dismissLoading(mContext)
        view.getDetailsRequest(data)
    }

    override fun getOrderFormDetailsError(code:Int) {
        view.dismissLoading(mContext)
        view.getDetailsError(code)
    }

    fun getOrderFormDetails(body:OrderFormDetailsBody){
        view.showLoading(mContext)
        order.getOrderFormDetails(body)
    }

}