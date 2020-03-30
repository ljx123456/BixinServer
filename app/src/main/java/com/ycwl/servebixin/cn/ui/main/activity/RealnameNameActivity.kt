package com.ycwl.servebixin.cn.ui.main.activity

import android.text.Editable
import android.text.TextWatcher
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_realname_name.*
import kotlinx.android.synthetic.main.layout_title.*
import java.util.regex.Pattern

class RealnameNameActivity:BaseActivity(){

    //^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$
    private var str="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]\$"

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int =R.layout.activity_realname_name

    override fun setActivityTitle() {
        titleText.text="实名认证"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {

    }

    override fun clickListener() {
        edit_name.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s!=null&&s.toString().length>0&&edit_card.text!=null&&edit_card.text.length==18){
                    btn_next.isEnabled=true
                }else{
                    btn_next.isEnabled=false
                }
//                btn_next.isEnabled=s!=null&&s.toString().length>0&&edit_card.text!=null&&edit_card.text.length==18
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        edit_card.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s!=null&&s.toString().length==18&&edit_name.text!=null&&edit_name.text.length>0){
                    btn_next.isEnabled=true
                }else{
                    btn_next.isEnabled=false
                }
//                btn_next.isEnabled=s!=null&&s.toString().length>0&&edit_card.text!=null&&edit_card.text.length==18
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        Click.viewClick(titleLeft).subscribe {
            finish()
        }

        Click.viewClick(btn_next).subscribe {
            if (Pattern.matches(str,edit_card.text.toString())) {
                intentUtils.intentRealnameUpload(edit_name.text.toString(), edit_card.text.toString())
                finish()
            }else{
                Toast.Tips("请输入正确的身份证号码")
            }
        }

    }

}