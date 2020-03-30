package com.ycwl.servebixin.cn.ui.message.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.message.mvp.bean.BannerBean
import com.ycwl.servebixin.cn.ui.message.mvp.data.BannerData
import com.ycwl.servebixin.cn.ui.message.mvp.view.BannerView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class BannerPresenter(owner: LifecycleOwner, val view: BannerView, val mContext: Context) : BasePresenter(owner, view, mContext), BannerData.Banner{

    private val banner=BannerData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        banner.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getBannerRequest(data: BannerBean) {
        view.dismissLoading(mContext)
        view.getBannerRequest(data)
    }

    override fun getBannerError() {
        view.dismissLoading(mContext)
    }

    fun getBanner(){
        view.showLoading(mContext)
        banner.getBanner()
    }

}