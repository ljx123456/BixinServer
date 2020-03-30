package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.UploadCardBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.UploadCardBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.UploadCardData
import com.ycwl.servebixin.cn.ui.main.mvp.view.RealnameCardView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class RealnameCardPresenter(ower: LifecycleOwner, val view: RealnameCardView, val mContext: Context) : BasePresenter(ower, view, mContext),UploadCardData.Upload{

    private val upload=UploadCardData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        upload.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getUploadRequest(data: UploadCardBean) {
        view.dismissLoading(mContext)
        view.getRealnameCardRequest(data)
    }

    override fun getUploadError(code: Int, msg: String) {
        view.dismissLoading(mContext)
        view.getRealnameCardError()
    }

    fun getUpload(body:UploadCardBody){
        view.showLoading(mContext)
        upload.getUpload(body)
    }
}