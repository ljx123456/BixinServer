package com.ycwl.servebixin.cn.ui.main.location

import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.ycwl.servebixin.cn.base.BaseContext.getContext
import com.ycwl.servebixin.cn.db.user.setlat
import com.ycwl.servebixin.cn.db.user.setlng
//import com.ycwl.servebixin.cn.db.user.getCity
//import com.ycwl.servebixin.cn.db.user.setCity
//import com.ycwl.servebixin.cn.db.user.setlat
//import com.ycwl.servebixin.cn.db.user.setlng
import java.text.SimpleDateFormat
import java.util.*


class LocationUtils (val location:Location): AMapLocationListener {
    var mlocationClient = AMapLocationClient(getContext())
    var mLocationOption = AMapLocationClientOption()

    fun getLocation() {
        mlocationClient.setLocationListener(this)
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
        mLocationOption.setInterval(1000*60*5)
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true)
        mlocationClient.setLocationOption(mLocationOption)
        mlocationClient.startLocation()
    }

    fun stopLocation(){
        mlocationClient.stopLocation()
        mlocationClient.onDestroy()
    }

    override fun onLocationChanged(amapLocation: AMapLocation) {
        if (amapLocation != null && amapLocation.errorCode == 0) {

//            amapLocation.locationType
//            amapLocation.latitude//获取纬度
//            amapLocation.longitude//获取经度
//            amapLocation.accuracy//获取精度信息
//            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//            val date = Date(amapLocation.time)
//            df.format(date)//定位时间


            setlng("${amapLocation.longitude}")
            setlat("${amapLocation.latitude}")
//            val city = amapLocation.city
//            setCity(city.replace("市", ""))
            location.getLocationSuccess()
//            LogUtils.a("定位" + getCity() + amapLocation.cityCode + Gson().toJson(amapLocation))
//            mlocationClient.stopLocation()
        }
    }

    interface Location{
        fun getLocationSuccess()
    }
}
