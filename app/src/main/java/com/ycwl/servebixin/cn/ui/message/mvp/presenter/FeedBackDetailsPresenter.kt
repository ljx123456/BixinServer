package com.ycwl.servebixin.cn.ui.message.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.message.mvp.bean.FeedBackDetailsBean
import com.ycwl.servebixin.cn.ui.message.mvp.body.FeedBackDetailsBody
import com.ycwl.servebixin.cn.ui.message.mvp.data.FeedBackDetailsData
import com.ycwl.servebixin.cn.ui.message.mvp.view.FeedBackDetailsView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class FeedBackDetailsPresenter(owner: LifecycleOwner, val view: FeedBackDetailsView, val mContext: Context) : BasePresenter(owner, view, mContext), FeedBackDetailsData.FeedBackDetails{

    private val details=FeedBackDetailsData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        details.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    fun getFeedBackDetails(body:FeedBackDetailsBody){
        view.showLoading(mContext)
        details.getFeedBackDetails(body)
    }

    override fun getFeedBackDetailsRequest(data: FeedBackDetailsBean) {
        view.dismissLoading(mContext)
        view.getFeedBackDetailsRequest(data)
    }

    override fun getFeedBackDetailsError() {
        view.dismissLoading(mContext)
        view.getFeedBackDetailsError()
    }
}