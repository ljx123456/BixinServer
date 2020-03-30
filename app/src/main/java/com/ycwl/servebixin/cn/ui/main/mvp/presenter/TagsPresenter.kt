package com.ycwl.servebixin.cn.ui.main.mvp.presenter

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ycwl.servebixin.cn.base.BasePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.bean.TagBean
import com.ycwl.servebixin.cn.ui.main.mvp.data.TagsData
import com.ycwl.servebixin.cn.ui.main.mvp.view.TagsView
import io.reactivex.disposables.Disposable
import java.util.ArrayList


class TagsPresenter(owner: LifecycleOwner, val view: TagsView, val context: Context) : BasePresenter(owner,view,context),TagsData.Tags{

    private val tags=TagsData(this)

    override fun getTagsRequest(data: TagBean) {
        view.dismissLoading(context)
        view.getTagsRequest(data)
    }

    fun getTags(){
        view.showLoading(context)
        tags.getTags()
    }

    override fun getTagsError() {
        view.dismissLoading(context)
    }

    override fun addDisposableList(list: ArrayList<Disposable>) {
        tags.getDisposable()?.let { list.add(it) }
    }

    override fun presenterDestroy() {

    }


}