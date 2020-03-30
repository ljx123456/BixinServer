package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.SetWithdrawPwdBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawChangePwdBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.WithdrawResetPwdData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawResetPwdView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class WithdrawResetPwdPresenter(owner: LifecycleOwner, val view: WithdrawResetPwdView, val context: Context) : BasePresenter(owner, view, context), WithdrawResetPwdData.WithdrawResetPwd{

    private val reset=WithdrawResetPwdData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        reset.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getWithdrawResetPwdRequest(data: SetWithdrawPwdBean) {
        view.dismissLoading(context)
        view.getWithdrawResetPwdRequest()
    }

    override fun getWithdrawResetPwdError() {
        view.dismissLoading(context)
    }

    fun getWithdrawResetPwd(body:WithdrawChangePwdBody){
        view.showLoading(context)
        reset.getWithdrawResetPwd(body)
    }

}