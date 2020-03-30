package com.ycwl.servebixin.cn.ui.broker.mvp.bean;

public class ApplyBrokerRecordDetailsBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"auditId":17,"auditState":1,"description":"待审核","startTime":"2019-03-18 21:14:20","createTime":"2019-03-05 16:55:00","updateTime":"2019-03-12 10:43:46"}
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
         * auditId : 17
         * auditState : 1
         * description : 待审核
         * startTime : 2019-03-18 21:14:20
         * createTime : 2019-03-05 16:55:00
         * updateTime : 2019-03-12 10:43:46
         */

        private int auditId;
        private int auditState;
        private String description;
        private String startTime;
        private String createTime;
        private String updateTime;

        public int getAuditId() {
            return auditId;
        }

        public void setAuditId(int auditId) {
            this.auditId = auditId;
        }

        public int getAuditState() {
            return auditState;
        }

        public void setAuditState(int auditState) {
            this.auditState = auditState;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
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
