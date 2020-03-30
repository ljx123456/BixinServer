package com.ycwl.servebixin.cn.db

import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.ycwl.servebixin.cn.db.db.PicturesDB
import com.ycwl.servebixin.cn.db.db.VideosDB
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean

object PicturesUtils{

    //删除对应某条数据
    fun deletePicture(key: Long) {
        var picturesDB = GreenDaoHelper.getDaoSessions().picturesDBDao
        picturesDB.deleteByKey(key)
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

    //获取数据库图片
    fun getPictures(): List<PicturesDB>? {
        var picturesDB = GreenDaoHelper.getDaoSessions().picturesDBDao
        if (picturesDB != null) {
            var data = picturesDB.loadAll()
            return data
        } else {
            return null
        }
    }


    //删除全部数据
    fun deleteAllPictures() {
        var picturesDB = GreenDaoHelper.getDaoSessions().picturesDBDao
        picturesDB.deleteAll()
    }

    //增加图片
    fun addPicture(info:ByCodeBean.DataBean.VideosBean){
        var picturesDB=PicturesDB(null,info.url,info.type)
        var pictureData = GreenDaoHelper.getDaoSessions().picturesDBDao
        try {
            pictureData.insertOrReplace(picturesDB)
        } catch (e: Exception) {
            LogUtils.a("DB", e.toString())
        }
    }

    //更换图片
    fun changePicture(info:PicturesDB,url:String){
        var picturesDB=info
        picturesDB.url=url
        var pictureData = GreenDaoHelper.getDaoSessions().picturesDBDao
        try {
            pictureData.update(picturesDB)
//            pictureData.insertOrReplace(picturesDB)
        } catch (e: Exception) {
            LogUtils.a("DB", e.toString())
        }
    }


    //获取对应某条信息
//    fun getDrinks(id: Int): VideosDB {
//        var data = getVideosData()
//        if (data != null && data.size > 0) {
//            for (i in data.indices) {
//                LogUtils.a("当前全部数据", Gson().toJson(data))
//                if (id.toLong() == data.get(i).id) {
//                    LogUtils.a("当前全部数据sss", Gson().toJson(data.get(i)))
//                    return data.get(i)
//                }
//            }
//        } else {
//            return VideosDB()
//        }
//        return VideosDB()
//    }
//
//    fun ScreenData(datas: VideosDB): Screen {
//        var data = getVideosData()
//        for (i in data!!.indices) {
//            if (data.get(i).mealName.equals(datas.mealName) && data.get(i).drinkName.equals(datas.drinkName)) {
//                return Screen(true, data.get(i).id)
//            }
//        }
//        return Screen(false, 0)
//    }
//
//    class Screen(var type: Boolean, var num: Long)
//
//    //添加某一条数据
//    fun addVideos(datas: VideosDB) {
//        var DrinkDB = GreenDaoHelper.getDaoSessions().videosDBDao
//        var data = getVideosData()
//        if (data != null && data.size >= 1 && data.get(0).url != null) {
//            var sereen = ScreenData(datas)
//            if (sereen.type) {
//                if (datas.drinkNum.equals("0")) {
//                    deleteDrinks(sereen.num)
//                } else {
//                    try {
//                        datas.id = sereen.num
//                        DrinkDB.update(datas)
//                    } catch (e: Exception) {
//                        LogUtils.a("DB", e.toString())
//                    }
//                }
//            } else {
////                var size = data.size
////                datas.id = size.toLong()
//                try {
//                    DrinkDB.insert(datas)
//                } catch (e: Exception) {
//                    LogUtils.a("DB", e.toString())
//                }
//            }
//        } else {
//            try {
////                datas.id = 0.toLong()
//                DrinkDB.insert(datas)
//            } catch (e: Exception) {
//                LogUtils.a("DB", e.toString())
//            }
//        }
//    }
}