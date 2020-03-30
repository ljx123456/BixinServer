package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.EditUserBody
import com.ycwl.servebixin.cn.ui.main.mvp.data.EditUserData
import com.ycwl.servebixin.cn.ui.main.mvp.view.EditUserView
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class EditUserPresenter(owner: LifecycleOwner, val view: EditUserView, val context: Context) : BasePresenter(owner, view, context),EditUserData.EditUser{

    private val edit=EditUserData(this)

    override fun addDisposableList(list: ArrayList<Disposable>) {
        edit.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }

    override fun getEditUserRequest(data: EditUserBean) {
        view.dismissLoading(context)
        view.getEditUserRequest(data)
    }

    override fun getEditUserError() {
        view.dismissLoading(context)
    }

    fun getEditUser(body:EditUserBody){
        view.showLoading(context)
        edit.getEditUser(body)
    }
}