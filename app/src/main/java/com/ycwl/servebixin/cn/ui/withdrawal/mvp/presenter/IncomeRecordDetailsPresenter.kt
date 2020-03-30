package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeRecordDetailsBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.IncomeRecordDetailsBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.IncomeRecordDetailsData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.IncomeRecordDetailsView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class IncomeRecordDetailsPresenter(owner: LifecycleOwner, val view: IncomeRecordDetailsView, val context: Context) : BasePresenter(owner, view, context), IncomeRecordDetailsData.IncomeRecordDetails{

    private val details=IncomeRecordDetailsData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        details.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getIncomeRecordDetailsRequest(data: IncomeRecordDetailsBean) {
        view.dismissLoading(context)
        view.getIncomeRecordDetailsRequest(data)
    }

    override fun getIncomeRecordDetailsError(data: Int) {
        view.dismissLoading(context)
    }

    fun getIncomeRecordDetails(body:IncomeRecordDetailsBody){
        view.showLoading(context)
        details.getIncomeRecordDetails(body)
    }

}