package com.ycwl.servebixin.cn.ui.login.mvp.body;

public class RegisterDataBody {

    /**
     * phone : 18783014637
     * nickname : bvgdfg
     * sex : 1
     * birthday : 1993-08-21 00:00:00
     * referee : 1
     * pwd : 123465456
     * avatar : http://pic.bixinyule.com/Ftcfvpg1MT-ExYRxhP9KXx5XyPzz
     */

    private String phone;
    private String nickname;
    private int sex;
    private String birthday;
    private String referee;
    private String pwd;
    private String avatar;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RegisterDataBody(String phone, String nickname, int sex, String birthday, String referee, String pwd, String avatar) {
        this.phone = phone;
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday;
        this.referee = referee;
        this.pwd = pwd;
        this.avatar = avatar;
    }
}
