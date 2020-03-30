package com.ycwl.servebixin.cn.ui.message.mvp.bean;

public class FeedBackDetailsBean {


    /**
     * code : 0
     * message : 请求成功.
     * data : {"content":"test","handleRes":"你说的对","createTime":"2019-07-30 19:32:58","updateTime":"2019-07-30 19:33:38"}
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
         * content : test
         * handleRes : 你说的对
         * createTime : 2019-07-30 19:32:58
         * updateTime : 2019-07-30 19:33:38
         */

        private String content;
        private String handleRes;
        private String createTime;
        private String updateTime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHandleRes() {
            return handleRes;
        }

        public void setHandleRes(String handleRes) {
            this.handleRes = handleRes;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

}
