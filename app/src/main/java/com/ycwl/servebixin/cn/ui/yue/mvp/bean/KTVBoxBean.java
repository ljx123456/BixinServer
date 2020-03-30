package com.ycwl.servebixin.cn.ui.yue.mvp.bean;

import java.util.List;

public class KTVBoxBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"businessInfo":{"businessName":"美乐迪KTV(二环路东三段)","businessAddress":"二环路东三段11号附109号","longitude":"104.113985","latitude":"30.660263","servicePrice":123,"avatar":"27/m_5a6c4f0a67861.jpg"},"myOrderInfo":[{"boxTypeId":261,"boxTypeName":"11","boxs":[{"businessBoxId":15354,"businessBoxName":"741"},{"businessBoxId":15355,"businessBoxName":"745"}]},{"boxTypeId":1,"boxTypeName":"包间1","boxs":[{"businessBoxId":1,"businessBoxName":"N097"},{"businessBoxId":2,"businessBoxName":"U642"},{"businessBoxId":3,"businessBoxName":"Y377"},{"businessBoxId":4,"businessBoxName":"F980"}]},{"boxTypeId":3,"boxTypeName":"中包","boxs":[]},{"boxTypeId":4,"boxTypeName":"小包","boxs":[]},{"boxTypeId":2,"boxTypeName":"大包","boxs":[]}]}
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
         * businessInfo : {"businessName":"美乐迪KTV(二环路东三段)","businessAddress":"二环路东三段11号附109号","longitude":"104.113985","latitude":"30.660263","servicePrice":123,"avatar":"27/m_5a6c4f0a67861.jpg"}
         * myOrderInfo : [{"boxTypeId":261,"boxTypeName":"11","boxs":[{"businessBoxId":15354,"businessBoxName":"741"},{"businessBoxId":15355,"businessBoxName":"745"}]},{"boxTypeId":1,"boxTypeName":"包间1","boxs":[{"businessBoxId":1,"businessBoxName":"N097"},{"businessBoxId":2,"businessBoxName":"U642"},{"businessBoxId":3,"businessBoxName":"Y377"},{"businessBoxId":4,"businessBoxName":"F980"}]},{"boxTypeId":3,"boxTypeName":"中包","boxs":[]},{"boxTypeId":4,"boxTypeName":"小包","boxs":[]},{"boxTypeId":2,"boxTypeName":"大包","boxs":[]}]
         */

        private BusinessInfoBean businessInfo;
        private List<MyOrderInfoBean> myOrderInfo;

        public BusinessInfoBean getBusinessInfo() {
            return businessInfo;
        }

        public void setBusinessInfo(BusinessInfoBean businessInfo) {
            this.businessInfo = businessInfo;
        }

        public List<MyOrderInfoBean> getMyOrderInfo() {
            return myOrderInfo;
        }

        public void setMyOrderInfo(List<MyOrderInfoBean> myOrderInfo) {
            this.myOrderInfo = myOrderInfo;
        }

        public static class BusinessInfoBean {
            /**
             * businessName : 美乐迪KTV(二环路东三段)
             * businessAddress : 二环路东三段11号附109号
             * longitude : 104.113985
             * latitude : 30.660263
             * servicePrice : 123
             * avatar : 27/m_5a6c4f0a67861.jpg
             */

            private String businessName;
            private String businessAddress;
            private String longitude;
            private String latitude;
            private int servicePrice;
            private String avatar;

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

            public int getServicePrice() {
                return servicePrice;
            }

            public void setServicePrice(int servicePrice) {
                this.servicePrice = servicePrice;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }

        public static class MyOrderInfoBean {
            /**
             * boxTypeId : 261
             * boxTypeName : 11
             * boxs : [{"businessBoxId":15354,"businessBoxName":"741"},{"businessBoxId":15355,"businessBoxName":"745"}]
             */

            private String boxTypeId;
            private String boxTypeName;
            private List<BoxsBean> boxs;

            public String getBoxTypeId() {
                return boxTypeId;
            }

            public void setBoxTypeId(String boxTypeId) {
                this.boxTypeId = boxTypeId;
            }

            public String getBoxTypeName() {
                return boxTypeName;
            }

            public void setBoxTypeName(String boxTypeName) {
                this.boxTypeName = boxTypeName;
            }

            public List<BoxsBean> getBoxs() {
                return boxs;
            }

            public void setBoxs(List<BoxsBean> boxs) {
                this.boxs = boxs;
            }

            public static class BoxsBean {
                /**
                 * businessBoxId : 15354
                 * businessBoxName : 741
                 */

                private String businessBoxId;
                private String businessBoxName;

                public String getBusinessBoxId() {
                    return businessBoxId;
                }

                public void setBusinessBoxId(String businessBoxId) {
                    this.businessBoxId = businessBoxId;
                }

                public String getBusinessBoxName() {
                    return businessBoxName;
                }

                public void setBusinessBoxName(String businessBoxName) {
                    this.businessBoxName = businessBoxName;
                }
            }
        }
    }
}
