package com.ycwl.servebixin.cn.ui.order.mvp.body;

public class OrderFormAcceptBody {
    /**
     * orderServiceNo : 3000201903184330095458103
     */

    private String orderServiceNo;

    public String getOrderServiceNo() {
        return orderServiceNo;
    }

    public void setOrderServiceNo(String orderServiceNo) {
        this.orderServiceNo = orderServiceNo;
    }

    public OrderFormAcceptBody(String orderServiceNo) {
        this.orderServiceNo = orderServiceNo;
    }
}
