package com.ycwl.servebixin.cn.ui.yue.adapter

import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.ui.yue.mvp.bean.ExpListBean
import com.ycwl.servebixin.cn.view.MyRecyclerView
import com.ycwl.servebixin.cn.db.DrinkUtils.addDrinksNum
import com.ycwl.servebixin.cn.db.DrinkUtils.deleteDrinks

class DialogDrinksGroupAdapter(map:List<ExpListBean>,val clickExpandable: Expandable) : BaseQuickAdapter<ExpListBean, BaseViewHolder>(R.layout.item_group,map){
    override fun convert(helper: BaseViewHolder, item: ExpListBean) {
        helper.setText(R.id.group_name,item.name)
        var itemAdapter=DialogDrinksItemAdapter(item.drink)
        var list=helper.getView<MyRecyclerView>(R.id.group_drinkList)
        var manager= LinearLayoutManager(mContext)
        list.layoutManager=manager
        list.adapter=itemAdapter
        itemAdapter.setOnItemChildClickListener { adapter, view, position ->
            var tv=(view.parent as LinearLayout).findViewById<TextView>(R.id.drinksNum)
            var num=tv.text.toString().toInt()
            when(view.id){
                R.id.drinksSub ->{
                    if (num > 1) {
                        tv.text="${num-1}"
                        addDrinksNum(item.drink[position],tv.text.toString())
//                        itemAdapter.notifyDataSetChanged()
                    }else {
//                        if (num<1){
//                            com.ycwl.servebixin.cn.utils.utils.Toast.Tips("数量为0")
//                        }else{
                            deleteDrinks(item.drink[position].id)

//                        }
                    }
                    clickExpandable.ChildClick()
                    itemAdapter.notifyDataSetChanged()
                }
                R.id.drinksAdd ->{
                    tv.text="${num+1}"
                    addDrinksNum(item.drink[position],tv.text.toString())
                    clickExpandable.ChildClick()
                    itemAdapter.notifyDataSetChanged()
                }
            }
        }

    }

    interface Expandable {
        fun ChildClick()

    }

}