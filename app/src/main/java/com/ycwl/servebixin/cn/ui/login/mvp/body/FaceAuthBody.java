package com.ycwl.servebixin.cn.ui.login.mvp.body;

public class FaceAuthBody {

    /**
     * phone : 18783014637
     * faceUrl : http://pic.bixinyule.com/Ftcfvpg1MT-ExYRxhP9KXx5XyPzz
     */

    private String phone;
    private String faceUrl;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public FaceAuthBody(String phone, String faceUrl) {
        this.phone = phone;
        this.faceUrl = faceUrl;
    }
}
