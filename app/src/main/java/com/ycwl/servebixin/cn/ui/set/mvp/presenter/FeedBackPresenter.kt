package com.ycwl.servebixin.cn.ui.set.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.set.mvp.bean.LogoutBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.FeedBackBody
import com.ycwl.servebixin.cn.ui.set.mvp.data.FeedBackData
import com.ycwl.servebixin.cn.ui.set.mvp.view.FeedBackView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class FeedBackPresenter(owner: LifecycleOwner, val view: FeedBackView, val mContext: Context) : BasePresenter(owner, view, mContext),FeedBackData.FeedBack{

    private val opinion=FeedBackData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        opinion.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getFeedBackRequest(data: LogoutBean) {
        view.dismissLoading(mContext)
        view.getFeedBackRequest()
    }

    override fun getFeedBackError() {
        view.dismissLoading(mContext)
    }

    fun getFeedBack(body:FeedBackBody){
        view.showLoading(mContext)
        opinion.getFeedBack(body)
    }
}