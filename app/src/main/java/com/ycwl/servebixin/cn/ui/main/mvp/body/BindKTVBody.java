package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class BindKTVBody {

    /**
     * userSkillId : 299
     * skillTypeId : 1
     * businessId : 15
     */

    private int userSkillId;
    private int skillTypeId;
    private String businessId;

    public int getUserSkillId() {
        return userSkillId;
    }

    public void setUserSkillId(int userSkillId) {
        this.userSkillId = userSkillId;
    }

    public int getSkillTypeId() {
        return skillTypeId;
    }

    public void setSkillTypeId(int skillTypeId) {
        this.skillTypeId = skillTypeId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public BindKTVBody(int userSkillId, int skillTypeId, String businessId) {
        this.userSkillId = userSkillId;
        this.skillTypeId = skillTypeId;
        this.businessId = businessId;
    }
}
