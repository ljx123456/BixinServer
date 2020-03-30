package com.ycwl.servebixin.cn.ui.broker.mvp.body;

public class BrokerSearchKTVBody {

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

    public BrokerSearchKTVBody(String businessName) {
        this.businessName = businessName;
    }
}
