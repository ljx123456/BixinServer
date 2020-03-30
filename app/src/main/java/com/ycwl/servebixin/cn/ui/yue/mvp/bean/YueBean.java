package com.ycwl.servebixin.cn.ui.yue.mvp.bean;

public class YueBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"orderCode":"df381cd57843a633061fb3db36967f0a067e8e571f2ec28c9bae180e6c133091"}
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
         * orderCode : df381cd57843a633061fb3db36967f0a067e8e571f2ec28c9bae180e6c133091
         */

        private String orderCode;

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }
    }
}
