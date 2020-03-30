package com.ycwl.servebixin.cn.ui.yue.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.DBUtils
import com.ycwl.servebixin.cn.db.ServeUtils
import com.ycwl.servebixin.cn.db.db.ServePersonnelDB
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.YueServeBean
import com.ycwl.servebixin.cn.ui.yue.mvp.body.YueServeBody
import com.ycwl.servebixin.cn.ui.yue.mvp.presenter.YueServePresenter
import com.ycwl.servebixin.cn.ui.yue.mvp.view.YueServeView
import com.ycwl.servebixin.cn.utils.image.ImageLoad
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_yue_serve.*
import kotlinx.android.synthetic.main.layout_title.*

class YueServeActivity :BaseActivity(),YueServeView{
    override fun getYueServeError() {
        finish()
    }

    private val presenter by lazy { YueServePresenter(this,this,this) }
    private var bixinId=""
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_yue_serve

    override fun setActivityTitle() {
        titleText.text="达人信息"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        var merchantData = DBUtils.getMerchat()
        bixinId=intent.getStringExtra("bixinId")
        presenter.getYueServe(YueServeBody(merchantData.merchantID,bixinId))
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
    }

    override fun getYueServeRequest(data: YueServeBean) {
        if (data!=null&&data.data!=null&&!data.data.nickname.equals("")){
            ImageLoad.setImage(data.data.avatar,yue_serve_img)
            yue_serve_name.text=data.data.nickname
            var sex=""
            if (data.data.sex==1){
                sex="男"
            }else{
                sex="女"
            }
            yue_serve_content.text=sex+" ${data.data.age}岁"+" "+data.data.constellation


            Click.viewClick(yue_serve_add).subscribe {
                if (data.data.state!=null&&data.data.state==1&&data.data.skillPrice!=null){
                    ServeUtils.setServe(ServePersonnelDB(null,data.data.userId.toString(),data.data.nickname,data.data.avatar,data.data.skillPrice.toString(),bixinId,data.data.age.toString(),data.data.sex.toString(),data.data.constellation,data.data.occupationName))
                    intentUtils.intentYue()
                }else{
                    Toast.Tips("该达人尚未开启技能服务")
                }
            }
        }
    }
}