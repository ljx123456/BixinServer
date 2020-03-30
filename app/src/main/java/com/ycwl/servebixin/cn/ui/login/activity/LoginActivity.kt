package com.ycwl.servebixin.cn.ui.login.activity

import android.support.v4.app.Fragment
import android.view.KeyEvent
import cn.jpush.android.api.JPushInterface
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.AppUtils
import com.pp.wsy.bosom.app.ui.login.mvp.presenter.SplashPresenter
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.GreenDaoHelper
import com.ycwl.servebixin.cn.ui.login.adapter.FragmentAdapter
import com.ycwl.servebixin.cn.ui.login.fragment.CodeLoginFragment
import com.ycwl.servebixin.cn.ui.login.fragment.PwdLoginFragment
import com.ycwl.servebixin.cn.ui.main.dialog.VersionUpdatingDialog
import com.ycwl.servebixin.cn.ui.main.mvp.bean.UpdateBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.UpdateBody
import com.ycwl.servebixin.cn.ui.main.mvp.view.SplashView
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登陆
 */
class LoginActivity : BaseActivity(), SplashView, VersionUpdatingDialog.VersionUpdatingCallBack {

    //更新接口
    override fun getUpdateRequest(data: UpdateBean) {
        updatingdialog.setDialogContent(data, this)
        updatingdialog.show(supportFragmentManager, "")
//        updatingdialog.isCancelable=false
    }
    override fun getUpdateError(code: Int, message: String) {
        if (code!=-1301)
            Toast.Tips(message)
    }

    override fun enterInto() {

    }

    var mFragmentAdapter: FragmentAdapter? = null
    private val updataPresenter by lazy { SplashPresenter(this, this, this) }
    private val updatingdialog = VersionUpdatingDialog()

    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_login

    override fun setActivityTitle() {
    }

    override fun initActivityData() {
//        var user = GreenDaoHelper.getDaoSessions().userDBDao
//        if (user != null) {
//            var data = user.loadAll()
//            if (data != null && data.size >= 1 && data.get(0).token != null) {
//                intentUtils.intentMain()
//                finish()
//            }
//        }
//        JPushInterface.deleteAlias(this,1)
        updataPresenter.getUpdata(UpdateBody(1, AppUtils.getAppVersionCode()))
        var titles = ArrayList<String>()
        titles.add("短信登录")
        titles.add("密码登录")

        var fragments = ArrayList<Fragment>()
        loginTab.addTab(loginTab.newTab().setText(titles[0]),0)
        loginTab.addTab(loginTab.newTab().setText(titles[1]),1)
        fragments.add(CodeLoginFragment())
        fragments.add(PwdLoginFragment())

        mFragmentAdapter = FragmentAdapter(getSupportFragmentManager(), fragments, titles)
        loginPager.adapter = mFragmentAdapter
        loginTab.setupWithViewPager(loginPager)
        loginTab.setTabsFromPagerAdapter(mFragmentAdapter)
//        orderPager.setOffscreenPageLimit(1)
    }

    override fun clickListener() {
//        Click.viewClick(loginJoin).subscribe { intentJoin() }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if(KeyEvent.KEYCODE_BACK==keyCode)
            return false
        return super.onKeyDown(keyCode, event)
    }
}