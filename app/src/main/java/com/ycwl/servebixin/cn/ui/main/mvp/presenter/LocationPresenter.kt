package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.body.LocationBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.LocationData
import com.ycwl.servebixin.cn.ui.main.mvp.view.LocationView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class LocationPresenter(owner: LifecycleOwner, val view: LocationView, val context: Context) : BasePresenter(owner, view, context), LocationData.Location {
    private val data=LocationData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        data.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getLocationRequest() {
//        view.dismissLoading(context)
        view.getLocationRequest()
    }

    override fun getLocationError() {
//        view.dismissLoading(context)
    }

    fun getLocation(body:LocationBody){
//        view.showLoading(context)
        data.getLocation(body)
    }
}