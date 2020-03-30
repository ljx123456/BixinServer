package com.ycwl.servebixin.cn.ui.yue.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.KTVBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.YueKTVBody
import com.ycwl.servebixin.cn.ui.yue.mvp.data.YueKTVData
import com.ycwl.servebixin.cn.ui.yue.mvp.view.YueKTVView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class YueKTVPresenter(owner: LifecycleOwner, val view: YueKTVView, val context: Context) : BasePresenter(owner, view, context), YueKTVData.YueKTV{

    private var ktv=YueKTVData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        ktv.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getYueKTVRequest(data: KTVBean) {
        view.dismissLoading(context)
        view.getKTVRequest(data)
    }

    override fun getYueKTVError() {
        view.dismissLoading(context)
    }

    fun getYueKTV(body: YueKTVBody){
        view.showLoading(context)
        ktv.getYueKTV(body)
    }

}