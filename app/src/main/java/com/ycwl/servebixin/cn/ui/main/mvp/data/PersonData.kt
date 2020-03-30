package com.ycwl.servebixin.cn.ui.main.mvp.data

import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import com.ycwl.servebixin.cn.utils.http.Api
import com.ycwl.servebixin.cn.utils.http.BaseData
import com.ycwl.servebixin.cn.utils.http.SaveInfo
import com.ycwl.servebixin.cn.utils.utils.Toast

class PersonData(val person: Person) : BaseData<ByCodeBean>() {
    override fun requestCache(): SaveInfo = SaveInfo(false, javaClass.name)

    fun getPersonData() {
        api(Api.getApi().getPersonData()).build()
    }

    override fun onSucceedRequest(data: ByCodeBean, what: Int) {
        person.getPersonDataRequest(data)
    }

    override fun onErrorRequest(flag: Int, msg: String, what: Int) {
        Toast.Tips(msg)
        person.getPersonDataError()
    }

    interface Person {
        fun getPersonDataRequest(data: ByCodeBean)
        fun getPersonDataError()
    }
}