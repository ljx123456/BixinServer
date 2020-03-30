package com.ycwl.servebixin.cn.db

import com.ycwl.servebixin.cn.base.BaseContext.getContext
import com.ycwl.servebixin.cn.db.DBUtils.getUser
import java.util.*

object user {

    //获取用户头像
    fun getUserImage(): String {
        return getUser().avatar
    }

    //获取用户昵称
    fun getUserNick(): String {
        return getUser().nickname
    }

    //获取用户token
    fun getUserToken(): String {
        return getUser().token
    }

    //获取用户ID
    fun getUserID(): String {
        return "${getUser().id}"
    }

    //获取用户手机号
    fun getUserPhone():String{
        return  getUser().phone
    }

    //获取用户身份
    fun getIdentity():Int{
        return  getUser().identity
    }


    //获取用户生日
    fun getUserBirthday(): String {
        return getUser().birthday
    }

    //获取用户星座
    fun getUserConstellation(): String {
        return getUser().constellation
    }

    //获取用户职业
    fun getUserOccupation():String{
        return  getUser().occupationName
    }

    //获取用户是否需要完善信息
    fun getisPerfectData():Int{
        return  getUser().isPerfectData
    }


    //获取用户性别
    fun getSex():String{
        var sex=""
        if (getUser().sex==1){
            sex="男"
        }else{
            sex="女"
        }

        return sex
    }

    //获取用户年龄
    fun getUserAge(): Int {
        return getUser().age
    }

    //获取用户比心id
    fun getUserBiXinID(): Int {
        return getUser().bixinId
    }

    //获取用户userID
    fun getUserUserID():String{
        return  getUser().userID
    }

//    fun getUserToken(): String = "42VbLl2tlRBxTnBAcnzZPZMxzF1j4weMwCGAdxB0FLwJbqRJPYhsO7/VJfA1JPhhbenE+D5EaIJaQ+A/pPia9KtgbBMIN5SVQ7b9+H0YOw65z36H+mTcz1x0752cRnVUD1xqiMI76kaBsctpx+jsDxiadLbNFhqf"

    //获取用户极光聊天密码
    fun getUserIM(): String {
        return getUser().jmpassword
    }

    //获取用户是否实名
    fun getRealname():Int{
        return getUser().serviceIDCardAuditState
    }

    //当前精度
    fun getlng(): String {
        if (mCache.getAsString("lng") != null) {
            return mCache.getAsString("lng")
        } else {
            return ""
        }
    }

    //当前维度
    fun getlat(): String {
        if (mCache.getAsString("lat") != null) {
            return mCache.getAsString("lat")
        } else {
            return ""
        }
    }

    //当前职业
    fun getOccupation(): String {
        if (mCache.getAsString("Occupation") != null) {
            return mCache.getAsString("Occupation")
        } else {
            return ""
        }
    }

    //设置职业
    fun setOccupation(skill: String) {
        mCache.put("Occupation", skill)
    }

    //当前职业ID
    fun getOccupationID(): String{
        if (mCache.getAsString("OccupationID") != null) {
            return mCache.getAsString("OccupationID")
        } else {
            return ""
        }
    }

    //职业ID
    fun setOccupationID(skillID: String) {
        mCache.put("OccupationID", skillID)
    }

    //当前技能ID
    fun getUserSkillID(): String{
        if (mCache.getAsString("userSkillID") != null) {
            return mCache.getAsString("userSkillID")
        } else {
            return "-1"
        }
    }

    //设置技能ID
    fun setUserSkillID(skillID: String) {
        if (mCache!=null)
            mCache.put("userSkillID", skillID)
        else
            mCache= ACache.get(getContext())
    }

    //当前技能类型ID
    fun getSkillTypeID(): String{
        if (mCache.getAsString("SkillTypeID") != null) {
            return mCache.getAsString("SkillTypeID")
        } else {
            return "-1"
        }
    }

    //设置技能类型ID
    fun setSkillTypeID(skillTypeID: String) {
        if (mCache!=null)
            mCache.put("SkillTypeID", skillTypeID)
        else
            mCache= ACache.get(getContext())
    }

    //当前城市
    fun getCity(): String {
        if (mCache.getAsString("city") != null) {
            return mCache.getAsString("city")
        } else {
            return ""
        }
    }

    //城市
    fun setCity(city: String) {
        mCache.put("city", city)
    }

    var mCache = ACache.get(getContext()) // 初始化，一般放在基类里
    //精度
    fun setlng(lng: String) {
        mCache.put("lng", lng)
    }

    //维度
    fun setlat(lat: String) {
        mCache.put("lat", lat)
    }



    //城市ID
    fun setCityID(cityId: String) {
        mCache.put("cityid", cityId)
    }

    fun getCityID(): String {
        if (mCache.getAsBinary("cityid") != null) {
            return mCache.getAsString("cityid")
        } else {
            return ""
        }

    }

//    //当前约会人数
//    fun setNum(num: String) {
//        mCache.put("num", num)
//    }
//
//    //获取当前约会人数
//    fun getNum(): String {
//        if (mCache.getAsString("num") != null) {
//            return mCache.getAsString("num")
//        } else {
//            return "0"
//        }
//    }

    //设置从订单还是主流程添加酒水和服务人员1为主流程0为订单
    fun setType(type: String) {
        mCache.put("type", type)
    }

    //设置从订单还是主流程添加酒水和服务人员
    fun getType(): String {
        if (mCache.getAsString("type") != null) {
            return mCache.getAsString("type")
        } else {
            return "1"
        }
    }

    fun setOrderID(orderID: String) {
        mCache.put("orderID", orderID)
    }

    fun getOrderID(): String = mCache.getAsString("orderID")

    //设置微信code
    fun setWxCode(code: String) {
        mCache.put("WxCode", code)
    }
    fun getWxCode(): String {
        if (mCache.getAsString("WxCode") != null) {
            return mCache.getAsString("WxCode")
        } else {
            return ""
        }
    }

//    //设置code
//    fun setWxCode(code: String) {
//        mCache.put("WxCode", code)
//    }
//    fun getWxCode(): String {
//        if (mCache.getAsString("WxCode") != null) {
//            return mCache.getAsString("WxCode")
//        } else {
//            return ""
//        }
//    }

}