package com.ycwl.servebixin.cn.ui.withdrawal.mvp.body;

public class BindWithdrawTypeBody {

    /**
     * type : 1
     * nickname : 放鸡岛开咯
     * drawCashId : 208845615713114361
     */

    private int type;
    private String nickname;
    private String drawCashId;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDrawCashId() {
        return drawCashId;
    }

    public void setDrawCashId(String drawCashId) {
        this.drawCashId = drawCashId;
    }

    public BindWithdrawTypeBody(int type, String nickname, String drawCashId) {
        this.type = type;
        this.nickname = nickname;
        this.drawCashId = drawCashId;
    }
}
