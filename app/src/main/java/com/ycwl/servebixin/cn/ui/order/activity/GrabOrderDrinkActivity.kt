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
import com.ycwl.servebixin.cn.ui.login.adapter.FragmentAdapter
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderDrinkCodeBean
import com.ycwl.servebixin.cn.ui.order.mvp.bean.OrderDrinkDB
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderDrinkBody
import com.ycwl.servebixin.cn.ui.order.mvp.body.GrabOrderDrinkCodeBody
import com.ycwl.servebixin.cn.ui.order.mvp.presenter.GrabOrderDrinkPresenter
import com.ycwl.servebixin.cn.ui.order.mvp.view.GrabOrderDrinkView
import com.ycwl.servebixin.cn.ui.yue.dialog.DrinksDialog
import com.ycwl.servebixin.cn.ui.yue.fragment.YueDrinksFragment
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.utils.utils.QRCodeUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_yue_drink.*
import kotlinx.android.synthetic.main.layout_error_network.*
import kotlinx.android.synthetic.main.layout_title.*
import java.math.BigDecimal

class GrabOrderDrinkActivity:BaseActivity(),GrabOrderDrinkView, YueDrinksFragment.Drinks, DrinksDialog.Drinks{


    override fun ChildDrinksClick() {
        setMoney()
        (fragments[drinksPager.currentItem] as YueDrinksFragment).init()
    }

    override fun DrinksDialogOver() {
        setMoney()
        (fragments[drinksPager.currentItem] as YueDrinksFragment).init()
        if (DrinkUtils.getDrinksData()!=null&&DrinkUtils.getDrinksData().size>0)  {
            presenter.getGrabOrderDrinkCode(GrabOrderDrinkCodeBody(inviteId, drinks))
        }else{
            Toast.Tips("请添加酒水")
        }
    }

    override fun setNewMoney() {
        setMoney()
    }

    private val presenter by lazy { GrabOrderDrinkPresenter(this,this,this) }
    var mFragmentAdapter: FragmentAdapter? = null
    var drinksDialog = DrinksDialog(this,1)
    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View
    private var fragments = ArrayList<Fragment>()

    private var inviteId = ""
    private var drinks=ArrayList<GrabOrderDrinkCodeBody.WinesBean>()

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int = R.layout.activity_yue_drink

    override fun setActivityTitle() {
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
        titleText.text="添加酒水"
    }

    override fun initActivityData() {
        drinksGo.text="生成订单二维码"
//        var mCache = ACache.get(this)
        inviteId = intent.getStringExtra("inviteId")
//        var info = Gson().fromJson("${mCache.getAsString("invite")}", OrderDrinkDB::class.java)
//        if (info != null && inviteId.equals(info.orderNo)) {
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
//        } else {
            presenter.getGrabOrderDrink(GrabOrderDrinkBody(inviteId))
//        }
        setMoney()
        popView = LayoutInflater.from(mContext).inflate(R.layout.pop_order_code, null)
        pop = PopupWindowHelper(popView)
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(drinksGo).subscribe {
            if (DrinkUtils.getDrinksData()!=null&&DrinkUtils.getDrinksData().size>0)  {
                presenter.getGrabOrderDrinkCode(GrabOrderDrinkCodeBody(inviteId, drinks))
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
        info.orderNo = inviteId
        info.orderDrinkData = Gson().toJson(data)
//        mCache.put("invite", Gson().toJson(info))
        errorLayout.visibility = View.GONE
        var titles = ArrayList<String>()
        data.data.forEach { titles.add(it.wineTypes.wineTypeName) }
        fragments = ArrayList<Fragment>()
        fragments.clear()
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

    override fun getDrinkCodeRequest(data: OrderDrinkCodeBean) {
//        Toast.Tips("订单生成成功")
        //http://bixinyule.com?bixinClientType=2&qrcodeType=2&wineCode=c3c5b2ec57fd5d0ac36323ba1363b3cadb9b3ee2dffea9a9eec486b7bd915a10
        var url="http://bixinyule.com?bixinClientType=2&qrcodeType=2&wineCode="+"${data.data.wineCode}"
        var bitmap= QRCodeUtils.createQRcodeImage(url,560,560)
        popView.findViewById<RoundedImageView>(R.id.pop_img).setImageBitmap(bitmap)
        pop.showAtLocation(window.decorView, Gravity.FILL,0,0)
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
        drinks.clear()
        var num=BigDecimal("0")
        var drinks = DrinkUtils.getDrinksData()
        if (drinks!=null&&drinks.size>0) {
            drinks.forEachIndexed { index, drinkDB ->
                this.drinks.add(GrabOrderDrinkCodeBody.WinesBean(drinkDB.drinkID, drinkDB.drinkNum))
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