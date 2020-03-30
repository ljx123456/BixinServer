package com.ycwl.servebixin.cn.ui.order.mvp.body;

public class OrderFormRefuseBody {

    /**
     * orderServiceNo : 3000201903184330095458103
     * reasonsForRefusal : 1
     */

    private String orderServiceNo;
    private int reasonsForRefusal;

    public String getOrderServiceNo() {
        return orderServiceNo;
    }

    public void setOrderServiceNo(String orderServiceNo) {
        this.orderServiceNo = orderServiceNo;
    }

    public int getReasonsForRefusal() {
        return reasonsForRefusal;
    }

    public void setReasonsForRefusal(int reasonsForRefusal) {
        this.reasonsForRefusal = reasonsForRefusal;
    }

    public OrderFormRefuseBody(String orderServiceNo, int reasonsForRefusal) {
        this.orderServiceNo = orderServiceNo;
        this.reasonsForRefusal = reasonsForRefusal;
    }
}
