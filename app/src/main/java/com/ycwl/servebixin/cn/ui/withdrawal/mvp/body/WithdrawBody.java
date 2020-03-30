package com.ycwl.servebixin.cn.ui.withdrawal.mvp.body;

public class WithdrawBody {
    /**
     * payType : 2
     * drawCashMoney : 1
     * password : 123456
     * authType : 2
     * faceUrl : http://pic.bixinyule.com/Ftcfvpg1MT-ExYRxhP9KXx5XyPzz
     */

    private String payType;
    private String drawCashMoney;
    private String password;
    private String authType;
    private String faceUrl;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDrawCashMoney() {
        return drawCashMoney;
    }

    public void setDrawCashMoney(String drawCashMoney) {
        this.drawCashMoney = drawCashMoney;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public WithdrawBody(String payType, String drawCashMoney, String password, String authType, String faceUrl) {
        this.payType = payType;
        this.drawCashMoney = drawCashMoney;
        this.password = password;
        this.authType = authType;
        this.faceUrl = faceUrl;
    }
}
