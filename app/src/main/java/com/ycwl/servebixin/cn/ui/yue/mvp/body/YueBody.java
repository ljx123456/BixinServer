package com.ycwl.servebixin.cn.ui.yue.mvp.body;

import java.util.List;

public class YueBody {

    /**
     * fieldId : 329
     * boxTypeId : 21
     * boxId : 81
     * serviceBixinIds : [3265,3266]
     * wines : [{"wineId":"153","num":"3"},{"wineId":"154","num":"2"}]
     */

    private String fieldId;
    private String boxTypeId;
    private String boxId;
    private List<String> serviceBixinIds;
    private List<WinesBean> wines;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getBoxTypeId() {
        return boxTypeId;
    }

    public void setBoxTypeId(String boxTypeId) {
        this.boxTypeId = boxTypeId;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public List<String> getServiceBixinIds() {
        return serviceBixinIds;
    }

    public void setServiceBixinIds(List<String> serviceBixinIds) {
        this.serviceBixinIds = serviceBixinIds;
    }

    public List<WinesBean> getWines() {
        return wines;
    }

    public void setWines(List<WinesBean> wines) {
        this.wines = wines;
    }

    public static class WinesBean {
        /**
         * wineId : 153
         * num : 3
         */

        private String wineId;
        private String num;

        public String getWineId() {
            return wineId;
        }

        public void setWineId(String wineId) {
            this.wineId = wineId;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public WinesBean(String wineId, String num) {
            this.wineId = wineId;
            this.num = num;
        }
    }

    public YueBody(String fieldId, String boxTypeId, String boxId, List<String> serviceBixinIds, List<WinesBean> wines) {
        this.fieldId = fieldId;
        this.boxTypeId = boxTypeId;
        this.boxId = boxId;
        this.serviceBixinIds = serviceBixinIds;
        this.wines = wines;
    }

    public YueBody() {
    }
}
