package com.ycwl.servebixin.cn.ui.message.mvp.bean;

import java.util.List;

public class NotificationBean {

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

    public static class DataBean{
        /**
         * id :1972
         * title : 新订单
         * content : 内容
         * isRead: 0
         * auditId : 1
         * createTime : 1553242585477
         * inviteId : 1
         * orderNo : 订单号
         * serviceOrderType : 1
         * type : 12
         * url : 这是个链接
         * walletRecodeId : 1
         */

        private String title;
        private String content;
        private String id;
        private int auditId;
        private int isRead;
        private String createTime;
        private int inviteId;
        private String orderNo;
        private int serviceOrderType;
        private int type;
        private String url;
        private int walletRecodeId;
        private int serviceLeader;
        private String feedBackId;
        private String messageId;
        private int messageType;

        public int getServiceLeader() {
            return serviceLeader;
        }

        public void setServiceLeader(int serviceLeader) {
            this.serviceLeader = serviceLeader;
        }

        public String getFeedBackId() {
            return feedBackId;
        }

        public void setFeedBackId(String feedBackId) {
            this.feedBackId = feedBackId;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public int getMessageType() {
            return messageType;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getAuditId() {
            return auditId;
        }

        public void setAuditId(int auditId) {
            this.auditId = auditId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getInviteId() {
            return inviteId;
        }

        public void setInviteId(int inviteId) {
            this.inviteId = inviteId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getServiceOrderType() {
            return serviceOrderType;
        }

        public void setServiceOrderType(int serviceOrderType) {
            this.serviceOrderType = serviceOrderType;
        }

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

        public int getWalletRecodeId() {
            return walletRecodeId;
        }

        public void setWalletRecodeId(int walletRecodeId) {
            this.walletRecodeId = walletRecodeId;
        }
    }
}
