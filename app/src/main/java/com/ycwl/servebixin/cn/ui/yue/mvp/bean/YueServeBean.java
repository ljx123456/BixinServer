package com.ycwl.servebixin.cn.ui.yue.mvp.bean;

public class YueServeBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"userId":3461,"bixinId":4215776,"nickname":"Be her","sex":2,"age":4,"avatar":"http://pic1.win4000.com/pic/6/7d/2565d860c5_250_350.jpg","occupationName":"销售","constellation":"巨蟹座","skillPrice":800,"state":1,"businessId":1,"enableField":1}
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
         * userId : 3461
         * bixinId : 4215776
         * nickname : Be her
         * sex : 2
         * age : 4
         * avatar : http://pic1.win4000.com/pic/6/7d/2565d860c5_250_350.jpg
         * occupationName : 销售
         * constellation : 巨蟹座
         * skillPrice : 800
         * state : 1
         * businessId : 1
         * enableField : 1
         */

        private int userId;
        private int bixinId;
        private String nickname;
        private int sex;
        private int age;
        private String avatar;
        private String occupationName;
        private String constellation;
        private double skillPrice;
        private int state;
        private int businessId;
        private int enableField;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getBixinId() {
            return bixinId;
        }

        public void setBixinId(int bixinId) {
            this.bixinId = bixinId;
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

        public double getSkillPrice() {
            return skillPrice;
        }

        public void setSkillPrice(double skillPrice) {
            this.skillPrice = skillPrice;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public int getEnableField() {
            return enableField;
        }

        public void setEnableField(int enableField) {
            this.enableField = enableField;
        }
    }
}
