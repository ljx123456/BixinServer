package com.ycwl.servebixin.cn.ui.yue.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.KTVBoxBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.KTVBoxBody
import com.ycwl.servebixin.cn.ui.yue.mvp.data.KTVBoxData
import com.ycwl.servebixin.cn.ui.yue.mvp.view.KTVBoxView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class KTVBoxPresenter(owner: LifecycleOwner, val view: KTVBoxView, val context: Context) : BasePresenter(owner, view, context),KTVBoxData.KTVBox{

    private val ktv=KTVBoxData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        ktv.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getKTVBoxRequest(data: KTVBoxBean) {
        view.dismissLoading(context)
        view.getKTVBoxRequest(data)
    }

    override fun getKTVBoxError() {
        view.dismissLoading(context)
    }

    fun getKTVBox(body:KTVBoxBody){
        view.showLoading(context)
        ktv.getKTVBox(body)
    }
}