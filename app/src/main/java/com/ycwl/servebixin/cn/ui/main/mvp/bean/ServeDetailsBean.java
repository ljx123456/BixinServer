package com.ycwl.servebixin.cn.ui.main.mvp.bean;

import java.util.List;

public class ServeDetailsBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"userSkillId":297,"skillTypeId":1,"userSkillPrice":600,"skillName":"欢歌纵酒","skillPriceUp":800,"isOut":0,"leader":{"bixinId":1,"nickname":"黄小小依依","sex":2,"age":19,"avatar":"http://pic.bixinyule.com/Fu_9VPUZF48catM2jt2OvaMyLgOB","occupationName":"健身教练","constellation":"射手座"},"businesss":[{"businessId":7,"businessName":"万千时尚ktv","businessAddress":"成都市新都区静安路16号万千城3楼（转盘处）","businessImg":"http://e.hiphotos.baidu.com/bainuo/crop%3D0%2C21%2C690%2C418%3Bw%3D470%3Bq%3D79/sign=c838bc7bd709b3defff0be28f18f40b3/d53f8794a4c27d1e4c6d20d71dd5ad6eddc4385d.jpg","enableField":1}]}
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
         * userSkillId : 297
         * skillTypeId : 1
         * userSkillPrice : 600
         * skillName : 欢歌纵酒
         * skillPriceUp : 800
         * isOut : 0
         * leader : {"bixinId":1,"nickname":"黄小小依依","sex":2,"age":19,"avatar":"http://pic.bixinyule.com/Fu_9VPUZF48catM2jt2OvaMyLgOB","occupationName":"健身教练","constellation":"射手座"}
         * businesss : [{"businessId":7,"businessName":"万千时尚ktv","businessAddress":"成都市新都区静安路16号万千城3楼（转盘处）","businessImg":"http://e.hiphotos.baidu.com/bainuo/crop%3D0%2C21%2C690%2C418%3Bw%3D470%3Bq%3D79/sign=c838bc7bd709b3defff0be28f18f40b3/d53f8794a4c27d1e4c6d20d71dd5ad6eddc4385d.jpg","enableField":1}]
         */

        private int userSkillId;
        private int skillTypeId;
        private double userSkillPrice;
        private String skillName;
        private double skillPriceUp;
        private int isOut;
        private LeaderBean leader;
        private List<BusinesssBean> businesss;

        public int getUserSkillId() {
            return userSkillId;
        }

        public void setUserSkillId(int userSkillId) {
            this.userSkillId = userSkillId;
        }

        public int getSkillTypeId() {
            return skillTypeId;
        }

        public void setSkillTypeId(int skillTypeId) {
            this.skillTypeId = skillTypeId;
        }

        public double getUserSkillPrice() {
            return userSkillPrice;
        }

        public void setUserSkillPrice(int userSkillPrice) {
            this.userSkillPrice = userSkillPrice;
        }

        public String getSkillName() {
            return skillName;
        }

        public void setSkillName(String skillName) {
            this.skillName = skillName;
        }

        public double getSkillPriceUp() {
            return skillPriceUp;
        }

        public void setSkillPriceUp(int skillPriceUp) {
            this.skillPriceUp = skillPriceUp;
        }

        public int getIsOut() {
            return isOut;
        }

        public void setIsOut(int isOut) {
            this.isOut = isOut;
        }

        public LeaderBean getLeader() {
            return leader;
        }

        public void setLeader(LeaderBean leader) {
            this.leader = leader;
        }

        public List<BusinesssBean> getBusinesss() {
            return businesss;
        }

        public void setBusinesss(List<BusinesssBean> businesss) {
            this.businesss = businesss;
        }

        public static class LeaderBean {
            /**
             * bixinId : 1
             * nickname : 黄小小依依
             * sex : 2
             * age : 19
             * avatar : http://pic.bixinyule.com/Fu_9VPUZF48catM2jt2OvaMyLgOB
             * occupationName : 健身教练
             * constellation : 射手座
             */

            private int bixinId;
            private String nickname;
            private int sex;
            private int age;
            private String avatar;
            private String occupationName;
            private String constellation;

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
        }

        public static class BusinesssBean {
            /**
             * businessId : 7
             * businessName : 万千时尚ktv
             * businessAddress : 成都市新都区静安路16号万千城3楼（转盘处）
             * businessImg : http://e.hiphotos.baidu.com/bainuo/crop%3D0%2C21%2C690%2C418%3Bw%3D470%3Bq%3D79/sign=c838bc7bd709b3defff0be28f18f40b3/d53f8794a4c27d1e4c6d20d71dd5ad6eddc4385d.jpg
             * enableField : 1
             */

            private int businessId;
            private String businessName;
            private String businessAddress;
            private String businessImg;
            private int enableField;

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public String getBusinessName() {
                return businessName;
            }

            public void setBusinessName(String businessName) {
                this.businessName = businessName;
            }

            public String getBusinessAddress() {
                return businessAddress;
            }

            public void setBusinessAddress(String businessAddress) {
                this.businessAddress = businessAddress;
            }

            public String getBusinessImg() {
                return businessImg;
            }

            public void setBusinessImg(String businessImg) {
                this.businessImg = businessImg;
            }

            public int getEnableField() {
                return enableField;
            }

            public void setEnableField(int enableField) {
                this.enableField = enableField;
            }
        }
    }
}
