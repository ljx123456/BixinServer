package com.ycwl.servebixin.cn.utils.intent

import android.Manifest
import android.app.Activity
import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ycwl.servebixin.cn.base.BaseContext.getContext
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.ui.broker.activity.*
import com.ycwl.servebixin.cn.ui.image.ImageActivity
import com.ycwl.servebixin.cn.ui.image.ImageBrowseInfo
import com.ycwl.servebixin.cn.ui.image.ImageInfo
import com.ycwl.servebixin.cn.ui.login.activity.*
import com.ycwl.servebixin.cn.ui.main.activity.*
import com.ycwl.servebixin.cn.ui.main.fragment.PersonFragment
import com.ycwl.servebixin.cn.ui.message.activity.ActivityHtmlActivity
import com.ycwl.servebixin.cn.ui.message.activity.BannerActivity
import com.ycwl.servebixin.cn.ui.message.activity.BindNotiActivity
import com.ycwl.servebixin.cn.ui.message.activity.FeedBackDetailsActivity
import com.ycwl.servebixin.cn.ui.order.activity.GrabOrderDetailsActivity
import com.ycwl.servebixin.cn.ui.order.activity.GrabOrderDrinkActivity
import com.ycwl.servebixin.cn.ui.order.activity.OrderFormDetailsActivity
import com.ycwl.servebixin.cn.ui.order.activity.OrderFormDrinkActivity
import com.ycwl.servebixin.cn.ui.set.activity.*
import com.ycwl.servebixin.cn.ui.withdrawal.activity.*
import com.ycwl.servebixin.cn.ui.yue.activity.*
import com.ycwl.servebixin.cn.utils.utils.Toast
import com.ycwl.servebixin.cn.utils.zxing.android.CaptureActivity

object intentUtils{
    /**
     * 跳转到验证码输入界面
     */
    fun intentLoginCode(phone: String) {
//        setType("1")//设置从订单还是主流程添加酒水和服务人员1为主流程0为订单
        var intent = Intent(getContext(), LoginCodeActivity::class.java)
        intent.putExtra("phone", phone)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到人脸识别界面
     */
    fun intentFaceRecognition(){
        ActivityUtils.startActivity(FaceRecogintionActivity::class.java)
    }

    /**
     * 跳转到设置密码界面
     */
    fun intentSetPassWord(){
        ActivityUtils.startActivity(SetPasswordActivity::class.java)
    }

    /**
     * 跳转到完善资料界面
     */
    fun intentRegisterData(){
        ActivityUtils.startActivity(RegisterDataActivity::class.java)
    }

    /**
     * 跳转到使用协议界面
     */
    fun intentAgreement(){
        ActivityUtils.startActivity(RegisterAgreementActivity::class.java)
    }

    /**
     * 跳转到隐私协议界面
     */
    fun intentPrivacy(){
        ActivityUtils.startActivity(PrivacyAgreementActivity::class.java)
    }

    /**
     * 跳转到扫一扫
     */
    fun intentSao(activity: Activity,title : String) {
        val rxPermissions = RxPermissions(activity)
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA).subscribe { aBoolean ->
            if (aBoolean!!) {
                var intent = Intent(getContext(), CaptureActivity::class.java)
                intent.putExtra("title", title)
                activity.startActivityForResult(intent,0)
            }
            else {
                Toast.Tips("请打开相机，内存读取权限")
            }
        }

    }

    /**
     * 跳转到主界面界面
     */
    fun intentMain(){
        ActivityUtils.startActivity(MainActivity::class.java)
    }

    /**
     * 跳转到找回密码输入手机号界面
     */
    fun intentResetPwdPhone() {
        ActivityUtils.startActivity(ResetPasswordPhoneActivity::class.java)
    }

