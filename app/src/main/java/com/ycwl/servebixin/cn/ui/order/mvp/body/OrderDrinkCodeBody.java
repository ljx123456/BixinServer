package com.ycwl.servebixin.cn.ui.order.mvp.body;

import java.util.List;

public class OrderDrinkCodeBody {

    /**
     * orderServiceNo : 3000201903184330095458103
     * wines : [{"wineId":"51","num":"3"},{"wineId":"52","num":"2"},{"wineId":"53","num":"1"},{"wineId":"54","num":"1"}]
     */

    private String orderServiceNo;
    private List<WinesBean> wines;

    public String getOrderServiceNo() {
        return orderServiceNo;
    }

    public void setOrderServiceNo(String orderServiceNo) {
        this.orderServiceNo = orderServiceNo;
    }

    public List<WinesBean> getWines() {
        return wines;
    }

    public void setWines(List<WinesBean> wines) {
        this.wines = wines;
    }

    public static class WinesBean {
        /**
         * wineId : 51
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

    public OrderDrinkCodeBody(String orderServiceNo, List<WinesBean> wines) {
        this.orderServiceNo = orderServiceNo;
        this.wines = wines;
    }
}
