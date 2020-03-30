package com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean;

import java.util.List;

public class NewWithdrawRecordListBean {
    private String month;
    private List<WithdrawRecordListBean.DataBean> list;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<WithdrawRecordListBean.DataBean> getList() {
        return list;
    }

    public void setList(List<WithdrawRecordListBean.DataBean> list) {
        this.list = list;
    }

    public NewWithdrawRecordListBean(String month, List<WithdrawRecordListBean.DataBean> list) {
        this.month = month;
        this.list = list;
    }
}
