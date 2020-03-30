package com.ycwl.servebixin.cn.ui.broker.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVBean
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVDelBean
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.BrokerKTVOpenBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerKTVDelBody
import com.ycwl.servebixin.cn.ui.broker.mvp.body.BrokerKTVOpenBody
import com.ycwl.servebixin.cn.ui.broker.mvp.data.BrokerKTVData
import com.ycwl.servebixin.cn.ui.broker.mvp.data.BrokerKTVDelData
import com.ycwl.servebixin.cn.ui.broker.mvp.data.BrokerKTVOpenData
import com.ycwl.servebixin.cn.ui.broker.mvp.view.BrokerKTVView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class BrokerKTVPresenter(owner: LifecycleOwner, val view: BrokerKTVView, val context: Context) : BasePresenter(owner, view, context),BrokerKTVData.BrokerKTV,BrokerKTVOpenData.BrokerKTVOpen,BrokerKTVDelData.BrokerKTVDel{

    private val ktv=BrokerKTVData(this)
    private val open=BrokerKTVOpenData(this)
    private val del=BrokerKTVDelData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
    }

    override fun presenterDestroy() {

    }

    override fun getBrokerKTVRequest(data: BrokerKTVBean) {
        view.dismissLoading(context)
        view.getBrokerKTVRequest(data)
    }

    override fun getBrokerKTVError() {
        view.dismissLoading(context)
        view.getBrokerKTVError()
    }

    override fun getBrokerKTVOpenRequest(data: BrokerKTVOpenBean) {
        view.dismissLoading(context)
        view.getBrokerKTVOpenRequest(data)
    }

    override fun getBrokerKTVOpenError() {
        view.dismissLoading(context)
        view.getBrokerKTVOpenError()
    }

    override fun getBrokerKTVDelRequest(data: BrokerKTVDelBean) {
        view.dismissLoading(context)
        view.getBrokerKTVDelRequest(data)
    }

    override fun getBrokerKTVDelError() {
        view.dismissLoading(context)
        view.getBrokerKTVDelError()
    }


    fun getBrokerKTV(){
        view.showLoading(context)
        ktv.getBrokerKTV()
    }

    fun getBrokerKTVOpen(body :BrokerKTVOpenBody){
        view.showLoading(context)
        open.getBrokerKTVOpen(body)
    }

    fun getBrokerKTVDel(body:BrokerKTVDelBody){
        view.showLoading(context)
        del.getBrokerKTVDel(body)
    }

}