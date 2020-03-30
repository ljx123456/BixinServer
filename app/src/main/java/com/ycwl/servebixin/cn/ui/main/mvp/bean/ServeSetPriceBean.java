package com.ycwl.servebixin.cn.ui.main.mvp.bean;

public class ServeSetPriceBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"userSkillId":"123"}
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
         * userSkillId : 123
         */

        private String userSkillId;

        public String getUserSkillId() {
            return userSkillId;
        }

        public void setUserSkillId(String userSkillId) {
            this.userSkillId = userSkillId;
        }
    }
}
