package com.ycwl.servebixin.cn.ui.main.activity

import android.util.Log
import android.view.View
import cn.yoyo.com.utils.utils.Click
import com.blankj.utilcode.util.LogUtils
import com.ycwl.servebixin.cn.R
import com.ycwl.servebixin.cn.base.BaseActivity
import com.ycwl.servebixin.cn.db.TagsUtils
import com.ycwl.servebixin.cn.ui.login.mvp.bean.ByCodeBean.DataBean.TagsBean
import com.ycwl.servebixin.cn.ui.main.mvp.bean.TagBean
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.TagsPresenter
import com.ycwl.servebixin.cn.ui.main.mvp.view.TagsView
import com.ycwl.servebixin.cn.utils.utils.Toast
import com.ycwl.servebixin.cn.view.tag.MultiLineChooseLayout
import kotlinx.android.synthetic.main.activity_tags.*
import kotlinx.android.synthetic.main.layout_title.*

class TagsActivity : BaseActivity(),TagsView{

    private val presenter by lazy { TagsPresenter(this,this,this) }
    private var listBean:ArrayList<TagsBean> = ArrayList()
    private var listBean1:ArrayList<TagsBean> = ArrayList()
    private var listBean2:ArrayList<TagsBean> = ArrayList()
    private var listBean3:ArrayList<TagsBean> = ArrayList()
    private var listBean4:ArrayList<TagsBean> = ArrayList()
    private var listBean5:ArrayList<TagsBean> = ArrayList()
    private var list:ArrayList<String> = ArrayList()
    private var list1:ArrayList<String> = ArrayList()
    private var list2:ArrayList<String> = ArrayList()
    private var list3:ArrayList<String> = ArrayList()
    private var list4:ArrayList<String> = ArrayList()
    private var list5:ArrayList<String> = ArrayList()
    override fun openEventBus(): Boolean = false

    override fun getActivityLayout(): Int =R.layout.activity_tags

    override fun setActivityTitle() {
        titleText.text="标签"
        titleRightText.visibility=View.VISIBLE
        titleRightText.text="确定"
        titleLeft.setImageResource(R.mipmap.nav_button_back_black_nor)

    }

    override fun initActivityData() {
        val tags= TagsUtils.getTags()
        if (tags==null||tags.size==0) {
            chooseTagsLabel.visibility = View.GONE
            tagNum.text="0/12"
        }else{
            chooseTagsLabel.visibility = View.VISIBLE
            tagNum.text=tags.size.toString()+"/12"
            tags.forEach {
                list.add(it.tagName)
                listBean.add(TagsBean(it.tagId,it.tagName))
            }
            chooseTagsLabel.setList(list)
        }
        presenter.getTags()
    }

