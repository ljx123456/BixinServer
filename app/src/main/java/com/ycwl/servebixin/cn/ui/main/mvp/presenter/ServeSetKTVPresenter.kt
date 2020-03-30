package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.NearByKTVBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchKTVBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.BindKTVBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.NearByKTVBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.SearchKTVBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.BindKTVData
import com.ycwl.servebixin.cn.ui.main.mvp.data.NearByKTVData
import com.ycwl.servebixin.cn.ui.main.mvp.data.SearchKTVData
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeSetKTVView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ServeSetKTVPresenter(owner: LifecycleOwner, val view: ServeSetKTVView, val context: Context) : BasePresenter(owner,view,context),SearchKTVData.SearchKTV,BindKTVData.BindKTV,NearByKTVData.NearByKTV{
    override fun getNearByKTVRequest(data: NearByKTVBean) {
        view.dismissLoading(context)
        view.getNearByKTVRequest(data)
    }

    override fun getNearByKTVError() {
        view.dismissLoading(context)
    }

    private val search=SearchKTVData(this)
    private val bind=BindKTVData(this)
    private val nearBy=NearByKTVData(this)

    fun getNearByKTV(body:NearByKTVBody){
        view.showLoading(context)
        nearBy.getNearByKTV(body)
    }

    fun getSearchKTV(body:SearchKTVBody){
        view.showLoading(context)
        search.getSearchKTV(body)
    }

    fun getBindKTV(body:BindKTVBody){
        view.showLoading(context)
        bind.getBindKTV(body)
    }

    override fun getSearchKTVRequest(data: SearchKTVBean) {
        view.dismissLoading(context)
        view.getSearchKTVRequest(data)
    }

    override fun getSearchKTVError() {
        view.dismissLoading(context)
        view.getSearchKTVError()
    }

    override fun getBindKTVRequest(data: EditUserBean) {
        view.dismissLoading(context)
        view.getBindKTVRequest(data)
    }

    override fun getBindKTVError() {
        view.dismissLoading(context)
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {

    }

    override fun presenterDestroy() {

    }

}