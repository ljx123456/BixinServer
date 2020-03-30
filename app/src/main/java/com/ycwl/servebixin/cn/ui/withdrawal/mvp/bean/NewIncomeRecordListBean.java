package com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean;

import java.util.List;

public class NewIncomeRecordListBean {
    private String month;
    private List<IncomeRecordListBean.DataBean> list;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<IncomeRecordListBean.DataBean> getList() {
        return list;
    }

    public void setList(List<IncomeRecordListBean.DataBean> list) {
        this.list = list;
    }

    public NewIncomeRecordListBean(String month, List<IncomeRecordListBean.DataBean> list) {
        this.month = month;
        this.list = list;
    }
}