    override fun clickListener() {
        Click.viewClick(titleLeft).subscribe { finish() }
        chooseTagsLabel.setOnItemClickListener { position, text ->
            Log.e("测试pos",":"+position.toString())
//            chooseTagsLabel.isSelected()
            if (list1.contains(list[position])){
                chooseTags1.cancelSelectedIndex(list1.indexOf(list[position]))
            }else if (list2.contains(list[position])){
                chooseTags2.cancelSelectedIndex(list2.indexOf(list[position]))
            }else if (list3.contains(list[position])){
                chooseTags3.cancelSelectedIndex(list3.indexOf(list[position]))
            }else if (list4.contains(list[position])){
                chooseTags4.cancelSelectedIndex(list4.indexOf(list[position]))
            }else if (list5.contains(list[position])){
                chooseTags5.cancelSelectedIndex(list5.indexOf(list[position]))
            }
            list.removeAt(position)
            listBean.removeAt(position)
            chooseTagsLabel.setList(list)
            tagNum.text=list.size.toString()+"/12"
            if (list.size==0){
                chooseTagsLabel.visibility=View.GONE
            }

        }

        chooseTags1.setOnItemClickListener { position, text ->
            if (list.size<12) {
                if (!chooseTags1.isSelected(position)) {
//                if (list.size<12) {
                    list.remove(list1[position])
                    var tags: TagsBean? = null
                    listBean.forEach {
                        if (it.tagName == list1[position]) {
                            tags = it
                            return@forEach
                        }
                    }
                    listBean.remove(tags)
                    LogUtils.a("测试listBean变化", listBean.size.toString())
                    chooseTagsLabel.setList(list)
                    tagNum.text = list.size.toString() + "/12"
                    if (list.size == 0) {
                        chooseTagsLabel.visibility = View.GONE
                    }
//                }
                } else {
                    list.add(list1[position])
                    listBean.add(listBean1[position])
                    chooseTagsLabel.setList(list)
                    chooseTagsLabel.visibility = View.VISIBLE
                    tagNum.text = list.size.toString() + "/12"
                }
            }else{
                if (!chooseTags1.isSelected(position)){
                    list.remove(list1[position])
                    var tags: TagsBean? = null
                    listBean.forEach {
                        if (it.tagName == list1[position]) {
                            tags = it
                            return@forEach
                        }
                    }
                    listBean.remove(tags)
                    LogUtils.a("测试listBean变化", listBean.size.toString())
                    chooseTagsLabel.setList(list)
                    tagNum.text = list.size.toString() + "/12"
                    if (list.size == 0) {
                        chooseTagsLabel.visibility = View.GONE
                    }
                }else{
                    Toast.Tips("标签最多允许选择12个")
                    chooseTags1.cancelSelectedIndex(position)
                }
            }
        }

        chooseTags2.setOnItemClickListener { position, text ->
            if (list.size<12) {
                if (!chooseTags2.isSelected(position)) {
                    list.remove(list2[position])
                    var tags: TagsBean? = null
                    listBean.forEach {
                        if (it.tagName == list2[position]) {
                            tags = it
                            return@forEach
                        }
                    }
                    listBean.remove(tags)
                    chooseTagsLabel.setList(list)
                    tagNum.text = list.size.toString() + "/12"
                    if (list.size == 0) {
                        chooseTagsLabel.visibility = View.GONE
                    }
                } else {
                    list.add(list2[position])
                    listBean.add(listBean2[position])
                    chooseTagsLabel.setList(list)
                    chooseTagsLabel.visibility = View.VISIBLE
                    tagNum.text = list.size.toString() + "/12"

                }
            }else{
                if (!chooseTags2.isSelected(position)) {
                    list.remove(list2[position])
                    var tags: TagsBean? = null
                    listBean.forEach {
                        if (it.tagName == list2[position]) {
                            tags = it
                            return@forEach
                        }
                    }
                    listBean.remove(tags)
                    chooseTagsLabel.setList(list)
                    tagNum.text = list.size.toString() + "/12"
                    if (list.size == 0) {
                        chooseTagsLabel.visibility = View.GONE
                    }
                } else {
                    Toast.Tips("标签最多允许选择12个")
                    chooseTags2.cancelSelectedIndex(position)
                }
            }
        }

        chooseTags3.setOnItemClickListener { position, text ->
            if (list.size<12) {
                if (!chooseTags3.isSelected(position)) {
                    list.remove(list3[position])
                    var tags: TagsBean? = null
                    listBean.forEach {
                        if (it.tagName == list3[position]) {
                            tags = it
                            return@forEach
                        }
                    }
                    listBean.remove(tags)
                    chooseTagsLabel.setList(list)
                    tagNum.text = list.size.toString() + "/12"
                    if (list.size == 0) {
                        chooseTagsLabel.visibility = View.GONE
                    }
                } else {

                    list.add(list3[position])
                    listBean.add(listBean3[position])
                    chooseTagsLabel.setList(list)
                    chooseTagsLabel.visibility = View.VISIBLE
                    tagNum.text = list.size.toString() + "/12"

                }
            }else{
                if (!chooseTags3.isSelected(position)) {
                    list.remove(list3[position])
                    var tags: TagsBean? = null
                    listBean.forEach {
                        if (it.tagName == list3[position]) {
                            tags = it
                            return@forEach
                        }
                    }
                    listBean.remove(tags)
                    chooseTagsLabel.setList(list)
                    tagNum.text = list.size.toString() + "/12"
                    if (list.size == 0) {
                        chooseTagsLabel.visibility = View.GONE
                    }
                } else {
                    Toast.Tips("标签最多允许选择12个")
                    chooseTags3.cancelSelectedIndex(position)
                }
            }
        }

        chooseTags4.setOnItemClickListener { position, text ->
            if (list.size<12) {
                if (!chooseTags4.isSelected(position)) {
                    list.remove(list4[position])
                    var tags: TagsBean? = null
                    listBean.forEach {
                        if (it.tagName == list4[position]) {
                            tags = it
                            return@forEach
                        }
                    }
                    listBean.remove(tags)
                    chooseTagsLabel.setList(list)
                    tagNum.text = list.size.toString() + "/12"
                    if (list.size == 0) {
                        chooseTagsLabel.visibility = View.GONE
                    }
                } else {

                    list.add(list4[position])
                    listBean.add(listBean4[position])
                    chooseTagsLabel.setList(list)
                    chooseTagsLabel.visibility = View.VISIBLE
                    tagNum.text = list.size.toString() + "/12"

                }
            }else{
                if (!chooseTags4.isSelected(position)) {
                    list.remove(list4[position])
                    var tags: TagsBean? = null
                    listBean.forEach {
                        if (it.tagName == list4[position]) {
                            tags = it
                            return@forEach
                        }
                    }
                    listBean.remove(tags)
                    chooseTagsLabel.setList(list)
                    tagNum.text = list.size.toString() + "/12"
                    if (list.size == 0) {
                        chooseTagsLabel.visibility = View.GONE
                    }
                } else {
                    Toast.Tips("标签最多允许选择12个")
                    chooseTags4.cancelSelectedIndex(position)
                }
            }
        }

        chooseTags5.setOnItemClickListener { position, text ->
            if (list.size<12) {
                if (!chooseTags5.isSelected(position)) {
                    list.remove(list5[position])
                    var tags: TagsBean? = null
                    listBean.forEach {
                        if (it.tagName == list5[position]) {
                            tags = it
                            return@forEach
                        }
                    }
                    listBean.remove(tags)
                    chooseTagsLabel.setList(list)
                    tagNum.text = list.size.toString() + "/12"
                    if (list.size == 0) {
                        chooseTagsLabel.visibility = View.GONE
                    }
                } else {

                    list.add(list5[position])
                    listBean.add(listBean5[position])
                    chooseTagsLabel.setList(list)
                    chooseTagsLabel.visibility = View.VISIBLE
                    tagNum.text = list.size.toString() + "/12"

                }
            }else{
                if (!chooseTags5.isSelected(position)) {
                    list.remove(list5[position])
                    var tags: TagsBean? = null
                    listBean.forEach {
                        if (it.tagName == list5[position]) {
                            tags = it
                            return@forEach
                        }
                    }
                    listBean.remove(tags)
                    chooseTagsLabel.setList(list)
                    tagNum.text = list.size.toString() + "/12"
                    if (list.size == 0) {
                        chooseTagsLabel.visibility = View.GONE
                    }
                } else {
                    Toast.Tips("标签最多允许选择12个")
                    chooseTags5.cancelSelectedIndex(position)
                }
            }
        }

        Click.viewClick(titleRightText).subscribe {
            TagsUtils.deleteAllTags()
            LogUtils.a("测试listBean大小",listBean.size.toString())
            if (listBean.size>0){
                listBean.forEach {
                    TagsUtils.addTag(it)
                }
            }
            finish()
        }
    }

