package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class ServeSetOpenBody {

    /**
     * userSkillId : 299
     * skillTypeId : 2
     * state : 0
     */

    private int userSkillId;
    private int skillTypeId;
    private int state;

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ServeSetOpenBody(int userSkillId, int skillTypeId, int state) {
        this.userSkillId = userSkillId;
        this.skillTypeId = skillTypeId;
        this.state = state;
    }
}
