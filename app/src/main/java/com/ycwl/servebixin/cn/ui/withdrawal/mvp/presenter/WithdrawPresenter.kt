package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.WithdrawData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class WithdrawPresenter(owner: LifecycleOwner, val view: WithdrawView, val context: Context) : BasePresenter(owner, view, context),WithdrawData.Withdraw{

    private val withdraw=WithdrawData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        withdraw.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getWithdrawRequest(data: WithdrawBean) {
        view.dismissLoading(context)
        view.getWithdrawRequest()
    }

    override fun getWithdrawError() {
        view.dismissLoading(context)
    }

    fun getWithdraw(body: WithdrawBody){
        view.showLoading(context)
        withdraw.getWithdraw(body)
    }

}