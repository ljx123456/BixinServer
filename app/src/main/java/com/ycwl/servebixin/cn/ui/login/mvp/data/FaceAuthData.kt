package com.ycwl.servebixin.cn.ui.login.mvp.data

import com.ycwl.servebixin.cn.ui.login.mvp.bean.FaceAuthBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.FaceAuthBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class FaceAuthData(val sendFace: SendFace):BaseData<FaceAuthBean>(){



    override fun requestCache(): SaveInfo =SaveInfo(false, javaClass.name)

    override fun onSucceedRequest(data: FaceAuthBean, what: Int) {
        sendFace.getSendFaceRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        sendFace.getSendFaceError()
    }

    fun getSendFace(body:FaceAuthBody){
        api(Api.getApi().getFaceAuth(body)).build()
    }

    interface SendFace {
        fun getSendFaceRequest(data: FaceAuthBean)
        fun getSendFaceError()
    }
}