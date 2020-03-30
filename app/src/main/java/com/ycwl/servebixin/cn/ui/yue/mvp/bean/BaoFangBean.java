package com.ycwl.servebixin.cn.ui.yue.mvp.bean;

import java.util.List;

public class BaoFangBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"boxFreePrice":2000,"boxTypeId":21,"boxTypeName":"豪包","boxs":[{"boxId":81,"boxName":"A999","isIdle":0},{"boxId":82,"boxName":"B666","isIdle":0}]},{"boxFreePrice":1500,"boxTypeId":22,"boxTypeName":"大包","boxs":[{"boxId":85,"boxName":"C666","isIdle":0},{"boxId":86,"boxName":"C888","isIdle":0}]}]
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
         * boxFreePrice : 2000
         * boxTypeId : 21
         * boxTypeName : 豪包
         * boxs : [{"boxId":81,"boxName":"A999","isIdle":0},{"boxId":82,"boxName":"B666","isIdle":0}]
         */

        private int boxFreePrice;
        private int boxTypeId;
        private String boxTypeName;
        private List<BoxsBean> boxs;

        public int getBoxFreePrice() {
            return boxFreePrice;
        }

        public void setBoxFreePrice(int boxFreePrice) {
            this.boxFreePrice = boxFreePrice;
        }

        public int getBoxTypeId() {
            return boxTypeId;
        }

        public void setBoxTypeId(int boxTypeId) {
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
             * boxId : 81
             * boxName : A999
             * isIdle : 0
             */

            private int boxId;
            private String boxName;
            private int isIdle;

            public int getBoxId() {
                return boxId;
            }

            public void setBoxId(int boxId) {
                this.boxId = boxId;
            }

            public String getBoxName() {
                return boxName;
            }

            public void setBoxName(String boxName) {
                this.boxName = boxName;
            }

            public int getIsIdle() {
                return isIdle;
            }

            public void setIsIdle(int isIdle) {
                this.isIdle = isIdle;
            }
        }
    }
}
