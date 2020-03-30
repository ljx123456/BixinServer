package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeDetailsBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.UnbindLeaderData
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeMyLeaderView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ServeMyLeaderPresenter(owner: LifecycleOwner, val view: ServeMyLeaderView, val context: Context) : BasePresenter(owner,view,context),UnbindLeaderData.UnbindLeader{

    private val unbind=UnbindLeaderData(this)

    fun getUnbindLeader(body:ServeDetailsBody){
        view.showLoading(context)
        unbind.getUnbindLeader(body)
    }
    override fun getUnbindLeaderRequest(data: EditUserBean) {
        view.dismissLoading(context)
        view.getUnbindLeaderRequest(data)
    }

    override fun getUnbindLeaderError() {
        view.dismissLoading(context)
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {

    }

    override fun presenterDestroy() {

    }

}