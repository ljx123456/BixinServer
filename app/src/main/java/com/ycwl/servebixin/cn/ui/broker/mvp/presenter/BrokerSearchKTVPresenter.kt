package com.ycwl.servebixin.cn.ui.broker.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerAddKTVBean
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerSearchKTVBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerAddKTVBody
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerSearchKTVBody
import com.ycwl.servebixin.cn.ui.broker.mvp.data.BrokerAddKTVData
import com.ycwl.servebixin.cn.ui.broker.mvp.data.BrokerSearchKTVData
import com.ycwl.servebixin.cn.ui.broker.mvp.view.BrokerSearchKTVView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchKTVBean
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class BrokerSearchKTVPresenter(owner: LifecycleOwner, val view: BrokerSearchKTVView, val context: Context) : BasePresenter(owner, view, context), BrokerSearchKTVData.BrokerSearchKTV, BrokerAddKTVData.BrokerAddKTV {


    private val search=BrokerSearchKTVData(this)
    private val add=BrokerAddKTVData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {

    }

    override fun presenterDestroy() {

    }


    override fun getBrokerRequest(data: BrokerAddKTVBean) {
        view.dismissLoading(context)
        view.getAddRequest(data)
    }

    override fun getBrokerError() {
        view.dismissLoading(context)
        view.getSearchError()
    }

    override fun getBrokerSearchRequest(data: SearchKTVBean) {
        view.dismissLoading(context)
        view.getSearchRequest(data)
    }

    override fun getBrokerSearchError() {
        view.dismissLoading(context)
        view.getSearchError()
    }


    fun getSearch(body:BrokerSearchKTVBody){
        view.showLoading(context)
        search.getBrokerSearchKTV(body)
    }

    fun getAdd(body:BrokerAddKTVBody){
        view.showLoading(context)
        add.getBrokerAddKTV(body)
    }




}