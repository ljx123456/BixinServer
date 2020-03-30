package com.ycwl.servebixin.cn.ui.order.mvp.body;

public class GrabOrderListBody {

    /**
     * state : -1
     * pageIndex : 1
     * pageSize : 10
     */

    private String state;
    private int pageIndex;
    private int pageSize;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public GrabOrderListBody(String state, int pageIndex, int pageSize) {
        this.state = state;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
