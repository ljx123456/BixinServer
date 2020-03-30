package com.ycwl.servebixin.cn.ui.order.mvp.body;

public class OrderFormDetailsBody {

    /**
     * orderServiceNo : 3000201903184330095939975
     */

    private String orderServiceNo;

    public String getOrderServiceNo() {
        return orderServiceNo;
    }

    public void setOrderServiceNo(String orderServiceNo) {
        this.orderServiceNo = orderServiceNo;
    }

    public OrderFormDetailsBody(String orderServiceNo) {
        this.orderServiceNo = orderServiceNo;
    }
}
