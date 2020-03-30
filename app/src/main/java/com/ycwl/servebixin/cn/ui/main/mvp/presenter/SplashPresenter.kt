package com.pp.wsy.bosom.app.ui.login.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.UpdateBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.UpdateBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.UpdateData
import com.ycwl.servebixin.cn.ui.main.mvp.view.SplashView

import io.reactivex.disposables.Disposable
import java.util.*

/**
 * Created by Administrator on 2018/11/7 0007.
 */
class SplashPresenter(owner: LifecycleOwner, val view: SplashView, val context: Context)
    : BasePresenter(owner, view, context), UpdateData.Update {

    override fun getUpdateRequest(data: UpdateBean) {
//        view.dismissLoading(context)
        view.getUpdateRequest(data)
    }

    override fun getUpdateError(code:Int,msg:String) {
        view.getUpdateError(code,msg)
    }

    private val update = UpdateData(this)

    fun getUpdata(body: UpdateBody) {
//        view.showLoading(context)
        update.getUpdate(body)
    }

    //取消网络请求
    override fun addDisposableList(list: ArrayList<Disposable>) {
        update.getDisposable()?.let { list.add(it) }
    }


    override fun presenterDestroy() {

    }
}