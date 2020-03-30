package com.ycwl.servebixin.cn.ui.broker.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerRecordDetailsBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.ApplyBrokerRecordDetailsBody
import com.ycwl.servebixin.cn.ui.broker.mvp.data.ApplyBrokerRecordDetailsData
import com.ycwl.servebixin.cn.ui.broker.mvp.view.ApplyBrokerRecordDetailsView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ApplyBrokerRecordDetailsPresenter(owner: LifecycleOwner, val view: ApplyBrokerRecordDetailsView, val context: Context) : BasePresenter(owner, view, context), ApplyBrokerRecordDetailsData.ApplyBrokerRecordDetails {

    private val details=ApplyBrokerRecordDetailsData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        details.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getApplyBrokerRecordDetailsRequest(data: ApplyBrokerRecordDetailsBean) {
        view.dismissLoading(context)
        view.getApplyBrokerRecordDetailsRequest(data)
    }

    override fun getApplyBrokerRecordDetailsError(data: Int) {
        view.dismissLoading(context)
    }

    fun getApplyBrokerRecordDetails(body:ApplyBrokerRecordDetailsBody){
        view.showLoading(context)
        details.getApplyBrokerRecordDetails(body)
    }

}