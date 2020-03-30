package com.ycwl.servebixin.cn.ui.order.mvp.body;

public class OrderFormListBody {

    /**
     * orderState : 3
     * pageIndex : 1
     * pageSize : 10
     */

    private String orderState;
    private int pageIndex;
    private int pageSize;

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
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

    public OrderFormListBody(String orderState, int pageIndex, int pageSize) {
        this.orderState = orderState;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
