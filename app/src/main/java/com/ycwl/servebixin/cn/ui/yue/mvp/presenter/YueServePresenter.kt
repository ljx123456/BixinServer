package com.ycwl.servebixin.cn.ui.yue.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.YueServeBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.YueServeBody
import com.ycwl.servebixin.cn.ui.yue.mvp.data.YueServeData
import com.ycwl.servebixin.cn.ui.yue.mvp.view.YueServeView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class YueServePresenter(owner: LifecycleOwner, val view: YueServeView, val context: Context) : BasePresenter(owner, view, context),YueServeData.YueServe{

    private val serve=YueServeData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        serve.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getYueServeRequest(data: YueServeBean) {
        view.dismissLoading(context)
        view.getYueServeRequest(data)
    }

    override fun getYueServeError() {
        view.dismissLoading(context)
        view.getYueServeError()
    }

    fun getYueServe(body:YueServeBody){
        view.showLoading(context)
        serve.getYueServe(body)
    }

}