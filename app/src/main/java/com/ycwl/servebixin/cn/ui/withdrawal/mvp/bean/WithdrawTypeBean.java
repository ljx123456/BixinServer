package com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean;

public class WithdrawTypeBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"aliNickname":"支付宝昵称","wechatNickname":"微信昵称"}
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
         * aliNickname : 支付宝昵称
         * wechatNickname : 微信昵称
         */

        private String aliNickname;
        private String wechatNickname;

        public String getAliNickname() {
            return aliNickname;
        }

        public void setAliNickname(String aliNickname) {
            this.aliNickname = aliNickname;
        }

        public String getWechatNickname() {
            return wechatNickname;
        }

        public void setWechatNickname(String wechatNickname) {
            this.wechatNickname = wechatNickname;
        }
    }
}
