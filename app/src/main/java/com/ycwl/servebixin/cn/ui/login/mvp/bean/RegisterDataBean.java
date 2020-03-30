package com.ycwl.servebixin.cn.ui.login.mvp.bean;

import java.util.List;

public class RegisterDataBean {

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
         * tokenCreateTime : 1552571203976
         * token : J8bHqCg8D4ePvQazll6FG9npNE1S1P5Io4zITXJHIxsJbqRJPYhsOzsoFA1pKQyjP4WHhK59Ai1yw3QMlesx0yMOEDt6MpcOr9Trt1vgib5rMFx28tOyMktxab8KLwjLvXm89EhPTiH1UOrYBiJtb5d5AFmZx7vXAZB17vHSCNM=
         * identity : 3
         * rongToken : +00I2OCyyGxjgDlFMgQM/Wle5kmt239TmjRYhg==
         * phone : 18783014636
         * nickname : 牛奶煮萝莉
         * sex : 1
         * birthday : 1990-06-30 23:00:00
         * constellation : 巨蟹座
         * avatar : http://pic1.win4000.com/pic/1/4b/230157fe85_250_350.jpg
         * occupationName : 护士
         * isPerfectData : 1
         * dataAuditState : 2
         * isEnableSkill : 1
         * orderNum : 0
         * fansNum : 0
         * followNum : 0
         * bixinId : 9214128
         * age : 28
         * videos : [{"url":"http://pic1.win4000.com/pic/1/4b/dc98f2714f.jpg","type":2},{"url":"http://pic1.win4000.com/pic/1/4b/230157fe85.jpg","type":2}]
         * tags : ["善良","体贴"]
         */

        private long tokenCreateTime;
        private String token;
        private int identity;
        private String rongToken;
        private String phone;
        private String nickname;
        private int sex;
        private String birthday;
        private String constellation;
        private String avatar;
        private String occupationName;
        private int isPerfectData;
        private int dataAuditState;
        private int isEnableSkill;
        private int orderNum;
        private int fansNum;
        private int followNum;
        private int bixinId;
        private int age;
        private List<VideosBean> videos;
        private List<String> tags;

        public long getTokenCreateTime() {
            return tokenCreateTime;
        }

        public void setTokenCreateTime(long tokenCreateTime) {
            this.tokenCreateTime = tokenCreateTime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        public String getRongToken() {
            return rongToken;
        }

        public void setRongToken(String rongToken) {
            this.rongToken = rongToken;
        }

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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
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

        public String getOccupationName() {
            return occupationName;
        }

        public void setOccupationName(String occupationName) {
            this.occupationName = occupationName;
        }

        public int getIsPerfectData() {
            return isPerfectData;
        }

        public void setIsPerfectData(int isPerfectData) {
            this.isPerfectData = isPerfectData;
        }

        public int getDataAuditState() {
            return dataAuditState;
        }

        public void setDataAuditState(int dataAuditState) {
            this.dataAuditState = dataAuditState;
        }

        public int getIsEnableSkill() {
            return isEnableSkill;
        }

        public void setIsEnableSkill(int isEnableSkill) {
            this.isEnableSkill = isEnableSkill;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public int getFansNum() {
            return fansNum;
        }

        public void setFansNum(int fansNum) {
            this.fansNum = fansNum;
        }

        public int getFollowNum() {
            return followNum;
        }

        public void setFollowNum(int followNum) {
            this.followNum = followNum;
        }

        public int getBixinId() {
            return bixinId;
        }

        public void setBixinId(int bixinId) {
            this.bixinId = bixinId;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public static class VideosBean {
            /**
             * url : http://pic1.win4000.com/pic/1/4b/dc98f2714f.jpg
             * type : 2
             */

            private String url;
            private int type;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
