package com.ycwl.servebixin.cn.ui.main.mvp.bean;

import java.util.List;

public class UpdateBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"packageType":1,"packageVersion":5,"packageNo":"1.5.0.0","packageUrl":"http://www.baidu.com/325.apk","description":["更新1","更新2","更新3"],"isForce":1,"isShowSensitive":0}
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
         * packageType : 1
         * packageVersion : 5
         * packageNo : 1.5.0.0
         * packageUrl : http://www.baidu.com/325.apk
         * description : ["更新1","更新2","更新3"]
         * isForce : 1
         * isShowSensitive : 0
         */

        private int packageType;
        private int packageVersion;
        private String packageNo;
        private String packageUrl;
        private int isForce;
        private int isShowSensitive;
        private List<String> description;

        public int getPackageType() {
            return packageType;
        }

        public void setPackageType(int packageType) {
            this.packageType = packageType;
        }

        public int getPackageVersion() {
            return packageVersion;
        }

        public void setPackageVersion(int packageVersion) {
            this.packageVersion = packageVersion;
        }

        public String getPackageNo() {
            return packageNo;
        }

        public void setPackageNo(String packageNo) {
            this.packageNo = packageNo;
        }

        public String getPackageUrl() {
            return packageUrl;
        }

        public void setPackageUrl(String packageUrl) {
            this.packageUrl = packageUrl;
        }

        public int getIsForce() {
            return isForce;
        }

        public void setIsForce(int isForce) {
            this.isForce = isForce;
        }

        public int getIsShowSensitive() {
            return isShowSensitive;
        }

        public void setIsShowSensitive(int isShowSensitive) {
            this.isShowSensitive = isShowSensitive;
        }

        public List<String> getDescription() {
            return description;
        }

        public void setDescription(List<String> description) {
            this.description = description;
        }
    }
}
