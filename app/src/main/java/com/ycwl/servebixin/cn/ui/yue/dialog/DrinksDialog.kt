package com.ycwl.servebixin.cn.ui.yue.dialog

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseDialogFragment
import com.ycwl.servebixin.cn.db.DrinkUtils
import com.ycwl.servebixin.cn.db.DrinkUtils.deleteALLDrinks
import com.ycwl.servebixin.cn.db.DrinkUtils.getDrinksData
import com.ycwl.servebixin.cn.db.GreenDaoHelper
import com.ycwl.servebixin.cn.db.db.DrinkDB
import com.ycwl.servebixin.cn.ui.yue.adapter.DialogDrinksGroupAdapter
import com.ycwl.servebixin.cn.ui.yue.adapter.DialogDrinksItemAdapter

import com.ycwl.servebixin.cn.ui.yue.mvp.bean.ExpListBean
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import kotlinx.android.synthetic.main.dialog_drinks.*
import java.math.BigDecimal

@SuppressLint("ValidFragment")
class DrinksDialog(val drinks: Drinks,var type:Int) : BaseDialogFragment(), DialogDrinksGroupAdapter.Expandable{

    override fun ChildClick() {
        haveDrinks()
        drinks.ChildDrinksClick()
    }

    override fun setLayoutID(): Int = R.layout.dialog_drinks

    override fun isFullScreen(): Boolean = true

    override fun setLayoutData() {
        if (type==0){
            dialogdrinksGo.text="去约会"
        }else{
            dialogdrinksGo.text="生成订单二维码"
        }
        haveDrinks()
    }

    //有酒水时
    private fun haveDrinks() {
        var merchat = GreenDaoHelper.getDaoSessions().drinkDBDao
        if (merchat != null) {
            var data = merchat.loadAll()
            if (data != null && data.size >= 1) {
                drinksExList.visibility=View.VISIBLE
                val map = HashMap<String, List<DrinkDB>>()
                for (wine in data) {
                    if (!map.containsKey(wine.mealName)) {
                        val drinkDBS = ArrayList<DrinkDB>()
                        drinkDBS.add(wine)
                        map[wine.mealName] = drinkDBS
                    } else {
                        var drinkDBS = map[wine.mealName] as ArrayList
                        drinkDBS.add(wine)
                    }
                }
//                drinksExList.setGroupIndicator(null)
                var list = ArrayList<ExpListBean>()
                for (wine in map) {
                    var info = ExpListBean()
                    var drinkList = ArrayList<ExpListBean.DrinkBean>()
                    info.name = wine.key
                    for (drink in wine.value) {
                        var drinkInfo = ExpListBean.DrinkBean()
                        drinkInfo.drinkID = drink.drinkID
                        drinkInfo.drinkImage = drink.drinkImage
                        drinkInfo.drinkMoney = drink.drinkMoney
                        drinkInfo.drinkName = drink.drinkName
                        drinkInfo.id = drink.id
                        drinkInfo.drinkNum = drink.drinkNum
                        drinkInfo.drinkText = drink.drinkText
                        drinkList.add(drinkInfo)
                    }
                    info.drink = drinkList
                    list.add(info)
                }
                var adapters = DialogDrinksGroupAdapter(list,this)
                var manager= LinearLayoutManager(activity!!)
                drinksExList.layoutManager=manager
                drinksExList.setAdapter(adapters)
//                Click.viewClick(addDrinkss).subscribe { intentDrinks(merchantID) }
                setMoney()
            } else {
                drinksExList.visibility=View.GONE
                setMoney()
            }
        } else {
            drinksExList.visibility=View.GONE
            setMoney()
        }
    }

    override fun clickListener() {
        Click.viewClick(dialogOver).subscribe {
            dismiss()
        }
        Click.viewClick(delDrinks).subscribe {
            ShowDialog.showCustomDialogs(activity, "是否清空已选酒水信息", "清空", "取消", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            dialog.dismiss()
//                            deleteALLDrinks()
                            DrinkUtils.deleteALLDrinks()
                            dismiss()
                            drinks.ChildDrinksClick()
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }
            })
        }
        Click.viewClick(dialogdrinksGo).subscribe {
            dismiss()
            if (type==0){
                intentUtils.intentYue()
            }else{
                drinks.DrinksDialogOver()
            }

//            if ("1".equals(user.getType())) {
//                dismiss()
//                drinks.DrinksDialogOver()
//            } else {
//                val addDrinks = AddDrinksDialog()
//                addDrinks.show(activity!!.supportFragmentManager, "")
//            }
        }
    }

    fun setMoney() {
        var merchat = GreenDaoHelper.getDaoSessions().drinkDBDao
        if (merchat != null) {
            var data = merchat.loadAll()
            if (data != null && data.size >= 1) {
                DrinksMoney()
            } else {
                dialogmoneysss.setText("¥:0.00")
                dialogdrinksnum.setText("0")
            }
        } else {
            dialogmoneysss.setText("¥:0.00")
            dialogdrinksnum.setText("0")
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
//                money = money + m
//                num=num+drinkDB.drinkNum.toInt()
                var m = BigDecimal(drinkDB.drinkMoney).multiply(BigDecimal(drinkDB.drinkNum))
                money = money.add(m)
                num=num.add(BigDecimal(drinkDB.drinkNum))
            }
        }
        dialogmoneysss.setText("¥:${money.toString()}")
        dialogdrinksnum.setText("${num.toString()}")

    }

    interface Drinks {
        fun ChildDrinksClick()
        fun DrinksDialogOver()
    }
}