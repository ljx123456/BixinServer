package com.ycwl.servebixin.cn.ui.yue.mvp.bean;

import java.util.List;

public class DrinksBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"wineTypes":{"wineTypeId":31,"wineTypeName":"酒水套餐","isDetails":1,"wines":[{"businessWineId":151,"businessWineName":"情迷巴西","businessWinePrice":378,"businessWineImg":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548686654258&di=23523b6be3bdaa3256351f7e58a23361&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fbainuo%2Fwh%3D470%2C285%2Fsign%3D8628a35205e939015657853a4cdc78d5%2Fa8ec8a13632762d00df2bfcda4ec08fa503dc6f0.jpg","businessWineUnit":"套","businessWineDetails":"茅台+哈尔滨啤酒+Martell"}]}},{"wineTypes":{"wineTypeId":32,"wineTypeName":"白酒","isDetails":0,"wines":[{"businessWineId":152,"businessWineName":"茅台","businessWinePrice":196,"businessWineImg":"https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=91e018bf506034a83defb0d3aa7a2231/960a304e251f95cad90d0308c5177f3e66095297.jpg","businessWineUnit":"瓶","businessWineSpecifications":"500ML"},{"businessWineId":153,"businessWineName":"五粮液","businessWinePrice":775,"businessWineImg":"https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=5c569456a944ad343ab28fd5b1cb6791/1ad5ad6eddc451da4cbe3a50befd5266d11632c4.jpg","businessWineUnit":"瓶","businessWineSpecifications":"500ML"}]}}]
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
         * wineTypes : {"wineTypeId":31,"wineTypeName":"酒水套餐","isDetails":1,"wines":[{"businessWineId":151,"businessWineName":"情迷巴西","businessWinePrice":378,"businessWineImg":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548686654258&di=23523b6be3bdaa3256351f7e58a23361&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fbainuo%2Fwh%3D470%2C285%2Fsign%3D8628a35205e939015657853a4cdc78d5%2Fa8ec8a13632762d00df2bfcda4ec08fa503dc6f0.jpg","businessWineUnit":"套","businessWineDetails":"茅台+哈尔滨啤酒+Martell"}]}
         */

        private WineTypesBean wineTypes;

        public WineTypesBean getWineTypes() {
            return wineTypes;
        }

        public void setWineTypes(WineTypesBean wineTypes) {
            this.wineTypes = wineTypes;
        }

        public static class WineTypesBean {
            /**
             * wineTypeId : 31
             * wineTypeName : 酒水套餐
             * isDetails : 1
             * wines : [{"businessWineId":151,"businessWineName":"情迷巴西","businessWinePrice":378,"businessWineImg":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548686654258&di=23523b6be3bdaa3256351f7e58a23361&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fbainuo%2Fwh%3D470%2C285%2Fsign%3D8628a35205e939015657853a4cdc78d5%2Fa8ec8a13632762d00df2bfcda4ec08fa503dc6f0.jpg","businessWineUnit":"套","businessWineDetails":"茅台+哈尔滨啤酒+Martell"}]
             */

            private int wineTypeId;
            private String wineTypeName;
            private int isDetails;
            private List<WinesBean> wines;

            public int getWineTypeId() {
                return wineTypeId;
            }

            public void setWineTypeId(int wineTypeId) {
                this.wineTypeId = wineTypeId;
            }

            public String getWineTypeName() {
                return wineTypeName;
            }

            public void setWineTypeName(String wineTypeName) {
                this.wineTypeName = wineTypeName;
            }

            public int getIsDetails() {
                return isDetails;
            }

            public void setIsDetails(int isDetails) {
                this.isDetails = isDetails;
            }

            public List<WinesBean> getWines() {
                return wines;
            }

            public void setWines(List<WinesBean> wines) {
                this.wines = wines;
            }

            public static class WinesBean {
                /**
                 * businessWineId : 151
                 * businessWineName : 情迷巴西
                 * businessWinePrice : 378
                 * businessWineImg : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548686654258&di=23523b6be3bdaa3256351f7e58a23361&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fbainuo%2Fwh%3D470%2C285%2Fsign%3D8628a35205e939015657853a4cdc78d5%2Fa8ec8a13632762d00df2bfcda4ec08fa503dc6f0.jpg
                 * businessWineUnit : 套
                 * businessWineDetails : 茅台+哈尔滨啤酒+Martell
                 */

                private int businessWineId;
                private String businessWineName;
                private double businessWinePrice;
                private String businessWineImg;
                private String businessWineUnit;
                private String businessWineDetails;
                private String businessWineSpecifications;
                private int drinksNum ;

                public WinesBean(int businessWineId, String businessWineName, double businessWinePrice, String businessWineImg,  String businessWineDetails, int drinksNum) {
                    this.businessWineId = businessWineId;
                    this.businessWineName = businessWineName;
                    this.businessWinePrice = businessWinePrice;
                    this.businessWineImg = businessWineImg;
                    this.businessWineDetails = businessWineDetails;
                    this.drinksNum = drinksNum;
                }

                public WinesBean() {
                }

                public String getBusinessWineSpecifications() {
                    return businessWineSpecifications;
                }

                public void setBusinessWineSpecifications(String businessWineSpecifications) {
                    this.businessWineSpecifications = businessWineSpecifications;
                }

                public int getDrinksNum() {
                    return drinksNum;
                }

                public void setDrinksNum(int drinksNum) {
                    this.drinksNum = drinksNum;
                }

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

                public double getBusinessWinePrice() {
                    return businessWinePrice;
                }

                public void setBusinessWinePrice(double businessWinePrice) {
                    this.businessWinePrice = businessWinePrice;
                }

                public String getBusinessWineImg() {
                    return businessWineImg;
                }

                public void setBusinessWineImg(String businessWineImg) {
                    this.businessWineImg = businessWineImg;
                }

                public String getBusinessWineUnit() {
                    return businessWineUnit;
                }

                public void setBusinessWineUnit(String businessWineUnit) {
                    this.businessWineUnit = businessWineUnit;
                }

                public String getBusinessWineDetails() {
                    return businessWineDetails;
                }

                public void setBusinessWineDetails(String businessWineDetails) {
                    this.businessWineDetails = businessWineDetails;
                }
            }

        }
    }
}
