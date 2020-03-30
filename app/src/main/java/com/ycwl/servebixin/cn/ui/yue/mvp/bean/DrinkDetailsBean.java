package com.ycwl.servebixin.cn.ui.yue.mvp.bean;

import java.util.List;

public class DrinkDetailsBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"wineInfo":{"businessWineId":81,"businessWineName":"情迷巴西","businessWinePrice":1888},"wineDetailsInfo":[{"businessWineUnit":"瓶","businessWineSpecifications":"500ML","wineNum":"1","wineName":"茅台"},{"businessWineUnit":"瓶","businessWineSpecifications":"500ML","wineNum":"12","wineName":"百威啤酒"},{"businessWineUnit":"瓶","businessWineSpecifications":"500ML","wineNum":"1","wineName":"Martell"}]}
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
         * wineInfo : {"businessWineId":81,"businessWineName":"情迷巴西","businessWinePrice":1888}
         * wineDetailsInfo : [{"businessWineUnit":"瓶","businessWineSpecifications":"500ML","wineNum":"1","wineName":"茅台"},{"businessWineUnit":"瓶","businessWineSpecifications":"500ML","wineNum":"12","wineName":"百威啤酒"},{"businessWineUnit":"瓶","businessWineSpecifications":"500ML","wineNum":"1","wineName":"Martell"}]
         */

        private WineInfoBean wineInfo;
        private List<WineDetailsInfoBean> wineDetailsInfo;

        public WineInfoBean getWineInfo() {
            return wineInfo;
        }

        public void setWineInfo(WineInfoBean wineInfo) {
            this.wineInfo = wineInfo;
        }

        public List<WineDetailsInfoBean> getWineDetailsInfo() {
            return wineDetailsInfo;
        }

        public void setWineDetailsInfo(List<WineDetailsInfoBean> wineDetailsInfo) {
            this.wineDetailsInfo = wineDetailsInfo;
        }

        public static class WineInfoBean {
            /**
             * businessWineId : 81
             * businessWineName : 情迷巴西
             * businessWinePrice : 1888
             */

            private int businessWineId;
            private String businessWineName;
            private int businessWinePrice;

            public int getBusinessWineId() {
                return businessWineId;
            }

            public void setBusinessWineId(int businessWineId) {
                this.businessWineId = businessWineId;
            }

            public String getBusinessWineName() {
                return businessWineName;
            }

            public void setBusinessWineName(String businessWineName) {
                this.businessWineName = businessWineName;
            }

            public int getBusinessWinePrice() {
                return businessWinePrice;
            }

            public void setBusinessWinePrice(int businessWinePrice) {
                this.businessWinePrice = businessWinePrice;
            }
        }

        public static class WineDetailsInfoBean {
            /**
             * businessWineUnit : 瓶
             * businessWineSpecifications : 500ML
             * wineNum : 1
             * wineName : 茅台
             */

            private String businessWineUnit;
            private String businessWineSpecifications;
            private String wineNum;
            private String wineName;

            public String getBusinessWineUnit() {
                return businessWineUnit;
            }

            public void setBusinessWineUnit(String businessWineUnit) {
                this.businessWineUnit = businessWineUnit;
            }

            public String getBusinessWineSpecifications() {
                return businessWineSpecifications;
            }

            public void setBusinessWineSpecifications(String businessWineSpecifications) {
                this.businessWineSpecifications = businessWineSpecifications;
            }

            public String getWineNum() {
                return wineNum;
            }

            public void setWineNum(String wineNum) {
                this.wineNum = wineNum;
            }

            public String getWineName() {
                return wineName;
            }

            public void setWineName(String wineName) {
                this.wineName = wineName;
            }

            public WineDetailsInfoBean(String businessWineUnit, String businessWineSpecifications, String wineNum, String wineName) {
                this.businessWineUnit = businessWineUnit;
                this.businessWineSpecifications = businessWineSpecifications;
                this.wineNum = wineNum;
                this.wineName = wineName;
            }
        }
    }
}
