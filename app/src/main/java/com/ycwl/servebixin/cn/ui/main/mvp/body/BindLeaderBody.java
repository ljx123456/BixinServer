package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class BindLeaderBody {

    /**
     * userSkillId : 299
     * skillTypeId : 2
     * bixinId : 1
     */

    private int userSkillId;
    private int skillTypeId;
    private String bixinId;

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

    public String getBixinId() {
        return bixinId;
    }

    public void setBixinId(String bixinId) {
        this.bixinId = bixinId;
    }

    public BindLeaderBody(int userSkillId, int skillTypeId, String bixinId) {
        this.userSkillId = userSkillId;
        this.skillTypeId = skillTypeId;
        this.bixinId = bixinId;
    }
}
