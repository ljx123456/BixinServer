package com.ycwl.servebixin.cn.ui.yue.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.DrinkUtils
import com.ycwl.servebixin.cn.db.GreenDaoHelper
import com.ycwl.servebixin.cn.db.ServeUtils
import com.ycwl.servebixin.cn.db.db.MerchantDB
import com.ycwl.servebixin.cn.ui.yue.adapter.YueKTVBoxAdapter
import com.ycwl.servebixin.cn.ui.yue.adapter.YueKTVBoxDetailsAdapter
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.BaoFangBean
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.BaoFangListBean
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.BaoFangTypeBean
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.KTVBoxBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.BaoFangBody
import com.ycwl.servebixin.cn.ui.yue.mvp.body.KTVBoxBody
import com.ycwl.servebixin.cn.ui.yue.mvp.presenter.KTVBoxPresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.presenter.YueKTVBoxPresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.view.KTVBoxView
import com.ycwl.servebixin.cn.ui.yue.mvp.view.YueKTVBoxView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import com.ycwl.servebixin.cn.utils.utils.UrlParse
import kotlinx.android.synthetic.main.activity_yue_ktv_box.*
import kotlinx.android.synthetic.main.layout_title.*

class YueKTVBoxActivity:BaseActivity(),YueKTVBoxView,KTVBoxView {
    override fun getKTVBoxRequest(data: KTVBoxBean) {
        if (data.data!=null&&data.data.myOrderInfo!=null&&data.data.myOrderInfo.size>0){
            ktvImg=data.data.businessInfo.avatar
            ktvName=data.data.businessInfo.businessName
            ktvAddress=data.data.businessInfo.businessAddress
            boxPrice=data.data.businessInfo.servicePrice.toDouble()
            var myorder=data.data.myOrderInfo
            var flag = false
            for (i in myorder.indices){
                if (myorder[i].boxTypeId==boxTypeID){
                    if (myorder[i].boxs!=null&&myorder[i].boxs.size>0) {
                        var box = myorder[i].boxs
                        boxTypeName = myorder[i].boxTypeName
                        for (t in box.indices) {
                            if (box[t].businessBoxId == boxID) {
                                boxName = box[t].businessBoxName
                                flag = true
                                break
                            }
                        }
                    }
                }
                if (flag){
                    break
                }else if (i==myorder.size-1){
                    Toast.Tips("没有该包房请重新扫描")
                    return
                }
            }
//            data.data.myOrderInfo.forEachIndexed { i, myOrderInfoBean ->
//                if (myOrderInfoBean.boxTypeId.toString()==boxTypeID){
//                    boxTypeName=myOrderInfoBean.boxTypeName
//                    myOrderInfoBean.boxs.forEachIndexed { index, boxsBean ->
//                        if (boxsBean.businessBoxId.toString()==boxID){
//                            boxName=boxsBean.businessBoxName
//                            return@forEachIndexed
//                        }else if (index==myOrderInfoBean.boxs.size-1){
//                            Toast.Tips("没有该包房请重新扫描")
//                            return
//                        }
//                    }
//                    return@forEachIndexed
//                }else if (i==data.data.myOrderInfo.size-1){
//                    Toast.Tips("没有该包房请重新扫描")
//                    return
//                }
//            }
            var merchat = GreenDaoHelper.getDaoSessions().merchantDBDao
            if (merchat != null && merchat.loadAll() != null && merchat.loadAll().size > 0) {
                if (!merchat.loadAll().get(0).merchantID.equals(ktvID)){
                    DrinkUtils.deleteALLDrinks()//删除所有酒水
                    ServeUtils.deleteALLServe()//删除所有服务员
                }
            }
            DBUtils.setMerchatDB(MerchantDB(0.toLong(),ktvName,ktvID,ktvImg,boxTypeID,boxTypeName,boxID,boxName,ktvAddress,boxPrice.toString()))
            intentUtils.intentYue()
        }
    }

    private val presenter by lazy { YueKTVBoxPresenter(this,this,this) }
    private val ktvPresenter by lazy { KTVBoxPresenter(this,this,this) }
    private var ktvID=""
    private var ktvImg=""
    private var ktvName=""
    private var ktvAddress=""
    private var boxPrice=0.00
    private var boxTypeID=""
    private var boxTypeName=""
    private var boxName=""
    private var boxID=""

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_yue_ktv_box

    override fun setActivityTitle() {
        titleText.text="添加包房"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRight.setImageResource(R.mipmap.searchpage_box_qr)
    }

