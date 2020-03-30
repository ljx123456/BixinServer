package com.ycwl.servebixin.cn.ui.withdrawal.mvp.body;

public class SetWithdrawPwdBody {

    /**
     * password : 123456
     */

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SetWithdrawPwdBody(String password) {
        this.password = password;
    }
}
