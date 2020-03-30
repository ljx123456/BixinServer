package com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean;


import java.math.BigDecimal;

public class IncomeBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"userBalance":2187.77,"cumulativeBalance":2388,"yesterdayProfit":0,"lastWeekProfit":0,"thisMonthProfit":1000,"enableFaceID":1,"payType":1,"drawCashNickname":"阿里号"}
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
         * userBalance : 2187.77
         * cumulativeBalance : 2388
         * yesterdayProfit : 0
         * lastWeekProfit : 0
         * thisMonthProfit : 1000
         * enableFaceID : 1
         * payType : 1
         * drawCashNickname : 阿里号
         */

        private BigDecimal userBalance=new BigDecimal("0.00");
        private String cumulativeBalance;
        private BigDecimal yesterdayProfit=new BigDecimal("0.00");
        private BigDecimal lastWeekProfit=new BigDecimal("0.00");
        private BigDecimal thisMonthProfit=new BigDecimal("0.00");
        private int enableFaceID;
        private int payType;
        private String drawCashNickname;

        public BigDecimal getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(BigDecimal userBalance) {
            this.userBalance = userBalance;
        }

        public String getCumulativeBalance() {
            return cumulativeBalance;
        }

        public void setCumulativeBalance(String cumulativeBalance) {
            this.cumulativeBalance = cumulativeBalance;
        }

        public BigDecimal getYesterdayProfit() {
            return yesterdayProfit;
        }

        public void setYesterdayProfit(BigDecimal yesterdayProfit) {
            this.yesterdayProfit = yesterdayProfit;
        }

        public BigDecimal getLastWeekProfit() {
            return lastWeekProfit;
        }

        public void setLastWeekProfit(BigDecimal lastWeekProfit) {
            this.lastWeekProfit = lastWeekProfit;
        }

        public BigDecimal getThisMonthProfit() {
            return thisMonthProfit;
        }

        public void setThisMonthProfit(BigDecimal thisMonthProfit) {
            this.thisMonthProfit = thisMonthProfit;
        }

        public int getEnableFaceID() {
            return enableFaceID;
        }

        public void setEnableFaceID(int enableFaceID) {
            this.enableFaceID = enableFaceID;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getDrawCashNickname() {
            return drawCashNickname;
        }

        public void setDrawCashNickname(String drawCashNickname) {
            this.drawCashNickname = drawCashNickname;
        }
    }
}
