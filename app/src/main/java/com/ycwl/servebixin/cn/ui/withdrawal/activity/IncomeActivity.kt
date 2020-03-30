package com.ycwl.servebixin.cn.ui.withdrawal.activity

import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.IncomeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.IncomePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.IncomeView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_withdrawal.*

class IncomeActivity :BaseActivity(),IncomeView{

    private val presenter by lazy { IncomePresenter(this,this,this) }

    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View

    override fun onSavedInstanceState(bundle: Bundle?) {
        super.onSavedInstanceState(bundle)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = window.decorView
            val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                             or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            decorView.systemUiVisibility = option
            window.navigationBarColor = Color.TRANSPARENT
            window.statusBarColor = Color.TRANSPARENT
        }
    }
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_withdrawal

    override fun setActivityTitle() {

    }

    override fun initActivityData() {
        popView = LayoutInflater.from(mContext).inflate(R.layout.pop_income, null)
        pop = PopupWindowHelper(popView)
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(titleRight).subscribe {
            pop.showAsDropDown(titleRight, 0, 0)
        }
        Click.viewClick(popView.findViewById(R.id.goReport)).subscribe {
            pop.dismiss()
            intentUtils.intentWithdrawRecord()
        }
        Click.viewClick(popView.findViewById(R.id.goBlock)).subscribe {
            pop.dismiss()
            intentUtils.intentWithdrawSet()
        }


    }

    override fun getIncomeRequest(data: IncomeBean) {
        withdrawalMoney.text="${data.data.userBalance.toString()}"
        withdrawalAllMoney.text="累计收益："+"${data.data.cumulativeBalance.toString()}"+"元"
        yesterdayMoney.text="${data.data.yesterdayProfit.toString()}"
        lastWeekMoney.text="${data.data.lastWeekProfit.toString()}"
        monthMoney.text="${data.data.thisMonthProfit.toString()}"
        Click.viewClick(withdrawalWithdraw).subscribe {
            //if (user.getRealname())//

                if (user.getRealname()==1) {
                    if (data.data.payType==-1){
                        ShowDialog.showCustomDialogNoTitle(this,"请先设置您的提现方式","去设置","取消",object :DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> {
                                        dialog.dismiss()
                                        intentUtils.intentWithdrawSet()
                                    }
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        dialog.dismiss()
                                    }
                                }
                            }
                        })
                    }else {
                        intentUtils.intentWithdraw(data.data.payType.toString(), data.data.drawCashNickname, data.data.userBalance.toString())
                    }
                }else if (user.getRealname()==-1){
                    ShowDialog.showCustomDialogs(this,"如需发起提现\n请先完成实名认证!","去认证","取消",object :DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
                                    intentUtils.intentRealnameName()
                                    finish()
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog.dismiss()
                                }
                            }
                        }
                    })
                }else if (user.getRealname()==2){
//                    Toast.Tips("实名认证正在审核中,请耐心等待审核结果...")
                    ShowDialog.showCustomDialogs(this,"实名认证正在审核中","去查看","取消",object :DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
                                    intentUtils.intentRealnameDetails()
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog.dismiss()
                                }
                            }
                        }
                    })
                }else{
                    ShowDialog.showCustomDialogs(this,"如需发起提现\n请先完成实名认证!","去认证","取消",object :DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
//                                    intentUtils.intentRealnameName()
                                    intentUtils.intentRealnameDetails()
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog.dismiss()
                                }
                            }
                        }
                    })
                }

        }

        Click.viewClick(withdrawalIncomeDetails).subscribe {
            intentUtils.intentIncomeRecord()
        }
    }

    override fun getIncomeError(data: Int) {

    }

    override fun onResume() {
        super.onResume()
        presenter.getIncome()
    }

}