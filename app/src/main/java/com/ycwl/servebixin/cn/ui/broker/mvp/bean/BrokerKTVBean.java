package com.ycwl.servebixin.cn.ui.broker.mvp.bean;

import java.util.List;

public class BrokerKTVBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"fieldId":314,"auditState":3,"enableField":0,"createTime":"2019-03-06 15:45:50","businessName":"好乐星KTV(南门店)","businessAddress":"成都市武侯区人民南路四段53号附1号(近肿瘤医院)","businessImg":"http://e.hiphotos.baidu.com/bainuo/crop%3D0%2C5%2C690%2C418%3Bw%3D470%3Bq%3D80/sign=b10b669e063b5bb5aa987abe0be3f90c/d01373f082025aaf3d3019fefeedab64034f1a80.jpg"},{"fieldId":316,"auditState":3,"enableField":0,"createTime":"2019-03-06 15:45:50","businessName":"梦乐美纯K量贩KTV","businessAddress":"成都市金牛区五福桥东路9号9幢1单元2,3楼(龙湖北城天街)","businessImg":"http://e.hiphotos.baidu.com/bainuo/crop%3D0%2C5%2C690%2C418%3Bw%3D470%3Bq%3D80/sign=b10b669e063b5bb5aa987abe0be3f90c/d01373f082025aaf3d3019fefeedab64034f1a80.jpg"}]
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
         * fieldId : 314
         * auditState : 3
         * enableField : 0
         * createTime : 2019-03-06 15:45:50
         * businessName : 好乐星KTV(南门店)
         * businessAddress : 成都市武侯区人民南路四段53号附1号(近肿瘤医院)
         * businessImg : http://e.hiphotos.baidu.com/bainuo/crop%3D0%2C5%2C690%2C418%3Bw%3D470%3Bq%3D80/sign=b10b669e063b5bb5aa987abe0be3f90c/d01373f082025aaf3d3019fefeedab64034f1a80.jpg
         */

        private int fieldId;
        private int auditState;
        private int enableField;
        private String createTime;
        private String businessName;
        private String businessAddress;
        private String businessImg;

        public int getFieldId() {
            return fieldId;
        }

        public void setFieldId(int fieldId) {
            this.fieldId = fieldId;
        }

        public int getAuditState() {
            return auditState;
        }

        public void setAuditState(int auditState) {
            this.auditState = auditState;
        }

        public int getEnableField() {
            return enableField;
        }

        public void setEnableField(int enableField) {
            this.enableField = enableField;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getBusinessAddress() {
            return businessAddress;
        }

        public void setBusinessAddress(String businessAddress) {
            this.businessAddress = businessAddress;
        }

        public String getBusinessImg() {
            return businessImg;
        }

        public void setBusinessImg(String businessImg) {
            this.businessImg = businessImg;
        }
    }
}
