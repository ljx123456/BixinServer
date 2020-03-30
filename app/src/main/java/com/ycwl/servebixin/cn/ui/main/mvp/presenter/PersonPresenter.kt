package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.data.ByCodeData
import com.ycwl.servebixin.cn.ui.main.mvp.data.PersonData
import com.ycwl.servebixin.cn.ui.main.mvp.view.PersonView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class PersonPresenter (ower: LifecycleOwner, val view: PersonView, val mContext: Context) : BasePresenter(ower, view, mContext),PersonData.Person{

    private val personData=PersonData(this)

    fun getPersonData() {
        view.showLoading(mContext)
        personData.getPersonData()
    }
    override fun getPersonDataRequest(data: ByCodeBean) {
        view.dismissLoading(mContext)
        view.getPersonDataRequest(data)
    }

    override fun getPersonDataError() {
        view.dismissLoading(mContext)
        view.getPersonDataError()
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {
        personData.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }
}