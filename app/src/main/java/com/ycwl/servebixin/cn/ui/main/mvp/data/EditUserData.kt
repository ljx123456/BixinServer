package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.EditUserBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class EditUserData(val edit:EditUser) :BaseData<EditUserBean>(){
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)


    fun getEditUser(body:EditUserBody){
        api(Api.getApi().getEditUser(body)).build()
    }

    override fun onSucceedRequest(data: EditUserBean, what: Int) {
        edit.getEditUserRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        edit.getEditUserError()
    }

    interface EditUser{
        fun getEditUserRequest(data: EditUserBean)
        fun getEditUserError()
    }

}