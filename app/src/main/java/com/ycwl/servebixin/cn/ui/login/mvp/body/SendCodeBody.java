package com.ycwl.servebixin.cn.ui.login.mvp.body;

public class SendCodeBody {


    private String phone;
    private String token;
    private int type;

    public SendCodeBody(String phone, String token, int type) {
        this.phone = phone;
        this.token = token;
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
