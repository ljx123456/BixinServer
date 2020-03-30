package com.ycwl.servebixin.cn.ui.order.utils

import android.os.CountDownTimer
import android.view.View
import android.widget.TextView



/**
 * Created by Administrator on 2018/8/11 0011.
 * 验证码倒计时
 */
class TimeUtils {

    private val startTime: Long = 1000
    private var endTimer = 0.toLong()
    private var countDownTimer: CountDownTimer? = null
    private var callBack:TimeUtilCallBack?=null

    fun setEndTimer(endTimer: Long) {
        this.endTimer = endTimer * 1000
    }


//    fun codeCountTimer(codeView: TextView) {
//        countDownTimer = object : CountDownTimer(endTimer, startTime) {
//            override fun onFinish() {
//                codeView.isEnabled = true
//                codeView.text = "发送邀约"
//                codeView.setBackgroundResource(R.drawable.pink_shape)
//            }
//
//            override fun onTick(millisUntilFinished: Long) {
//                codeView.isEnabled = false
//                codeView.setBackgroundResource(R.drawable.pink_shape_no)
//                codeView.text = formatTimeS(millisUntilFinished / 1000).toString()
//            }
//        }.start()
//    }

    fun codeCountTimerOrder(before:String,after:String,codeView: TextView) {
        if (countDownTimer!=null) {
            countDownTimer!!.cancel()
            countDownTimer = null
        }
        countDownTimer = object : CountDownTimer(endTimer, startTime) {
            override fun onFinish() {
                codeView.visibility= View.GONE
                callBack!!.finishTime()
            }

            override fun onTick(millisUntilFinished: Long) {
//                codeView.visibility= View.VISIBLE
                codeView.text =  before+formatTimeS(millisUntilFinished / 1000).toString()+after
            }
        }.start()
    }

    fun onDestroy() {
        if (countDownTimer!=null) {
//            countDownTimer!!.onFinish()
            countDownTimer!!.cancel()
            countDownTimer=null
        }
    }

    fun formatTimeS(seconds: Long): String {
        var temp = 0
        val sb = StringBuffer()
        if (seconds > 3600) {
            temp = (seconds / 3600).toInt()
            sb.append(if (seconds / 3600 < 10) "0$temp:" else temp.toString() + ":")
            temp = (seconds % 3600 / 60).toInt()
            changeSeconds(seconds, temp, sb)
        } else {
            temp = (seconds % 3600 / 60).toInt()
            changeSeconds(seconds, temp, sb)
        }
        return sb.toString()
    }

    private fun changeSeconds(seconds: Long, temp: Int, sb: StringBuffer) {
        var temp = temp
        sb.append(if (temp < 10) "0$temp:" else "$temp:")
        temp = (seconds % 3600 % 60).toInt()
        sb.append(if (temp < 10) "0$temp" else "" + temp)
    }

    public fun setCallBack(callBack: TimeUtilCallBack){
        this.callBack=callBack
    }

    interface TimeUtilCallBack{
        fun finishTime()
    }

}