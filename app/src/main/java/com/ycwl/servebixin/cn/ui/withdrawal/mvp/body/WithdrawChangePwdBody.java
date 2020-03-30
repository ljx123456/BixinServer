package com.ycwl.servebixin.cn.ui.withdrawal.mvp.body;

public class WithdrawChangePwdBody {

    /**
     * newPassword : 123456
     */

    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public WithdrawChangePwdBody(String newPassword) {
        this.newPassword = newPassword;
    }

}
