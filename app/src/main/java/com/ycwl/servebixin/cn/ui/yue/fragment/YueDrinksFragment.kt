package com.ycwl.servebixin.cn.ui.yue.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseFragment
import com.ycwl.servebixin.cn.db.DrinkUtils.setDrinks
import com.ycwl.servebixin.cn.db.GreenDaoHelper
import com.ycwl.servebixin.cn.db.db.DrinkDB
import com.ycwl.servebixin.cn.ui.yue.adapter.DrinksAdapter
import com.ycwl.servebixin.cn.ui.yue.dialog.DrinkDetailsDialog
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.fragment_yue_drinks.*

@SuppressLint("ValidFragment")
class YueDrinksFragment(val data: MutableList<DrinksBean.DataBean.WineTypesBean.WinesBean>, val drinks: Drinks) : BaseFragment(), DrinkDetailsDialog.DrinkDetails {
    override fun setNewMoney() {
//        drinksAdapter.notifyDataSetChanged()
//        var adapterData = ArrayList<DrinksBean.DataBean.WineTypesBean.WinesBean>()
//        for (i in data.indices) {
//            var info = DrinksBean.DataBean.WineTypesBean.WinesBean()
//            info.businessWinePrice = data.get(i).businessWinePrice
//            info.businessWineDetails = data.get(i).businessWineDetails
//            info.businessWineName = data.get(i).businessWineName
//            info.businessWineId = data.get(i).businessWineId
//            info.businessWineImg = data.get(i).businessWineImg
//            info.businessWineUnit = data.get(i).businessWineUnit
//            info.businessWineSpecifications=data.get(i).businessWineSpecifications
//            var merchat = GreenDaoHelper.getDaoSessions().drinkDBDao
//            if (merchat != null) {
//                var datadb = merchat.loadAll()
//                if (datadb != null && datadb.size >= 1) {
//                    for (drinks in datadb) {
//                        if (info.businessWineName.equals(drinks.drinkName)) {
//                            info.drinksNum = drinks.drinkNum.toInt()
//                        }
//                    }
//                } else info.drinksNum = 0
//            } else info.drinksNum = 0
//            adapterData.add(info)
//        }
//        drinksAdapter!!.setNewData(adapterData)
//        drinks.setNewMoney()
        init()
    }

    override fun openEventBus(): Boolean = false
    private lateinit var info: DrinksBean.DataBean
    private var drinksAdapter:DrinksAdapter?=null
    private var isCreat=false

    fun setData(get: DrinksBean.DataBean) {
        this.info = get
    }

    override fun setLayoutTitle() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCreat=true
    }

    override fun initFragmentData() {
        var adapterData = ArrayList<DrinksBean.DataBean.WineTypesBean.WinesBean>()
        for (i in data.indices) {
            var info = DrinksBean.DataBean.WineTypesBean.WinesBean()
            info.businessWinePrice = data.get(i).businessWinePrice
            info.businessWineDetails = data.get(i).businessWineDetails
            info.businessWineName = data.get(i).businessWineName
            info.businessWineId = data.get(i).businessWineId
            info.businessWineImg = data.get(i).businessWineImg
            info.businessWineUnit = data.get(i).businessWineUnit
            info.businessWineSpecifications=data.get(i).businessWineSpecifications
            var merchat = GreenDaoHelper.getDaoSessions().drinkDBDao
            if (merchat != null) {
                var datadb = merchat.loadAll()
                if (datadb != null && datadb.size >= 1) {
                    for (drinks in datadb) {
                        if (info.businessWineName.equals(drinks.drinkName)) {
                            info.drinksNum = drinks.drinkNum.toInt()
                        }
                    }
                } else info.drinksNum = 0
            } else info.drinksNum = 0
            adapterData.add(info)
        }
        drinksAdapter = DrinksAdapter(adapterData, info)
        var manager = LinearLayoutManager(mContext)
        manager.orientation = LinearLayout.VERTICAL
        drinksList.layoutManager = manager
        drinksList.adapter = drinksAdapter
        drinksAdapter!!.setOnItemChildClickListener { adapter, view, position ->
            var item = drinksAdapter!!.data.get(position)
            when (view.id) {
                R.id.drinksSub -> {
                    when (item.drinksNum) {
                        0 -> Toast.Tips("数量为0")
                        else -> {
                            drinksAdapter!!.data.get(position).drinksNum = item.drinksNum - 1
                            drinksAdapter!!.notifyDataSetChanged()
                            setDrinksDB(item, "${drinksAdapter!!.data.get(position).drinksNum}")
                        }
                    }

                }
                R.id.drinksAdd -> {
                    var nuwnum = drinksAdapter!!.data.get(position).drinksNum
                    drinksAdapter!!.data.get(position).drinksNum = nuwnum + 1
                    drinksAdapter!!.notifyDataSetChanged()
                    setDrinksDB(item, "${drinksAdapter!!.data.get(position).drinksNum}")
                }
            }
        }
        drinksAdapter!!.setOnItemClickListener { adapter, view, position ->
            var drinkDialog = DrinkDetailsDialog(this)
            drinkDialog.setData(drinksAdapter!!.data.get(position), info.wineTypes)
            drinkDialog.show(activity!!.supportFragmentManager, "")

        }
    }

    private fun setDrinksDB(item: DrinksBean.DataBean.WineTypesBean.WinesBean, s: String) {

        var drink = DrinkDB()
        drink.mealName = info.wineTypes.wineTypeName
        drink.mealId = "${info.wineTypes.wineTypeId}"
        drink.drinkNum = s
        drink.drinkID = "${item.businessWineId}"
        drink.drinkMoney = "${item.businessWinePrice}"
        drink.drinkName = item.businessWineName
        if (item.businessWineDetails==null&&item.businessWineSpecifications!=null){
            drink.drinkText=item.businessWineSpecifications+"/${item.businessWineUnit}"
        }else {
            drink.drinkText = item.businessWineDetails
        }
        drink.drinkImage = item.businessWineImg
        setDrinks(drink)
        drinks.setNewMoney()
    }

    override fun setFragmentListener() {
    }

    override fun layoutID(): Int = R.layout.fragment_yue_drinks

    interface Drinks {
        fun setNewMoney()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isCreat){
            return
        }

        if (isVisibleToUser){
            init()
        }
    }

    public fun init(){
        var adapterData = ArrayList<DrinksBean.DataBean.WineTypesBean.WinesBean>()
        for (i in data.indices) {
            var info = DrinksBean.DataBean.WineTypesBean.WinesBean()
            info.businessWinePrice = data.get(i).businessWinePrice
            info.businessWineDetails = data.get(i).businessWineDetails
            info.businessWineName = data.get(i).businessWineName
            info.businessWineId = data.get(i).businessWineId
            info.businessWineImg = data.get(i).businessWineImg
            info.businessWineUnit = data.get(i).businessWineUnit
            info.businessWineSpecifications=data.get(i).businessWineSpecifications
            var merchat = GreenDaoHelper.getDaoSessions().drinkDBDao
            if (merchat != null) {
                var datadb = merchat.loadAll()
                if (datadb != null && datadb.size >= 1) {
                    for (drinks in datadb) {
                        if (info.businessWineName.equals(drinks.drinkName)) {
                            info.drinksNum = drinks.drinkNum.toInt()
                        }
                    }
                } else info.drinksNum = 0
            } else info.drinksNum = 0
            adapterData.add(info)
        }
        drinksAdapter!!.setNewData(adapterData)
        drinks.setNewMoney()
    }
}