package com.ycwl.servebixin.cn.utils

import android.view.LayoutInflater
import android.view.View
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseContext.getContext


object layoutUtils {

    fun getNoneData(): View {
        return LayoutInflater.from(getContext()).inflate(R.layout.layout_no_data, null)
    }

    fun getNoneMessage(): View {
        return LayoutInflater.from(getContext()).inflate(R.layout.layout_none_message, null)
    }

    fun getNoneFans(): View {
        return LayoutInflater.from(getContext()).inflate(R.layout.layout_none_fans, null)
    }
}