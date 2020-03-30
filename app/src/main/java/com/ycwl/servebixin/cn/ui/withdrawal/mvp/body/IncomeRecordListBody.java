package com.ycwl.servebixin.cn.ui.withdrawal.mvp.body;

public class IncomeRecordListBody {

    /**
     * date : 2019-02-28 00:00:00
     * profitType :
     * recodeRole :
     * pageIndex : 1
     * pageSize : 10
     */

    private String date;
    private String profitType;
    private String recodeRole;
    private String pageIndex;
    private String pageSize;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getRecodeRole() {
        return recodeRole;
    }

    public void setRecodeRole(String recodeRole) {
        this.recodeRole = recodeRole;
    }

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

    public IncomeRecordListBody(String date, String profitType, String recodeRole, String pageIndex, String pageSize) {
        this.date = date;
        this.profitType = profitType;
        this.recodeRole = recodeRole;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
