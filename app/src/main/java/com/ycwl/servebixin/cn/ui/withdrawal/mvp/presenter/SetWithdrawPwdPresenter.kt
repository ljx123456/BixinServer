package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.SetWithdrawPwdBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.SetWithdrawPwdBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.SetWithdrawPwdData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.SetWithdrawPwdView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class SetWithdrawPwdPresenter(owner: LifecycleOwner, val view: SetWithdrawPwdView, val context: Context) : BasePresenter(owner, view, context),SetWithdrawPwdData.SetWithdrawPwd{

    private val set=SetWithdrawPwdData(this)
    override fun getSetWithdrawPwdRequest(data: SetWithdrawPwdBean) {
        view.dismissLoading(context)
        view.getSetPwdRequest()
    }

    override fun getSetWithdrawPwdError() {
        view.dismissLoading(context)
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {
        set.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    fun setWithdrawPwd(body:SetWithdrawPwdBody){
        view.showLoading(context)
        set.getSetWithdrawPwd(body)
    }
}