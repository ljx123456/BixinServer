package com.ycwl.servebixin.cn.ui.set.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.set.mvp.bean.LogoutBean
import com.ycwl.servebixin.cn.ui.set.mvp.bean.NotiStateBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.SetNotiBody
import com.ycwl.servebixin.cn.ui.set.mvp.data.NotiStateData
import com.ycwl.servebixin.cn.ui.set.mvp.data.SetNotiData
import com.ycwl.servebixin.cn.ui.set.mvp.view.SetNotiView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class SetNotiPresenter(owner: LifecycleOwner, val view: SetNotiView, val mContext: Context) : BasePresenter(owner, view, mContext), SetNotiData.SetNoti,NotiStateData.NotiState {

    private val state=NotiStateData(this)
    private val set=SetNotiData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {

    }

    override fun presenterDestroy() {

    }

    override fun getSetNotiRequest(data: LogoutBean) {
        view.dismissLoading(mContext)
        view.getSetNotiRequest()
    }

    override fun getSetNotiError() {
        view.dismissLoading(mContext)
        view.getSetNotiRequestError()
    }

    override fun getNotiStateRequest(data: NotiStateBean) {
        view.dismissLoading(mContext)
        view.getNotiStateRequest(data)
    }

    override fun getNotiStateError() {
        view.dismissLoading(mContext)
    }

    fun getSetNoti(body:SetNotiBody){
        view.showLoading(mContext)
        set.getSetNoti(body)
    }

    fun getNotiState(){
        view.showLoading(mContext)
        state.getNotiState()
    }
}