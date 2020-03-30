package com.ycwl.servebixin.cn.ui.set.mvp.bean;

public class NotiStateBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"messageNotify":1,"systemNotify":1}
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
         * messageNotify : 1
         * systemNotify : 1
         */

        private int messageNotify;
        private int systemNotify;

        public int getMessageNotify() {
            return messageNotify;
        }

        public void setMessageNotify(int messageNotify) {
            this.messageNotify = messageNotify;
        }

        public int getSystemNotify() {
            return systemNotify;
        }

        public void setSystemNotify(int systemNotify) {
            this.systemNotify = systemNotify;
        }
    }
}
