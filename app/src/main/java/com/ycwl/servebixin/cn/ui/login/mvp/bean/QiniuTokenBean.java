package com.ycwl.servebixin.cn.ui.login.mvp.bean;

public class QiniuTokenBean {

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
         * token : a-eT7dVVA0PgfHo0rgGL1NMVtLLSqe5lyiVtU8YE:Q60VIBMHkL9forfgVNFR5_HmtZI=:eyJzY29wZSI6ImJpeGlueXVsZSIsImRlYWRsaW5lIjoxNTM2MDM0MzA4fQ==
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
