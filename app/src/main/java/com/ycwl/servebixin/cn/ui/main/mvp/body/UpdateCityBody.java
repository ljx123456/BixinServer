package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class UpdateCityBody {

    /**
     * lng : 104.083628
     * lat : 30.824224
     */

    private String lng;
    private String lat;

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

    public UpdateCityBody(String lng, String lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
