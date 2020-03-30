package com.ycwl.servebixin.cn.ui.broker.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerBean
import com.ycwl.servebixin.cn.ui.broker.mvp.data.BrokerData
import com.ycwl.servebixin.cn.ui.broker.mvp.view.BrokerView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class BrokerPresenter(owner: LifecycleOwner, val view: BrokerView, val context: Context) : BasePresenter(owner, view, context), BrokerData.Broker{

    private val broker=BrokerData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        broker.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getBrokerRequest(data: BrokerBean) {
        view.dismissLoading(context)
        view.getBrokerRequest(data)
    }

    override fun getBrokerError(data: Int) {
        view.dismissLoading(context)
    }

    fun getBroker(){
        view.showLoading(context)
        broker.getBroker()
    }

}