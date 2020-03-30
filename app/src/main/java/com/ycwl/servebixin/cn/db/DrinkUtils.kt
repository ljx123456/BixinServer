package com.ycwl.servebixin.cn.db

import com.blankj.utilcode.util.LogUtils

import com.google.gson.Gson
import com.ycwl.servebixin.cn.db.db.DrinkDB
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.ExpListBean

object DrinkUtils {


    //添加或者减少数量
    fun addDrinksNum(key: ExpListBean.DrinkBean, skillNum: String) {
        var info = DrinkDB()
        info.drinkName = key.drinkName
        info.drinkText = key.drinkText
        info.drinkMoney = key.drinkMoney
        info.drinkNum = skillNum
        info.drinkID = key.drinkID
        info.drinkImage = key.drinkImage
        info.id = key.id
        info.mealId = key.mealId
        info.mealName = key.mealName

        var DrinkDB = GreenDaoHelper.getDaoSessions().drinkDBDao
        var data = DrinkDB.load(key.id)
        data.drinkNum = skillNum
        DrinkDB.update(data)
//        try {
//            DrinkDB.updateKeyAfterInsert(info, key.id)
//        } catch (e: Exception) {
//            LogUtils.a("DB", e.toString())
//        }
        LogUtils.a("全部数据", Gson().toJson(getDrinksData()))
    }

    //删除对应某条数据
    fun deleteDrinks(key: Long) {
        var DrinkDB = GreenDaoHelper.getDaoSessions().drinkDBDao
        DrinkDB.deleteByKey(key)
    }

    //获取全部数据库数据
    fun getDrinksData(): List<DrinkDB> {
        var DrinkDB = GreenDaoHelper.getDaoSessions().drinkDBDao
        if (DrinkDB != null) {
            var data = DrinkDB.loadAll()
            if (data != null && data.size > 0) {
                for (i in data.indices) {
                    if (data.get(i).drinkNum.equals("0")) {
                        deleteDrinks(data.get(i).id)
                    }
                }
                return data
            } else {
                var list = ArrayList<DrinkDB>()
//                var model = DrinkDB()
//                list.add(model)
                return list
            }
        } else {
            var list = ArrayList<DrinkDB>()
//            var model = DrinkDB()
//            list.add(model)
            return list
        }
    }


    //删除全部数据
    fun deleteALLDrinks() {
        var DrinkDB = GreenDaoHelper.getDaoSessions().drinkDBDao
        DrinkDB.deleteAll()
    }


    //获取对应某条信息
    fun getDrinks(id: Int): DrinkDB {
        var data = getDrinksData()
        if (data != null && data.size > 0) {
            for (i in data.indices) {
                LogUtils.a("当前全部数据", Gson().toJson(data))
                if (id.toLong() == data.get(i).id) {
                    LogUtils.a("当前全部数据sss", Gson().toJson(data.get(i)))
                    return data.get(i)
                }
            }
        } else {
            return DrinkDB()
        }
        return DrinkDB()
    }

    fun ScreenData(datas: DrinkDB): Screen {
        var data = getDrinksData()
        if (data!=null&&data.size>0) {
            for (i in data.indices) {
                if (data.get(i).mealName.equals(datas.mealName) && data.get(i).drinkName.equals(datas.drinkName)) {
                    return Screen(true, data.get(i).id)
                }
            }
        }
        return Screen(false, 0)
    }

    class Screen(var type: Boolean, var num: Long)

    //插入某一条数据
    fun setDrinks(datas: DrinkDB) {
        var DrinkDB = GreenDaoHelper.getDaoSessions().drinkDBDao
        var data = getDrinksData()
        if (data != null && data.size >= 1 && data.get(0).mealName != null) {
            var sereen = ScreenData(datas)
            if (sereen.type) {
                if (datas.drinkNum.equals("0")) {
                    deleteDrinks(sereen.num)
                } else {
                    try {
                        datas.id = sereen.num
                        DrinkDB.update(datas)
                    } catch (e: Exception) {
                        LogUtils.a("DB", e.toString())
                    }
                }
            } else {
//                var size = data.size
//                datas.id = size.toLong()
                try {
                    DrinkDB.insert(datas)
                } catch (e: Exception) {
                    LogUtils.a("DB", e.toString())
                }
            }
        } else {
            try {
//                datas.id = 0.toLong()
                DrinkDB.insert(datas)
            } catch (e: Exception) {
                LogUtils.a("DB", e.toString())
            }
        }
    }

}