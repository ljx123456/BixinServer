package com.ycwl.servebixin.cn.ui.yue.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class DrinksAdapter(list: MutableList<DrinksBean.DataBean.WineTypesBean.WinesBean>, val info: DrinksBean.DataBean) : BaseQuickAdapter<DrinksBean.DataBean.WineTypesBean.WinesBean, BaseViewHolder>(R.layout.item_drinks, list) {

    override fun convert(helper: BaseViewHolder, item: DrinksBean.DataBean.WineTypesBean.WinesBean) {
//        var sub = helper.getView<ImageView>(R.id.drinksSub)
//        var add = helper.getView<ImageView>(R.id.drinksAdd)
//        var num = helper.getView<TextView>(R.id.drinksNum)
        ImageLoad.setImage(item.businessWineImg, helper.getView(R.id.drinksImage) as RoundedImageView)
        helper.setText(R.id.drinksName, item.businessWineName)
                .setText(R.id.drinksMoney, "${item.businessWinePrice}")
                .addOnClickListener(R.id.drinksSub)
                .addOnClickListener(R.id.drinksAdd)
                .setText(R.id.drinksNum, "${item.drinksNum}")
        if (item.businessWineDetails==null&&item.businessWineSpecifications!=null){
            helper.setText(R.id.drinksContext, item.businessWineSpecifications+"/${item.businessWineUnit}")
        }else{
            helper.setText(R.id.drinksContext, item.businessWineDetails)
        }
//        var merchat = GreenDaoHelper.getDaoSessions().drinkDBDao
//        if (merchat != null) {
//            var datadb = merchat.loadAll()
//            if (datadb != null && datadb.size >= 1) {
//                for (drinks in datadb) {
//                    if (item.businessWineName.equals(drinks.drinkName)) {
//                        helper.setText(R.id.drinksNum, "${drinks.drinkNum}")
//                    }
//                }
//            } else helper.setText(R.id.drinksNum, "0")
//        } else helper.setText(R.id.drinksNum, "0")

//        Click.viewClick(sub).subscribe {
//            var nuwnum = num.text.toString().toInt()
//            var drink = DrinkDB()
//            when (nuwnum) {
//                0 -> {
//                    num.setText("${0}")
//                    drink.drinkNum = "0"
//                }
//                else -> {
//                    num.setText("${nuwnum - 1}")
//                    drink.drinkNum = "${num.text.toString()}"
//                }
//            }
//            drink.mealName = info.wineTypes.wineTypeName
//            drink.mealId = "${info.wineTypes.wineTypeId}"
//            drink.drinkID = "${item.businessWineId}"
//            drink.drinkMoney = "${item.businessWinePrice}"
//            drink.drinkName = item.businessWineName
//            drink.drinkText = item.businessWineDetails
//            drink.drinkImage = item.businessWineImg
//            setDrinks(drink)
//            notifyDataSetChanged()
//            drinks.setNewMoney(sub)
//        }

//        Click.viewClick(add).subscribe {
//            var nuwnum = num.text.toString().toInt()
//            num.setText("${nuwnum + 1}")
//            notifyDataSetChanged()
//            var drink = DrinkDB()
//            drink.mealName = info.wineTypes.wineTypeName
//            drink.mealId = "${info.wineTypes.wineTypeId}"
//            drink.drinkNum = "${num.text.toString()}"
//            drink.drinkID = "${item.businessWineId}"
//            drink.drinkMoney = "${item.businessWinePrice}"
//            drink.drinkName = item.businessWineName
//            drink.drinkText = item.businessWineDetails
//            drink.drinkImage = item.businessWineImg
//            setDrinks(drink)
//            drinks.setNewMoney(add)
//        }
    }
}