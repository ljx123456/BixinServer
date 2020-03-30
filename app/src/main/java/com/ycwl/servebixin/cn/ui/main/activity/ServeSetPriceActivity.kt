package com.ycwl.servebixin.cn.ui.main.activity

import android.graphics.Color
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeSetPriceBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.ServeSetPriceBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.ServeSetPricePresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.ServeSetPriceView
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_serve_set_price.*
import kotlinx.android.synthetic.main.layout_title.*

class ServeSetPriceActivity:BaseActivity(),ServeSetPriceView{

    private val presenter by lazy { ServeSetPricePresenter(this,this,this) }
    private var money=0.00
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_serve_set_price

    override fun setActivityTitle() {
        titleText.text="欢唱达人"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRightText.text="确定"
        titleRightText.visibility=View.VISIBLE
    }

    override fun initActivityData() {
        if (intent.getDoubleExtra("price",0.0)>0){
            money=intent.getDoubleExtra("price",0.0)
        }
        var text="服务价格仅能输入整数字，不得包含小数点及其他任何符号；且金额最低不得低于"+money.toString()+"元<font color='#FF6464'>（服务时长五小时）</font>"
        tv_serve_set_price_tips.text=Html.fromHtml(text)
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(titleRightText).subscribe {
            if (edt_serve_set_price.text!=null&&edt_serve_set_price.text.toString()!=""&&edt_serve_set_price.text.toString().toInt()>=money) {
                presenter.getServeSetPrice(ServeSetPriceBody(user.getUserSkillID(), user.getSkillTypeID(), edt_serve_set_price.text.toString()))
            }else{
                Toast.Tips("请输入正确的服务价格")
            }
        }
        edt_serve_set_price.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s!=null&&s.length>0){
                    tv_serve_set_price.visibility=View.VISIBLE
                    if (s.toString().toInt()<money){
                        edt_serve_set_price.setTextColor(Color.parseColor("#FF6464"))
                    }else{
                        edt_serve_set_price.setTextColor(Color.parseColor("#535252"))
                    }
                }else{
                    tv_serve_set_price.visibility=View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    override fun getServeSetPriceRequest(data: ServeSetPriceBean) {
        if (data.data!=null)
            user.setUserSkillID(data.data.userSkillId)
        finish()
    }

}