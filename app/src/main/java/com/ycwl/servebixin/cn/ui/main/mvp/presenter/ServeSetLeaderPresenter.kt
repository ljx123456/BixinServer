package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.SearchLeaderBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.BindLeaderBody
import com.ycwl.servebixin.cn.ui.main.mvp.body.SearchLeaderBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.BindLeaderData
import com.ycwl.servebixin.cn.ui.main.mvp.data.SearchLeaderData
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeSetLeaderView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ServeSetLeaderPresenter(owner: LifecycleOwner, val view: ServeSetLeaderView, val context: Context) : BasePresenter(owner,view,context),SearchLeaderData.SearchLeader,BindLeaderData.BindLeader{

    private val search=SearchLeaderData(this)
    private val bind=BindLeaderData(this)


    fun getSearchLeader(body:SearchLeaderBody){
        view.showLoading(context)
        search.getSearchLeader(body)
    }

    fun getBindLeader(body:BindLeaderBody){
        view.showLoading(context)
        bind.getBindLeader(body)
    }

    override fun getSearchLeaderRequest(data: SearchLeaderBean) {
        view.dismissLoading(context)
        view.getSearchLeaderRequest(data)
    }

    override fun getSearchLeaderError() {
        view.dismissLoading(context)
        view.getSearchLeaderError()
    }

    override fun getBindLeaderRequest(data: EditUserBean) {
        view.dismissLoading(context)
        view.getBindLeaderRequest(data)
    }

    override fun getBindLeaderError() {
        view.dismissLoading(context)
    }


    override fun addDisposableList(list: ArrayList<Disposable>) {
        search.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

}