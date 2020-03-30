package com.ycwl.servebixin.cn.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ycwl.servebixin.cn.R;
import com.ycwl.servebixin.cn.db.user;
import com.ycwl.servebixin.cn.ui.main.mvp.bean.OccupationBean;

import java.util.List;

public class OccupationAdapter extends BaseAdapter {
    private List<OccupationBean.DataBean.UserOccupationsBean> list;
    private Context mContext;

    public OccupationAdapter(List<OccupationBean.DataBean.UserOccupationsBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_occupation, null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.occupationName);
            holder.choose = convertView.findViewById(R.id.occupationChoose);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getOccupationName());
        if (user.INSTANCE.getOccupation().equals(list.get(position).getOccupationName())){
          holder.choose.setVisibility(View.VISIBLE);
        }else {
            holder.choose.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView name;
        TextView choose;
    }
}