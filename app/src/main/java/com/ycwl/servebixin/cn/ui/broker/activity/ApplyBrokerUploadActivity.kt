package com.ycwl.servebixin.cn.ui.broker.activity

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.util.Log
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.makeramen.roundedimageview.RoundedImageView
import com.muzhi.camerasdk.SelectCameraDialog
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.broker.mvp.body.ApplyBrokerUploadBody
import com.ycwl.servebixin.cn.ui.broker.mvp.presenter.ApplyBrokerUploadPresenter
import com.ycwl.servebixin.cn.ui.broker.mvp.view.ApplyBrokerUploadView
import com.ycwl.servebixin.cn.ui.broker.utils.CameraActivity
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.QiniuPresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.QiniuView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.image.ImageLoad
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.GetPathFromUri
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_upload.*
import kotlinx.android.synthetic.main.layout_title.*
import java.io.File

class ApplyBrokerUploadActivity :BaseActivity(),ApplyBrokerUploadView, QiniuView, SelectCameraDialog.SelectCameraDialogFace{
    override fun sendSucceedImage(fileUrlList: ArrayList<String>) {
//        if (isFront) {
//            imageFrontUrl= "http://pic.bixinyule.com/" + fileUrlList.get(0)
//            idCardFront.setImageBitmap(BitmapFactory.decodeFile(imgPath))
//            idCardFrontTips.visibility=View.GONE
//        } else {
//            imageBackUrl= "http://pic.bixinyule.com/" + fileUrlList.get(0)
//            idCardBack.setImageBitmap(BitmapFactory.decodeFile(imgPath))
//            idCardBackTips.visibility=View.GONE
//        }

        url="http://pic.bixinyule.com/" + fileUrlList.get(0)
        layoutDefault.visibility=View.GONE
        imageUpload.visibility=View.VISIBLE
        imageUpload.setImageBitmap(BitmapFactory.decodeFile(imgPath))
//        ImageLoad.setImageNull(url,imageUpload as RoundedImageView)
        imageAgain.visibility=View.VISIBLE
        if (!url.equals("")){
            btnUpload.isEnabled=true
        }else{
            btnUpload.isEnabled=false
        }
        if (flag) {
            if (file!!.exists()) {
                file!!.delete()
//                deletePic(file!!.absolutePath)
            }

        }
    }

    override fun sendFileErrorImage() {

    }

    override fun cameraBtn() {
//        if (isFront) {
//            CameraActivity.toCameraActivity(this, CameraActivity.TYPE_IDCARD_FRONT)
//        }else{
//            CameraActivity.toCameraActivity(this, CameraActivity.TYPE_IDCARD_BACK)
//        }
        val rxPermission= RxPermissions(this)
        rxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA).subscribe {
            if (it) {
                var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    uri = FileProvider.getUriForFile(this, "com.ycwl.servebixin.cn.fileprovider", cameraPath!!)
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                } else {
                    uri = Uri.fromFile(cameraPath!!)
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                startActivityForResult(intent, 2)
            }else{
                Toast.Tips("请打开相机权限")
            }
        }
    }

    override fun photoBtn() {
        val rxPermission= RxPermissions(this)
        rxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA).subscribe {
            if (it){
                var intent=Intent(Intent.ACTION_PICK)
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
                startActivityForResult(intent, IMAGE)
            } else{
                Toast.Tips("请打开相册读取权限")
            }

        }
    }

    companion object {
        private val IMAGE=1
    }
    private val qiniuPresenter by lazy { QiniuPresenter(this,this,this) }
    private val presenter by lazy { ApplyBrokerUploadPresenter(this,this,this) }
    private lateinit var dialog: SelectCameraDialog
    private var imgPath=""
    private var url=""
    private var cameraPath:File?=null
    private var uri:Uri?=null
//    private var imageFrontUrl=""
//    private var imageBackUrl=""
//    private var isFront=true
    private var file:File?=null
    private var flag=false


    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_upload
    override fun setActivityTitle() {
        titleText.text="申请成为达人经纪人"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        dialog = SelectCameraDialog(this)
        dialog.setDialogFace(this)
        cameraPath=File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg")
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
//        Click.viewClick(layout_idCardFront).subscribe {
//            isFront=true
//            dialog.showDialog()
//        }
        Click.viewClick(imageAgain).subscribe {
            dialog.showDialog()
        }
        Click.viewClick(layoutAdd).subscribe {
//            isFront=false
            dialog.showDialog()
        }
        Click.viewClick(btnUpload).subscribe {
            var list=ArrayList<ApplyBrokerUploadBody.VideosBean>()
            list.add(ApplyBrokerUploadBody.VideosBean(url))
//            list.add(ApplyBrokerUploadBody.VideosBean(imageBackUrl))
            presenter.getApplyBrokerUpload(ApplyBrokerUploadBody(list))
        }
    }

    override fun getUploadRequest() {
        ShowDialog.showCustomDialogNoTitle(this,"资料已提交\n平台将在五个工作日内处理！","确认",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface, which: Int) {
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        dialog.dismiss()
                        intentUtils.intentApplyBrokerRecord()
                        this@ApplyBrokerUploadActivity.finish()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == CameraActivity.REQUEST_CODE && resultCode == CameraActivity.RESULT_CODE) {
//            imgPath =CameraActivity.getImagePath(data)
//            if(!TextUtils.isEmpty(imgPath)){
//                var list=ArrayList<String>()
//                list.add(imgPath)
//                file= File(imgPath)
//                qiniuPresenter.setImage(list)
//            }
//        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                imgPath = cameraPath!!.absolutePath
            } else {
                imgPath = uri!!.getEncodedPath()
            }
            Log.e("拍照返回图片路径:", imgPath)
            flag=true
            var list=ArrayList<String>()
            list.add(imgPath)
            file=File(imgPath)
            qiniuPresenter.setImage(list)
        }
         else if (resultCode== Activity.RESULT_OK&&requestCode== IMAGE){
            flag=false
            imgPath= GetPathFromUri.getPath(this,data!!.data)
            var list=ArrayList<String>()
            list.add(imgPath)
            file=File(imgPath)
            qiniuPresenter.setImage(list)
        }
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