package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.IncomeData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.IncomeView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class IncomePresenter(owner: LifecycleOwner, val view: IncomeView, val context: Context) : BasePresenter(owner, view, context),IncomeData.Income{
    private val income=IncomeData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        income.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getIncomeRequest(data: IncomeBean) {
        view.dismissLoading(context)
        view.getIncomeRequest(data)
    }

    override fun getIncomeError(data: Int) {
        view.dismissLoading(context)
        view.getIncomeError(data)
    }

    fun getIncome(){
        view.showLoading(context)
        income.getIncome()
    }

}