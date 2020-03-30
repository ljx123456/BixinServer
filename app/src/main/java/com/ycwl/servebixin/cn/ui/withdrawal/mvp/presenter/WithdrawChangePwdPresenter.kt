package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.SetWithdrawPwdBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawChangePwdBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.WithdrawChangePwdData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawChangePwdView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class WithdrawChangePwdPresenter (owner: LifecycleOwner, val view: WithdrawChangePwdView, val context: Context) : BasePresenter(owner, view, context), WithdrawChangePwdData.WithdrawChangePwd{


    private val change =WithdrawChangePwdData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        change.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getWithdrawChangePwdRequest(data: SetWithdrawPwdBean) {
        view.dismissLoading(context)
        view.getWithdrawChangePwdAgainRequest()
    }

    override fun getWithdrawChangePwdError() {
        view.dismissLoading(context)
    }

    fun getWithdrawChangePwd(body:WithdrawChangePwdBody){
        view.showLoading(context)
        change.getWithdrawChangePwd(body)
    }

}