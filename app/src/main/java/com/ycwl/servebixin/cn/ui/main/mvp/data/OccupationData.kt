package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.OccupationBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class OccupationData(val occupation:Occupation):BaseData<OccupationBean>(){

    fun getOccupation(){
        api(Api.getApi().getOccupation()).build()
    }

    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    override fun onSucceedRequest(data: OccupationBean, what: Int) {
        occupation.getOccupationRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        occupation.getOccupationError()
    }

    interface Occupation{
        fun getOccupationRequest(data: OccupationBean)
        fun getOccupationError()
    }
}