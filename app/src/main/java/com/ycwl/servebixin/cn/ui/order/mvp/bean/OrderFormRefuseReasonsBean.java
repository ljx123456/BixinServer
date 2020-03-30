package com.ycwl.servebixin.cn.ui.order.mvp.bean;

import java.util.List;

public class OrderFormRefuseReasonsBean {


    /**
     * code : 0
     * message : 请求成功.
     * data : [{"code":1,"description":"距离太远"},{"code":2,"description":"临时有事"},{"code":3,"description":"已有其他订单"},{"code":4,"description":"身体不适"}]
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
         * code : 1
         * description : 距离太远
         */

        private int code;
        private String description;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
