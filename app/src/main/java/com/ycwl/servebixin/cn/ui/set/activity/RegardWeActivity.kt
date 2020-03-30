package com.ycwl.servebixin.cn.ui.set.activity

import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import kotlinx.android.synthetic.main.layout_title.*

class RegardWeActivity:BaseActivity(){
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_regard_we

    override fun setActivityTitle() {
        titleText.text = "关于我们"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
    }

    override fun initActivityData() {
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
//        Click.viewClick(user_agreement).subscribe {
//            intentHtml(1)
//        }
//        Click.viewClick(service_phone).subscribe {
//            ShowDialog.showCustomDialog(this, "提示信息", "是否拨打400-867-7080电话", "拨打电话", "取消", object : DialogInterface.OnClickListener {
//                override fun onClick(dialog: DialogInterface?, which: Int) {
//                    when (which) {
//                        DialogInterface.BUTTON_POSITIVE -> {
//                            CallPhone("4008677080")
//                            dialog!!.dismiss()
//                        }
//                        DialogInterface.BUTTON_NEGATIVE -> {
//                            dialog!!.dismiss()
//                        }
//                    }
//                }
//            })
//        }
    }
}