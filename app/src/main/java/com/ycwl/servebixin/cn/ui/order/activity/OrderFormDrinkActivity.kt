package com.ycwl.servebixin.cn.ui.order.activity

import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.google.gson.Gson
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.ACache
import com.ycwl.servebixin.cn.db.DrinkUtils
import com.ycwl.servebixin.cn.db.GreenDaoHelper
import com.ycwl.servebixin.cn.db.db.MerchantDrinkDB
import com.ycwl.servebixin.cn.ui.login.adapter.FragmentAdapter
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderDrinkCodeBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderDrinkDB
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderDrinkCodeBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.OrderFormDrinkBody
import com.ycwl.servebixin.cn.ui.order.mvp.presenter.OrderFormDrinkPresenter
import com.ycwl.servebixin.cn.ui.order.mvp.view.OrderFormDrinkView
import com.ycwl.servebixin.cn.ui.yue.dialog.DrinksDialog
import com.ycwl.servebixin.cn.ui.yue.fragment.YueDrinksFragment
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.utils.utils.QRCodeUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_yue_drink.*
import kotlinx.android.synthetic.main.layout_error_network.*
import kotlinx.android.synthetic.main.layout_title.*
import java.math.BigDecimal

class OrderFormDrinkActivity:BaseActivity(),OrderFormDrinkView,YueDrinksFragment.Drinks,DrinksDialog.Drinks{
    override fun getDrinkCodeRequest(data: OrderDrinkCodeBean) {
//        Toast.Tips("订单生成成功")
//
        var url="http://bixinyule.com?bixinClientType=2&qrcodeType=2&wineCode="+"${data.data.wineCode}"
        var bitmap= QRCodeUtils.createQRcodeImage(url,560,560)
        popView.findViewById<RoundedImageView>(R.id.pop_img).setImageBitmap(bitmap)
        pop.showAtLocation(window.decorView, Gravity.FILL,0,0)
    }

    override fun ChildDrinksClick() {
        setMoney()
        (fragments[drinksPager.currentItem] as YueDrinksFragment).init()
    }

    override fun DrinksDialogOver() {
        setMoney()
        (fragments[drinksPager.currentItem] as YueDrinksFragment).init()
        if (DrinkUtils.getDrinksData()!=null&&DrinkUtils.getDrinksData().size>0) {
            presenter.getOrderDrinkCode(OrderDrinkCodeBody(orderNo,drinks))
        }else{
            Toast.Tips("请添加酒水")
        }

    }

    override fun setNewMoney() {
        setMoney()
//        (fragments[drinksPager.currentItem] as YueDrinksFragment).init()
    }

    private val presenter by lazy { OrderFormDrinkPresenter(this,this,this) }
    var mFragmentAdapter: FragmentAdapter? = null
    var drinksDialog = DrinksDialog(this,1)
    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View

    private var orderNo = ""
    private var drinks=ArrayList<OrderDrinkCodeBody.WinesBean>()
    private var fragments = ArrayList<Fragment>()

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int = R.layout.activity_yue_drink

    override fun setActivityTitle() {
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
        titleText.text="添加酒水"
    }

    override fun initActivityData() {
        drinksGo.text="生成订单二维码"
//        var mCache = ACache.get(this)
        orderNo = intent.getStringExtra("orderNo")
//        var info = Gson().fromJson("${mCache.getAsString("order")}", OrderDrinkDB::class.java)
//        if (info != null && orderNo.equals(info.orderNo)) {
//            var data = Gson().fromJson(info.orderDrinkData, DrinksBean::class.java)
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
//
//        } else {
            presenter.getOrderFormDrink(OrderFormDrinkBody(orderNo))
//        }
        setMoney()
        popView = LayoutInflater.from(mContext).inflate(R.layout.pop_order_code, null)
        pop = PopupWindowHelper(popView)
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(drinksGo).subscribe {
            if (DrinkUtils.getDrinksData()!=null&&DrinkUtils.getDrinksData().size>0) {
                presenter.getOrderDrinkCode(OrderDrinkCodeBody(orderNo, drinks))
            }else{
                Toast.Tips("请添加酒水")
            }
        }
        Click.viewClick(drinksLists).subscribe { drinksDialog.show(supportFragmentManager, "") }

        Click.viewClick(popView.findViewById(R.id.pop_close)).subscribe { pop.dismiss() }
    }

    override fun getDrinksRequest(data: DrinksBean) {
//        var mCache = ACache.get(this)
        var info = OrderDrinkDB()
        info.orderNo = orderNo
        info.orderDrinkData = Gson().toJson(data)
//        mCache.put("order", Gson().toJson(info))
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
        drinks.clear()
        var drinks = DrinkUtils.getDrinksData()
        if (drinks!=null&&drinks.size>0) {
            drinks.forEachIndexed { index, drinkDB ->
                this.drinks.add(OrderDrinkCodeBody.WinesBean(drinkDB.drinkID, drinkDB.drinkNum))
//                var m = drinkDB.drinkMoney.toDouble() * drinkDB.drinkNum.toInt()
//                money = money + m
//                num=num+drinkDB.drinkNum.toInt()
                var m = BigDecimal(drinkDB.drinkMoney).multiply(BigDecimal(drinkDB.drinkNum))
                money=money.add(m)
                num=num.add(BigDecimal(drinkDB.drinkNum))
            }
        }
        moneysss.setText("¥:${money.toString()}")
        drinksnum.setText("${num.toString()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        DrinkUtils.deleteALLDrinks()//删除所有酒水
    }
}