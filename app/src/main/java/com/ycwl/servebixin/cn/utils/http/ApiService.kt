package com.ycwl.servebixin.cn.utils.http

import com.ycwl.servebixin.cn.ui.broker.mvp.body.*
import com.ycwl.servebixin.cn.ui.broker.mvp.bean.*
import com.ycwl.servebixin.cn.ui.login.mvp.bean.*
import com.ycwl.servebixin.cn.ui.login.mvp.body.*
import com.ycwl.servebixin.cn.ui.main.mvp.bean.*
import com.ycwl.servebixin.cn.ui.main.mvp.body.*
import com.ycwl.servebixin.cn.ui.message.mvp.bean.*
import com.ycwl.servebixin.cn.ui.message.mvp.body.*
import com.ycwl.servebixin.cn.ui.order.mvp.bean.*
import com.ycwl.servebixin.cn.ui.order.mvp.body.*
import com.ycwl.servebixin.cn.ui.set.mvp.bean.*
import com.ycwl.servebixin.cn.ui.set.mvp.body.*
import com.ycwl.servebixin.cn.ui.set.mvp.body.*
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean.*
import com.ycwl.servebixin.cn.ui.withdrawal.mvp.body.*
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.*
import com.ycwl.servebixin.cn.ui.yue.mvp.body.*

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Administrator on 2017/12/18 0018.
 */
interface ApiService {

    //验证码登录
    @POST("rl/login/by/code")
    fun getByCode(@Body body: ByCodeBody): Observable<ByCodeBean>

    //发送验证码接口
    @POST("sms/send/code")
    fun getSendCode(@Body body: SendCodeBody): Observable<SendCodeBean>

    //验证码，验证
    @POST("sms/validationCode")
    fun getValidationCode(@Body body: ValidationCodeBody): Observable<ValidationCodeBean>

    //密码登录
    @POST("rl/login/by/pwd")
    fun getByPwd(@Body body: ByPwdBody): Observable<ByCodeBean>

    //获取七牛token
    @POST("public/qiniu/upload/token")
    fun getQiniuToken(): Observable<QiniuTokenBean>

    //人脸认证
    @POST("rl/faceAuth")
    fun getFaceAuth(@Body body: FaceAuthBody): Observable<FaceAuthBean>

    //昵称校验
    @POST("rl/nicknameCheck")
    fun getCheckName(@Body body: CheckNameBody): Observable<CheckNameBean>

    //注册基本资料完善
    @POST("rl/perfected/user/info")
    fun getRegisterData(@Body body: RegisterDataBody): Observable<ByCodeBean>

    //忘记密码
    @POST("rl/forget/pwd")
    fun getResetPwd(@Body body: ResetPwdBody): Observable<ResetPwdBean>

    //刷新用户信息
    @POST("public/refresInfo")
    fun getPersonData(): Observable<ByCodeBean>

    //刷新用户粉丝列表
    @POST("user/fanslist")
    fun getFansData(@Body body: FansBody): Observable<FansBean>

    //APP 是否更新
    @POST("app/is/update")
    fun getUpdate(@Body body: UpdateBody): Observable<UpdateBean>

    //更新用户城市
    @POST("user/updateCityInfo")
    fun getUpdateCity(@Body body: UpdateCityBody): Observable<UpdateCityBean>

    //上传实名认证照片
    @POST("user/realNameAuth")
    fun getUpload(@Body body: UploadCardBody): Observable<UploadCardBean>

    //获取实名认证详情
    @POST("user/realNameAuth/details")
    fun getDetailsData(): Observable<RealnameDetailsBean>

    //获取完善资料审核详情
    @POST("user/audit/data/details")
    fun getCompleteDetailsData(): Observable<CompleteDataDetailsBean>

    //刷新定位
    @POST("user/updateLocation")
    fun getLocation(@Body body: LocationBody): Observable<EditUserBean>

    //修改用户信息
    @POST("user/edit")
    fun getEditUser(@Body body: EditUserBody): Observable<EditUserBean>

    //获取职业信息
    @POST("user/edit/occupation")
    fun getOccupation(): Observable<OccupationBean>

