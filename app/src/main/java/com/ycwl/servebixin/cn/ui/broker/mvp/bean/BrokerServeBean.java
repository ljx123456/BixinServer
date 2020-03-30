package com.ycwl.servebixin.cn.ui.broker.mvp.bean;

import java.util.List;

public class BrokerServeBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"phone":"13377685894","nickname":"少女的日记⊙","sex":1,"age":35,"avatar":"http://pic1.win4000.com/pic/1/ae/df371328484_250_350.jpg","occupationName":"教师","constellation":"处女座"},{"phone":"15276286739","nickname":"秋桜","sex":1,"age":6,"avatar":"http://pic1.win4000.com/pic/e/c8/7c411326576_250_350.jpg","occupationName":"教师","constellation":"处女座"}]
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
         * phone : 13377685894
         * nickname : 少女的日记⊙
         * sex : 1
         * age : 35
         * avatar : http://pic1.win4000.com/pic/1/ae/df371328484_250_350.jpg
         * occupationName : 教师
         * constellation : 处女座
         */

        private String phone;
        private String nickname;
        private int sex;
        private int age;
        private String avatar;
        private String occupationName;
        private String constellation;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getOccupationName() {
            return occupationName;
        }

        public void setOccupationName(String occupationName) {
            this.occupationName = occupationName;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }
    }
}
