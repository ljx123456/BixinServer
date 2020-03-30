package com.ycwl.servebixin.cn.ui.login.mvp.body;

public class CheckNameBody {


    /**
     * nickname : 你好555
     */

    private String nickname;

    public String getName() {
        return nickname;
    }

    public void setName(String name) {
        this.nickname = name;
    }

    public CheckNameBody(String name) {
        this.nickname = name;
    }
}
