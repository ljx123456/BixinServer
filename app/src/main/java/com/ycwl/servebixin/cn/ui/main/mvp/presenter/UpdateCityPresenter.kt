package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.UpdateCityBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.UpdateCityBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.UpdateCityData
import com.ycwl.servebixin.cn.ui.main.mvp.view.UpdateCityView

import io.reactivex.disposables.Disposable
import java.util.ArrayList

class UpdateCityPresenter(owner: LifecycleOwner, val view: UpdateCityView, val mContext: Context) : BasePresenter(owner, view, mContext), UpdateCityData.UpdateCity{

    private val update=UpdateCityData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        update.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getUpdateCityRequest(data: UpdateCityBean) {
        view.getUpdateCityRequest()
    }

    override fun getUpdateCityError(code: Int, msg: String) {
        view.getUpdateCityError()
    }

    fun getUpdateCity(body: UpdateCityBody){
        update.getUpdateCity(body)
    }
}