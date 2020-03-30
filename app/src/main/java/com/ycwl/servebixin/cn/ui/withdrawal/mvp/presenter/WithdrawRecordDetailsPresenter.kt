package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawRecordDetailsBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawRecordDetailsBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.WithdrawRecordDetailsData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawRecordDetailsView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class WithdrawRecordDetailsPresenter(owner: LifecycleOwner, val view: WithdrawRecordDetailsView, val context: Context) : BasePresenter(owner, view, context), WithdrawRecordDetailsData.WithdrawRecordDetails{

    private val details= WithdrawRecordDetailsData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        details.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getWithdrawRecordDetailsRequest(data: WithdrawRecordDetailsBean) {
        view.dismissLoading(context)
        view.getRecordDetailsRequest(data)
    }

    override fun getWithdrawRecordDetailsError() {
        view.dismissLoading(context)
    }

    fun getWithdrawRecordDetails(body: WithdrawRecordDetailsBody){
        view.showLoading(context)
        details.getWithdrawRecordDetails(body)
    }

}