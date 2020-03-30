package com.ycwl.servebixin.cn.ui.broker.mvp.body;

public class BrokerServeBody {

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

    public BrokerServeBody(String pageIndex, String pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
