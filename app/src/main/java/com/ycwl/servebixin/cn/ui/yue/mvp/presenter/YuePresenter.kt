package com.ycwl.servebixin.cn.ui.yue.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.YueBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.YueBody
import com.ycwl.servebixin.cn.ui.yue.mvp.data.YueData
import com.ycwl.servebixin.cn.ui.yue.mvp.view.YueView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class YuePresenter(owner: LifecycleOwner, val view: YueView, val mContext: Context) : BasePresenter(owner, view, mContext),YueData.OrderCode{

    private val order=YueData(this)
    override fun addDisposableList(list: ArrayList<Disposable>) {
        order.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getOrderCodeRequest(data: YueBean) {
        view.dismissLoading(mContext)
        view.getOrderCodeRequest(data)
    }

    override fun getOrderCodeError() {
        view.dismissLoading(mContext)
    }

    fun getOrderCode(body:YueBody){
        view.showLoading(mContext)
        order.getDrinks(body)
    }
}