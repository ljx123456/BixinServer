package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeDetailsBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.DelKTVBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.OpenAllBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.OpenKTVBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeDetailsBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.DelKTVData
import com.ycwl.servebixin.cn.ui.main.mvp.data.OpenAllData
import com.ycwl.servebixin.cn.ui.main.mvp.data.OpenKTVData
import com.ycwl.servebixin.cn.ui.main.mvp.data.ServeDetailsData
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeDetailsView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ServeDetailsPresenter(owner: LifecycleOwner, val view: ServeDetailsView, val context: Context) : BasePresenter(owner,view,context),ServeDetailsData.ServeDetails,DelKTVData.DelKTV,OpenKTVData.OpenKTV,OpenAllData.OpenAll{
    override fun getOpenAllRequest() {
        view.dismissLoading(context)
        view.getOpenAllRequest()
    }

    override fun getOpenAllError() {
        view.dismissLoading(context)

    }

    override fun getOpenKTVRequest() {
        view.dismissLoading(context)
        view.getopenKTVRequest()
    }

    override fun getOpenKTVError() {
        view.dismissLoading(context)
    }

    override fun getDelKTVRequest() {
        view.dismissLoading(context)
        view.delKTVRequest()
    }

    override fun getDelKTVError() {
        view.dismissLoading(context)
    }

    private val serve=ServeDetailsData(this)
    private val del=DelKTVData(this)
    private val open=OpenKTVData(this)
    private val openAll=OpenAllData(this)

    fun getServeDetails(body:ServeDetailsBody){
        view.showLoading(context)
        serve.getServeDetails(body)
    }
    fun delKTV(body: DelKTVBody){
        view.showLoading(context)
        del.getDelKTV(body)
    }

    fun openKTV(body: OpenKTVBody){
        view.showLoading(context)
        open.getOpenKTV(body)
    }

    fun openAll(body: OpenAllBody){
        view.showLoading(context)
        openAll.getOpenAll(body)
    }

    override fun getServeSetOpenRequest(data: ServeDetailsBean) {
        view.dismissLoading(context)
        view.getServeDetailsRequest(data)
    }

    override fun getServeSetOpenError() {
        view.dismissLoading(context)
        view.getServeDetailsError()
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {
        serve.getDisposable()?.let { list.add(it) }
        del.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

}