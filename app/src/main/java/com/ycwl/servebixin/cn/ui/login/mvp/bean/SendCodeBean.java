package com.ycwl.servebixin.cn.ui.login.mvp.bean;

public class SendCodeBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"message":"验证码1234","isSuccess":true}
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
         * message : 验证码1234
         * isSuccess : true
         */

        private String message;
        private boolean isSuccess;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }
    }
}
