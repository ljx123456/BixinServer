package com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.AuthInfoBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.BindWithdrawTypeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.UnbindTypeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawTypeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.BindAlipayTypeBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.UnbindTypeBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.AuthInfoData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.BindAlipayTypeData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.UnbindTypeData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.data.WithdrawTypeData
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawTypeView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class WithdrawTypePresenter(owner: LifecycleOwner, val view: WithdrawTypeView, val context: Context) : BasePresenter(owner, view, context), WithdrawTypeData.WithdrawType, AuthInfoData.AuthInfo,BindAlipayTypeData.BindAlipayType, UnbindTypeData.UnbindType {
    override fun getUnbindTypeRequest(data: UnbindTypeBean) {
        view.dismissLoading(context)
        view.getUnbindType()
    }

    override fun getUnbindTypeError() {
        view.dismissLoading(context)
    }

    fun getUnbindType(body: UnbindTypeBody){
        view.showLoading(context)
        unbind.getUnbindType(body)
    }

    override fun getBindAlipayTypeRequest(data: BindWithdrawTypeBean) {
        view.dismissLoading(context)
        view.getBindAlipay(data)
    }

    override fun getBindAlipayTypeError() {
        view.dismissLoading(context)
    }

    fun getBindAlipay(body:BindAlipayTypeBody){
        view.showLoading(context)
        bind.getBindAlipayType(body)
    }

    override fun getAuthInfoRequest(data: AuthInfoBean) {
        view.dismissLoading(context)
        view.getAlipayInfo(data)
    }

    override fun getAuthInfoError() {
        view.dismissLoading(context)
    }

    fun getAuthInfo(){
        view.showLoading(context)
        info.getAuthInfo()
    }

    private val type=WithdrawTypeData(this)
    private val info=AuthInfoData(this)
    private val bind=BindAlipayTypeData(this)
    private val unbind=UnbindTypeData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        type.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getWithdrawTypeRequest(data: WithdrawTypeBean) {
        view.dismissLoading(context)
        view.getWithdrawTypeRequest(data)
    }

    override fun getWithdrawTypeError() {
        view.dismissLoading(context)
        view.getWithdrawTypeError()
    }

    fun getWithdrawType(){
        view.showLoading(context)
        type.getWithdrawType()
    }

}
