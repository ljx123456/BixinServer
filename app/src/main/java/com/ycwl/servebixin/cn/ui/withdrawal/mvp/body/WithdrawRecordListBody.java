package com.ycwl.servebixin.cn.ui.withdrawal.mvp.body;

public class WithdrawRecordListBody {

    /**
     * date : 2019-02-28 00:00:00
     * pageIndex : 1
     * pageSize : 10
     */

    private String date;
    private int pageIndex;
    private int pageSize;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public WithdrawRecordListBody(String date, int pageIndex, int pageSize) {
        this.date = date;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
