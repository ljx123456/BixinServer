package com.ycwl.servebixin.cn.ui.yue.mvp.bean;

public class BaoFangTypeBean {
    private int boxFreePrice;
    private int boxTypeId;
    private String boxTypeName;
    private boolean flag;

    public int getBoxFreePrice() {
        return boxFreePrice;
    }

    public void setBoxFreePrice(int boxFreePrice) {
        this.boxFreePrice = boxFreePrice;
    }

    public int getBoxTypeId() {
        return boxTypeId;
    }

    public void setBoxTypeId(int boxTypeId) {
        this.boxTypeId = boxTypeId;
    }

    public String getBoxTypeName() {
        return boxTypeName;
    }

    public void setBoxTypeName(String boxTypeName) {
        this.boxTypeName = boxTypeName;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BaoFangTypeBean(int boxFreePrice, int boxTypeId, String boxTypeName, boolean flag) {
        this.boxFreePrice = boxFreePrice;
        this.boxTypeId = boxTypeId;
        this.boxTypeName = boxTypeName;
        this.flag = flag;
    }

    public BaoFangTypeBean() {
    }
}
