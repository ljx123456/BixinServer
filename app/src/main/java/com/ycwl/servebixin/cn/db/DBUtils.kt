package com.ycwl.servebixin.cn.db

import com.blankj.utilcode.util.LogUtils
import com.ycwl.servebixin.cn.db.GreenDaoHelper.getDaoSessions
import com.ycwl.servebixin.cn.db.db.MerchantDB
import com.ycwl.servebixin.cn.db.db.UserDB
import com.ycwl.servebixin.cn.db.db.VideosDB
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean
import java.lang.StringBuilder

object DBUtils{

    fun getUser(): UserDB {
        var user = getDaoSessions().userDBDao
        var data = user.loadAll().get(0)
        return data
    }

    fun setUser(info:ByCodeBean.DataBean){

        var userDB=UserDB(0.toLong(),info.token,info.identity,info.rongToken,info.phone,info.nickname,info.sex,info.birthday,info.constellation,info.avatar,info.occupationName,
              info.isPerfectData,info.dataAuditState,info.leaderIDCardAuditState,info.isEnableSkill,info.serviceIDCardAuditState,info.orderNum,info.fansNum,info.followNum,info.bixinId,info.age,info.userId,info.jmpassword)
        var userdata = getDaoSessions().userDBDao
        if (userdata != null) {
            userdata.deleteAll()
        }
        try {
            userdata.insert(userDB)
        } catch (e: Exception) {
            LogUtils.a("DB", e.toString())
        }

    }

    fun DelUser(){
        var user = getDaoSessions().userDBDao
        user.deleteAll()
    }




    fun getVideo():VideosDB?{
        var videos= getDaoSessions().videosDBDao
        if (videos.loadAll().isEmpty()){
            return null
        }else {
            var data = videos.loadAll().get(0)
            return data
        }
    }

    fun setVideo(info: ByCodeBean.DataBean.VideosBean){
        var videoDB=VideosDB(0.toLong(),info.url,info.type)
        var videodata = getDaoSessions().videosDBDao
        if (videodata != null) {
            videodata.deleteAll()
        }
        try {
            videodata.insert(videoDB)
        } catch (e: Exception) {
            LogUtils.a("DB", e.toString())
        }
    }

    fun DelVideo(){
        var video = getDaoSessions().videosDBDao
        video.deleteAll()
    }

    fun setMerchatDB(info: MerchantDB) {
        var merchatDB = MerchantDB(0.toLong(),info.merchantName,info.merchantID,info.merchantImage,info.baofangType,info.baofangTypeName,info.baofangID,info.baofangName,info.merchantAdds,info.baofangPrice)
        var merchatdata = getDaoSessions().merchantDBDao
        if (merchatdata != null) {
            merchatdata.deleteAll()
        }
        try {
            merchatdata.insert(merchatDB)
        } catch (e: Exception) {
            LogUtils.a("DB", e.toString())
        }
    }




    fun delMerchat() {
        var merchat = getDaoSessions().merchantDBDao
        if (merchat != null) {
            merchat.deleteAll()
        }
    }

    fun getMerchat(): MerchantDB {
        var merchat = getDaoSessions().merchantDBDao
        var data:MerchantDB=MerchantDB()
        if (merchat!=null&&merchat.loadAll().size>0)
            data = merchat.loadAll().get(0)
        return data
    }


}