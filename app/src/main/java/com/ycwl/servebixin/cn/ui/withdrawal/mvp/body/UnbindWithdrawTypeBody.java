package com.ycwl.servebixin.cn.ui.withdrawal.mvp.body;

public class UnbindWithdrawTypeBody {

    /**
     * password : 123456
     * type : 1
     */

    private String password;
    private int type;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UnbindWithdrawTypeBody(String password, int type) {
        this.password = password;
        this.type = type;
    }
}
