package com.ycwl.servebixin.cn.ui.main.mvp.bean;

public class SearchLeaderBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"bixinId":9214128,"nickname":"牛奶煮萝莉","avatar":"http://pic1.win4000.com/pic/d/53/331fa326ce_250_350.jpg","sex":"1","constellation":"天蝎座","age":"23"}
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
         * bixinId : 9214128
         * nickname : 牛奶煮萝莉
         * avatar : http://pic1.win4000.com/pic/d/53/331fa326ce_250_350.jpg
         * sex : 1
         * constellation : 天蝎座
         * age : 23
         */

        private int bixinId;
        private String nickname;
        private String avatar;
        private String sex;
        private String constellation;
        private String age;

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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
