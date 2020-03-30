package com.ycwl.servebixin.cn.ui.yue.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.DrinkUtils
import com.ycwl.servebixin.cn.db.GreenDaoHelper
import com.ycwl.servebixin.cn.db.ServeUtils
import com.ycwl.servebixin.cn.db.db.MerchantDB
import com.ycwl.servebixin.cn.ui.yue.adapter.YueKTVAdapter
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.KTVBean
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.KTVBoxBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.KTVBoxBody
import com.ycwl.servebixin.cn.ui.yue.mvp.body.YueKTVBody
import com.ycwl.servebixin.cn.ui.yue.mvp.presenter.KTVBoxPresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.presenter.YueKTVPresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.view.KTVBoxView
import com.ycwl.servebixin.cn.ui.yue.mvp.view.YueKTVView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import com.ycwl.servebixin.cn.utils.utils.UrlParse
import kotlinx.android.synthetic.main.activity_serve_details_set.*
import kotlinx.android.synthetic.main.activity_yue_ktv.*
import kotlinx.android.synthetic.main.layout_title.*

class YueKTVActivity:BaseActivity(),YueKTVView,KTVBoxView{
    override fun getKTVBoxRequest(data: KTVBoxBean) {
        if (data.data!=null&&data.data.myOrderInfo!=null&&data.data.myOrderInfo.size>0){
            ktvImg=data.data.businessInfo.avatar
            ktvName=data.data.businessInfo.businessName
            ktvAddress=data.data.businessInfo.businessAddress
            boxPrice=data.data.businessInfo.servicePrice.toDouble()
            var myorder=data.data.myOrderInfo
            var flag = false
            for (i in myorder.indices){
                if (myorder[i].boxTypeId==boxTypeId){
                    if (myorder[i].boxs!=null&&myorder[i].boxs.size>0) {
                        var box = myorder[i].boxs
                        boxTypeName = myorder[i].boxTypeName
                        for (t in box.indices) {
                            if (box[t].businessBoxId == boxId) {
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
//                if (myOrderInfoBean.boxTypeId.toString()==boxTypeId){
//                    boxTypeName=myOrderInfoBean.boxTypeName
//                    myOrderInfoBean.boxs.forEach {
//                        if (it.businessBoxId.toString()==boxId){
//                            Log.e("测试房间","测试：${i}")
//                            boxName=it.businessBoxName
//                            return@forEach
//                            return@forEachIndexed
//                        }
//                    }
//                }else if (i==data.data.myOrderInfo.size-1){
//                    Log.e("测试${data.data.myOrderInfo.size}","测试：${i}")
//                    Toast.Tips("没有该包房请重新扫描")
//                    return
//                }
//            }
            var merchat = GreenDaoHelper.getDaoSessions().merchantDBDao
            if (merchat != null && merchat.loadAll() != null && merchat.loadAll().size > 0) {
                if (!merchat.loadAll().get(0).merchantID.equals(businesssId)){
                    DrinkUtils.deleteALLDrinks()//删除所有酒水
                    ServeUtils.deleteALLServe()//删除所有服务员
                }
            }
            DBUtils.setMerchatDB(MerchantDB(0.toLong(),ktvName,businesssId,ktvImg,boxTypeId,boxTypeName,boxId,boxName,ktvAddress,boxPrice.toString()))
            intentUtils.intentYue()
        }
    }

    private val presenter by lazy { YueKTVPresenter(this,this,this) }
    private val ktvPresenter by lazy { KTVBoxPresenter(this,this,this) }
    private var businesssId=""
    private var boxTypeId=""
    private var boxId=""
    private var ktvImg=""
    private var ktvName=""
    private var ktvAddress=""
    private var boxPrice=0.00
    private var boxTypeName=""
    private var boxName=""

    private var Adapter:YueKTVAdapter?=null
    private var pageIndex=1
    private var pageSize="10"
    private var ids=ArrayList<String>()


    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_yue_ktv

    override fun setActivityTitle() {
        titleText.text="添加包房"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRight.setImageResource(R.mipmap.searchpage_box_qr)
    }

    override fun initActivityData() {
        presenter.getYueKTV(YueKTVBody(pageIndex.toString(),pageSize))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(titleRight).subscribe {
            intentUtils.intentSao(this,"扫描包房二维码")
        }
        swip_yue_ktv.setOnRefreshListener {
            pageIndex = 1
            presenter.getYueKTV(YueKTVBody(pageIndex.toString(),pageSize))
        }
    }

    override fun getKTVRequest(data: KTVBean) {
        swip_yue_ktv.isRefreshing = false
        if (data!=null&&data.data.size>0){
            if (Adapter==null){
                Adapter=YueKTVAdapter(data.data)
                var manager=LinearLayoutManager(this)
                manager.orientation=LinearLayout.VERTICAL
                recy_yue_ktv.layoutManager=manager
                recy_yue_ktv.adapter=Adapter
                ids.clear()
                data.data.forEach {
                    ids.add(it.businessId)
                }
            }else{
                if (pageIndex==1){
                    Adapter!!.setNewData(data.data)
                    ids.clear()
                    data.data.forEach {
                        ids.add(it.businessId)
                    }
                }else{
                    Adapter!!.addData(data.data)
                    data.data.forEach {
                        ids.add(it.businessId)
                    }
                }
            }

            Adapter!!.setOnItemClickListener { adapter, view, position ->
                intentUtils.intentYueBaoFang(Adapter!!.data[position].fieldId.toString(),Adapter!!.data[position].businessImg,Adapter!!.data[position].businessName,Adapter!!.data[position].businessAddress)
            }

            Adapter!!.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener{
                override fun onLoadMoreRequested() {
                    pageIndex=pageIndex+1
                    presenter.getYueKTV(YueKTVBody(pageIndex.toString(),pageSize))
                }
            },recy_yue_ktv)
            if (Adapter != null) {
                Adapter!!.disableLoadMoreIfNotFullPage()
                Adapter!!.setPreLoadNumber(2)
            }
            if (data.data.size<pageSize.toInt()){
                Adapter!!.loadMoreEnd()
            }
        }else{
            Adapter!!.loadMoreEnd()
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
                    businesssId=map["businessId"]!!
                    boxTypeId=map["boxTypeId"]!!
                    boxId=map["businessBoxId"]!!
                    if (ids.contains(businesssId)) {
                        ktvPresenter.getKTVBox(KTVBoxBody((businesssId)))
                    }else{
                        Toast.Tips("请扫描绑定的商家包房二维码")
                    }
                }else{
                    Toast.Tips("请扫描包房二维码")
                }
            }else{
                Toast.Tips("请扫描正确的二维码")
            }
        }
    }

}