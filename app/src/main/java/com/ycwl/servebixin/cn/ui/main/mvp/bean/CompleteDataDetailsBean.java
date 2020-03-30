package com.ycwl.servebixin.cn.ui.main.mvp.bean;

import java.util.List;

public class CompleteDataDetailsBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"datas":[{"type":1,"url":"http://pic.bixinyule.com/b8d72629-d5f0-4e8e-9105-e1a7d102f409"}],"auditState":1,"startTime":"2019-07-12 13:03:14","createTime":"2019-07-12 12:58:26","updateTime":"2019-07-12 13:03:18"}
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
         * datas : [{"type":1,"url":"http://pic.bixinyule.com/b8d72629-d5f0-4e8e-9105-e1a7d102f409"}]
         * auditState : 1
         * startTime : 2019-07-12 13:03:14
         * createTime : 2019-07-12 12:58:26
         * updateTime : 2019-07-12 13:03:18
         */

        private int auditState;
        private String startTime;
        private String createTime;
        private String updateTime;
        private String description;
        private List<DatasBean> datas;

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

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public static class DatasBean {
            /**
             * type : 1
             * url : http://pic.bixinyule.com/b8d72629-d5f0-4e8e-9105-e1a7d102f409
             */

            private int type;
            private String url;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