    //获取标签
    @POST("user/edit/tags")
    fun getTags(): Observable<TagBean>

    //获取服务设置列表
    @POST("user/skill/list")
    fun getServeSet(): Observable<ServeSetBean>

    //控制服务开关
    @POST("user/skill/switch")
    fun getServeSetOpen(@Body body: ServeSetOpenBody): Observable<EditUserBean>

    //获取服务设置详情
    @POST("user/skill/details")
    fun getServeDetails(@Body body: ServeDetailsBody): Observable<ServeDetailsBean>

    //删除绑定商家KTV
    @POST("user/business/del")
    fun getDelKTV(@Body body: DelKTVBody): Observable<EditUserBean>

    //开启商家KTV接单
    @POST("user/business/switch")
    fun getOpenKTV(@Body body: OpenKTVBody): Observable<EditUserBean>

    //开启全平台接单
    @POST("user/skill/isOut/switch")
    fun getOpenAll(@Body body: OpenAllBody): Observable<EditUserBean>

    //服务价格设置
    @POST("user/skill/set/price")
    fun getServeSetPrice(@Body body: ServeSetPriceBody): Observable<ServeSetPriceBean>

    //搜索经纪人
    @POST("user/leader/search")
    fun getSearchLeader(@Body body: SearchLeaderBody): Observable<SearchLeaderBean>

    //绑定经纪人
    @POST("user/leader/binding")
    fun getBindLeader(@Body body: BindLeaderBody): Observable<EditUserBean>

    //解绑经纪人
    @POST("user/leader/untying")
    fun getUnbindLeader(@Body body: ServeDetailsBody): Observable<EditUserBean>

    //搜索KTV
    @POST("user/business/search")
    fun getSearchKTV(@Body body: SearchKTVBody): Observable<SearchKTVBean>

    //附近KTV
    @POST("user/nearby/business")
    fun getNearByKTV(@Body body: NearByKTVBody): Observable<NearByKTVBean>

    //绑定KTV
    @POST("user/business/binding")
    fun getBindKTV(@Body body: BindKTVBody): Observable<EditUserBean>

    //设置提现密码
    @POST("user/wallet/bindingPassword")
    fun getSetWithdrawPwd(@Body body: SetWithdrawPwdBody): Observable<SetWithdrawPwdBean>


    //获取收益
    @POST("user/wallet")
    fun getIncome(): Observable<IncomeBean>

    //提现记录
    @POST("user/wallet/drawCash/recordList")
    fun getWithdrawRecord(@Body body: WithdrawRecordListBody): Observable<WithdrawRecordListBean>

    //提现记录详情
    @POST("user/wallet/drawCash/record")
    fun getWithdrawRecordDetails(@Body body: WithdrawRecordDetailsBody): Observable<WithdrawRecordDetailsBean>

    //获取提现方式
    @POST("user/wallet/drawCash/method")
    fun getWithdrawType(): Observable<WithdrawTypeBean>

    //绑定提现方式
    @POST("user/wallet/drawCash/binding")
    fun getBindWithdrawType(@Body body: BindWithdrawTypeBody): Observable<BindWithdrawTypeBean>

    //解绑提现方式
    @POST("user/wallet/drawCash/relieve")
    fun getUnbindWithdrawType(@Body body: UnbindWithdrawTypeBody): Observable<UnbindWithdrawTypeBean>

    //验证提现密码
    @POST("user/wallet/changePassword/auth")
    fun getWithdrawOldPwd(@Body body: WithdrawOldPwdBody): Observable<EditUserBean>

    //修改提现密码
    @POST("user/wallet/changePassword")
    fun getWithdrawChangePwd(@Body body: WithdrawChangePwdBody): Observable<SetWithdrawPwdBean>

    //重置提现密码
    @POST("user/wallet/findPassword")
    fun getWithdrawResetPwd(@Body body: WithdrawChangePwdBody): Observable<SetWithdrawPwdBean>

    //提现
    @POST("user/wallet/drawCash")
    fun getWithdraw(@Body body:WithdrawBody): Observable<WithdrawBean>

