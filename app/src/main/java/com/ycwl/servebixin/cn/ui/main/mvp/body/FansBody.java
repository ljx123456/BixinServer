package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class FansBody {

    /**
     * pageIndex : 1
     * pageSize : 10
     */

    private int pageIndex;
    private String pageSize;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public FansBody(int pageIndex, String pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
