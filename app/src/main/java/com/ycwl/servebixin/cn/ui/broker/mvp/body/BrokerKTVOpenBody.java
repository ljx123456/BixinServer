package com.ycwl.servebixin.cn.ui.broker.mvp.body;

public class BrokerKTVOpenBody {

    /**
     * fieldId : 326
     * switchState : 1
     */

    private String fieldId;
    private String switchState;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getSwitchState() {
        return switchState;
    }

    public void setSwitchState(String switchState) {
        this.switchState = switchState;
    }

    public BrokerKTVOpenBody(String fieldId, String switchState) {
        this.fieldId = fieldId;
        this.switchState = switchState;
    }
}
