package com.ycwl.servebixin.cn.ui.set.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.set.mvp.bean.BlackListBean
import com.ycwl.servebixin.cn.ui.set.mvp.bean.LogoutBean
import com.ycwl.servebixin.cn.ui.set.mvp.body.BlackListBody
import com.ycwl.servebixin.cn.ui.set.mvp.body.DelBlackBody
import com.ycwl.servebixin.cn.ui.set.mvp.data.BlackListData
import com.ycwl.servebixin.cn.ui.set.mvp.data.DelBlackData
import com.ycwl.servebixin.cn.ui.set.mvp.view.BlackListView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class BlackListPresenter(owner: LifecycleOwner, val view: BlackListView, val mContext: Context) : BasePresenter(owner, view, mContext),BlackListData.BlackList,DelBlackData.DelBlack{

    private val list=BlackListData(this)
    private val del=DelBlackData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {

    }

    override fun presenterDestroy() {

    }

    override fun getBlackListRequest(data: BlackListBean) {
        view.dismissLoading(mContext)
        view.getBlackListRequest(data)
    }

    override fun getBlackListError() {
        view.dismissLoading(mContext)
        view.getBlackListError()
    }

    override fun getDelBlackRequest(data: LogoutBean) {
        view.dismissLoading(mContext)
        view.getDelBlackRequest()
    }

    override fun getDelBlackError() {
        view.dismissLoading(mContext)
    }

    fun getBlackList(body:BlackListBody){
        view.showLoading(mContext)
        list.getBlackList(body)
    }

    fun getDelBlack(body:DelBlackBody){
        view.showLoading(mContext)
        del.getDelBlack(body)
    }

}