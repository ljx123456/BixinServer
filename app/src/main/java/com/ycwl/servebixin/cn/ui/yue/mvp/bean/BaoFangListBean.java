package com.ycwl.servebixin.cn.ui.yue.mvp.bean;

public class BaoFangListBean {
    private int boxId;
    private String boxName;
    private int isIdle;
    private boolean flag;

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public int getIsIdle() {
        return isIdle;
    }

    public void setIsIdle(int isIdle) {
        this.isIdle = isIdle;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BaoFangListBean(int boxId, String boxName, int isIdle, boolean flag) {
        this.boxId = boxId;
        this.boxName = boxName;
        this.isIdle = isIdle;
        this.flag = flag;
    }

    public BaoFangListBean() {
    }
}
