package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeRecordListBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.IncomeRecordListBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.IncomeRecordData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.IncomeRecordView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class IncomeRecordPresenter(owner: LifecycleOwner, val view: IncomeRecordView, val context: Context) : BasePresenter(owner, view, context),IncomeRecordData.IncomeRecord{

    private val record=IncomeRecordData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        record.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getIncomeRecordRequest(data: IncomeRecordListBean) {
        view.dismissLoading(context)
        view.getIncomeRecordRequest(data)
    }

    override fun getIncomeRecordError() {
        view.dismissLoading(context)
        view.getIncomeRecordError()
    }

    fun getIncomeRecord(body:IncomeRecordListBody){
        view.showLoading(context)
        record.getIncomeRecord(body)
    }

}