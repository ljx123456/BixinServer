package com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean;

public class AuthInfoBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"authInfo":"app_name=mc&auth_type=AUTHACCOUNT&apiname=com.alipay.account.auth&biz_type=openservice&product_id=APP_FAST_LOGIN&scope=kuaijie&pid=2088721336941972&target_id=1557901662821&app_id=2019041763889588&sign_type=RSA2&methodname=alipay.open.auth.sdk.code.get&sign=DPmxcTAbRwz%2Fez7Ecoxt9eGj7sql6KEaLvSmN4SmNCEIpTpDAd9VNcimO0Fgcd7j3HVKHORaIyJHfuM2VlPIFlCuQ3vWwivGkuE87BdNk%2BJEo73sBIRVjAz1fqw4jau9mToUMP57CWL1mT5oD1qrc1vNGA%2Bkz7X1PlPqm3AXPzvBotWa%2BxoJC5OTQdY2BJ6Bhlze2AWaChVwsrKTFtpyD3%2B9Har1v8k9BuMHi5sQ%2FLiZb7QWBIMYVu6qF3m%2F%2FV9nbWgbmVQYiY%2FF9WILUxLd5doHVvuR%2BGkTQ%2F3wVz0z%2BLWnonJlfQ8h2MAEu4JAR95QppW3ou92W8l53z8ZB4BJHQ%3D%3D"}
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
         * authInfo : app_name=mc&auth_type=AUTHACCOUNT&apiname=com.alipay.account.auth&biz_type=openservice&product_id=APP_FAST_LOGIN&scope=kuaijie&pid=2088721336941972&target_id=1557901662821&app_id=2019041763889588&sign_type=RSA2&methodname=alipay.open.auth.sdk.code.get&sign=DPmxcTAbRwz%2Fez7Ecoxt9eGj7sql6KEaLvSmN4SmNCEIpTpDAd9VNcimO0Fgcd7j3HVKHORaIyJHfuM2VlPIFlCuQ3vWwivGkuE87BdNk%2BJEo73sBIRVjAz1fqw4jau9mToUMP57CWL1mT5oD1qrc1vNGA%2Bkz7X1PlPqm3AXPzvBotWa%2BxoJC5OTQdY2BJ6Bhlze2AWaChVwsrKTFtpyD3%2B9Har1v8k9BuMHi5sQ%2FLiZb7QWBIMYVu6qF3m%2F%2FV9nbWgbmVQYiY%2FF9WILUxLd5doHVvuR%2BGkTQ%2F3wVz0z%2BLWnonJlfQ8h2MAEu4JAR95QppW3ou92W8l53z8ZB4BJHQ%3D%3D
         */

        private String authInfo;

        public String getAuthInfo() {
            return authInfo;
        }

        public void setAuthInfo(String authInfo) {
            this.authInfo = authInfo;
        }
    }
}
