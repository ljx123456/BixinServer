package com.ycwl.servebixin.cn.ui.broker.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.ApplyBrokerUploadBean
import com.ycwl.servebixin.cn.ui.broker.mvp.body.ApplyBrokerUploadBody
import com.ycwl.servebixin.cn.ui.broker.mvp.data.ApplyBrokerUploadData
import com.ycwl.servebixin.cn.ui.broker.mvp.view.ApplyBrokerUploadView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ApplyBrokerUploadPresenter(owner: LifecycleOwner, val view: ApplyBrokerUploadView, val context: Context) : BasePresenter(owner, view, context), ApplyBrokerUploadData.ApplyBrokerUpload{

    private val upload=ApplyBrokerUploadData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        upload.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getApplyBrokerUploadRequest(data: ApplyBrokerUploadBean) {
        view.dismissLoading(context)
        view.getUploadRequest()
    }

    override fun getApplyBrokerUploadError(data: Int) {
       view.dismissLoading(context)
    }

    fun getApplyBrokerUpload(body:ApplyBrokerUploadBody){
        view.showLoading(context)
        upload.getApplyBrokerUpload(body)
    }

}