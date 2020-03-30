package com.ycwl.servebixin.cn.ui.main.activity

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.muzhi.camerasdk.SelectCameraDialog
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.ui.broker.activity.ApplyBrokerUploadActivity
import com.ycwl.servebixin.cn.ui.broker.utils.CameraActivity
import com.ycwl.servebixin.cn.ui.login.mvp.presenter.QiniuPresenter
import com.ycwl.servebixin.cn.ui.login.mvp.view.QiniuView
import com.ycwl.servebixin.cn.ui.main.mvp.bean.UploadCardBean
import com.ycwl.servebixin.cn.ui.main.mvp.body.UploadCardBody
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.RealnameCardPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.RealnameCardView
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.intent.intentUtils
import com.ycwl.servebixin.cn.utils.utils.GetPathFromUri
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_apply_broker_upload.*
import kotlinx.android.synthetic.main.layout_title.*
import java.io.File


class RealnameCardActivity: BaseActivity(),RealnameCardView, QiniuView, SelectCameraDialog.SelectCameraDialogFace{

    private val qiniuPresenter by lazy { QiniuPresenter(this,this,this) }
    private val presenter by lazy { RealnameCardPresenter(this,this,this) }
    private lateinit var dialog: SelectCameraDialog
    private var imgPath=""
    private var imageFrontUrl=""
    private var imageBackUrl=""
    private var isFront=true
    private var file: File?=null
    private var name=""
    private var card=""
    private var flag=false


    companion object {
        private val IMAGE=1
    }

    override fun getRealnameCardRequest(data: UploadCardBean) {

        ShowDialog.showCustomDialogNoTitle(this,"资料已提交\n平台将在五个工作日内处理！\n您可以在“设置-账号与安全”中查看","确认",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface, which: Int) {
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        dialog.dismiss()
                        finish()
                        intentUtils.intentRealnameDetails("1")
//                        this@ApplyBrokerUploadActivity.finish()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }
        })

    }

    override fun getRealnameCardError() {

    }

    override fun sendSucceedImage(fileUrlList: ArrayList<String>) {
        if (isFront) {
            imageFrontUrl= "http://pic.bixinyule.com/" + fileUrlList.get(0)
            idCardFront.setImageBitmap(BitmapFactory.decodeFile(imgPath))
            idCardFrontTips.visibility= View.GONE
        } else {
            imageBackUrl= "http://pic.bixinyule.com/" + fileUrlList.get(0)
            idCardBack.setImageBitmap(BitmapFactory.decodeFile(imgPath))
            idCardBackTips.visibility= View.GONE
        }
        if (!imageFrontUrl.equals("")&&!imageBackUrl.equals("")){
            btnUpload.isEnabled=true
        }else{
            btnUpload.isEnabled=false
        }
        if (!flag) {
            if (file!!.exists()) {
//            file!!.delete()
                deletePic(file!!.absolutePath)
            }
        }

    }

    override fun sendFileErrorImage() {

    }

    override fun cameraBtn() {
        flag=false
        if (isFront) {
            CameraActivity.toCameraActivity(this, CameraActivity.TYPE_IDCARD_FRONT)
        }else{
            CameraActivity.toCameraActivity(this, CameraActivity.TYPE_IDCARD_BACK)
        }
    }

    override fun photoBtn() {
        flag=true
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CameraActivity.REQUEST_CODE && resultCode == CameraActivity.RESULT_CODE) {
            imgPath = CameraActivity.getImagePath(data)
            if(!TextUtils.isEmpty(imgPath)){
                var list=ArrayList<String>()
                list.add(imgPath)
                file= File(imgPath)
                qiniuPresenter.setImage(list)
            }
        }
        if (resultCode== Activity.RESULT_OK&&requestCode== IMAGE){
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

    override fun openEventBus(): Boolean =false

    override fun getActivityLayout(): Int = R.layout.activity_apply_broker_upload
    override fun setActivityTitle() {
        titleText.text="实名认证"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
    }

    override fun initActivityData() {
        dialog = SelectCameraDialog(this)
        dialog.setDialogFace(this)
        name=intent.getStringExtra("name")
        card=intent.getStringExtra("card")
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe {
            finish()
        }
        Click.viewClick(layout_idCardFront).subscribe {
            isFront=true
            dialog.showDialog()
        }
        Click.viewClick(layout_idCardBack).subscribe {
            isFront=false
            dialog.showDialog()
        }
        Click.viewClick(btnUpload).subscribe {
            var list=ArrayList<UploadCardBody.VideosBean>()
            list.add(UploadCardBody.VideosBean(imageFrontUrl))
            list.add(UploadCardBody.VideosBean(imageBackUrl))
            presenter.getUpload(UploadCardBody(name,card,list))
        }


    }


}