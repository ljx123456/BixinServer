package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class SearchKTVBody {

    /**
     * businessName : 五角
     */

    private String businessName;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public SearchKTVBody(String businessName) {
        this.businessName = businessName;
    }
}
