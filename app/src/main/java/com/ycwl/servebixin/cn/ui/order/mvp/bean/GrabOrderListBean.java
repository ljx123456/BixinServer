package com.ycwl.servebixin.cn.ui.order.mvp.bean;

import java.util.List;

public class GrabOrderListBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"inviteId":1,"inviteState":0,"type":0,"orderNo":"R19031800001111A","acceptTime":"2019-03-21 10:22:46","createTime":"2019-03-21 10:21:56","updateTime":"2019-03-21 10:27:16","reserveStartTime":"2019-03-18 11:00:00","skillTypeName":"纵享欢唱","boxTypeName":"大包","boxName":"Ajjj","address":"成都市彭州市金彭东路122号逸都城沃尔玛四楼","userId":3642,"latitude":30.987476,"longitude":103.957317,"isPoinListService":"1","waitGrabOrderTime":"-2"}]
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
         * inviteId : 1
         * inviteState : 0
         * type : 0
         * orderNo : R19031800001111A
         * acceptTime : 2019-03-21 10:22:46
         * createTime : 2019-03-21 10:21:56
         * updateTime : 2019-03-21 10:27:16
         * reserveStartTime : 2019-03-18 11:00:00
         * skillTypeName : 纵享欢唱
         * boxTypeName : 大包
         * boxName : Ajjj
         * address : 成都市彭州市金彭东路122号逸都城沃尔玛四楼
         * userId : 3642
         * latitude : 30.987476
         * longitude : 103.957317
         * isPoinListService : 1
         * waitGrabOrderTime : -2
         */

        private String inviteId;
        private int inviteState;
        private int type;
        private String orderNo;
        private String acceptTime;
        private String createTime;
        private String updateTime;
        private String reserveStartTime;
        private String skillTypeName;
        private String boxTypeName;
        private String boxName;
        private String address;
        private int userId;
        private String latitude;
        private String longitude;
        private int isPoinListService;
        private int waitGrabOrderTime;
        private String startTime;
        private String businessName;

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getInviteId() {
            return inviteId;
        }

        public void setInviteId(String inviteId) {
            this.inviteId = inviteId;
        }

        public int getInviteState() {
            return inviteState;
        }

        public void setInviteState(int inviteState) {
            this.inviteState = inviteState;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(String acceptTime) {
            this.acceptTime = acceptTime;
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

        public String getReserveStartTime() {
            return reserveStartTime;
        }

        public void setReserveStartTime(String reserveStartTime) {
            this.reserveStartTime = reserveStartTime;
        }

        public String getSkillTypeName() {
            return skillTypeName;
        }

        public void setSkillTypeName(String skillTypeName) {
            this.skillTypeName = skillTypeName;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getIsPoinListService() {
            return isPoinListService;
        }

        public void setIsPoinListService(int isPoinListService) {
            this.isPoinListService = isPoinListService;
        }

        public int getWaitGrabOrderTime() {
            return waitGrabOrderTime;
        }

        public void setWaitGrabOrderTime(int waitGrabOrderTime) {
            this.waitGrabOrderTime = waitGrabOrderTime;
        }
    }
}
