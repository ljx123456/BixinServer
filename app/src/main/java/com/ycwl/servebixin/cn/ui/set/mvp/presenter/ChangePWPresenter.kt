package com.ycwl.servebixin.cn.ui.set.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.set.mvp.bean.ChangePWBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.ChangePWBody
import com.ycwl.servebixin.cn.ui.set.mvp.data.ChangePWData
import com.ycwl.servebixin.cn.ui.set.mvp.view.ChangePWView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ChangePWPresenter(owner: LifecycleOwner, val view: ChangePWView, val mContext: Context) : BasePresenter(owner, view, mContext), ChangePWData.ChangePW {

    private val resetpwd = ChangePWData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        resetpwd.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    //重置密码
    fun getChangePW(body: ChangePWBody) {
        view.showLoading(mContext)
        resetpwd.getChangePW(body)
    }


    //重置成功
    override fun getChangePWRequest(data: ChangePWBean) {
        view.dismissLoading(mContext)
        view.getChangePWRequest()
    }

    //重置失败
    override fun getChangePWError() {
        view.dismissLoading(mContext)
    }

}