    override fun getTagsRequest(data: TagBean) {
        Log.e("测试tags",":"+data.data.size.toString())
        when(data.data.size){
            1->{
                layout_tag1.visibility=View.VISIBLE
                layout_tag2.visibility=View.GONE
                layout_tag3.visibility=View.GONE
                layout_tag4.visibility=View.GONE
                layout_tag5.visibility=View.GONE
                data.data[0].tags.forEach {
                    list1.add(it.tagName)
                    listBean1.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags1.setList(list1)
                list.forEach {
                    if (list1.contains(it))
                        chooseTags1.setIndexItemSelected(list1.indexOf(it))
                }
                tv_tag1.text=data.data[0].typeName

            }
            2->{
                layout_tag1.visibility=View.VISIBLE
                layout_tag2.visibility=View.VISIBLE
                layout_tag3.visibility=View.GONE
                layout_tag4.visibility=View.GONE
                layout_tag5.visibility=View.GONE
                data.data[0].tags.forEach {
                    list1.add(it.tagName)
                    listBean1.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags1.setList(list1)
                tv_tag1.text=data.data[0].typeName
                list.forEach {
                    if (list1.contains(it))
                        chooseTags1.setIndexItemSelected(list1.indexOf(it))
                }


                data.data[1].tags.forEach {
                    list2.add(it.tagName)
                    listBean2.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags2.setList(list2)
                tv_tag2.text=data.data[1].typeName

                list.forEach {
                    if (list2.contains(it))
                        chooseTags2.setIndexItemSelected(list2.indexOf(it))
                }
            }
            3->{
                layout_tag1.visibility=View.VISIBLE
                layout_tag2.visibility=View.VISIBLE
                layout_tag3.visibility=View.VISIBLE
                layout_tag4.visibility=View.GONE
                layout_tag5.visibility=View.GONE

                data.data[0].tags.forEach {
                    list1.add(it.tagName)
                    listBean1.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags1.setList(list1)
                tv_tag1.text=data.data[0].typeName
                list.forEach {
                    if (list1.contains(it))
                        chooseTags1.setIndexItemSelected(list1.indexOf(it))
                }


                data.data[1].tags.forEach {
                    list2.add(it.tagName)
                    listBean2.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags2.setList(list2)
                tv_tag2.text=data.data[1].typeName
                list.forEach {
                    if (list2.contains(it))
                        chooseTags2.setIndexItemSelected(list2.indexOf(it))
                }


                data.data[2].tags.forEach {
                    list3.add(it.tagName)
                    listBean3.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags3.setList(list3)
                tv_tag3.text=data.data[2].typeName
                list.forEach {
                    if (list3.contains(it))
                        chooseTags3.setIndexItemSelected(list3.indexOf(it))
                }
            }
            4->{
                layout_tag1.visibility=View.VISIBLE
                layout_tag2.visibility=View.VISIBLE
                layout_tag3.visibility=View.VISIBLE
                layout_tag4.visibility=View.VISIBLE
                layout_tag5.visibility=View.GONE

                data.data[0].tags.forEach {
                    list1.add(it.tagName)
                    listBean1.add(TagsBean(it.tagId,it.tagName))
                    LogUtils.a("测试listBean1大小",listBean1.size.toString())
                }
                chooseTags1.setList(list1)
                tv_tag1.text=data.data[0].typeName
                list.forEach {
                    if (list1.contains(it))
                        chooseTags1.setIndexItemSelected(list1.indexOf(it))
                }


                data.data[1].tags.forEach {
                    list2.add(it.tagName)
                    listBean2.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags2.setList(list2)
                tv_tag2.text=data.data[1].typeName
                list.forEach {
                    if (list2.contains(it))
                        chooseTags2.setIndexItemSelected(list2.indexOf(it))
                }


                data.data[2].tags.forEach {
                    list3.add(it.tagName)
                    listBean3.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags3.setList(list3)
                tv_tag3.text=data.data[2].typeName
                list.forEach {
                    if (list3.contains(it))
                        chooseTags3.setIndexItemSelected(list3.indexOf(it))
                }


                data.data[3].tags.forEach {
                    list4.add(it.tagName)
                    listBean4.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags4.setList(list4)
                tv_tag4.text=data.data[3].typeName
                list.forEach {
                    if (list4.contains(it))
                        chooseTags4.setIndexItemSelected(list4.indexOf(it))
                }

            }
            5->{
                layout_tag1.visibility=View.VISIBLE
                layout_tag2.visibility=View.VISIBLE
                layout_tag3.visibility=View.VISIBLE
                layout_tag4.visibility=View.VISIBLE
                layout_tag5.visibility=View.VISIBLE

                data.data[0].tags.forEach {
                    list1.add(it.tagName)
                    listBean1.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags1.setList(list1)
                tv_tag1.text=data.data[0].typeName
                list.forEach {
                    if (list1.contains(it))
                        chooseTags1.setIndexItemSelected(list1.indexOf(it))
                }


                data.data[1].tags.forEach {
                    list2.add(it.tagName)
                    listBean2.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags2.setList(list2)
                tv_tag2.text=data.data[1].typeName
                list.forEach {
                    if (list2.contains(it))
                        chooseTags2.setIndexItemSelected(list2.indexOf(it))
                }


                data.data[2].tags.forEach {
                    list3.add(it.tagName)
                    listBean3.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags3.setList(list3)
                tv_tag3.text=data.data[2].typeName
                list.forEach {
                    if (list3.contains(it))
                        chooseTags3.setIndexItemSelected(list3.indexOf(it))
                }


                data.data[3].tags.forEach {
                    list4.add(it.tagName)
                    listBean4.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags4.setList(list4)
                tv_tag4.text=data.data[3].typeName
                list.forEach {
                    if (list4.contains(it))
                        chooseTags4.setIndexItemSelected(list4.indexOf(it))
                }


                data.data[4].tags.forEach {
                    list5.add(it.tagName)
                    listBean5.add(TagsBean(it.tagId,it.tagName))
                }
                chooseTags5.setList(list5)
                tv_tag5.text=data.data[4].typeName
                list.forEach {
                    if (list5.contains(it))
                        chooseTags5.setIndexItemSelected(list5.indexOf(it))
                }
            }
        }
    }

}