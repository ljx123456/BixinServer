package com.ycwl.servebixin.cn.ui.login.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.bean.FaceAuthBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.FaceAuthBody
import com.ycwl.servebixin.cn.ui.login.mvp.data.FaceAuthData
import com.ycwl.servebixin.cn.ui.login.mvp.view.FaceAuthView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class FaceAuthPresenter(owner: LifecycleOwner, val view:FaceAuthView, val mContext: Context): BasePresenter(owner,view,mContext),FaceAuthData.SendFace{

    private val faceAuthData = FaceAuthData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        faceAuthData.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getSendFaceRequest(data: FaceAuthBean) {
        view.dismissLoading(mContext)
        view.getSendFaceRequest()
    }

    override fun getSendFaceError() {
        view.dismissLoading(mContext)
    }

    //人脸验证
    fun getFaceData(body: FaceAuthBody) {
        view.showLoading(mContext)
        faceAuthData.getSendFace(body)
    }

}