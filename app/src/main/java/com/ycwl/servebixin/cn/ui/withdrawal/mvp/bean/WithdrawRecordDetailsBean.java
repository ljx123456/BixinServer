package com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean;

public class WithdrawRecordDetailsBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"orderNo":"60201903091055258310145","recodePrice":0.01,"servicePrice":0,"applyTime":"2019-03-09 21:10:57","arrivalAccountTime":"2019-03-16 16:59:37","drawCashType":2,"drawCashAccount":"2088802738514206"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderNo : 60201903091055258310145
         * recodePrice : 0.01
         * servicePrice : 0
         * applyTime : 2019-03-09 21:10:57
         * arrivalAccountTime : 2019-03-16 16:59:37
         * drawCashType : 2
         * drawCashAccount : 2088802738514206
         */

        private String orderNo;
        private double recodePrice;
        private int servicePrice;
        private String applyTime;
        private String arrivalAccountTime;
        private int drawCashType;
        private String drawCashAccount;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public double getRecodePrice() {
            return recodePrice;
        }

        public void setRecodePrice(double recodePrice) {
            this.recodePrice = recodePrice;
        }

        public int getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(int servicePrice) {
            this.servicePrice = servicePrice;
        }

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public String getArrivalAccountTime() {
            return arrivalAccountTime;
        }

        public void setArrivalAccountTime(String arrivalAccountTime) {
            this.arrivalAccountTime = arrivalAccountTime;
        }

        public int getDrawCashType() {
            return drawCashType;
        }

        public void setDrawCashType(int drawCashType) {
            this.drawCashType = drawCashType;
        }

        public String getDrawCashAccount() {
            return drawCashAccount;
        }

        public void setDrawCashAccount(String drawCashAccount) {
            this.drawCashAccount = drawCashAccount;
        }
    }
}
