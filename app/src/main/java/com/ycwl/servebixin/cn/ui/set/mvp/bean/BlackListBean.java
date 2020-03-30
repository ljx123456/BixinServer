package com.ycwl.servebixin.cn.ui.set.mvp.bean;

import java.util.List;

public class BlackListBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"userId":3366,"nickname":"你马子真美","sex":2,"avatar":"http://t2.hddhhn.com/uploads/tu/201808/9999/d5bf24a7ae.jpg","km":"11.4km","charmValue":68,"age":0,"occupationName":"教师"},{"userId":3290,"nickname":"小慧","sex":1,"avatar":"http://pic.bixinyule.com/803f4acd-22c4-4c4f-bec9-52c8cad84e70","km":"11.3km","charmValue":80,"age":30,"occupationName":"教师"}]
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
         * userId : 3366
         * nickname : 你马子真美
         * sex : 2
         * avatar : http://t2.hddhhn.com/uploads/tu/201808/9999/d5bf24a7ae.jpg
         * km : 11.4km
         * charmValue : 68
         * age : 0
         * occupationName : 教师
         */

        private int userId;
        private String nickname;
        private int sex;
        private String avatar;
        private String km;
        private int charmValue;
        private int age;
        private String occupationName;
        private String constellation;

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        public int getCharmValue() {
            return charmValue;
        }

        public void setCharmValue(int charmValue) {
            this.charmValue = charmValue;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getOccupationName() {
            return occupationName;
        }

        public void setOccupationName(String occupationName) {
            this.occupationName = occupationName;
        }
    }
}
