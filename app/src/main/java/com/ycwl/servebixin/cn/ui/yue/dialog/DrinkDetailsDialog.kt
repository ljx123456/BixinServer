package com.ycwl.servebixin.cn.ui.yue.dialog

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseDialogFragment
import com.ycwl.servebixin.cn.db.DrinkUtils.setDrinks
import com.ycwl.servebixin.cn.db.db.DrinkDB
import com.ycwl.servebixin.cn.ui.yue.adapter.DrinkDetailsAdapter
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinkDetailsBean
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.DrinksBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.DrinkDetailsBody
import com.ycwl.servebixin.cn.ui.yue.mvp.presenter.DrinkDetailsPresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.view.DrinkDetailsView

import kotlinx.android.synthetic.main.dialog_drinks_details.*


@SuppressLint("ValidFragment")
class DrinkDetailsDialog(val drinkdetails: DrinkDetails) : BaseDialogFragment(), DrinkDetailsView {
    override fun getDrinkDetailsRequest(data: DrinkDetailsBean) {
        var drinkAdapter = DrinkDetailsAdapter(data.data.wineDetailsInfo)
        var manager = LinearLayoutManager(activity)
        drinksDetailsList.layoutManager = manager
        drinksDetailsList.adapter = drinkAdapter
    }

    private val presenter by lazy { DrinkDetailsPresenter(this, this, activity!!) }
    override fun setLayoutID(): Int = R.layout.dialog_drinks_details
    private var dataMonel = DrinksBean.DataBean.WineTypesBean.WinesBean()
    private var wineType = DrinksBean.DataBean.WineTypesBean()
    override fun isFullScreen(): Boolean = false
    private var num = 0
    fun setData(get: DrinksBean.DataBean.WineTypesBean.WinesBean, wineTypes: DrinksBean.DataBean.WineTypesBean) {
        this.dataMonel = get
        this.wineType = wineTypes
    }

    override fun setLayoutData() {
        drinksDetailsName.text = dataMonel.businessWineName
        drinksDetailsMoney.text = "¥:${dataMonel.businessWinePrice}"
        drinksDetailsNum.text = "${dataMonel.drinksNum}"
        num = dataMonel.drinksNum
        drinksDetailsList
        if (wineType.isDetails==1) {
            presenter.getDrinkDetails(DrinkDetailsBody(dataMonel.businessWineId.toString()))
        }else{
            var list=ArrayList<DrinkDetailsBean.DataBean.WineDetailsInfoBean>()
//            var util=""
//            if (dataMonel.businessWineSpecifications!=null)
//                util=dataMonel.businessWineSpecifications
            list.add(DrinkDetailsBean.DataBean.WineDetailsInfoBean(dataMonel.businessWineUnit,dataMonel.businessWineSpecifications,"1",dataMonel.businessWineName))
            var drinkAdapter = DrinkDetailsAdapter(list)
            var manager = LinearLayoutManager(activity)
            drinksDetailsList.layoutManager = manager
            drinksDetailsList.adapter = drinkAdapter
        }
//        LogUtils.a("当前酒水" + Gson().toJson(dataMonel))
    }

    override fun clickListener() {
        Click.viewClick(drinksDetailsOver).subscribe { dismiss() }
//        Click.viewClick(drinksDetailsSub).subscribe {
//
//        }
        drinksDetailsSub.setOnClickListener {
            if (num == 0) {
                num = 0
            } else {
                num = num - 1
            }
            drinksDetailsNum.text = "${num}"
        }

        drinksDetailsAdd.setOnClickListener {
            num = num + 1
            drinksDetailsNum.text = "${num}"
        }
//        Click.viewClick(drinksDetailsAdd).subscribe {
//
//        }
        Click.viewClick(drinksDetailsOk).subscribe {
            setDrinksDB(dataMonel, "${num}")
        }
    }

    private fun setDrinksDB(item: DrinksBean.DataBean.WineTypesBean.WinesBean, s: String) {

        var drink = DrinkDB()
        drink.mealName = wineType.wineTypeName
        drink.mealId = "${wineType.wineTypeId}"
        drink.drinkNum = s
        drink.drinkID = "${item.businessWineId}"
        drink.drinkMoney = "${item.businessWinePrice}"
        drink.drinkName = item.businessWineName
        if (item.businessWineDetails==null&&item.businessWineSpecifications!=null){
            drink.drinkText = item.businessWineSpecifications+"/${item.businessWineUnit}"
        }else {
            drink.drinkText = item.businessWineDetails
        }
        drink.drinkImage = item.businessWineImg
        setDrinks(drink)
        dismiss()
        drinkdetails.setNewMoney()
    }

    interface DrinkDetails {
        fun setNewMoney()

    }


}