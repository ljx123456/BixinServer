package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawRecordListBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawRecordListBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.WithdrawRecordData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawRecordView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class WithdrawRecordPresenter(owner: LifecycleOwner, val view: WithdrawRecordView, val context: Context) : BasePresenter(owner, view, context), WithdrawRecordData.WithdrawRecord {

    private var record=WithdrawRecordData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        record.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getWithdrawRecordRequest(data: WithdrawRecordListBean) {
        view.dismissLoading(context)
        view.getWithdrawRecordRequest(data)
    }

    override fun getWithdrawRecordError() {
        view.dismissLoading(context)
        view.getWithdrawRecordError()
    }

    fun getWithdrawRecord(body:WithdrawRecordListBody){
        record.getWithdrawRecord(body)
    }
}