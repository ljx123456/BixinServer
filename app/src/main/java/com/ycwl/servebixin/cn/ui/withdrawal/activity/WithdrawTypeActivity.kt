package com.ycwl.servebixin.cn.ui.withdrawal.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import cn.yoyo.com.utils.utils.Click
import com.alipay.sdk.app.AuthTask
import com.blankj.utilcode.util.LogUtils
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.main.pop.PopupWindowHelper
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.AuthInfoBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.BindWithdrawTypeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.WithdrawTypeBean
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.BindAlipayTypeBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.UnbindTypeBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.WithdrawOldPwdBody
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.WithdrawOldPwdPresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.WithdrawTypePresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.presenter.WithdrawWxPresenter
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawOldPwdView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawTypeView
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.view.WithdrawWxView
import com.ycwl.servebixin.cn.ui.withdrawal.utils.AuthResult
import com.ycwl.servebixin.cn.ui.withdrawal.utils.PasswordCode
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_withdraw_type.*
import kotlinx.android.synthetic.main.layout_title.*

class WithdrawTypeActivity :BaseActivity(),WithdrawTypeView,WithdrawWxView, WithdrawOldPwdView {
    override fun getChangePwdRequest() {
        popPwd.dismiss()
        presenter.getUnbindType(UnbindTypeBody(pwd,withdrawType))
    }

