package com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean;

import java.util.List;

public class IncomeRecordListBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"walletRecodeId":2,"recodePrice":500,"profitType":1,"businessName":"世纪怡都","createTime":"2019-03-06 16:37:55","wineCountPrice":3000,"recodeRole":3}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * walletRecodeId : 2
         * recodePrice : 500
         * profitType : 1
         * businessName : 世纪怡都
         * createTime : 2019-03-06 16:37:55
         * wineCountPrice : 3000
         * recodeRole : 3
         */

        private int walletRecodeId;
        private String recodePrice;
        private int profitType;
        private String businessName;
        private String createTime;
        private String wineCountPrice;
        private int recodeRole;

        public int getWalletRecodeId() {
            return walletRecodeId;
        }

        public void setWalletRecodeId(int walletRecodeId) {
            this.walletRecodeId = walletRecodeId;
        }

        public String getRecodePrice() {
            return recodePrice;
        }

        public void setRecodePrice(String recodePrice) {
            this.recodePrice = recodePrice;
        }

        public int getProfitType() {
            return profitType;
        }

        public void setProfitType(int profitType) {
            this.profitType = profitType;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getWineCountPrice() {
            return wineCountPrice;
        }

        public void setWineCountPrice(String wineCountPrice) {
            this.wineCountPrice = wineCountPrice;
        }

        public int getRecodeRole() {
            return recodeRole;
        }

        public void setRecodeRole(int recodeRole) {
            this.recodeRole = recodeRole;
        }
    }
}