    override fun initActivityData() {
        ktvID=intent.getStringExtra("id")
        ktvImg=intent.getStringExtra("img")
        ktvName=intent.getStringExtra("name")
        ktvAddress=intent.getStringExtra("address")
        presenter.getYueKTVBox(BaoFangBody(ktvID))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(titleRight).subscribe {
            intentUtils.intentSao(this,"扫描包房二维码")
        }
        Click.viewClick(btn_yue_ktv_box).subscribe {
            if (boxTypeName.equals("")||boxName.equals("")){
                Toast.Tips("请选择包房")
            }else{
                var merchat = GreenDaoHelper.getDaoSessions().merchantDBDao
                if (merchat != null && merchat.loadAll() != null && merchat.loadAll().size > 0) {
                    if (!merchat.loadAll().get(0).merchantID.equals(ktvID)){
                        DrinkUtils.deleteALLDrinks()//删除所有酒水
                        ServeUtils.deleteALLServe()//删除所有服务员
                    }
                }
                DBUtils.setMerchatDB(MerchantDB(0.toLong(),ktvName,ktvID,ktvImg,boxTypeID,boxTypeName,boxID,boxName,ktvAddress,boxPrice.toString()))
                intentUtils.intentYue()
            }
        }
    }

    override fun getBoxRequest(data: BaoFangBean) {
        var list=ArrayList<BaoFangTypeBean>()
        var map=HashMap<Int,ArrayList<BaoFangListBean>>()
        if (data!=null&&data.data!=null&&data.data.size>0&&data.data[0]!=null){
            data.data.forEach {
                if (it!=null&&it.boxs!=null&&it.boxs.size>0) {
                    list.add(BaoFangTypeBean(it.boxFreePrice, it.boxTypeId, it.boxTypeName, false))
                    var list2 = ArrayList<BaoFangListBean>()
                    it.boxs.forEach {
                        list2.add(BaoFangListBean(it.boxId, it.boxName, it.isIdle, false))
                    }
                    map.put(it.boxTypeId, list2)
                }
            }

            if (list!=null&&list.size>0&&map!=null&&map.size>0) {
                var boxAdapter = YueKTVBoxAdapter(list)
                list[0].isFlag = true
                boxPrice = list[0].boxFreePrice.toDouble()
                boxTypeID = list[0].boxTypeId.toString()
                boxTypeName = list[0].boxTypeName
                boxName = ""
                boxID = ""
                var boxManager = LinearLayoutManager(this)
                boxManager.orientation = LinearLayout.VERTICAL
                recy_yue_ktv_box.layoutManager = boxManager
                recy_yue_ktv_box.adapter = boxAdapter

                var Adapter = YueKTVBoxDetailsAdapter(map[boxAdapter.data[0].boxTypeId]!!)
                var manager = LinearLayoutManager(this)
                manager.orientation = LinearLayout.VERTICAL
                recy_yue_ktv_box_details.layoutManager = manager
                recy_yue_ktv_box_details.adapter = Adapter


                boxAdapter.setOnItemClickListener { adapter, view, position ->
                    boxAdapter.data[position].isFlag = true
                    boxPrice = boxAdapter.data[position].boxFreePrice.toDouble()
                    boxTypeID = boxAdapter.data[position].boxTypeId.toString()
                    boxTypeName = boxAdapter.data[position].boxTypeName
                    boxName = ""
                    boxID = ""
                    for (i in boxAdapter.data.indices) {
                        if (i != position) {
                            boxAdapter.data[i].isFlag = false
                        }
                    }

                    Adapter.data.forEach {
                        it.isFlag = false
                    }
                    Adapter.notifyDataSetChanged()
                    boxAdapter.notifyDataSetChanged()
                    Adapter.setNewData(map[boxAdapter.data[position].boxTypeId]!!)
//                Adapter=YueKTVBoxDetailsAdapter(map[boxAdapter.data[position].boxTypeId]!!)
//                recy_yue_ktv_box_details.adapter=Adapter

                }

                Adapter.setOnItemClickListener { adapter, view, position ->
                    Adapter.data[position].isFlag = true
                    boxName = Adapter.data[position].boxName
                    boxID = Adapter.data[position].boxId.toString()
                    for (i in Adapter.data.indices) {
                        if (i != position) {
                            Adapter.data[i].isFlag = false
                        }
                    }
                    Adapter.notifyDataSetChanged()
                }
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK){
            LogUtils.a("扫描成功" + data!!.extras.getString("codedContent"))
//            http://bixinyule.com?bixinClientType=3&qrcodeType=1&businessId=1&boxTypeId=1&businessBoxId=1
            var map= UrlParse.getUrlParams(data!!.extras.getString("codedContent"))
            if (map.containsKey("bixinClientType")){
                if (map.containsKey("qrcodeType")&&map["qrcodeType"]!=""&&map["qrcodeType"]=="1"&&map["bixinClientType"]=="3"&&map.containsKey("businessId")&&map.containsKey("boxTypeId")&&map.containsKey("businessBoxId")){
                    if(ktvID==map["businessId"]!!) {
                        ktvID = map["businessId"]!!
                        boxTypeID = map["boxTypeId"]!!
                        boxID = map["businessBoxId"]!!
                        ktvPresenter.getKTVBox(KTVBoxBody((ktvID)))
                    }else{
                        Toast.Tips("请扫描选择的商家包房二维码")
                    }
                }else{
                    Toast.Tips("请扫描商家包房二维码")
                }
            }else{
                Toast.Tips("请扫描正确的二维码")
            }
        }
    }
}