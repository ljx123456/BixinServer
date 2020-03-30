package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class ServeSetPriceBody {

    /**
     * userSkillId : -1
     * skillTypeId : 2
     * userSkillPrice : 100
     */

    private String userSkillId;
    private String skillTypeId;
    private String userSkillPrice;

    public String getUserSkillId() {
        return userSkillId;
    }

    public void setUserSkillId(String userSkillId) {
        this.userSkillId = userSkillId;
    }

    public String getSkillTypeId() {
        return skillTypeId;
    }

    public void setSkillTypeId(String skillTypeId) {
        this.skillTypeId = skillTypeId;
    }

    public String getUserSkillPrice() {
        return userSkillPrice;
    }

    public void setUserSkillPrice(String userSkillPrice) {
        this.userSkillPrice = userSkillPrice;
    }

    public ServeSetPriceBody(String userSkillId, String skillTypeId, String userSkillPrice) {
        this.userSkillId = userSkillId;
        this.skillTypeId = skillTypeId;
        this.userSkillPrice = userSkillPrice;
    }
}
