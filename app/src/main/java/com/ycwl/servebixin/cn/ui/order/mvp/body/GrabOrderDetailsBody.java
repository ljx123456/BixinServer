package com.ycwl.servebixin.cn.ui.order.mvp.body;

public class GrabOrderDetailsBody {

    /**
     * inviteId : 1
     */

    private String inviteId;


    public String getInviteId() {
        return inviteId;
    }

    public void setInviteId(String inviteId) {
        this.inviteId = inviteId;
    }

    public GrabOrderDetailsBody(String inviteId) {
        this.inviteId = inviteId;
    }
}
