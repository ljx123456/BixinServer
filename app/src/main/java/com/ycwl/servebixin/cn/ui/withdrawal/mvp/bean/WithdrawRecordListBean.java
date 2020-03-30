package com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean;

import java.util.List;

public class WithdrawRecordListBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"walletRecodeId":48,"recodePrice":0.01,"applyTime":"2019-03-09 21:10:57","drawCashType":2},{"walletRecodeId":47,"recodePrice":0.01,"applyTime":"2019-03-09 21:09:10","drawCashType":2}]
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
         * walletRecodeId : 48
         * recodePrice : 0.01
         * applyTime : 2019-03-09 21:10:57
         * drawCashType : 2
         */

        private int walletRecodeId;
        private double recodePrice;
        private String applyTime;
        private int drawCashType;

        public int getWalletRecodeId() {
            return walletRecodeId;
        }

        public void setWalletRecodeId(int walletRecodeId) {
            this.walletRecodeId = walletRecodeId;
        }

        public double getRecodePrice() {
            return recodePrice;
        }

        public void setRecodePrice(double recodePrice) {
            this.recodePrice = recodePrice;
        }

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public int getDrawCashType() {
            return drawCashType;
        }

        public void setDrawCashType(int drawCashType) {
            this.drawCashType = drawCashType;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "walletRecodeId=" + walletRecodeId +
                    ", recodePrice=" + recodePrice +
                    ", applyTime='" + applyTime + '\'' +
                    ", drawCashType=" + drawCashType +
                    '}';
        }
    }
}
