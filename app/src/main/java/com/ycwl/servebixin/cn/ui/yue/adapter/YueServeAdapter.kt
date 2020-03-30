package com.ycwl.servebixin.cn.ui.yue.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.db.db.ServePersonnelDB
import com.ycwl.servebixin.cn.utils.image.ImageLoad

class YueServeAdapter(serveData: List<ServePersonnelDB>) : BaseQuickAdapter<ServePersonnelDB, BaseViewHolder>(R.layout.item_yue_serve, serveData) {
    override fun convert(helper: BaseViewHolder, item: ServePersonnelDB) {
        ImageLoad.setUserHead(item.avatar, helper.getView(R.id.yueServeImage) as RoundedImageView)
        var sex=""
        if (item.sex.equals("1")){
            sex="男 "
        }else{
            sex="女 "
        }
        helper.setText(R.id.yueServeName, item.nickname)
//                .setText(R.id.yueServeAdds, "(距离约玩场地约${item.adds}km)")
                .setText(R.id.yueServeContent,sex+item.age+" "+item.constellation)
                .setText(R.id.yueServeMoney, "¥${item.skillPrice}")
//                .setText(R.id.yueServeJoin,item.)

//        var ageView = helper.getView<TextView>(R.id.yueServeAge)
//        setAgeUtils(ageView, item.sex.toInt(), "${item.age}")
        helper.addOnClickListener(R.id.yueServeDell)
    }
}