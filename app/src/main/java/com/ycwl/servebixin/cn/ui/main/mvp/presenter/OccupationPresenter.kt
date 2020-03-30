package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.OccupationBean
import com.ycwl.servebixin.cn.ui.main.mvp.data.OccupationData
import com.ycwl.servebixin.cn.ui.main.mvp.view.OccupationView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class OccupationPresenter(owner: LifecycleOwner, val view: OccupationView, val context: Context) : BasePresenter(owner, view, context),OccupationData.Occupation{

    private val occupation=OccupationData(this)

    fun getOccupation(){
        view.showLoading(context)
        occupation.getOccupation()
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {
        occupation.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getOccupationRequest(data: OccupationBean) {
        view.dismissLoading(context)
        view.getOccupationRequest(data)
    }

    override fun getOccupationError() {
        view.dismissLoading(context)
    }

}