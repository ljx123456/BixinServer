package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawOldPwdBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.WithdrawOldPwdData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawOldPwdView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class WithdrawOldPwdPresenter(owner: LifecycleOwner, val view: WithdrawOldPwdView, val context: Context) : BasePresenter(owner, view, context), WithdrawOldPwdData.WithdrawOldPwd{

    private val set=WithdrawOldPwdData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        set.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getWithdrawOldPwdRequest() {
        view.dismissLoading(context)
        view.getChangePwdRequest()
    }

    override fun getWithdrawOldPwdError(msg:String) {
        view.dismissLoading(context)
        view.getChangePwdError(msg)
    }

    fun getWithdrawOldPwd(body:WithdrawOldPwdBody){
        view.showLoading(context)
        set.getWithdrawOldPwd(body)
    }

}