package com.ycwl.servebixin.cn.ui.yue.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.*
import com.ycwl.servebixin.cn.db.DBUtils.getMerchat
import com.ycwl.servebixin.cn.db.DrinkUtils.getDrinksData
import com.ycwl.servebixin.cn.db.ServeUtils.deleteServe
import com.ycwl.servebixin.cn.db.ServeUtils.getServeData
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.ui.yue.adapter.YueDrinksAdapter
import com.ycwl.servebixin.cn.ui.yue.adapter.YueServeAdapter
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.ExpListBean
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.YueBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.YueBody
import com.ycwl.servebixin.cn.ui.yue.mvp.presenter.YuePresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.view.YueView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.image.ImageLoad
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.QRCodeUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import com.ycwl.servebixin.cn.utils.utils.UrlParse
import kotlinx.android.synthetic.main.activity_yue.*
import kotlinx.android.synthetic.main.layout_title.*
import kotlinx.android.synthetic.main.layout_yue_people.*
import kotlinx.android.synthetic.main.layout_yue_wine.*
import java.math.BigDecimal

class YueActivity:BaseActivity(),YueView{
    override fun getOrderCodeRequest(data: YueBean) {
        //http://bixinyule.com?bixinClientType=2&qrcodeType=2&orderCode=c3c5b2ec57fd5d0ac36323ba1363b3cadb9b3ee2dffea9a9eec486b7bd915a10
        var url="http://bixinyule.com?bixinClientType=2&qrcodeType=2&orderCode="+"${data.data.orderCode}"
        var bitmap= QRCodeUtils.createQRcodeImage(url,560,560)
        popView.findViewById<RoundedImageView>(R.id.pop_img).setImageBitmap(bitmap)
        pop.showAtLocation(window.decorView, Gravity.FILL,0,0)
    }

    private var drinkMoney=BigDecimal("0.0")
    private var serveMoney=BigDecimal("0.0")
    private var baofangMoney=BigDecimal("0.0")
    private var merchantID = ""
    private var baofangID=""
    private var baofangTypeID=""
    private var Adapter:YueDrinksAdapter?=null
    private var bixinIds=ArrayList<String>()
    private var drinks=ArrayList<YueBody.WinesBean>()
    private val presenter by lazy { YuePresenter(this,this,this) }
    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_yue

    override fun setActivityTitle() {
        titleText.text="生成订单"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRightText.text="重置"
    }

