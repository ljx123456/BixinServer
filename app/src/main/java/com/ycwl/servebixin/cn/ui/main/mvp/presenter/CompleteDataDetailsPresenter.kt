package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.CompleteDataDetailsBean
import com.ycwl.servebixin.cn.ui.main.mvp.data.CompleteDataDetailsData
import com.ycwl.servebixin.cn.ui.main.mvp.view.CompleteDataDetailsView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class CompleteDataDetailsPresenter(ower: LifecycleOwner, val view: CompleteDataDetailsView, val mContext: Context) : BasePresenter(ower, view, mContext),CompleteDataDetailsData.Details{

    private val details=CompleteDataDetailsData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        details.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getDetailsDataRequest(data: CompleteDataDetailsBean) {
        view.dismissLoading(mContext)
        view.getCompleteDataDetailsRequest(data)
    }

    override fun getDetailsDataError() {
        view.dismissLoading(mContext)
        view.getCompleteDataDetailError()

    }

    fun getDetails(){
        view.showLoading(mContext)
        details.getCompleteDetailsData()
    }

}