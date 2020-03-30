package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.UploadCardBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.UploadCardBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class UploadCardData(val upload: Upload) : BaseData<UploadCardBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getUpload(body: UploadCardBody) {
        api(Api.getApi().getUpload(body)).build()
    }

    override fun onSucceedRequest(data: UploadCardBean, what: Int) {
        upload.getUploadRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        upload.getUploadError(flag,msg)
    }

    interface Upload {
        fun getUploadRequest(data: UploadCardBean)
        fun getUploadError(code:Int,msg:String)
    }
}