package com.ycwl.servebixin.cn.ui.set.mvp.body;

public class BlackListBody {

    /**
     * lng : 105.83
     * lat : 32.43
     * pageIndex : 1
     * pageSize : 10
     */

    private String lng;
    private String lat;
    private int pageIndex;
    private int pageSize;

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

    public BlackListBody(String lng, String lat, int pageIndex, int pageSize) {
        this.lng = lng;
        this.lat = lat;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
