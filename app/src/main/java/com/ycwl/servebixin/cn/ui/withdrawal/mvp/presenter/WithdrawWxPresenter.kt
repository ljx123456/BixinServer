package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.BindWithdrawTypeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.BindAlipayTypeBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.WithdrawWxData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawWxView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class WithdrawWxPresenter(owner: LifecycleOwner, val view: WithdrawWxView, val context: Context) : BasePresenter(owner, view, context),WithdrawWxData.BindWxType{

    private val wx=WithdrawWxData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        wx.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getBindWxTypeRequest(data: BindWithdrawTypeBean) {
        view.dismissLoading(context)
        view.getBindWXRequest(data)
    }

    override fun getBindWxTypeError() {
        view.dismissLoading(context)
    }

    fun getBindWx(body: BindAlipayTypeBody){
        view.showLoading(context)
        wx.getBindWxType(body)
    }
}