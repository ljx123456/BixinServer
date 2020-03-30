package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class LocationBody {

    /**
     * lng : 60
     * lat : 60
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

    public LocationBody(String lng, String lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
