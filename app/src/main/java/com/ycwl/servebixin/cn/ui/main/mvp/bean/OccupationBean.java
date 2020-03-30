package com.ycwl.servebixin.cn.ui.main.mvp.bean;

import java.util.ArrayList;
import java.util.List;

public class OccupationBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"nowOccupation":1,"userOccupations":[{"occupationId":1,"occupationName":"教师"},{"occupationId":2,"occupationName":"律师"},{"occupationId":3,"occupationName":"医生"}]}
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
         * nowOccupation : 1
         * userOccupations : [{"occupationId":1,"occupationName":"教师"},{"occupationId":2,"occupationName":"律师"},{"occupationId":3,"occupationName":"医生"}]
         */

        private int nowOccupation;
        private ArrayList<UserOccupationsBean> userOccupations;

        public int getNowOccupation() {
            return nowOccupation;
        }

        public void setNowOccupation(int nowOccupation) {
            this.nowOccupation = nowOccupation;
        }

        public ArrayList<UserOccupationsBean> getUserOccupations() {
            return userOccupations;
        }

        public void setUserOccupations(ArrayList<UserOccupationsBean> userOccupations) {
            this.userOccupations = userOccupations;
        }

        public static class UserOccupationsBean {
            /**
             * occupationId : 1
             * occupationName : 教师
             */

            private int occupationId;
            private String occupationName;

            public int getOccupationId() {
                return occupationId;
            }

            public void setOccupationId(int occupationId) {
                this.occupationId = occupationId;
            }

            public String getOccupationName() {
                return occupationName;
            }

            public void setOccupationName(String occupationName) {
                this.occupationName = occupationName;
            }
        }
    }
}
