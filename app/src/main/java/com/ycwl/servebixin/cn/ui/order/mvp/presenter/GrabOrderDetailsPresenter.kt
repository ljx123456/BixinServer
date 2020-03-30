package com.ycwl.servebixin.cn.ui.order.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.order.mvp.bean.GrabOrderDetailsBean
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderDetailsBody
import com.ycwl.servebixin.cn.ui.order.mvp.data.GrabOrderDetailsData
import com.ycwl.servebixin.cn.ui.order.mvp.view.GrabOrderDetailsView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class GrabOrderDetailsPresenter(owner: LifecycleOwner, val view: GrabOrderDetailsView, val mContext: Context) : BasePresenter(owner, view, mContext), GrabOrderDetailsData.GrabOrderDetails{

    private val order=GrabOrderDetailsData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        order.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getGrabOrderDetailsRequest(data: GrabOrderDetailsBean) {
        view.dismissLoading(mContext)
        view.getGrabOrderDetailsRequest(data)
    }

    override fun getGrabOrderDetailsError(code:Int) {
        view.dismissLoading(mContext)
        view.getGrabOrderDetailsError(code)
    }

    fun getGrabOrderDetails(body:GrabOrderDetailsBody){
        view.showLoading(mContext)
        order.getGrabOrderDetails(body)
    }
}