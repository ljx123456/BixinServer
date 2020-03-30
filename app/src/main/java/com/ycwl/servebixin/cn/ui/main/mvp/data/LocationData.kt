package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.EditUserBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.LocationBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class LocationData(val edit:Location) : BaseData<EditUserBean>(){
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)


    fun getLocation(body: LocationBody){
        api(Api.getApi().getLocation(body)).build()
    }

    override fun onSucceedRequest(data: EditUserBean, what: Int) {
        edit.getLocationRequest()
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
//        Toast.Tips(msg)
        edit.getLocationError()
    }

    interface Location{
        fun getLocationRequest()
        fun getLocationError()
    }

}