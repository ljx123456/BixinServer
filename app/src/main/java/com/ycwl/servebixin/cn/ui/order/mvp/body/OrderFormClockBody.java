package com.ycwl.servebixin.cn.ui.order.mvp.body;

public class OrderFormClockBody {

    /**
     * orderServiceNo : 3000201904270140727898108
     * lng : 123.3
     * lat : 23.3
     */

    private String orderServiceNo;
    private String lng;
    private String lat;

    public String getOrderServiceNo() {
        return orderServiceNo;
    }

    public void setOrderServiceNo(String orderServiceNo) {
        this.orderServiceNo = orderServiceNo;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public OrderFormClockBody(String orderServiceNo, String lng, String lat) {
        this.orderServiceNo = orderServiceNo;
        this.lng = lng;
        this.lat = lat;
    }
}