    //获取支付宝授权信息
    @POST("user/wallet/drawCash/binding/authInfo")
    fun getAuthInfo(): Observable<AuthInfoBean>

    //绑定支付宝
    @POST("user/wallet/drawCash/binding/ali")
    fun getBindAlipayType(@Body body:BindAlipayTypeBody): Observable<BindWithdrawTypeBean>

    //绑定微信
    @POST("user/wallet/drawCash/binding/wx")
    fun getBindWxType(@Body body:BindAlipayTypeBody): Observable<BindWithdrawTypeBean>

    //解绑提现方式
    @POST("user/wallet/drawCash/relieve")
    fun getUnbindType(@Body body:UnbindTypeBody): Observable<UnbindTypeBean>

    //收益明细记录
    @POST("user/wallet/profitList")
    fun getIncomeRecord(@Body body: IncomeRecordListBody): Observable<IncomeRecordListBean>

    //收益明细记录详情
    @POST("user/wallet/profitDetail")
    fun getIncomeRecordDetails(@Body body: IncomeRecordDetailsBody): Observable<IncomeRecordDetailsBean>

    //经纪人申请提交资料
    @POST("user/leader/apply")
    fun getApplyBrokerUpload(@Body body: ApplyBrokerUploadBody): Observable<ApplyBrokerUploadBean>

    //经纪人申请记录列表
    @POST("user/leader/applyList")
    fun getApplyBrokerRecord(): Observable<ApplyBrokerRecordBean>

    //经纪人申请记录详情
    @POST("user/leader/applyDetail")
    fun getApplyBrokerRecordDetails(@Body body: ApplyBrokerRecordDetailsBody): Observable<ApplyBrokerRecordDetailsBean>

    //经纪人主页
    @POST("leader")
    fun getBroker(): Observable<BrokerBean>

    //经纪人约玩达人
    @POST("leader/services")
    fun getBrokerServe(@Body body: BrokerServeBody): Observable<BrokerServeBean>

    //经纪人约玩场地
    @POST("leader/myFields")
    fun getBrokerKTV(): Observable<BrokerKTVBean>

    //经纪人打开约玩场地
    @POST("leader/field/switch")
    fun getBrokerKTVOpen(@Body body: BrokerKTVOpenBody): Observable<BrokerKTVOpenBean>

    //经纪人删除约玩场地
    @POST("leader/field/del")
    fun getBrokerKTVDel(@Body body: BrokerKTVDelBody): Observable<BrokerKTVDelBean>

    //搜索经纪人约玩场地
    @POST("leader/business/search")
    fun getBrokerSearchKTV(@Body body: BrokerSearchKTVBody): Observable<SearchKTVBean>

    //添加经纪人约玩场地
    @POST("leader/field/add")
    fun getBrokerAddKTV(@Body body: BrokerAddKTVBody): Observable<BrokerAddKTVBean>

    //生成订单约玩场地
    @POST("leader/business")
    fun getYueKTV(@Body body:YueKTVBody): Observable<KTVBean>

    //生成订单包房列表
    @POST("leader/business/box")
    fun getYueKTVBox(@Body body:BaoFangBody): Observable<BaoFangBean>

    //生成订单扫码获取包房信息
    @POST("public/find/business/box/info")
    fun getKTVBox(@Body body:KTVBoxBody): Observable<KTVBoxBean>

    //生成订单达人信息
    @POST("leader/order/sweepcode")
    fun getYueServe(@Body body:YueServeBody): Observable<YueServeBean>

    //KTV酒水展示(03-酒水其他)
    @POST("leader/business/wines")
    fun getDrinks(@Body body: DrinksBody): Observable<DrinksBean>

    //获取酒水套餐详情
    @POST("user/business/wine/details")
    fun getDrinkDetails(@Body body: DrinkDetailsBody): Observable<DrinkDetailsBean>

    //生成约会订单
    @POST("leader/makeOrder")
    fun getOrderCode(@Body body: YueBody): Observable<YueBean>

