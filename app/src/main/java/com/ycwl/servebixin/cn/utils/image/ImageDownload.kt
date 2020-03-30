package com.ycwl.servebixin.cn.utils.image

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import com.ycwl.servebixin.cn.base.BaseContext.getContext
import com.ycwl.servebixin.cn.event.ImageDownloadEvent
import com.ycwl.servebixin.cn.utils.utils.Toast
import com.bumptech.glide.Glide
import com.tbruyelle.rxpermissions2.RxPermissions


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus


object ImageDownload {


    fun download(context: Context, tag: String, url: String) {
        val rxPermissions = RxPermissions(context as Activity)
        rxPermissions.request(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE).subscribe {
            if (it) startDownload(tag, url) else Toast.Tips("请打开内存读写权限")
        }
    }


    private fun startDownload(tag: String, url: String) {
        Observable.create<String> {
            val future = Glide.with(getContext()).load(url).downloadOnly(1080, 1920)
            it.onNext(future.get().absolutePath)
        }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ path -> EventBus.getDefault().post(ImageDownloadEvent(tag, url)) })
    }

}



