package com.ycwl.servebixin.cn.ui.main.activity

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.util.Log
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.makeramen.roundedimageview.RoundedDrawable
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.user
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog
import com.ycwl.servebixin.cn.utils.image.ImageLoad
import com.ycwl.servebixin.cn.utils.permissions.UserPermissions
import com.ycwl.servebixin.cn.utils.utils.QRCodeUtils
import com.ycwl.servebixin.cn.utils.utils.Toast
import kotlinx.android.synthetic.main.activity_bixinid.*
import kotlinx.android.synthetic.main.layout_title.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class BiXinIDActivity:BaseActivity(){
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int = R.layout.activity_bixinid

    override fun setActivityTitle() {
        titleText.text="我的二维码"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)
        titleRightText.visibility=View.VISIBLE
    }

    override fun initActivityData() {
        ImageLoad.setUserHead(user.getUserImage(),biXinID_img)
        tv_biXinID_name.text=user.getUserNick()
        tv_biXinID_content.text=user.getSex()+" ${user.getUserAge()}岁 "+user.getUserConstellation()
        tv_biXinID_id.text="ID：${user.getUserBiXinID()}"
        var url="http://bixinyule.com?bixinClientType=2&qrcodeType=1&bixinId="+"${user.getUserBiXinID()}"+"&identity="+"${user.getIdentity()}"
        var bitmap=QRCodeUtils.addLogo(QRCodeUtils.createQRcodeImage(url,580,580),(biXinID_img.drawable as RoundedDrawable).toBitmap())
        iv_biXinID_code.setImageBitmap(bitmap)
    }


    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        Click.viewClick(titleRightText).subscribe {
//            Log.e("测试","点击了")
            val rxPermissions = RxPermissions(this)
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe { aBoolean ->
                if (aBoolean!!) {
//                    Log.e("测试","有权限")
                    saveMyBitmap("AuthCode", createViewBitmap(layout_code))
                }
                else {
                    ShowDialog.showCustomDialogNoTitle(this,"由于无法获取设备信息的权限,应用无法运行，请前往设置中心应用权限页设置","去设置","取消",object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    dialog.dismiss()
                                    UserPermissions.gotoSet(this@BiXinIDActivity)
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog.dismiss()
                                }
                            }
                        }
                    })
                }
            }
        }
    }

    //将要存为图片的view传进来 生成bitmap对象
    fun createViewBitmap(v: View): Bitmap {
        val bitmap = Bitmap.createBitmap(v.width, v.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        v.draw(canvas)
        return bitmap
    }

    //使用IO流将bitmap对象存到本地指定文件夹
    fun saveMyBitmap(bitName: String, bitmap: Bitmap) {
        Thread(Runnable {
//            val filePath = Environment.getExternalStorageDirectory().absolutePath
//            val file = File(filePath + "/DCIM/Camera/" + bitName + ".png")
            val paperFile = ".bixin_code"
            val fileName = bitName + ".png"
            val dir = File(Environment.getExternalStorageDirectory().path, paperFile)
            if (!dir.exists()) dir.mkdirs()
            val file=File(dir,fileName)
            try {
                file.createNewFile()
                var fOut: FileOutputStream? = null
                fOut = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
                val msg = Message.obtain()
                msg.obj = file.getPath()
                handler.sendMessage(msg)
//                Log.e("测试","写入了")
                //Toast.makeText(PayCodeActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                fOut!!.flush()
                fOut!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
//                Log.e("测试",e.toString())
            }
        }).start()
    }

    internal var handler: Handler = object : Handler() {
        override
        fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val picFile = msg.obj as String
            val split = picFile.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val fileName = split[split.size - 1]
//            Log.e("测试","文件名"+fileName)
            try {
                MediaStore.Images.Media.insertImage(applicationContext.contentResolver, picFile, fileName, null)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            // 最后通知图库更新
            sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://$picFile")))
            tv_biXinID_tips.visibility=View.VISIBLE
//            Toast.Tips("图片保存图库成功")
//            if (mLDialog != null && mLDialog.isShowing()) {
//                mLDialog.dismiss()
//            }
//            tvSave.setClickable(true)
        }
    }
}