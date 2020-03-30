package com.ycwl.servebixin.cn.ui.broker.mvp.bean;

import java.util.List;

public class ApplyBrokerRecordBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"auditId":17,"auditState":1,"startTime":"2019-03-18 21:14:20","createTime":"2019-03-18 21:14:18","updateTime":"2019-03-18 21:14:18"}]
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
         * auditId : 17
         * auditState : 1
         * startTime : 2019-03-18 21:14:20
         * createTime : 2019-03-18 21:14:18
         * updateTime : 2019-03-18 21:14:18
         */

        private int auditId;
        private int auditState;
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
