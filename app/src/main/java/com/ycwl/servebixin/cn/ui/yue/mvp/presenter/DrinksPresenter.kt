package com.ycwl.servebixin.cn.ui.yue.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.DrinksBody
import com.ycwl.servebixin.cn.ui.yue.mvp.data.DrinksData
import com.ycwl.servebixin.cn.ui.yue.mvp.view.DrinksView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class DrinksPresenter(owner: LifecycleOwner, val view: DrinksView, val mContext: Context) : BasePresenter(owner, view, mContext), DrinksData.Drinks {

    private val drinks = DrinksData(this)

    fun getDrinks(body: DrinksBody) {
        view.showLoading(mContext)
        drinks.getDrinks(body)
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {
        drinks.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getDrinksRequest(data: DrinksBean) {
        view.dismissLoading(mContext)
        view.getDrinksRequest(data)
    }

    override fun getDrinksError() {
        view.dismissLoading(mContext)
        view.getDrinksError()
    }
}