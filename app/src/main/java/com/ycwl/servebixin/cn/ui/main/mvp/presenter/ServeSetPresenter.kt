package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeSetBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeSetOpenBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.ServeSetData
import com.ycwl.servebixin.cn.ui.main.mvp.data.ServeSetOpenData
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeSetView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ServeSetPresenter(owner: LifecycleOwner, val view: ServeSetView, val context: Context) : BasePresenter(owner,view,context), ServeSetData.ServeSet,ServeSetOpenData.ServeSetOpen{
    override fun getServeSetOpenRequest(data: EditUserBean) {
        view.dismissLoading(context)
        view.getServeSetOpenRequest(data)
    }

    override fun getServeSetOpenError(flag:Int) {
        view.dismissLoading(context)
        view.getServeSetOpenError(flag)
    }

    private val serve=ServeSetData(this)
    private val open=ServeSetOpenData(this)

    override fun getServeSetRequest(data: ServeSetBean) {
        view.dismissLoading(context)
        view.getServeSetRequest(data)
    }

    fun getServeSet(){
        view.showLoading(context)
        serve.getServeSet()
    }

    fun getServeSetOpen(body:ServeSetOpenBody){
        view.showLoading(context)
        open.getServeSetOpen(body)
    }

    override fun getServeSetError() {
        view.dismissLoading(context)
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {
        serve.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }


}