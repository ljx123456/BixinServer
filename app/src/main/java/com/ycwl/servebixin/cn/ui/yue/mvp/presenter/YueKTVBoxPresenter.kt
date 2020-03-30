package com.ycwl.servebixin.cn.ui.yue.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.BaoFangBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.BaoFangBody
import com.ycwl.servebixin.cn.ui.yue.mvp.data.YueKTVBoxData
import com.ycwl.servebixin.cn.ui.yue.mvp.view.YueKTVBoxView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class YueKTVBoxPresenter(owner: LifecycleOwner, val view: YueKTVBoxView, val context: Context) : BasePresenter(owner, view, context), YueKTVBoxData.YueKTVBox{

    private val box=YueKTVBoxData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        box.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getYueKTVBoxRequest(data: BaoFangBean) {
        view.dismissLoading(context)
        view.getBoxRequest(data)
    }

    override fun getYueKTVBoxError() {
        view.dismissLoading(context)

    }

    fun getYueKTVBox(body:BaoFangBody){
        view.showLoading(context)
        box.getYueKTVBox(body)
    }
}
