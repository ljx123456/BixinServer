package com.ycwl.servebixin.cn.ui.yue.activity

import android.support.v4.app.Fragment
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.google.gson.Gson
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.ACache
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.DrinkUtils.getDrinksData
import com.ycwl.servebixin.cn.db.GreenDaoHelper
import com.ycwl.servebixin.cn.db.db.MerchantDrinkDB
import com.ycwl.servebixin.cn.ui.login.adapter.FragmentAdapter
import com.ycwl.servebixin.cn.ui.yue.dialog.DrinksDialog
import com.ycwl.servebixin.cn.ui.yue.fragment.YueDrinksFragment
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.DrinksBody
import com.ycwl.servebixin.cn.ui.yue.mvp.presenter.DrinksPresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.view.DrinksView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.activity_yue_drink.*
import kotlinx.android.synthetic.main.layout_error_network.*
import kotlinx.android.synthetic.main.layout_title.*
import org.greenrobot.greendao.DbUtils
import java.math.BigDecimal


class YueDrinkActivity: BaseActivity(), DrinksView, YueDrinksFragment.Drinks,DrinksDialog.Drinks {



    override fun DrinksDialogOver() {
        (fragments[drinksPager.currentItem] as YueDrinksFragment).init()
    }
//
    override fun ChildDrinksClick() {
        setMoney()
        (fragments[drinksPager.currentItem] as YueDrinksFragment).init()
    }

    override fun setNewMoney() {
        setMoney()
//        initActivityData()
    }


    private val presenter by lazy { DrinksPresenter(this, this, this) }

    var mFragmentAdapter: FragmentAdapter? = null
    var drinksDialog = DrinksDialog(this,0)
    private var businessId = ""
    private var fragments = ArrayList<Fragment>()

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_yue_drink

    override fun setActivityTitle() {
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
//        titleText.setText("蜂巢KTV（武侯店）")
    }

    override fun initActivityData() {
        var mCache = ACache.get(this)
        businessId = intent.getStringExtra("businessId")
        var merchantData = DBUtils.getMerchat()
        if (merchantData!=null){
            titleText.text=merchantData.merchantName
        }
//        businessId = "1"
//        var info = Gson().fromJson("${mCache.getAsString("merchant")}", MerchantDrinkDB::class.java)
//        if (info != null && businessId.equals(info.merchantID)) {
//            var data = Gson().fromJson(info.merchantData, DrinksBean::class.java)
//            errorLayout.visibility = View.GONE
//            var titles = ArrayList<String>()
//            data.data.forEach { titles.add(it.wineTypes.wineTypeName) }
//
//            titles.forEachIndexed { index, s ->
//                drinksTab.addTab(drinksTab.newTab().setText(s))
//                var fragment = YueDrinksFragment(data.data.get(index).wineTypes.wines, this)
//                fragment.setData(data.data.get(index))
//                fragments.add(fragment)
//            }
//            mFragmentAdapter = FragmentAdapter(getSupportFragmentManager(), fragments, titles)
//            drinksPager.adapter = mFragmentAdapter
//            drinksTab.setupWithViewPager(drinksPager)
//            drinksTab.setTabsFromPagerAdapter(mFragmentAdapter)
//            drinksPager.setOffscreenPageLimit(1)
//        } else {
            presenter.getDrinks(DrinksBody(businessId))
//        }
        setMoney()
    }

    override fun getDrinksRequest(data: DrinksBean) {
//        var mCache = ACache.get(this)
        var info = MerchantDrinkDB()
        info.merchantID = businessId
        info.merchantData = Gson().toJson(data)
//        mCache.put("merchant", Gson().toJson(info))
        errorLayout.visibility = View.GONE
        var titles = ArrayList<String>()
        data.data.forEach { titles.add(it.wineTypes.wineTypeName) }
         fragments = ArrayList<Fragment>()
        titles.forEachIndexed { index, s ->
            drinksTab.addTab(drinksTab.newTab().setText(s))
            var fragment = YueDrinksFragment(data.data.get(index).wineTypes.wines, this)
            fragment.setData(data.data.get(index))
            fragments.add(fragment)
        }
        mFragmentAdapter = FragmentAdapter(getSupportFragmentManager(), fragments, titles)
        drinksPager.adapter = mFragmentAdapter
        drinksTab.setupWithViewPager(drinksPager)
        drinksTab.setTabsFromPagerAdapter(mFragmentAdapter)
        drinksPager.setOffscreenPageLimit(1)
    }

    override fun getDrinksError() {
        errorLayout.visibility = View.VISIBLE
        Click.viewClick(errorLayout).subscribe { presenter.getDrinks(DrinksBody(businessId)) }
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(drinksGo).subscribe {
            intentUtils.intentYue()
//            if ("1".equals(getType())) {
//                finish()
//            } else {
//                val addDrinks = AddDrinksDialog()
//                addDrinks.show(supportFragmentManager, "")
//            }
        }
        Click.viewClick(drinksLists).subscribe { drinksDialog.show(supportFragmentManager, "") }
    }


    fun setMoney() {
        var merchat = GreenDaoHelper.getDaoSessions().drinkDBDao
        if (merchat != null) {
            var data = merchat.loadAll()
            if (data != null && data.size >= 1) {
                DrinksMoney()
            } else {
                moneysss.setText("¥:0.00")
                drinksnum.setText("0")
            }
        } else {
            moneysss.setText("¥:0.00")
            drinksnum.setText("0")
        }
    }

    //总价
    fun DrinksMoney() {
        var money = BigDecimal("0.0")
        var num=BigDecimal("0")
        var drinks = getDrinksData()
        if (drinks!=null&&drinks.size>0) {
            drinks.forEachIndexed { index, drinkDB ->
//                var m = drinkDB.drinkMoney.toDouble() * drinkDB.drinkNum.toInt()
                var m = BigDecimal(drinkDB.drinkMoney).multiply(BigDecimal(drinkDB.drinkNum))
                money = money.add(m)
                num=num.add(BigDecimal(drinkDB.drinkNum))
            }
        }
        moneysss.setText("¥:${money.toString()}")
        drinksnum.setText("${num.toString()}")
    }

    override fun onDestroy() {
        super.onDestroy()
//        if ("1".equals(getType())) {
//
//        } else {
//            DrinkUtils.deleteALLDrinks()//删除所有酒水
//        }
    }
}