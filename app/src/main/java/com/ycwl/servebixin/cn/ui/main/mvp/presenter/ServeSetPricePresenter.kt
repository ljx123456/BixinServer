package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter

import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeSetPriceBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeSetPriceBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.ServeSetPriceData
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeSetPriceView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ServeSetPricePresenter(owner: LifecycleOwner, val view: ServeSetPriceView, val context: Context) : BasePresenter(owner,view,context),ServeSetPriceData.ServeSetPrice{

    private val serve=ServeSetPriceData(this)

    fun getServeSetPrice(body:ServeSetPriceBody){
        view.showLoading(context)
        serve.getServeSetPrice(body)
    }
    override fun getServeSetPriceRequest(data: ServeSetPriceBean) {
        view.dismissLoading(context)
        view.getServeSetPriceRequest(data)
    }

    override fun getServeSetPriceError() {
        view.dismissLoading(context)

    }


    override fun addDisposableList(list: ArrayList<Disposable>) {
        serve.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

}