package com.ycwl.servebixin.cn.ui.broker.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerServeBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerServeBody
import com.ycwl.servebixin.cn.ui.broker.mvp.data.BrokerServeData
import com.ycwl.servebixin.cn.ui.broker.mvp.view.BrokerServeView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class BrokerServePresenter(owner: LifecycleOwner, val view: BrokerServeView, val context: Context) : BasePresenter(owner, view, context), BrokerServeData.BrokerServe{

    private val serve=BrokerServeData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        serve.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getBrokerServeRequest(data: BrokerServeBean) {
        view.dismissLoading(context)
        view.getBrokerServeRequest(data)
    }

    override fun getBrokerServeError() {
        view.dismissLoading(context)
    }

    fun getBrokerServe(body:BrokerServeBody){
        view.showLoading(context)
        serve.getBrokerServe(body)
    }
}
