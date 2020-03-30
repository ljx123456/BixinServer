package com.ycwl.servebixin.cn.ui.order.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderDrinkCodeBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderDrinkBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderDrinkCodeBody
import com.ycwl.servebixin.cn.ui.order.mvp.data.GrabOrderDrinkCodeData
import com.ycwl.servebixin.cn.ui.order.mvp.data.GrabOrderDrinkData
import com.ycwl.servebixin.cn.ui.order.mvp.view.GrabOrderDrinkView
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class GrabOrderDrinkPresenter(owner: LifecycleOwner, val view: GrabOrderDrinkView, val mContext: Context) : BasePresenter(owner, view, mContext), GrabOrderDrinkData.GrabOrderDrink,GrabOrderDrinkCodeData.GrabOrderDrinkCode{

    private val order=GrabOrderDrinkData(this)
    private val code=GrabOrderDrinkCodeData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {

    }

    override fun presenterDestroy() {

    }

    override fun getGrabOrderDrinkRequest(data: DrinksBean) {
        view.dismissLoading(mContext)
        view.getDrinksRequest(data)
    }

    override fun getGrabOrderDrinkError() {
        view.dismissLoading(mContext)
    }

    override fun getGrabOrderDrinkCodeRequest(data: OrderDrinkCodeBean) {
        view.dismissLoading(mContext)
        view.getDrinkCodeRequest(data)
    }

    override fun getGrabOrderDrinkCodeError() {
        view.dismissLoading(mContext)
    }

    fun getGrabOrderDrink(body:GrabOrderDrinkBody){
        view.showLoading(mContext)
        order.getGrabOrderDrink(body)
    }

    fun getGrabOrderDrinkCode(body:GrabOrderDrinkCodeBody){
        view.showLoading(mContext)
        code.getGrabOrderDrinkCode(body)
    }

}