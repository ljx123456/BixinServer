package com.ycwl.servebixin.cn.ui.order.mvp.body;

import java.util.List;

public class GrabOrderDrinkCodeBody {

    /**
     * inviteId : 1
     * wines : [{"wineId":"51","num":"3"},{"wineId":"52","num":"2"},{"wineId":"53","num":"1"},{"wineId":"54","num":"1"}]
     */

    private String inviteId;
    private List<WinesBean> wines;

    public String getInviteId() {
        return inviteId;
    }

    public void setInviteId(String inviteId) {
        this.inviteId = inviteId;
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

    public GrabOrderDrinkCodeBody(String inviteId, List<WinesBean> wines) {
        this.inviteId = inviteId;
        this.wines = wines;
    }
}
