package com.ycwl.servebixin.cn.ui.set.activity

import cn.jpush.android.api.JPushInterface
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.pp.wsy.bosom.app.ui.login.mvp.presenter.SplashPresenter
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.*
import com.ycwl.servebixin.cn.ui.main.dialog.VersionUpdatingDialog
import com.ycwl.servebixin.cn.ui.main.location.LocationUtils
import com.ycwl.servebixin.cn.ui.main.mvp.bean.UpdateBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.UpdateBody
import com.ycwl.servebixin.cn.ui.main.mvp.view.SplashView
import com.ycwl.servebixin.cn.ui.set.dialog.ClearCacheDialog
import com.ycwl.servebixin.cn.ui.set.dialog.ExitDialog
import com.ycwl.servebixin.cn.ui.set.mvp.bean.LogoutBean
import com.ycwl.servebixin.cn.ui.set.mvp.presenter.SetPresenter
import com.ycwl.servebixin.cn.ui.set.mvp.view.SetView
import com.ycwl.servebixin.cn.ui.set.utils.DataCleanManager.clearAllCache
import com.ycwl.servebixin.cn.ui.set.utils.DataCleanManager.getTotalCacheSize
import com.ycwl.servebixin.cn.utils.intent.intentUtils.intentBlack
import com.ycwl.servebixin.cn.utils.intent.intentUtils.intentExplain
import com.ycwl.servebixin.cn.utils.intent.intentUtils.intentLogin
import com.ycwl.servebixin.cn.utils.intent.intentUtils.intentOpinionFeedBack
import com.ycwl.servebixin.cn.utils.intent.intentUtils.intentRegardWe
import com.ycwl.servebixin.cn.utils.intent.intentUtils.intentSetAccount
import com.ycwl.servebixin.cn.utils.intent.intentUtils.intentSetNotification
import com.ycwl.servebixin.cn.utils.utils.DataCleanManager
import com.ycwl.servebixin.cn.utils.utils.GlideCacheUtil
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_set.*
import kotlinx.android.synthetic.main.layout_title.*

class SetActivity:BaseActivity(), SetView, ClearCacheDialog.ClearCacheDialogFace, SplashView, VersionUpdatingDialog.VersionUpdatingCallBack, ExitDialog.Exit,LocationUtils.Location{
    override fun getLocationSuccess() {

    }

    private val updatingdialog = VersionUpdatingDialog()
    private val presenter by lazy { SetPresenter(this, this, this) }
    private val updataPresenter by lazy { SplashPresenter(this, this, this) }
    private val clearcachedialog = ClearCacheDialog()
    private val exitdialog = ExitDialog(this)

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int = R.layout.activity_set

    override fun setActivityTitle() {
        titleText.text="设置"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        set_clearawaySize.text = GlideCacheUtil.getInstance().getCacheSize(this)
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe {
            finish()
        }

        Click.viewClick(set_account).subscribe {
            intentSetAccount()
        }
        //通知设置
        Click.viewClick(set_noti).subscribe {
            intentSetNotification()
        }
        //黑名单
        Click.viewClick(set_blacklist).subscribe { intentBlack() }
        //版本更新
        Click.viewClick(set_update).subscribe { updataPresenter.getUpdata(UpdateBody(1, AppUtils.getAppVersionCode())) }
        //清楚缓存
        Click.viewClick(set_clearaway).subscribe {
            clearcachedialog.setDataSize(getTotalCacheSize(this), this)
            clearcachedialog.showDialog(this)
        }
        //用户说明
        Click.viewClick(set_explain).subscribe { intentExplain() }
        //关于我们
        Click.viewClick(set_we).subscribe { intentRegardWe() }
        //意见反馈
        Click.viewClick(set_opinion).subscribe { intentOpinionFeedBack() }


        //退出登录
        Click.viewClick(set_over_user).subscribe {
            exitdialog.show(supportFragmentManager, "")

        }
    }

    override fun getLogoutRequest(data: LogoutBean) {
        DBUtils.DelUser()
        DBUtils.delMerchat()
        ServeUtils.deleteALLServe()
        DrinkUtils.deleteALLDrinks()
        PicturesUtils.deleteAllPictures()
        TagsUtils.deleteAllTags()
        DBUtils.DelVideo()
        LocationUtils(this).stopLocation()
//        JPushInterface.deleteAlias(this,1)
        ActivityUtils.finishAllActivities()
        user.setOccupation("")
        user.setOccupationID("")
        user.setUserSkillID("")
        user.setSkillTypeID("")
        intentLogin()
    }

    override fun clearBtnClick() {
//        clearAllCache(this)
        GlideCacheUtil.getInstance().clearImageAllCache(this)//清除图片所有缓存
        DataCleanManager.cleanInternalCache(mContext)//清除本应用内部缓存
        DataCleanManager.cleanFiles(mContext)//清除/data/data/com.xxx.xxx/files下的内容
        DataCleanManager.cleanExternalCache(mContext)//清除外部cache下的内容
//        set_clearawaySize.text = getTotalCacheSize(this)
        set_clearawaySize.text = GlideCacheUtil.getInstance().getCacheSize(this)
    }

    override fun getUpdateRequest(data: UpdateBean) {
        updatingdialog.setDialogContent(data, this)
        updatingdialog.show(supportFragmentManager, "")
//        updatingdialog.isCancelable=false
    }
    override fun getUpdateError(code: Int, message: String) {
        Toast.Tips(message)
    }

    override fun enterInto() {

    }

    override fun OkExit() {
        presenter.getLogout()
    }



}