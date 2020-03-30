package com.ycwl.servebixin.cn.ui.main.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ycwl.servebixin.cn.R;
import com.ycwl.servebixin.cn.utils.image.ImageLoad;
import com.ycwl.servebixin.cn.view.gridview.DragGridBaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PictureAdapter extends BaseAdapter  {

    private Context context;
    private List<String> list;
//    private int mHidePosition = -1;
    private View[] views;


    public PictureAdapter(Context context){
        this.context=context;
        this.list=new ArrayList<>();
        if (this.list.size()<12) {
            this.views = new View[this.list.size() + 1];
        }else {
            this.views = new View[this.list.size()];
        }
//        this.views=new View[this.list.size()+1];
    }

    public void addList(List<String> list){
        if (this.list.contains(list)){
            return;
        }
        else {
            this.list.addAll(list);
            this.views=new View[this.list.size()+1];
            notifyDataSetChanged();
        }

    }

    public void updateList(List<String> list){
        this.list=list;
        if (list.size()<12) {
            this.views = new View[this.list.size() + 1];
        }else {
            this.views = new View[this.list.size()];
        }
//        this.views=new View[this.list.size()+1];
        notifyDataSetChanged();
    }

    public List getList(){
        return this.list;
    }

    @Override
    public int getCount() {
        if (list.size() == 12) {
            return list.size();
        } else {
            return list.size() + 1;
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (views[position] == null ||views[position].getTag()!=null) {
            viewHolder = new ViewHolder();
            views[position] = LayoutInflater.from(context).inflate(R.layout.item_picture, parent, false);
            viewHolder.addimage = (ImageView) views[position].findViewById(R.id.itemImage);


            views[position].setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) views[position].getTag();
        }
        if (position == list.size()) {
            if (position == 12) {
                viewHolder.addimage.setVisibility(View.GONE);
            }else {
                //加号图标
                viewHolder.addimage.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.add_pic));
                viewHolder.addimage.setVisibility(View.VISIBLE);
            }
        } else {
            //原先的正常数据的显示，操作等
            Glide.with(context)
                    .load(list.get(position))
                    .placeholder(R.mipmap.ic_loading)
                    .dontAnimate()
                    .centerCrop()
                    .into(viewHolder.addimage);
//            ImageLoad.INSTANCE.setImage(list.get(position),);
        }

//        if(position == mHidePosition){
//            views[position].setVisibility(View.INVISIBLE);
//        }
        return views[position];
    }

//    @Override
//    public void reorderItems(int oldPosition, int newPosition) {
//        if (list.size()>2){
//            String temp=list.get(oldPosition);
//            if(oldPosition < newPosition){
//                for(int i=oldPosition; i<newPosition; i++){
//                    Collections.swap(list, i, i+1);
//                }
//            }else if(oldPosition > newPosition){
//                for(int i=oldPosition; i>newPosition; i--){
//                    Collections.swap(list, i, i-1);
//                }
//            }
//            list.set(newPosition,temp);
//        }
//    }
//
//    @Override
//    public void setHideItem(int hidePosition) {
//        this.mHidePosition = hidePosition;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public void removeItem(int removePosition) {
//        list.remove(removePosition);
//        notifyDataSetChanged();
//    }

    class ViewHolder {
        ImageView addimage;

    }
}
