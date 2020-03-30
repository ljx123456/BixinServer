package com.ycwl.servebixin.cn.ui.login.mvp.body;

public class ResetPwdBody {

    /**
     * phone : 18783014637
     * pwd : 12345678
     */

    private String phone;
    private String pwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ResetPwdBody(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
