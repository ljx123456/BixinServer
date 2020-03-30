package com.ycwl.servebixin.cn.ui.order.mvp.body;

public class OrderFormDrinkBody {

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

    public OrderFormDrinkBody(String orderServiceNo) {
        this.orderServiceNo = orderServiceNo;
    }
}
