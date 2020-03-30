package com.ycwl.servebixin.cn.ui.main.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.ycwl.servebixin.cn.R;
import com.ycwl.servebixin.cn.ui.main.mvp.bean.ServeDetailsBean;
import com.ycwl.servebixin.cn.ui.main.mvp.body.DelKTVBody;
import com.ycwl.servebixin.cn.ui.main.mvp.body.OpenKTVBody;
import com.ycwl.servebixin.cn.ui.main.mvp.presenter.ServeDetailsPresenter;
import com.ycwl.servebixin.cn.utils.dialog.ShowDialog;
import com.ycwl.servebixin.cn.utils.image.ImageLoad;

import java.util.ArrayList;
import java.util.List;

public class ServeDetailsKTVAdapter extends RecyclerView.Adapter implements View.OnClickListener{
    private Context context;
    private List<ServeDetailsBean.DataBean.BusinesssBean> list;
    private Boolean flag;
    private ServeDetailsPresenter presenter;

    public ServeDetailsKTVAdapter(Context context,ServeDetailsPresenter presenter) {
        this.context = context;
        this.presenter=presenter;
        this.list = new ArrayList<>();
        this.flag=false;
    }

    //添加数据
    public void addList(List<ServeDetailsBean.DataBean.BusinesssBean> list) {
        if (this.list.containsAll(list)) {
            return;
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    //更新数据
    public void updateList(List<ServeDetailsBean.DataBean.BusinesssBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void clear() {
        this.list.clear();
        notifyDataSetChanged();
    }

    //是否编辑
    public void edit(Boolean flag){
        this.flag=flag;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (ServeDetailsBean.DataBean.BusinesssBean) v.getTag());
        }
    }


    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, ServeDetailsBean.DataBean.BusinesssBean data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_serve_details_ktv, parent,false);
        ViewHodler vh = new ViewHodler(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHodler mHodler = (ViewHodler) holder;
        ImageLoad.INSTANCE.setUserHead(list.get(position).getBusinessImg(),mHodler.img);
        mHodler.name.setText(list.get(position).getBusinessName());
        mHodler.address.setText(list.get(position).getBusinessAddress());
        if (list.get(position).getEnableField()==0){
            mHodler.aSwitch.setChecked(false);
        }else {
            mHodler.aSwitch.setChecked(true);
        }
        if (flag){
            mHodler.aSwitch.setVisibility(View.GONE);
            mHodler.del.setVisibility(View.VISIBLE);
        }else {
            mHodler.aSwitch.setVisibility(View.VISIBLE);
            mHodler.del.setVisibility(View.GONE);
        }
        mHodler.del.setOnClickListener(v -> {
            ShowDialog.showCustomDialogNoTitle(context, "是否确认删除该KTV？", "确认", "取消", (dialog, which) -> {
                if (which==DialogInterface.BUTTON_POSITIVE){
                    dialog.dismiss();
                    presenter.delKTV(new DelKTVBody(list.get(position).getBusinessId()));
                    list.remove(position);
                    notifyDataSetChanged();
                }else if (which==DialogInterface.BUTTON_NEGATIVE){
                    dialog.dismiss();
                }
            });
        });

        mHodler.aSwitch.setOnClickListener(v -> {
            if (mHodler.aSwitch.isChecked()){
                presenter.openKTV(new OpenKTVBody(list.get(position).getBusinessId()+"","1"));
            }else {
                presenter.openKTV(new OpenKTVBody(list.get(position).getBusinessId()+"","0"));
            }
        });
        mHodler.itemView.setTag(list.get(position));

    }



    class ViewHodler extends RecyclerView.ViewHolder {

        private RoundedImageView img;
        private TextView name,address,del;
        private Switch aSwitch;

        public ViewHodler(View itemView) {
            super(itemView);
            img=(RoundedImageView)itemView.findViewById(R.id.item_serve_details_ktv_img);
            name= (TextView) itemView.findViewById(R.id.item_serve_details_ktv_name);
            address = (TextView) itemView.findViewById(R.id.item_serve_details_ktv_address);
            del = (TextView) itemView.findViewById(R.id.item_serve_details_ktv_del);
            aSwitch = (Switch) itemView.findViewById(R.id.switchKTV);

        }
    }
}