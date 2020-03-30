package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.main.mvp.bean.UpdateBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.UpdateBody
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast


class UpdateData(val update: Update) : BaseData<UpdateBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getUpdate(body: UpdateBody) {
        api(Api.getApi().getUpdate(body)).build()
    }

    override fun onSucceedRequest(data: UpdateBean, what: Int) {
        update.getUpdateRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
//        Toast.Tips(msg)
        update.getUpdateError(flag,msg)
    }

    interface Update {
        fun getUpdateRequest(data: UpdateBean)
        fun getUpdateError(code:Int,msg:String)
    }
}