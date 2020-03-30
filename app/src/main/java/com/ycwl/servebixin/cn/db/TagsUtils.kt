package com.ycwl.servebixin.cn.db

import com.blankj.utilcode.util.LogUtils
import com.ycwl.servebixin.cn.db.db.TagsDB
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean

object TagsUtils{

    //删除对应某条数据
    fun deleteTag(key: Long) {
        var tagsDB = GreenDaoHelper.getDaoSessions().tagsDBDao
        tagsDB.deleteByKey(key)
    }

    //获取数据库视频
//    fun getVideo(): VideosDB? {
//        var VideosDB = GreenDaoHelper.getDaoSessions().videosDBDao
//        if (VideosDB != null) {
//            var data = VideosDB.loadAll().get(0)
//            if (data.type==1){
//                return data
//            }else{
//                return null
//            }
//        } else {
//            return null
//        }
//    }

    //初始数据库标签
    fun setTags(info: List<ByCodeBean.DataBean.TagsBean>){
        var tagsDB = GreenDaoHelper.getDaoSessions().tagsDBDao

    }

    //获取数据库标签
    fun getTags(): List<TagsDB>? {
        var tagsDB = GreenDaoHelper.getDaoSessions().tagsDBDao
        if (tagsDB != null) {
            var data = tagsDB.loadAll()
            return data
        } else {
            return null
        }
    }


    //删除全部数据
    fun deleteAllTags() {
        var tagsDB = GreenDaoHelper.getDaoSessions().tagsDBDao
        tagsDB.deleteAll()
    }

    //增加标签
    fun addTag(info: ByCodeBean.DataBean.TagsBean){
        var TagsDB=TagsDB(null,info.tagId,info.tagName)
        var tagsDB = GreenDaoHelper.getDaoSessions().tagsDBDao
        try {
            tagsDB.insertOrReplace(TagsDB)
        } catch (e: Exception) {
            LogUtils.a("DB", e.toString())
        }
    }
}