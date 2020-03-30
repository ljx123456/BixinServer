package com.ycwl.servebixin.cn.db

import com.blankj.utilcode.util.LogUtils

import com.google.gson.Gson
import com.ycwl.servebixin.cn.db.GreenDaoHelper.getDaoSessions
import com.ycwl.servebixin.cn.db.db.ServePersonnelDB
import com.ycwl.servebixin.cn.utils.utils.Toast

object ServeUtils {


    //添加或者减少数量
    fun addServeNum(key: Long, skillNum: Int) {
        var serveDB = getDaoSessions().servePersonnelDBDao
        var data = serveDB.load(key)
//        LogUtils.a("全部数据", Gson().toJson(serveDB.load(key)))
    }

    //删除对应某条数据
    fun deleteServe(key: Long) {
        var serveDB = getDaoSessions().servePersonnelDBDao
        serveDB.deleteByKey(key)
    }

    //获取全部数据库数据
    fun getServeData(): List<ServePersonnelDB> {
        var serveDB = getDaoSessions().servePersonnelDBDao
        if (serveDB != null) {
            var data = serveDB.loadAll()
            if (data != null && data.size > 0) {
                return data
            } else {
                var list = ArrayList<ServePersonnelDB>()
                var model = ServePersonnelDB()
                list.add(model)
                return list
            }
        } else {
            var list = ArrayList<ServePersonnelDB>()
            var model = ServePersonnelDB()
            list.add(model)
            return list
        }
    }


    //删除全部数据
    fun deleteALLServe() {
        var serveDB = getDaoSessions().servePersonnelDBDao
        serveDB.deleteAll()
    }


    //获取对应某条信息
    fun getServe(id: String): ServePersonnelDB {
        var data = getServeData()
        if (data != null && data.size > 0) {
            for (i in data.indices) {
                LogUtils.a("当前全部数据", Gson().toJson(data))
                if (id.equals(data.get(i).userId)) {
                    LogUtils.a("当前全部数据sss", Gson().toJson(data.get(i)))
                    return data.get(i)
                }
            }
        } else {
            return ServePersonnelDB()
        }
        return ServePersonnelDB()
    }

    //插入某一条数据
    fun setServe(datas: ServePersonnelDB) {
        var serveDB = getDaoSessions().servePersonnelDBDao
        var data = getServeData()
        if (data != null && data.size >= 1 && data.get(0).nickname != null) {
            if (haveData(data, datas)) {
                Toast.Tips("请勿重复添加")
            } else {
//                if (data.size >= user.getNum().toInt()) {
//                    Toast.Tips("当前邀请人数已到达最多邀请数")
//                } else {
                    try {
                        serveDB.insert(datas)
                    } catch (e: Exception) {
                        LogUtils.a("DB", e.toString())
                    }
//                }

            }
        } else {
            try {
                serveDB.insert(datas)
            } catch (e: Exception) {
                LogUtils.a("DB", e.toString())
            }
        }
        LogUtils.a("当前全部数据sss", Gson().toJson(data))
    }

    internal fun haveData(data: List<ServePersonnelDB>, datas: ServePersonnelDB): Boolean {
        for (i in data.indices) {
            if (data.get(i).nickname.equals(datas.nickname)) {
                return true
            }
        }
        return false
    }
}