    override fun getChangePwdError(msg: String) {
        popPwd.dismiss()
        ShowDialog.showCustomDialogNoTitle(this,"密码输入错误，请重试","重试", "取消",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface, which: Int) {
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        popPwd.showAtLocation(getWindow().getDecorView(), Gravity.FILL, 0, 0)
                        dialog.dismiss()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }
        })
    }

    override fun getUnbindType() {
        presenter.getWithdrawType()
        Toast.Tips("已成功解除绑定")
    }

    override fun getBindWXRequest(data: BindWithdrawTypeBean) {
        user.setWxCode("")
        presenter.getWithdrawType()
    }

    override fun getBindAlipay(data: BindWithdrawTypeBean) {
        alipayCode=""
        presenter.getWithdrawType()
    }

    override fun getAlipayInfo(data: AuthInfoBean) {
        alipayAuth(data.data.authInfo)
    }

    private val presenter by lazy { WithdrawTypePresenter(this,this,this) }
    private val wxPresenter by lazy { WithdrawWxPresenter(this,this,this) }
    private val pwdPresenter by lazy { WithdrawOldPwdPresenter(this,this,this) }
    private lateinit var pop: PopupWindowHelper
    private lateinit var popView: View
    private val SDK_AUTH_FLAG = 2
    private var alipayCode=""
    private lateinit var popDel: PopupWindowHelper
    private lateinit var popDelView: View

    private lateinit var popPwd: PopupWindowHelper
    private lateinit var popPwdView: View

    private var pwd=""
    private var withdrawType=0
    private var wxName=""
    private var aliName=""


    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int =R.layout.activity_withdraw_type

    override fun setActivityTitle() {
        titleText.text="提现方式"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRight.setImageResource(R.mipmap.add_blue)
    }

    override fun initActivityData() {
        user.setWxCode("")
        alipayCode=""
        presenter.getWithdrawType()
        popView = LayoutInflater.from(mContext).inflate(R.layout.pop_withdraw_type, null)
        pop = PopupWindowHelper(popView)
        popDelView = LayoutInflater.from(mContext).inflate(R.layout.pop_del_withdraw_type, null)
        popDel = PopupWindowHelper(popDelView)
        popPwdView = LayoutInflater.from(mContext).inflate(R.layout.pop_password, null)
        popPwd = PopupWindowHelper(popPwdView)
        popPwdView.findViewById<TextView>(R.id.pop_pwd_title).text="请输入提现密码以解除绑定"
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(layout_withdraw_type_none).subscribe {
            pop.showFromBottomMatch(layout_withdraw_type_none)
        }
        Click.viewClick(titleRight).subscribe {
            pop.showFromBottomMatch(titleRight)
        }
        Click.viewClick(popView.findViewById(R.id.pop_withdraw_bg)).subscribe{
            pop.dismiss()
        }
        Click.viewClick(popView.findViewById(R.id.pop_type_wx)).subscribe {
            Toast.Tips("暂不支持微信")
//            if (layout_withdraw_type_wechat.visibility==View.VISIBLE){
//                Toast.Tips("已绑定微信，请重新选择")
//            }else {
//                pop.dismiss()
//                val APP_ID = "wx962d0b8ab1bac6ef"
//                var api: IWXAPI = WXAPIFactory.createWXAPI(this, APP_ID, true)
//                if (!api.isWXAppInstalled) {
//                    ShowDialog.showCustomDialog(this, "未检测到微信 请安装后重新尝试", "确定", object : DialogInterface.OnClickListener {
//                        override fun onClick(dialog: DialogInterface, which: Int) {
//                            dialog.dismiss()
//                        }
//                    })
//                } else {
//                    val req: SendAuth.Req = SendAuth.Req()
//                    req.scope = "snsapi_userinfo"
//                    req.state = "wechat_sdk_demo_test"
//                    api.sendReq(req)
//                }
//            }

        }
        Click.viewClick(popView.findViewById(R.id.pop_type_alipay)).subscribe {
            if (layout_withdraw_type_alipay.visibility==View.VISIBLE){
                Toast.Tips("已绑定支付宝，请重新选择")
            }else {
                pop.dismiss()
                presenter.getAuthInfo()
            }

        }
        Click.viewClick(popView.findViewById(R.id.pop_type_cancel)).subscribe {
            pop.dismiss()
        }

        Click.viewClick(popDelView.findViewById(R.id.pop_del_withdraw_bg)).subscribe{
            popDel.dismiss()
        }
        Click.viewClick(popDelView.findViewById(R.id.pop_del_type_cancel)).subscribe {
            popDel.dismiss()
        }
        Click.viewClick(popDelView.findViewById(R.id.pop_type_del)).subscribe {
            popDel.dismiss()
            popPwd.showAtLocation(getWindow().getDecorView(), Gravity.FILL, 0, 0)
        }

        Click.viewClick(layout_withdraw_type_wechat).subscribe {
            if (intent.getStringExtra("type")!=null&&intent.getStringExtra("type")=="1"){//删除提现方式
                withdrawType=2
                popDel.showFromBottomMatch(layout_withdraw_type_wechat)
            }else if (intent.getStringExtra("type")!=null&&intent.getStringExtra("type")=="2"){//选择提现方式
                var intent = intent
                intent.putExtra("type","2" )
                intent.putExtra("name",wxName )
//            intent.putExtra("codedBitmap", barcode);
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        Click.viewClick(layout_withdraw_type_alipay).subscribe {
            if (intent.getStringExtra("type")!=null&&intent.getStringExtra("type")=="1"){//删除提现方式
                withdrawType=1
                popDel.showFromBottomMatch(layout_withdraw_type_alipay)
            }else if (intent.getStringExtra("type")!=null&&intent.getStringExtra("type")=="2"){//选择提现方式
                var intent = intent
                intent.putExtra("type","1" )
                intent.putExtra("name",aliName )
//            intent.putExtra("codedBitmap", barcode);
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        popPwdView.findViewById<PasswordCode>(R.id.pc_withdraw_pop).setOnInputListener(object : PasswordCode.OnInputListener{
            override fun onSucess(code: String?) {
                pwd=popPwdView.findViewById<PasswordCode>(R.id.pc_withdraw_pop).passwordCode
                pwdPresenter.getWithdrawOldPwd(WithdrawOldPwdBody(pwd))
                popPwdView.findViewById<PasswordCode>(R.id.pc_withdraw_pop).clean()
            }

            override fun onInput() {

            }

        })
        Click.viewClick(popPwdView.findViewById(R.id.layout_bg)).subscribe { popPwd.dismiss() }
    }

    override fun getWithdrawTypeRequest(data: WithdrawTypeBean) {

        if (data!=null&&data.data!=null){
            if (data.data.wechatNickname.equals("")&&data.data.aliNickname.equals("")){
                layout_withdraw_type_none.visibility=View.VISIBLE
                titleRight.visibility=View.GONE
                layout_withdraw_type.visibility=View.GONE
            }else{
                layout_withdraw_type_none.visibility=View.GONE
                titleRight.visibility=View.VISIBLE
                layout_withdraw_type.visibility=View.VISIBLE
            }
            if (!data.data.wechatNickname.equals("")){
                layout_withdraw_type_wechat.visibility=View.VISIBLE
                tv_withdraw_type_wechat.text="微信昵称："+data.data.wechatNickname
                wxName=data.data.wechatNickname
            }else{
                layout_withdraw_type_wechat.visibility=View.GONE
            }

            if (!data.data.aliNickname.equals("")){
                layout_withdraw_type_alipay.visibility=View.VISIBLE
                tv_withdraw_type_alipay.text="支付宝昵称："+data.data.aliNickname
                aliName=data.data.aliNickname
            }else{
                layout_withdraw_type_alipay.visibility=View.GONE
            }

            if (layout_withdraw_type_alipay.visibility==View.VISIBLE&&layout_withdraw_type_wechat.visibility==View.VISIBLE){
                titleRight.visibility=View.GONE
            }


        }else{
            layout_withdraw_type_none.visibility=View.VISIBLE
            titleRight.visibility=View.GONE
            layout_withdraw_type.visibility=View.GONE
        }
    }

    override fun getWithdrawTypeError() {
        layout_withdraw_type_none.visibility=View.VISIBLE
        layout_withdraw_type.visibility=View.GONE
    }

    override fun onResume() {
        super.onResume()
//        if (alipayCode==""&&user.getWxCode()==""){
//            presenter.getWithdrawType()
//        }else{
            if (user.getWxCode()!=""){
                wxPresenter.getBindWx(BindAlipayTypeBody(user.getWxCode()))
                user.setWxCode("")
            }
//        }
    }

    fun alipayAuth(authInfo:String){
//        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
//                || TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE)
//                || TextUtils.isEmpty(TARGET_ID)) {
//            showAlert(this, getString(R.string.error_auth_missing_partner_appid_rsa_private_target_id))
//            return
//        }

        /*
//		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
//		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
//		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
//		 *
//		 * authInfo 的获取必须来自服务端；
//		 */
//        val rsa2 = RSA2_PRIVATE.length > 0
//        val authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2)
//        val info = OrderInfoUtil2_0.buildOrderParam(authInfoMap)
//
//        val privateKey = if (rsa2) RSA2_PRIVATE else RSA_PRIVATE
//        val sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2)
//        val authInfo = info + "&" + sign
        val authRunnable = Runnable {
            // 构造AuthTask 对象
            val authTask = AuthTask(this)
            // 调用授权接口，获取授权结果
            val result = authTask.authV2(authInfo, true)

            val msg = Message()
            msg.what = SDK_AUTH_FLAG
            msg.obj = result
            mHandler.sendMessage(msg)
        }

        // 必须异步调用
        val authThread = Thread(authRunnable)
        authThread.start()
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                SDK_AUTH_FLAG -> {
                    val authResult = AuthResult(msg.obj as Map<String, String>, true)
                    val resultStatus = authResult.getResultStatus()

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        LogUtils.a("alipay_open_id",authResult.alipayOpenId)
                        LogUtils.a("alipay_user_id",authResult.alipayUserId)
                        LogUtils.a("code",authResult.authCode)
                        alipayCode=authResult.authCode
                        LogUtils.a("result",authResult.result)
                        presenter.getBindAlipay(BindAlipayTypeBody(authResult.authCode))
                        // 传入，则支付账户为该授权账户
//                        showAlert(this@PayDemoActivity, getString(R.string.auth_success) + authResult)
                    } else {
                        // 其他状态值则为授权失败
//                        showAlert(this@PayDemoActivity, getString(R.string.auth_failed) + authResult)
                    }
                }
                else -> {
                }
            }
        }
    }


}