package com.ycwl.servebixin.cn.ui.broker.mvp.bean;

public class BrokerBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"myServiceNum":6}
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
         * myServiceNum : 6
         */

        private int myServiceNum;

        public int getMyServiceNum() {
            return myServiceNum;
        }

        public void setMyServiceNum(int myServiceNum) {
            this.myServiceNum = myServiceNum;
        }
    }
}
