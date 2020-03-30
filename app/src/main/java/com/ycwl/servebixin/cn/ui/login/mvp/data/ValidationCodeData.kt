package com.ycwl.servebixin.cn.ui.login.mvp.data


import com.ycwl.servebixin.cn.ui.login.mvp.bean.ValidationCodeBean
import com.ycwl.servebixin.cn.ui.login.mvp.body.ValidationCodeBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class ValidationCodeData(val validationcode: ValidationCode) : BaseData<ValidationCodeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getValidationCode(body: ValidationCodeBody) {
        api(Api.getApi().getValidationCode(body)).build()
    }

    override fun onSucceedRequest(data: ValidationCodeBean, what: Int) {
        validationcode.getValidationCodeRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        validationcode.getValidationCodeError()
    }

    interface ValidationCode {
        fun getValidationCodeRequest(data: ValidationCodeBean)
        fun getValidationCodeError()
    }
}