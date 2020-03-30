package com.ycwl.servebixin.cn.ui.yue.mvp.bean;

import java.util.List;

public class KTVBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"fieldId":334,"businessName":"五角星KTV(武侯店)","businessAddress":"成都市高新区天府二街1324号大源村公交车总站对面 （近青年公寓）","businessImg":"http://e.hiphotos.baidu.com/nuomi/pic/item/caef76094b36acaf937c13a775d98d1000e99cc9.jpg"},{"fieldId":335,"businessName":"五角星KTV(温江店)","businessAddress":"成都市高新区天府二街1324号大源村公交车总站对面 （近青年公寓）","businessImg":"http://e.hiphotos.baidu.com/nuomi/pic/item/caef76094b36acaf937c13a775d98d1000e99cc9.jpg"}]
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
         * fieldId : 334
         * businessName : 五角星KTV(武侯店)
         * businessAddress : 成都市高新区天府二街1324号大源村公交车总站对面 （近青年公寓）
         * businessImg : http://e.hiphotos.baidu.com/nuomi/pic/item/caef76094b36acaf937c13a775d98d1000e99cc9.jpg
         */

        private int fieldId;
        private String businessName;
        private String businessAddress;
        private String businessImg;
        private String businessId;

        public String getBusinessId() {
            return businessId;
        }

        public void setBusinessId(String businessId) {
            this.businessId = businessId;
        }

        public int getFieldId() {
            return fieldId;
        }

        public void setFieldId(int fieldId) {
            this.fieldId = fieldId;
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
