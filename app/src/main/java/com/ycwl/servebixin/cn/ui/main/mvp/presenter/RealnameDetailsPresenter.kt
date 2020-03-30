package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.RealnameDetailsBean
import com.ycwl.servebixin.cn.ui.main.mvp.data.RealnameDetailsData
import com.ycwl.servebixin.cn.ui.main.mvp.view.RealnameDetailsView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class RealnameDetailsPresenter(ower: LifecycleOwner, val view: RealnameDetailsView, val mContext: Context) : BasePresenter(ower, view, mContext),RealnameDetailsData.Details{

    private val details=RealnameDetailsData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        details.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getDetailsDataRequest(data: RealnameDetailsBean) {
        view.dismissLoading(mContext)
        view.getDetailsRequest(data)
    }

    override fun getDetailsDataError() {
        view.dismissLoading(mContext)
        view.getDetailsError()
    }

    fun getDetails(){
        view.showLoading(mContext)
        details.getDetailsData()
    }

}