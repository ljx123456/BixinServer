package com.ycwl.servebixin.cn.ui.login.activity

import android.content.Intent
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.ACache
import com.ycwl.servebixin.cn.ui.login.mvp.body.FaceAuthBody
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.FaceAuthPresenter
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.QiniuPresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.FaceAuthView
import com.ycwl.servebixin.cn.ui.login.mvp.view.QiniuView

import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.CameraFace

import kotlinx.android.synthetic.main.activity_face_recognition.*
import kotlinx.android.synthetic.main.layout_title.*
import java.io.File
import java.util.ArrayList

class FaceRecogintionActivity:BaseActivity(),CameraFace.CameraSelectFace, QiniuView,FaceAuthView {

    //人脸验证成功
    override fun getSendFaceRequest() {
        intentUtils.intentSetPassWord()
    }

    //人脸验证失败
    override fun getSendFaceError() {

    }


    private var imageUrl = ""
    private val qiniuPresenter by lazy { QiniuPresenter(this, this, this) }
    private val faceAuthPresenter by lazy { FaceAuthPresenter(this, this, this) }
    private var cameraSelect = CameraFace(this)
    private var file:File?=null

    //图片上传成功
    override fun sendSucceedImage(fileUrlList: ArrayList<String>) {
        imageUrl = "http://pic.bixinyule.com/" + fileUrlList.get(0)
        Log.e("测试","图片地址" + imageUrl)
        var mCache = ACache.get(this)
        mCache.put("faceUrl",imageUrl)
        faceAuthPresenter.getFaceData(FaceAuthBody(mCache.getAsString("phone"),imageUrl))
        if (!file!!.exists()){
            return
        }else{
//            file!!.delete()
            deletePic(file!!.absolutePath)
        }
    }

    //图片上传失败
    override fun sendFileErrorImage() {
    }

    //图片选择回调
    override fun returnCameraImageList(list: ArrayList<String>) {
        if (list!=null&&list.size>0) {
            qiniuPresenter.setImage(list)
            file = File(list[0])
        }
    }

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int = R.layout.activity_face_recognition

    override fun setActivityTitle() {
        titleText.text="绑定身份"
//        titleLeft.setImageResource(R.mipmap.nav_button_back_black_pre)
    }

    override fun initActivityData() {
        cameraSelect.initCameraSdk(this)
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clickListener() {
//        Click.viewClick(titleLeft).subscribe {
//            finish()
//        }
        Click.viewClick(openFaceRecognition).subscribe {
            cameraSelect.openCamera()
            //TODO
//            intentUtils.intentSetPassWord()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cameraSelect.onActivityCameraResult(requestCode, resultCode, data)
    }

    private fun deletePic(path:String){
        if(!TextUtils.isEmpty(path)){
            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val contentResolver = this.getContentResolver()//cutPic.this是一个上下文
//            val url =  MediaStore.Images.Media.DATA + "='" + path + "'"
//            //删除图片
//            contentResolver.delete(uri, url, null)
            val url =  MediaStore.Images.Media.DATA + "=?"
            //删除图片
            contentResolver.delete(uri, url, arrayOf(path))
        }
    }

}