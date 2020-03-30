package com.ycwl.servebixin.cn.utils.image

import android.widget.ImageView
import com.ycwl.servebixin.cn.base.BaseContext.getContext
import com.ycwl.servebixin.cn.utils.image.ImageConfiguration.imagePlaceholder
import com.ycwl.servebixin.cn.utils.image.ImageConfiguration.userHeadPlaceholder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


/**
 * Created by Administrator on 2018/3/1 0001.
 * 加载普通图片
 */
object ImageLoad {


    //加载一般图片
    fun setImage(url: String, image: ImageView) {
        Glide.with(getContext())
                .load(url)
                .placeholder(imagePlaceholder)
                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.)
                .into(image)
    }

    //无占位图加载
    fun setImageNull(url: String, image: ImageView) {
        Glide.with(getContext())
                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image)
    }

    //加载本地资源
    fun setResourceImage(id: Int, image: ImageView) {
        Glide.with(getContext()).load(id).into(image)
    }


    //加载头像
    fun setUserHead(url: String, image: ImageView) {
        if (url != null && !url.equals("")) {
            Glide.with(getContext())
                    .load(url)
                    .placeholder(userHeadPlaceholder)
                    .dontAnimate()
                    .error(userHeadPlaceholder)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(image)
        } else
            Glide.with(getContext())
                    .load(url)
                    .placeholder(userHeadPlaceholder)
                    .dontAnimate()
                    .error(userHeadPlaceholder)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(image)
    }



}