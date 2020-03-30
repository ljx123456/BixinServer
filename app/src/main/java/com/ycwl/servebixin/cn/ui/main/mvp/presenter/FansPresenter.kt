package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.FansBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.FansBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.FansData
import com.ycwl.servebixin.cn.ui.main.mvp.view.FansView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class FansPresenter(ower: LifecycleOwner, val view: FansView, val mContext: Context) : BasePresenter(ower, view, mContext),FansData.Fans{

    private val fans=FansData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        fans.getDisposable()?.let {
            list.add(it)
        }
    }

    override fun presenterDestroy() {

    }

    override fun getFansDataRequest(data: FansBean) {
//        view.dismissLoading(mContext)
        view.getFansRequest(data)
    }

    override fun getFansDataError() {
//        view.dismissLoading(mContext)
        view.getFansError()
    }

    fun getFans(body: FansBody){
//        view.showLoading(mContext)
        fans.getFansData(body)
    }
}