    /**
     * 跳转到找回密码验证码输入界面
     */
    fun intentResetPwdCode(phone: String) {
//        setType("1")//设置从订单还是主流程添加酒水和服务人员1为主流程0为订单
        var intent = Intent(getContext(), ResetPasswordCodeActivity::class.java)
        intent.putExtra("phone", phone)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到找回密码输入密码界面
     */
    fun intentResetPwd(phone: String) {
//        setType("1")//设置从订单还是主流程添加酒水和服务人员1为主流程0为订单
        var intent = Intent(getContext(), ResetPasswordActivity::class.java)
        intent.putExtra("phone", phone)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到登陆界面
     */
    fun intentLogin(){
        ActivityUtils.startActivity(LoginActivity::class.java)
    }

    /**
     * 跳转到完善资料卡界面
     */
    fun intentComplete(){
        ActivityUtils.startActivity(CompleteDataActivity::class.java)
    }


    /**
     * 跳转到完善资料界面
     */
    fun intentCompleteData(){
        ActivityUtils.startActivity(CompleteActivity::class.java)
    }

    /**
     * 跳转到完善资料详情界面
     */
    fun intentCompleteDataDetails(){
        ActivityUtils.startActivity(CompleteDataDetailsActivity::class.java)
    }

    /**
     * 跳转到实名认证第一步界面
     */
    fun intentRealnameName(){
        ActivityUtils.startActivity(RealnameNameActivity::class.java)
    }

    /**
     * 跳转到实名认证拍照界面
     */
    fun intentRealnameUpload(name:String,card:String){
        var intent = Intent(getContext(), RealnameCardActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("card", card)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到实名认证详情界面
     */
    fun intentRealnameDetails(){
        ActivityUtils.startActivity(RealnameDetailsActivity::class.java)
    }

    /**
     * 跳转到实名认证详情界面
     */
    fun intentRealnameDetails(i:String){
//        ActivityUtils.startActivity(RealnameDetailsActivity::class.java)
        var intent = Intent(getContext(), RealnameDetailsActivity::class.java)
        intent.putExtra("i", i)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到修改资料界面
     */
    fun intentEditUser(){
        ActivityUtils.startActivity(EditUserActivity::class.java)
    }

    /**
     * 跳转到完善资料界面
     */
    fun intentEditUser(i:String){
//        ActivityUtils.startActivity(EditUserActivity::class.java)
        var intent = Intent(getContext(), EditUserActivity::class.java)
        intent.putExtra("i", i)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 图片详情-查看自己的
     */
    fun intentImage(delete: Boolean, list: ArrayList<ImageInfo>, index: Int) {
        val imageList = ArrayList<ImageBrowseInfo>()
        list.forEach { if (!it.isAddButton) imageList.add(ImageBrowseInfo(it.imageUrl!!, false, it.imageId)) }
        val intent = Intent(getContext(), ImageActivity::class.java)
        intent.putExtra("images", imageList)
        intent.putExtra("delete", delete)
        intent.putExtra("index", index)
        ActivityUtils.startActivity(intent)
    }

    fun intentVideo(video: String) {
        var intent = Intent(getContext(), VideoActivity::class.java)
        intent.putExtra("video", video)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到选择职业界面
     */
    fun intentOccupation(){
        ActivityUtils.startActivity(OccupationActivity::class.java)
    }

    /**
     * 跳转到选择标签界面
     */
    fun intentTags(){
        ActivityUtils.startActivity(TagsActivity::class.java)
    }

    /**
     * 跳转到服务设置界面
     */
    fun intentServeSet(){
        ActivityUtils.startActivity(ServeSetActivity::class.java)
    }

    /**
     * 跳转到服务详情界面
     */
    fun intentServeDetailsSet(){
        ActivityUtils.startActivity(ServeDetailsSetActivity::class.java)
    }

    /**
     * 跳转到设置服务价格界面
     */
    fun intentServeSetPrice(price: Double) {
        var intent = Intent(getContext(), ServeSetPriceActivity::class.java)
        intent.putExtra("price", price)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到设置达人经纪人界面
     */
    fun intentServeSetLeader(){
        ActivityUtils.startActivity(ServeSetLeaderActivity::class.java)
    }

    /**
     * 跳转到我的达人经纪人界面
     */
    fun intentServeMyLeader(avatar:String,name:String,sex:Int,age:String,constellation:String){
        var intent = Intent(getContext(), ServeMyLeaderActivity::class.java)
        intent.putExtra("avatar", avatar)
        intent.putExtra("name", name)
        intent.putExtra("sex", sex)
        intent.putExtra("age", age)
        intent.putExtra("constellation", constellation)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到设置KTV界面
     */
    fun intentServeSetKTV(){
        ActivityUtils.startActivity(ServeSetKTVActivity::class.java)
    }

    /**
     * 跳转到设置提现密码输入验证码界面
     */
    fun intentSetWithdrawPwdCode(){
        ActivityUtils.startActivity(SetWithdrawPwdCodeActivity::class.java)
    }

    /**
     * 跳转到设置提现密码输入密码界面
     */
    fun intentSetWithdrawPwd(){
        ActivityUtils.startActivity(SetWithdrawPwdActivity::class.java)
    }

    /**
     * 跳转到设置提现密码输入密码界面
     */
    fun intentSetWithdrawPwd(tips:String){
        var intent = Intent(getContext(), SetWithdrawPwdActivity::class.java)
        intent.putExtra("tips", tips)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到设置提现密码确认密码界面
     */
    fun intentSetWithdrawPwdAgain(pwd:String){
        var intent = Intent(getContext(), SetWithdrawPwdAgainActivity::class.java)
        intent.putExtra("pwd", pwd)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到收益界面
     */
    fun intentIncome(){
        ActivityUtils.startActivity(IncomeActivity::class.java)
    }

    /**
     * 跳转到提现记录界面
     */
    fun intentWithdrawRecord(){
        ActivityUtils.startActivity(WithdrawRecordActivity::class.java)
    }

    /**
     * 跳转到提现记录详情界面
     */
    fun intentWithdrawRecordDetails(id:Int){
        var intent = Intent(getContext(), WithdrawRecordDetailsActivity::class.java)
        intent.putExtra("id", id)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到提现设置界面
     */
    fun intentWithdrawSet(){
        ActivityUtils.startActivity(WithdrawSetActivity::class.java)
    }

    /**
     * 跳转到提现方式界面
     */
    fun intentWithdrawType(type:String){
        var intent = Intent(getContext(), WithdrawTypeActivity::class.java)
        intent.putExtra("type", type)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到提现密码验证界面
     */
    fun intentWithdrawOldPwd(){
        ActivityUtils.startActivity(WithdrawOldPwdActivity::class.java)
    }

    /**
     * 跳转到新提现密码界面
     */
    fun intentWithdrawSetPwd(){
        ActivityUtils.startActivity(WithdrawSetPwdActivity::class.java)
    }

    /**
     * 跳转到新提现密码界面
     */
    fun intentWithdrawSetPwd(tips: String){
        var intent = Intent(getContext(), WithdrawSetPwdActivity::class.java)
        intent.putExtra("tips", tips)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到修改提现密码确认密码界面
     */
    fun intentWithdrawChangePwd(pwd:String){
        var intent = Intent(getContext(), WithdrawChangePwdActivity::class.java)
        intent.putExtra("pwd", pwd)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到重置提现密码验证码界面
     */
    fun intentWithdrawResetPwdCode(){
        ActivityUtils.startActivity(WithdrawResetPwdCodeActivity::class.java)
    }

    /**
     * 跳转到重置提现密码界面
     */
    fun intentWithdrawResetPwd(){
        ActivityUtils.startActivity(WithdrawResetPwdActivity::class.java)
    }

    /**
     * 跳转到重置提现密码界面
     */
    fun intentWithdrawResetPwd(tips: String){
        var intent = Intent(getContext(), WithdrawResetPwdActivity::class.java)
        intent.putExtra("tips", tips)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到修改提现密码确认密码界面
     */
    fun intentWithdrawResetPwdAgain(pwd:String){
        var intent = Intent(getContext(), WithdrawResetPwdAgainActivity::class.java)
        intent.putExtra("pwd", pwd)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到提现界面
     */
    fun intentWithdraw(type:String,name:String,money:String){
        var intent = Intent(getContext(), WithdrawActivity::class.java)
        intent.putExtra("type", type)
        intent.putExtra("name", name)
        intent.putExtra("money", money)
        ActivityUtils.startActivity(intent)
    }


    /**
     * 跳转到收益明细界面
     */
    fun intentIncomeRecord(){
        ActivityUtils.startActivity(IncomeRecordActivity::class.java)
    }

    /**
     * 跳转到收益明细界面
     */
    fun intentIncomeRecordDetails(id:Int){
        var intent = Intent(getContext(), IncomeRecordDetailsActivity::class.java)
        intent.putExtra("id", id)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到申请经纪人界面
     */
    fun intentApplyBroker(){
        ActivityUtils.startActivity(ApplyBrokerActivity::class.java)
    }

    /**
     * 跳转到申请经纪人问题界面
     */
    fun intentApplyBrokerQuestion(){
        ActivityUtils.startActivity(ApplyBrokerQuestionActivity::class.java)
    }


    /**
     * 跳转到申请经纪人上传照片界面
     */
    fun intentApplyBrokerUpload(){
        ActivityUtils.startActivity(ApplyBrokerUploadActivity::class.java)
    }

    /**
     * 跳转到申请经纪人记录界面
     */
    fun intentApplyBrokerRecord(){
        ActivityUtils.startActivity(ApplyBrokerRecordActivity::class.java)
    }

    /**
     * 跳转到申请经纪人记录详情界面
     */
    fun intentApplyBrokerRecordDetails(id:Int){
        var intent = Intent(getContext(), ApplyBrokerRecordDetailsActivity::class.java)
        intent.putExtra("id", id)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到二维码界面
     */
    fun intentBiXinIDCode(){
        ActivityUtils.startActivity(BiXinIDActivity::class.java)
    }

    /**
     * 跳转到经纪人界面
     */
    fun intentBroker(){
        ActivityUtils.startActivity(BrokerActivity::class.java)
    }

    /**
     * 跳转到经纪人约玩达人界面
     */
    fun intentBrokerServe(){
        ActivityUtils.startActivity(BrokerServeActivity::class.java)
    }

    /**
     * 跳转到经纪人约玩场地界面
     */
    fun intentBrokerKTV(){
        ActivityUtils.startActivity(BrokerKTVActivity::class.java)
    }

    /**
     * 跳转到添加经纪人约玩场地界面
     */
    fun intentBrokerSearchKTV(){
        ActivityUtils.startActivity(BrokerSearchKTVActivity::class.java)
    }

    /**
     * 跳转到生成订单界面
     */
    fun intentYue(){
        ActivityUtils.startActivity(YueActivity::class.java)
    }

    /**
     * 跳转到生成订单添加ktv列表界面
     */
    fun intentYueKTV(){
        ActivityUtils.startActivity(YueKTVActivity::class.java)
    }

    /**
     * 跳转到生成订单添加包房列表界面
     */
    fun intentYueBaoFang(id:String,img:String,name: String,address:String){
        var intent = Intent(getContext(), YueKTVBoxActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("img", img)
        intent.putExtra("name", name)
        intent.putExtra("address", address)
        ActivityUtils.startActivity(intent)
//        ActivityUtils.startActivity(YueKTVBoxActivity::class.java)
    }

    /**
     * 跳转到生成订单添加达人界面
     */
    fun intentYueServe(bixinId:String){
        var intent = Intent(getContext(), YueServeActivity::class.java)
        intent.putExtra("bixinId", bixinId)
        ActivityUtils.startActivity(intent)
//        ActivityUtils.startActivity(YueKTVBoxActivity::class.java)
    }

    /**
     * 跳转到生成订单添加酒水列表界面
     */
    fun intentYueDrinks(businessId:String){
        var intent = Intent(getContext(), YueDrinkActivity::class.java)
        intent.putExtra("businessId", businessId)
        ActivityUtils.startActivity(intent)
//        ActivityUtils.startActivity(YueDrinkActivity::class.java)
    }

    /**
     * 跳转到订单详情页
     */
    fun intentOrderFormDetails(orderNum:String){
        var intent = Intent(getContext(), OrderFormDetailsActivity::class.java)
        intent.putExtra("orderNum", orderNum)
        ActivityUtils.startActivity(intent)
//        ActivityUtils.startActivity(YueDrinkActivity::class.java)
    }
    /**
     * 跳转到订单点单页
     */
    fun intentOrderFormDrinks(orderNo:String){
        var intent = Intent(getContext(), OrderFormDrinkActivity::class.java)
        intent.putExtra("orderNo", orderNo)
        ActivityUtils.startActivity(intent)
//        ActivityUtils.startActivity(YueDrinkActivity::class.java)
    }

    /**
     * 跳转到抢单点单页
     */
    fun intentGrabOrderDrinks(inviteId:String){
        var intent = Intent(getContext(), GrabOrderDrinkActivity::class.java)
        intent.putExtra("inviteId", inviteId)
        ActivityUtils.startActivity(intent)
//        ActivityUtils.startActivity(YueDrinkActivity::class.java)
    }

    /**
     * 跳转到抢单详情页
     */
    fun intentGrabOrderDetails(inviteId:String){
        var intent = Intent(getContext(), GrabOrderDetailsActivity::class.java)
        intent.putExtra("inviteId", inviteId)
        ActivityUtils.startActivity(intent)
//        ActivityUtils.startActivity(YueDrinkActivity::class.java)
    }

    /**
     * 跳转到通知绑定关系详情页
     */
    fun intentBindNoti(id:String){
        var intent = Intent(getContext(),BindNotiActivity::class.java)
        intent.putExtra("id", id)
        ActivityUtils.startActivity(intent)
//        ActivityUtils.startActivity(YueDrinkActivity::class.java)
    }

    /**
     * 跳转到设置界面
     */
    fun intentSet(){
        ActivityUtils.startActivity(SetActivity::class.java)
    }

    /**
     * 跳转到账号安全界面
     */
    fun intentSetAccount(){
        ActivityUtils.startActivity(AccountActivity::class.java)
    }

    /**
     * 跳转到实名认证成功界面
     */
    fun intentAccountDetails(){
        ActivityUtils.startActivity(AccountDetailsActivity::class.java)
    }

    /**
     * 跳转到修改密码验证码界面
     */
    fun intentChangePWCode(){
        ActivityUtils.startActivity(ChangePWCodeActivity::class.java)
    }

    /**
     * 跳转到修改密码界面
     */
    fun intentChangePW(){
        ActivityUtils.startActivity(ChangePWActivity::class.java)
    }


    /**
     * 跳转到通知设置界面
     */
    fun intentSetNotification(){
        ActivityUtils.startActivity(SetNotificationActivity::class.java)
    }

    /**
     * 跳转到意见反馈界面
     */
    fun intentOpinionFeedBack(){
        ActivityUtils.startActivity(FeedBackActivity::class.java)
    }

    /**
     * 跳转到黑名单界面
     */
    fun intentBlack(){
        ActivityUtils.startActivity(BlackListActivity::class.java)
    }

    /**
     * 跳转到关于我们界面
     */
    fun intentRegardWe(){
        ActivityUtils.startActivity(RegardWeActivity::class.java)
    }

    /**
     * 跳转到用户说明界面
     */
    fun intentExplain(){
        ActivityUtils.startActivity(ExplainActivity::class.java)
    }

    /**
     * 跳转到活动banner界面
     */
    fun intentBanner(title:String,url:String){
        var intent = Intent(getContext(), BannerActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("title",title)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到粉丝界面
     */
    fun intentFans(){
        ActivityUtils.startActivity(FansActivity::class.java)
    }

    /**
     * 跳转到意见反馈详情界面
     */
    fun intentFeedBackDetails(id: String) {
        var intent = Intent(getContext(), FeedBackDetailsActivity::class.java)
        intent.putExtra("id", id)
        ActivityUtils.startActivity(intent)
    }

    /**
     * 跳转到活动公告详情界面
     */
    fun intentActivityHtml(messageId: String,messageType:Int) {
        var intent = Intent(getContext(), ActivityHtmlActivity::class.java)
        intent.putExtra("messageId", messageId)
        intent.putExtra("messageType", messageType)
        ActivityUtils.startActivity(intent)
    }



}