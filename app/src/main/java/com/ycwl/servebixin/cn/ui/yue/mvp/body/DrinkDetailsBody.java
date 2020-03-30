package com.ycwl.servebixin.cn.ui.yue.mvp.body;

public class DrinkDetailsBody {

    /**
     * businessWineId : 123
     */

    private String businessWineId;

    public String getBusinessWineId() {
        return businessWineId;
    }

    public void setBusinessWineId(String businessWineId) {
        this.businessWineId = businessWineId;
    }

    public DrinkDetailsBody(String businessWineId) {
        this.businessWineId = businessWineId;
    }
}
