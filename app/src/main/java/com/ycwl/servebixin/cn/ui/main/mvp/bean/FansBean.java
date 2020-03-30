package com.ycwl.servebixin.cn.ui.main.mvp.bean;

import java.util.List;

public class FansBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"nickname":"这黑膜","sex":2,"age":46,"constellation":"双子座","avatar":"http://pic.bixinyule.com/FtJQahLce7OArhAL_06eAsPmlX4z"}]
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
         * nickname : 这黑膜
         * sex : 2
         * age : 46
         * constellation : 双子座
         * avatar : http://pic.bixinyule.com/FtJQahLce7OArhAL_06eAsPmlX4z
         */

        private String nickname;
        private int sex;
        private int age;
        private String constellation;
        private String avatar;

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

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
