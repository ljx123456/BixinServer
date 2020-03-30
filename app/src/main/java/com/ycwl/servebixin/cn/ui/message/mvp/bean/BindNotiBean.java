package com.ycwl.servebixin.cn.ui.message.mvp.bean;

public class BindNotiBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"time":"2019-05-14 14:52:25","nickname":"空景孤扰人心","avatar":"http://pic1.win4000.com/pic/2/8b/595079b078_250_350.jpg","sex":2,"age":27,"constellation":"天秤座","phone":"14572119140","serviceLeader":1}
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
         * time : 2019-05-14 14:52:25
         * nickname : 空景孤扰人心
         * avatar : http://pic1.win4000.com/pic/2/8b/595079b078_250_350.jpg
         * sex : 2
         * age : 27
         * constellation : 天秤座
         * phone : 14572119140
         * serviceLeader : 1
         */

        private String time;
        private String nickname;
        private String avatar;
        private int sex;
        private int age;
        private String constellation;
        private String phone;
        private int serviceLeader;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getServiceLeader() {
            return serviceLeader;
        }

        public void setServiceLeader(int serviceLeader) {
            this.serviceLeader = serviceLeader;
        }
    }
}
