package com.ycwl.servebixin.cn.ui.order.mvp.bean;

public class OrderDrinkCodeBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"wineCode":"553e05cffaa79d82c3af5526a16cb5f5e9443d7bc53521c955803dc463e83720"}
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
         * wineCode : 553e05cffaa79d82c3af5526a16cb5f5e9443d7bc53521c955803dc463e83720
         */

        private String wineCode;

        public String getWineCode() {
            return wineCode;
        }

        public void setWineCode(String wineCode) {
            this.wineCode = wineCode;
        }
    }
}
