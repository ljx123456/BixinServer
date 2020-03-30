package com.ycwl.servebixin.cn.ui.order.mvp.bean;

import java.util.List;

public class OrderFormListBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"skillName":"欢歌纵酒","orderServiceNo":"3000201903184330095458103","address":"成都市彭州市金彭东路122号逸都城沃尔玛四楼","estimateArriveTime":"2019-03-18 11:13:06","longitude":103.957317,"latitude":30.987476,"serviceState":1,"isStationing":1,"userId":"1312","boxTypeName":"大包","boxName":"abc","businessName":"佳乐迪纯k(彭州店)","acceptTime":-2,"distance":"40.1","serviceCanRefuseTime":"15","isPoinListService":"1","endTime":"2019-03-18 11:13:06","startTime":"2019-03-18 11:13:06","cancelTime":"2019-03-18 11:13:06","walletRecodeId":32}]
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
         * skillName : 欢歌纵酒
         * orderServiceNo : 3000201903184330095458103
         * address : 成都市彭州市金彭东路122号逸都城沃尔玛四楼
         * estimateArriveTime : 2019-03-18 11:13:06
         * longitude : 103.957317
         * latitude : 30.987476
         * serviceState : 1
         * isStationing : 1
         * userId : 1312
         * boxTypeName : 大包
         * boxName : abc
         * businessName : 佳乐迪纯k(彭州店)
         * acceptTime : -2
         * distance : 40.1
         * serviceCanRefuseTime : 15
         * isPoinListService : 1
         * endTime : 2019-03-18 11:13:06
         * startTime : 2019-03-18 11:13:06
         * cancelTime : 2019-03-18 11:13:06
         * walletRecodeId : 32
         */

        private String skillName;
        private String orderServiceNo;
        private String address;
        private String estimateArriveTime;
        private String longitude;
        private String latitude;
        private int serviceState;
        private int isStationing;
        private String userId;
        private String boxTypeName;
        private String boxName;
        private String businessName;
        private int acceptTime;
        private String distance;
        private String serviceCanRefuseTime;
        private int isPoinListService;
        private String endTime;
        private String startTime;
        private String cancelTime;
        private int walletRecodeId;
        private String description;
        private int hasComplaints;
        private int waitPayServiceTime;
        private String updateTime;
        private String createUserName;

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public int getWaitPayServiceTime() {
            return waitPayServiceTime;
        }

        public void setWaitPayServiceTime(int waitPayServiceTime) {
            this.waitPayServiceTime = waitPayServiceTime;
        }

        public int getIsPoinListService() {
            return isPoinListService;
        }

        public void setIsPoinListService(int isPoinListService) {
            this.isPoinListService = isPoinListService;
        }

        public int getHasComplaints() {
            return hasComplaints;
        }

        public void setHasComplaints(int hasComplaints) {
            this.hasComplaints = hasComplaints;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSkillName() {
            return skillName;
        }

        public void setSkillName(String skillName) {
            this.skillName = skillName;
        }

        public String getOrderServiceNo() {
            return orderServiceNo;
        }

        public void setOrderServiceNo(String orderServiceNo) {
            this.orderServiceNo = orderServiceNo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEstimateArriveTime() {
            return estimateArriveTime;
        }

        public void setEstimateArriveTime(String estimateArriveTime) {
            this.estimateArriveTime = estimateArriveTime;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getServiceState() {
            return serviceState;
        }

        public void setServiceState(int serviceState) {
            this.serviceState = serviceState;
        }

        public int getIsStationing() {
            return isStationing;
        }

        public void setIsStationing(int isStationing) {
            this.isStationing = isStationing;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getBoxTypeName() {
            return boxTypeName;
        }

        public void setBoxTypeName(String boxTypeName) {
            this.boxTypeName = boxTypeName;
        }

        public String getBoxName() {
            return boxName;
        }

        public void setBoxName(String boxName) {
            this.boxName = boxName;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public int getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(int acceptTime) {
            this.acceptTime = acceptTime;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getServiceCanRefuseTime() {
            return serviceCanRefuseTime;
        }

        public void setServiceCanRefuseTime(String serviceCanRefuseTime) {
            this.serviceCanRefuseTime = serviceCanRefuseTime;
        }


        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getCancelTime() {
            return cancelTime;
        }

        public void setCancelTime(String cancelTime) {
            this.cancelTime = cancelTime;
        }

        public int getWalletRecodeId() {
            return walletRecodeId;
        }

        public void setWalletRecodeId(int walletRecodeId) {
            this.walletRecodeId = walletRecodeId;
        }
    }
}
