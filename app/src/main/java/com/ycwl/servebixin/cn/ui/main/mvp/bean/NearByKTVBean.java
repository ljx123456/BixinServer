package com.ycwl.servebixin.cn.ui.main.mvp.bean;

import java.math.BigDecimal;
import java.util.List;

public class NearByKTVBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"businessId":1,"businessName":"美乐迪KTV(二环路东三段)","businessAddress":"二环路东三段11号附109号","businessImg":"https://back.tobosu.com/ke_file/2018-01-27/m_5a6c4f0a67861.jpg","distance":0,"isAdd":0}]
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
         * businessId : 1
         * businessName : 美乐迪KTV(二环路东三段)
         * businessAddress : 二环路东三段11号附109号
         * businessImg : https://back.tobosu.com/ke_file/2018-01-27/m_5a6c4f0a67861.jpg
         * distance : 0
         * isAdd : 0
         */

        private int businessId;
        private String businessName;
        private String businessAddress;
        private String businessImg;
        private BigDecimal distance=new BigDecimal("0");
        private int isAdd;

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
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

        public BigDecimal getDistance() {
            return distance;
        }

        public void setDistance(BigDecimal distance) {
            this.distance = distance;
        }

        public int getIsAdd() {
            return isAdd;
        }

        public void setIsAdd(int isAdd) {
            this.isAdd = isAdd;
        }
    }
}