    //获取订单下的订单列表
    @POST("service/order")
    fun getOrderFormList(@Body body: OrderFormListBody): Observable<OrderFormListBean>

    //获取订单下的订单详情
    @POST("service/order/details")
    fun getOrderFormDetails(@Body body: OrderFormDetailsBody): Observable<OrderFormDetailsBean>

    //接受订单
    @POST("service/order/accept")
    fun getOrderFormAccept(@Body body: OrderFormAcceptBody): Observable<OrderFormAcceptBean>

    //订单打卡
    @POST("service/order/clockIn")
    fun getOrderFormClock(@Body body: OrderFormClockBody): Observable<OrderFormClockBean>

    //订单拒绝理由
    @POST("service/order/refuse/reasons")
    fun getOrderFormRefuseReasons(): Observable<OrderFormRefuseReasonsBean>

    //订单拒绝
    @POST("service/order/refuse")
    fun getOrderFormRefuse(@Body body: OrderFormRefuseBody): Observable<OrderFormRefuseBean>

    //订单点单
    @POST("service/order/business/wine")
    fun getOrderFormDrink(@Body body: OrderFormDrinkBody): Observable<DrinksBean>

    //订单点单生成二维码
    @POST("service/order/makeWineOrder")
    fun getOrderDrinkCode(@Body body: OrderDrinkCodeBody): Observable<OrderDrinkCodeBean>

    //获取抢单下的抢单列表
    @POST("leader/grabOrder")
    fun getGrabOrderList(@Body body: GrabOrderListBody): Observable<GrabOrderListBean>

    //抢单
    @POST("leader/grabOrder/accept")
    fun getGrabOrder(@Body body: GrabOrderBody): Observable<OrderFormAcceptBean>

    //抢单点单
    @POST("leader/pointList/wine")
    fun getGrabOrderDrink(@Body body: GrabOrderDrinkBody): Observable<DrinksBean>

    //抢单点单生成二维码
    @POST("leader/pointList/makeOrder")
    fun getGrabOrderDrinkCode(@Body body: GrabOrderDrinkCodeBody): Observable<OrderDrinkCodeBean>

    //抢单的订单详情
    @POST("leader/grabOrder/details")
    fun getGrabOrderDetails(@Body body: GrabOrderDetailsBody): Observable<GrabOrderDetailsBean>

    //banner活动
    @POST("user/service_banner")
    fun getBanner(): Observable<BannerBean>

    //通知列表
    @POST("user/service/notice")
    fun getNotification(@Body body: NotificationBody): Observable<NotificationBean>

    //读取通知
    @POST("user/service/notice/read")
    fun getReadNoti(@Body body: ReadNotiBody): Observable<ReadNotiBean>

    //清空通知
    @POST("user/notice/clear")
    fun getClearNotification(): Observable<ClearNotificationBean>

    //绑定关系通知
    @POST("user/service/notice/SLdetails")
    fun getBindNoti(@Body body: BindNotiBody): Observable<BindNotiBean>

    //意见反馈通知
    @POST("user/feedbackDetails")
    fun getFeedBackDetails(@Body body: FeedBackDetailsBody): Observable<FeedBackDetailsBean>

    //意见反馈
    @POST("user/service/feedback/add")
    fun getFeedBack(@Body body: FeedBackBody): Observable<LogoutBean>

    //通知设置状态
    @POST("user/service/notify")
    fun getNotiState(): Observable<NotiStateBean>

    //通知开关
    @POST("user/service/notify/switch")
    fun getSetNoti(@Body body: SetNotiBody): Observable<LogoutBean>

    //黑名单列表
    @POST("user/service/black/list")
    fun getBlackList(@Body body: BlackListBody): Observable<BlackListBean>

    //移除黑名单
    @POST("user/service/black/change")
    fun getDelBlack(@Body body: DelBlackBody): Observable<LogoutBean>

    //退出登录
    @POST("rl/logout")
    fun getLogout(): Observable<LogoutBean>

    //修改密码
    @POST("user/change/login/pwd")
    fun getChangePW(@Body body: ChangePWBody): Observable<ChangePWBean>

}