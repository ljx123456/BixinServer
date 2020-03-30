package com.ycwl.servebixin.cn.ui.main.mvp.bean;

public class RealnameDetailsBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"realName":"张**","idCardNo":"******31543154****","auditState":1,"description":"实名审核通过","startTime":"2019-07-12 13:03:25","createTime":"2019-07-12 12:47:55","updateTime":"2019-07-12 16:03:12"}
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
         * realName : 张**
         * idCardNo : ******31543154****
         * auditState : 1
         * description : 实名审核通过
         * startTime : 2019-07-12 13:03:25
         * createTime : 2019-07-12 12:47:55
         * updateTime : 2019-07-12 16:03:12
         */

        private String realName;
        private String idCardNo;
        private int auditState;
        private String description;
        private String startTime;
        private String createTime;
        private String updateTime;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
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
