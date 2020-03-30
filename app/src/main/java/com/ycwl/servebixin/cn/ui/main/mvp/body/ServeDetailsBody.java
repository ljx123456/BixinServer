package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class ServeDetailsBody {

    /**
     * userSkillId : 297
     * skillTypeId : 1
     */

    private int userSkillId;
    private int skillTypeId;

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

    public ServeDetailsBody(int userSkillId, int skillTypeId) {
        this.userSkillId = userSkillId;
        this.skillTypeId = skillTypeId;
    }
}
