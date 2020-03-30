package com.ycwl.servebixin.cn.ui.order.mvp.bean;

public class ReasonsBean {
    /**
     * code : 1
     * description : 距离太远
     */

    private int code;
    private String description;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReasonsBean(int code, String description, boolean flag) {
        this.code = code;
        this.description = description;
        this.flag = flag;
    }
}
