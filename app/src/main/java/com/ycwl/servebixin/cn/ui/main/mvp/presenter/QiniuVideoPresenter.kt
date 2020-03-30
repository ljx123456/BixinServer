package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.util.Log
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.qiniu.SendAppFile
import com.ycwl.servebixin.cn.qiniu.SendFileInterface
import com.ycwl.servebixin.cn.ui.login.mvp.bean.QiniuTokenBean
import com.ycwl.servebixin.cn.ui.login.mvp.data.QiniuTokenData
import com.ycwl.servebixin.cn.ui.login.mvp.view.QiniuView
import com.ycwl.servebixin.cn.utils.utils.Toast
import io.reactivex.disposables.Disposable

class QiniuVideoPresenter(owner: LifecycleOwner, val view: QiniuView, val mContext: Context) : BasePresenter(owner, view, mContext), QiniuTokenData.QiniuToken, SendFileInterface {
    //上传成功
    override fun sendSucceed(fileUrlList: ArrayList<String>) {
        view.dismissLoading(mContext)
        view.sendSucceedImage(fileUrlList)
    }

    //上传失败
    override fun sendFileError(msg: String) {
        view.dismissLoading(mContext)
        Toast.Tips(msg)
        view.sendFileErrorImage()
    }

    private val qiniu = QiniuTokenData(this)
    private var imageList = ArrayList<String>()
    //获取Token成功
    override fun getQiniuTokenRequest(data: QiniuTokenBean) {
//        Log.e("测试","开始上传")
//        Log.e("测试","开始上传"+imageList.size)
        SendAppFile.sendVideoFile(imageList, data.data.token, this)
    }

    //获取Token失败
    override fun getQiniuTokenError() {
        view.dismissLoading(mContext)
    }

    fun setImage(file: ArrayList<String>) {
        view.showLoading(mContext)
//        imageList.clear()
        imageList = file
//        Log.e("测试","提交视频i"+imageList.size)
//        Log.e("测试","提交视频f"+file.size)
        qiniu.getQiniuToken()
    }


    override fun addDisposableList(list: ArrayList<Disposable>) {
        qiniu.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {
    }
}