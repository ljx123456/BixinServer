package com.ycwl.servebixin.cn.ui.yue.mvp.body;

public class YueKTVBody {

    /**
     * pageIndex : 1
     * pageSize : 10
     */

    private String pageIndex;
    private String pageSize;

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public YueKTVBody(String pageIndex, String pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
