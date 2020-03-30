package com.ycwl.servebixin.cn.ui.broker.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerRecordBean
import com.ycwl.servebixin.cn.ui.broker.mvp.data.ApplyBrokerRecordData
import com.ycwl.servebixin.cn.ui.broker.mvp.view.ApplyBrokerRecordView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ApplyBrokerRecordPresenter(owner: LifecycleOwner, val view: ApplyBrokerRecordView, val context: Context) : BasePresenter(owner, view, context), ApplyBrokerRecordData.ApplyBrokerRecord{

    private val record =ApplyBrokerRecordData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        record.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getApplyBrokerRecordRequest(data: ApplyBrokerRecordBean) {
        view.dismissLoading(context)
        view.getRecordRequest(data)
    }

    override fun getApplyBrokerRecordError(data: Int) {
        view.dismissLoading(context)
    }

    fun getApplyBrokerRecord(){
        view.showLoading(context)
        record.getApplyBrokerRecord()
    }

}