    override fun initActivityData() {
        layout_yue_none.visibility = View.VISIBLE
        titleRightText.visibility=View.GONE
        layout_yue_pay.visibility=View.GONE
        layout_yue_ktv.visibility = View.GONE
        tv_yue_people.visibility = View.GONE
        layoutYuePeople.visibility=View.GONE
        layoutYueWine.visibility=View.GONE
        popView = LayoutInflater.from(mContext).inflate(R.layout.pop_order_code, null)
        pop = PopupWindowHelper(popView)
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(layout_yue_none).subscribe {
            intentUtils.intentYueKTV()
        }
        Click.viewClick(tv_yue_people).subscribe {
//            intentUtils.intentYueServe("68872")
            intentUtils.intentSao(this,"扫描达人二维码")
        }
        Click.viewClick(tv_yue_wine).subscribe {
            intentUtils.intentYueDrinks(merchantID)
        }

        Click.viewClick(wineDel).subscribe {
            ShowDialog.showCustomDialogs(this, "是否清空已选酒水信息", "清空", "取消", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            dialog.dismiss()
                            DrinkUtils.deleteALLDrinks()
                            haveDrinks()
                            drinks.clear()
                            tv_yue_money.text="¥ ${serveMoney.add(drinkMoney).toString()}"
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }
            })
        }

        Click.viewClick(yueServeDel).subscribe {
            ShowDialog.showCustomDialogs(this, "是否清空已选达人信息", "清空", "取消", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            dialog.dismiss()
                            ServeUtils.deleteALLServe()
                            haveServe()
                            bixinIds.clear()
                            tv_yue_money.text="¥ ${serveMoney.add(drinkMoney).toString()}"
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }
            })
        }

        Click.viewClick(titleRightText).subscribe {
            ShowDialog.showCustomDialogs(this, "“重置”将清空所有信息 是否继续？", "继续", "取消", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            dialog.dismiss()
                            DBUtils.delMerchat()
                            DrinkUtils.deleteALLDrinks()
                            ServeUtils.deleteALLServe()
                            haveMerchant()
                            haveServe()
                            haveDrinks()
                            tv_yue_money.text="¥ ${serveMoney.add(drinkMoney).toString()}"
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }
            })
        }
        Click.viewClick(tv_yue_code).subscribe {
            if (!merchantID.equals("")) {
                if (bixinIds.size>0||drinks.size>0){
                    presenter.getOrderCode(YueBody(merchantID, baofangTypeID, baofangID, bixinIds, drinks))
                }else{
                    Toast.Tips("请添加酒水或达人")
                }
            }else{
                Toast.Tips("请选择约玩场地")
            }
        }

        Click.viewClick(popView.findViewById(R.id.pop_close)).subscribe { pop.dismiss() }

    }

    //有酒水时
    private fun haveDrinks() {
        var merchat = GreenDaoHelper.getDaoSessions().drinkDBDao
        drinks.clear()
        if (merchat != null) {
            var data = merchat.loadAll()
            if (data != null && data.size > 0) {
                layoutYueWine.visibility = View.VISIBLE
                tv_yue_wine.visibility = View.GONE
                LogUtils.a("保存的酒水" + Gson().toJson(data))
//                for (wine in data) {
//                    if (!map.containsKey(wine.mealName)) {
//                        val drinkDBS = ArrayList<DrinkDB>()
//                        drinkDBS.add(wine)
//                        map[wine.mealName] = drinkDBS
//                    } else {
//                        var drinkDBS = map[wine.mealName] as ArrayList
//                        drinkDBS.add(wine)
//                    }
//                }
//                yueDrinkss.setGroupIndicator(null)
                var list = ArrayList<ExpListBean.DrinkBean>()
                data.forEach {
                    list.add(ExpListBean.DrinkBean(it.drinkID,it.drinkImage,it.drinkMoney,it.drinkName,it.drinkNum,it.drinkText,it.id,it.mealName,it.mealName))
                }
                drinks()
//                for (wine in map) {
//                    var info = ExpListBean()
//                    var drinkList = ArrayList<ExpListBean.DrinkBean>()
//                    info.name = wine.key
//                    for (drink in wine.value) {
//                        var drinkInfo = ExpListBean.DrinkBean()
//                        drinkInfo.drinkID = drink.drinkID
//                        drinkInfo.drinkImage = drink.drinkImage
//                        drinkInfo.drinkMoney = drink.drinkMoney
//                        drinkInfo.drinkName = drink.drinkName
//                        drinkInfo.id = drink.id
//                        drinkInfo.drinkNum = drink.drinkNum
//                        drinkInfo.drinkText = drink.drinkText
//                        drinkList.add(drinkInfo)
//                    }
//                    info.drink = drinkList
//                    list.add(info)
//                }
                if (Adapter==null) {
                    Adapter = YueDrinksAdapter(list)
                    var manager = LinearLayoutManager(this)
                    manager.orientation = LinearLayout.VERTICAL
                    yueDrinks.layoutManager = manager
                    yueDrinks.adapter = Adapter
                }else{
                    Adapter!!.setNewData(list)
                }
                Adapter!!.setOnItemChildClickListener { adapter, view, position ->
                    var tv=(view.parent as LinearLayout).findViewById<TextView>(R.id.drinksNum)
                    var num=tv.text.toString().toInt()
                    when(view.id){
                        R.id.drinksSub ->{
                            if (num > 1) {
                                tv.text="${num-1}"
                                DrinkUtils.addDrinksNum(Adapter!!.data[position], tv.text.toString())

//                                Adapter!!.notifyDataSetChanged()
                                DrinksMoney()
                                drinks()
                                tv_yue_money.text="¥ ${serveMoney.add(drinkMoney).toString()}"

                            }else {
                                DrinkUtils.deleteDrinks(Adapter!!.data[position].id)
                                Adapter!!.remove(position)
//                                    Adapter!!.notifyDataSetChanged()
                                DrinksMoney()
                                drinks()
                                tv_yue_money.text="¥ ${serveMoney.add(drinkMoney).toString()}"

                            }

                        }
                        R.id.drinksAdd ->{
                            tv.text="${num+1}"
                            DrinkUtils.addDrinksNum(Adapter!!.data[position], tv.text.toString())
//                            Adapter!!.notifyDataSetChanged()

                            DrinksMoney()
                            drinks()
                            tv_yue_money.text="¥ ${serveMoney.add(drinkMoney).toString()}"


                        }
                    }
                }
//                for (i in list.indices) {
//                    yueDrinkss.expandGroup(i)
//                }
//                yueDrinkss.setOnGroupClickListener { parent, v, groupPosition, id ->
//                    return@setOnGroupClickListener true
//                }


                Click.viewClick(addDrinks).subscribe {
                    intentUtils.intentYueDrinks(merchantID)
                }

            } else {
                layoutYueWine.visibility = View.GONE
                tv_yue_wine.visibility = View.VISIBLE
            }
        } else {
            DrinksMoney()
            layoutYueWine.visibility = View.GONE
            tv_yue_wine.visibility = View.VISIBLE
        }
        DrinksMoney()
    }

    //酒水总价
    fun DrinksMoney() {
        drinkMoney = BigDecimal("0.0")
        val drinksMoney = getDrinksData()
        if (drinksMoney != null && drinksMoney!!.size > 0 && !drinksMoney!!.get(0).drinkName.isNullOrEmpty())
            drinksMoney.forEachIndexed { index, drinkDB ->
//                var num = drinkDB.drinkMoney.toDouble() * drinkDB.drinkNum.toInt()
                var num = BigDecimal(drinkDB.drinkMoney).multiply(BigDecimal(drinkDB.drinkNum))
                drinkMoney = drinkMoney.add(num)
            }
        wineMoney.setText("¥ ${drinkMoney.toString()}")

    }

    //有数据时商家
    fun haveMerchant() {
        var merchat = GreenDaoHelper.getDaoSessions().merchantDBDao
        if (merchat != null) {
            var data = merchat.loadAll()
            if (data != null && data.size >= 1) {
                layout_yue_none.visibility = View.GONE
                titleRightText.visibility=View.VISIBLE
                layout_yue_pay.visibility=View.VISIBLE
                layout_yue_ktv.visibility = View.VISIBLE
                tv_yue_people.visibility = View.VISIBLE
                layoutYuePeople.visibility=View.GONE
                layoutYueWine.visibility=View.GONE
                var merchantData = getMerchat()
                merchantID = merchantData.merchantID
                baofangID=merchantData.baofangID
                baofangTypeID=merchantData.baofangType
                Log.e("测试id",merchantID)
                ImageLoad.setImage(merchantData.merchantImage,yue_ktv_img)
                tv_yue_ktv_name.text = merchantData.merchantName
                tv_yue_ktv_baofang.text = "包房信息："+merchantData.baofangTypeName+"  "+merchantData.baofangName
                tv_yue_ktv_address.text="详细地址："+merchantData.merchantAdds
                if (merchantData.baofangPrice!=null&&merchantData.baofangPrice!=""){
                    baofangMoney = BigDecimal(merchantData.baofangPrice)
                }
                tv_yue_ktv_money.text="¥${merchantData.baofangPrice}"
                //修改包房类型
                Click.viewClick(tv_yue_ktv_name).subscribe {
                    intentUtils.intentYueKTV()
                }
            } else {
                layout_yue_none.visibility = View.VISIBLE
                titleRightText.visibility=View.GONE
                layout_yue_pay.visibility=View.GONE
                layout_yue_ktv.visibility = View.GONE
                tv_yue_people.visibility = View.GONE
                layoutYuePeople.visibility=View.GONE
                layoutYueWine.visibility=View.GONE
            }
        } else {
            layout_yue_none.visibility = View.VISIBLE
            titleRightText.visibility=View.GONE
            layout_yue_pay.visibility=View.GONE
            layout_yue_ktv.visibility = View.GONE
            tv_yue_people.visibility = View.GONE
            layoutYuePeople.visibility=View.GONE
            layoutYueWine.visibility=View.GONE
        }
    }

    //有服务人员
    fun haveServe() {
        var serveData = getServeData()
        bixinIds.clear()
        if (serveData != null && serveData.size >= 1 && serveData.get(0).avatar != null) {
            tv_yue_people.visibility = View.GONE
            layoutYuePeople.visibility = View.VISIBLE
            serveData.forEach {
                if (!bixinIds.contains(it.bixinId)){
                    bixinIds.add(it.bixinId)
                }
            }
            LogUtils.a("测试${bixinIds.size}")
            var adapters = YueServeAdapter(serveData)
            var merager = LinearLayoutManager(mContext)
            merager.orientation = LinearLayout.VERTICAL
            yueServeList.layoutManager = merager
            yueServeList.adapter = adapters
            adapters.setOnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.yueServeDell -> {
//                        serveMoney=serveMoney - adapters.data[position].skillPrice.toDouble()
                        serveMoney=serveMoney.subtract(BigDecimal(adapters.data[position].skillPrice))
                        deleteServe(adapters.data.get(position).id)
                        bixinIds.remove(adapters.data.get(position).bixinId)
                        adapters.remove(position)
                        ServesMoney()
                        tv_yue_money.text="¥ ${serveMoney.add(drinkMoney).toString()}"
                    }
                }
            }
            Click.viewClick(yuePeopleAdd).subscribe {
//                intentUtils.intentYueServe("3781924")
            intentUtils.intentSao(this,"扫描达人二维码")
            }
        } else {
            tv_yue_people.visibility = View.VISIBLE
            layoutYuePeople.visibility = View.GONE
        }
        ServesMoney()
    }

    //达人总价
    fun ServesMoney() {
        serveMoney = BigDecimal("0.0")
        var serveData = getServeData()
        if (serveData != null && serveData.size >= 1 && serveData.get(0).avatar != null) {
            serveData.forEachIndexed { index, servePersonnelDB ->
                var num = BigDecimal(servePersonnelDB.skillPrice)
                serveMoney = serveMoney .add(num)
            }
        }
        yuePeopleMoney.setText("¥ ${serveMoney.toString()}")

    }

    fun drinks(){
        var merchat = GreenDaoHelper.getDaoSessions().drinkDBDao
        drinks.clear()
        if (merchat != null) {
            var data = merchat.loadAll()
            if (data != null && data.size > 0) {
                LogUtils.a("保存的酒水" + Gson().toJson(data))
                data.forEach {
                    drinks.add(YueBody.WinesBean(it.drinkID,it.drinkNum))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        haveMerchant()
        haveServe()
        haveDrinks()
        tv_yue_money.text="¥ ${serveMoney.add(drinkMoney).toString()}"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK){
            LogUtils.a("扫描成功aaa" + data!!.extras.getString("codedContent"))
//            http://bixinyule.com?bixinClientType=2&qrcodeType=1&bixinId=6546499&identity=2
            var map= UrlParse.getUrlParams(data!!.extras.getString("codedContent"))
            if (map.containsKey("bixinClientType")){
                if (map.containsKey("qrcodeType")&&map["qrcodeType"]!=""&&map["qrcodeType"]=="1"&&map.containsKey("identity")&&map.containsKey("bixinId")){
                    intentUtils.intentYueServe(map["bixinId"]!!)
                }else{
                    Toast.Tips("请扫描比心号二维码")
                }
            }else{
                Toast.Tips("请扫描正确的二维码")
            }
        }
    }
}