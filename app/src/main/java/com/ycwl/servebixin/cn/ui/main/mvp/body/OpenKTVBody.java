package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class OpenKTVBody {

    /**
     * businessId : 7
     * state : 0
     */

    private String businessId;
    private String state;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public OpenKTVBody(String businessId, String state) {
        this.businessId = businessId;
        this.state = state;
    }
}
