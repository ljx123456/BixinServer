package com.ycwl.servebixin.cn.ui.login.mvp.body;

public class ByPwdBody {
    private String phone;
    private String pwd;

    public ByPwdBody(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
