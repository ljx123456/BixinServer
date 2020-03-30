package com.ycwl.servebixin.cn.ui.login.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.bean.CheckNameBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.CheckNameBody
import com.ycwl.servebixin.cn.ui.login.mvp.body.RegisterDataBody
import com.ycwl.servebixin.cn.ui.login.mvp.data.CheckNameData
import com.ycwl.servebixin.cn.ui.login.mvp.data.RegisterDataData
import com.ycwl.servebixin.cn.ui.login.mvp.view.RegisterDataView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class RegisterDataPresenter(owner: LifecycleOwner,val view:RegisterDataView,val mContext: Context): BasePresenter(owner, view, mContext),RegisterDataData.RegisterData,CheckNameData.CheckName{
    override fun getCheckNameRequest(data: CheckNameBean) {
        view.dismissLoading(mContext)
        view.setCheckNameRequest(data)
    }

    override fun getCheckNameError() {
        view.dismissLoading(mContext)
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {
        registerdata.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getRegisterDataRequest(data: ByCodeBean) {
        view.dismissLoading(mContext)
        view.getRegisterDataRequest(data)

    }

    override fun getRegisterDataError() {
        view.dismissLoading(mContext)

    }

    private val registerdata = RegisterDataData(this)
    private val check=CheckNameData(this)

    //注册完成
    fun getRegisterData(body: RegisterDataBody) {
        view.showLoading(mContext)
        registerdata.register(body)
    }

    //校验昵称
    fun setCheckName(body: CheckNameBody){
        view.showLoading(mContext)
        check.getCheckName(body)
    }

}