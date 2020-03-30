package com.ycwl.servebixin.cn.ui.withdrawal.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.base.BaseContext
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawOldPwdBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.WithdrawOldPwdPresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.WithdrawPresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawOldPwdView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawView
import com.ycwl.servebixin.cn.ui.withdrawal.utils.PasswordCode
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_withdraw.*
import kotlinx.android.synthetic.main.layout_title.*
import kotlinx.android.synthetic.main.layout_yue_wine.view.*
import java.math.BigDecimal

class WithdrawActivity :BaseActivity(),WithdrawView,WithdrawOldPwdView{

    override fun getChangePwdError(msg:String) {
        ShowDialog.showCustomDialogNoTitle(this,"密码输入错误，请重试","重试", "取消",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface, which: Int) {
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        dialog.dismiss()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }
        })
    }

    override fun getChangePwdRequest() {
        pop.dismiss()
        presenter.getWithdraw(WithdrawBody(type,edt_withdraw.text.toString(),pwd,"1",""))
    }

    private val presenter by lazy { WithdrawPresenter(this,this,this) }
    private val pwdPresenter by lazy { WithdrawOldPwdPresenter(this,this,this) }
    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View
    private var pwd=""
    private var type=""
    private var money=BigDecimal("0.00")

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_withdraw

    override fun setActivityTitle() {
        titleText.text="提现"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRightText.text="提现记录"
        titleRightText.visibility=View.VISIBLE
    }

    override fun initActivityData() {
        popView = LayoutInflater.from(mContext).inflate(R.layout.pop_password, null)
        pop = PopupWindowHelper(popView)
        btn_withdraw.isEnabled=false
        if (intent!=null){
            type=intent.getStringExtra("type")
            if (intent.getStringExtra("type").equals("1")){
                tv_withdraw_account.text=intent.getStringExtra("name")+"（支付宝）"
            }else if (intent.getStringExtra("type").equals("2")){
                tv_withdraw_account.text=intent.getStringExtra("name")+"（微信）"
            }
            tv_withdraw_money.text="余额：${intent.getStringExtra("money")}"
            money= BigDecimal(intent.getStringExtra("money"))
        }


    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(titleRightText).subscribe {
            intentUtils.intentWithdrawRecord()
        }
        edt_withdraw.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s!=null&&s.toString()!=null&&!s.toString().equals("")){
//                    if (s.toString().toDouble()<=intent.getDoubleExtra("money",0.00)&&s.toString().toDouble()>=1)
                    if (BigDecimal(s.toString()).compareTo(money)!=1&&BigDecimal(s.toString()).compareTo(BigDecimal(1))!=-1){
                        btn_withdraw.isEnabled=true
                        tv_withdraw_tips.visibility=View.GONE
                        tv_withdraw_money.visibility=View.VISIBLE
                        tv_withdraw_all.visibility=View.VISIBLE
                    }else{
                        btn_withdraw.isEnabled=false
//                        if (s.toString().toDouble()>intent.getDoubleExtra("money",0.00))
                        if (BigDecimal(s.toString()).compareTo(money)==1) {
                            tv_withdraw_tips.visibility=View.VISIBLE
                            tv_withdraw_money.visibility=View.GONE
                            tv_withdraw_all.visibility=View.GONE
                        }else{
                            tv_withdraw_tips.visibility=View.GONE
                            tv_withdraw_money.visibility=View.VISIBLE
                            tv_withdraw_all.visibility=View.VISIBLE
                        }
                    }

                }else{
                    btn_withdraw.isEnabled=false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        Click.viewClick(layout_type).subscribe {
            var intent = Intent(BaseContext.getContext(), WithdrawTypeActivity::class.java)
            intent.putExtra("type", "2")
            startActivityForResult(intent,0)
        }

        Click.viewClick(tv_withdraw_all).subscribe {
            edt_withdraw.setText("${money.toString()}")
        }
        Click.viewClick(btn_withdraw).subscribe {
            pop.showAtLocation(getWindow().getDecorView(),Gravity.FILL, 0, 0)
        }
        Click.viewClick(popView.findViewById(R.id.layout_bg)).subscribe { pop.dismiss() }

        popView.findViewById<PasswordCode>(R.id.pc_withdraw_pop).setOnInputListener(object :PasswordCode.OnInputListener{
            override fun onSucess(code: String?) {
                pwd=popView.findViewById<PasswordCode>(R.id.pc_withdraw_pop).passwordCode
                pwdPresenter.getWithdrawOldPwd(WithdrawOldPwdBody(pwd))
                popView.findViewById<PasswordCode>(R.id.pc_withdraw_pop).clean()
            }

            override fun onInput() {

            }

        })
    }

    override fun getWithdrawRequest() {
        Toast.Tips("提现成功")
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK){
            type=data!!.extras.getString("type")
            if (data!!.extras.getString("type").equals("1")){
                tv_withdraw_account.text=data!!.extras.getString("name")+"（支付宝）"
            }else if (data!!.extras.getString("type").equals("2")){
                tv_withdraw_account.text=data!!.extras.getString("name")+"（微信）"
            }
        }
    }

}