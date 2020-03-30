package com.ycwl.servebixin.cn.utils.http

import android.util.Log

import com.blankj.utilcode.util.AppUtils
import com.ycwl.servebixin.cn.db.GreenDaoHelper
import com.ycwl.servebixin.cn.utils.http.BaseUrl
import com.ycwl.servebixin.cn.utils.http.MyGsonConverterFactory

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2017/12/18 0018.
 */
class RetrofitClient : HttpLoggingInterceptor.Logger {

    private val httpTimer: Int = 30
    private var mRetrofit: Retrofit? = null
    private var client: OkHttpClient.Builder? = null
    private var headInterceptor: BaseInterceptor? = null
    private var loggingInterceptor: HttpLoggingInterceptor? = null

//    constructor() {
//        loggingInterceptor = HttpLoggingInterceptor(this)
//    }

//    companion object {
//        fun instance(): RetrofitClient = SingletonHolder.INSTANCE
//    }

//    object SingletonHolder {
//        var INSTANCE = RetrofitClient()
//    }

    fun initRetrofitClient(): Retrofit? {
        initOkHttpClient()
        return initRetrofit()
    }

    private fun initRetrofit(): Retrofit? {
//        if (mRetrofit == null) {
            val builder = Retrofit.Builder()
            mRetrofit = builder.baseUrl(BaseUrl.HOST_URL)
                    .addConverterFactory(MyGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client!!.build())
                    .build()
//        }
        return mRetrofit
    }

    private fun initOkHttpClient() {
        if (client == null) {
            client = OkHttpClient.Builder()
        }
        addHeadInterceptor()
        loggingInterceptor = HttpLoggingInterceptor(this)
        loggingInterceptor!!.level = HttpLoggingInterceptor.Level.BODY
        client!!.addInterceptor(loggingInterceptor)
        client!!.retryOnConnectionFailure(true)
        client!!.addInterceptor(headInterceptor)
        client!!.connectTimeout(httpTimer.toLong(), TimeUnit.SECONDS)
    }


    //添加head
    private fun addHeadInterceptor() {
        headInterceptor = BaseInterceptor()
        headInterceptor?.addHeads("os", "android")
        headInterceptor?.addHeads("ename", "Others")
        headInterceptor?.addHeads("app-version", AppUtils.getAppVersionCode().toString())
        var user = GreenDaoHelper.getDaoSessions().userDBDao
        if (user != null) {
            var info = user.loadAll()
            if (info != null && info.size > 0) {
                headInterceptor?.addHeads("Authorization", info.get(0).token)
            }
        }

    }

    override fun log(message: String?) {
        Log.e("打印下接口参数值：", message)
    }

}