package com.ycwl.servebixin.cn.ui.order.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderDrinkCodeBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderDrinkCodeBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormDrinkBody
import com.ycwl.servebixin.cn.ui.order.mvp.data.OrderDrinkCodeData
import com.ycwl.servebixin.cn.ui.order.mvp.data.OrderFormDrinkData
import com.ycwl.servebixin.cn.ui.order.mvp.view.OrderFormDrinkView
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class OrderFormDrinkPresenter(owner: LifecycleOwner, val view: OrderFormDrinkView, val mContext: Context) : BasePresenter(owner, view, mContext), OrderFormDrinkData.OrderFormDrink, OrderDrinkCodeData.OrderDrinkCode {
    override fun getOrderDrinkCodeRequest(data: OrderDrinkCodeBean) {
        view.dismissLoading(mContext)
        view.getDrinkCodeRequest(data)
    }

    override fun getOrderDrinkCodeError() {
        view.dismissLoading(mContext)
    }

    private val data=OrderFormDrinkData(this)
    private val code=OrderDrinkCodeData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        data.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getOrderFormDrinkRequest(data: DrinksBean) {
        view.dismissLoading(mContext)
        view.getDrinksRequest(data)
    }

    override fun getOrderFormDrinkError() {
        view.dismissLoading(mContext)
    }

    fun getOrderFormDrink(body:OrderFormDrinkBody){
        view.showLoading(mContext)
        data.getOrderFormDrink(body)
    }

    fun getOrderDrinkCode(body:OrderDrinkCodeBody){
        view.showLoading(mContext)
        code.getOrderDrinkCode(body)
    }
}