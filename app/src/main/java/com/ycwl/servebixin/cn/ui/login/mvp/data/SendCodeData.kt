package com.ycwl.servebixin.cn.ui.login.mvp.data


import com.ycwl.servebixin.cn.ui.login.mvp.bean.SendCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.SendCodeBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

//发送验证码
class SendCodeData(val sendcode: SendCode) : BaseData<SendCodeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getSendCode(body: SendCodeBody) {
        api(Api.getApi().getSendCode(body)).build()
    }

    override fun onSucceedRequest(data: SendCodeBean, what: Int) {
        sendcode.getSendCodeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        sendcode.getSendCodeError()
    }

    interface SendCode {
        fun getSendCodeRequest(data: SendCodeBean)
        fun getSendCodeError()
    }
}