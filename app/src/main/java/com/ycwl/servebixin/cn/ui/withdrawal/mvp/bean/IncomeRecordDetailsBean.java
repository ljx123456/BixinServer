package com.ycwl.servebixin.cn.ui.withdrawal.mvp.bean;

import java.util.List;

public class IncomeRecordDetailsBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"walletRecodeId":2,"recodePrice":500,"orderNo":"1000201901210542826243095","businessAddress":"成都市金堂县赵镇金园街333号-343号1-4层（金堂车站对面）","boxTypeName":"大包","boxName":"Ajfj","recodeDescribe":"测试余额增加","profitType":1,"businessName":"世纪怡都","createTime":"2019-03-06 16:37:55","wineCountPrice":3000,"recodeRole":3,"users":[{"nickname":"黄小小依依","sex":2,"age":19,"avatar":"http://pic.bixinyule.com/Fu_9VPUZF48catM2jt2OvaMyLgOB","constellation":"射手座"},{"nickname":"雅琪小小","sex":2,"age":8,"avatar":"http://pic.bixinyule.com/Fs-41lSOFZHL21eaJGCrZnr8D3M0","constellation":"水瓶座"},{"nickname":"娇娇呀","sex":2,"age":8,"avatar":"http://pic.bixinyule.com/FkA2tOIV33wr7sv7lj0cLo7oHZgU","constellation":"水瓶座"}]}
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
         * walletRecodeId : 2
         * recodePrice : 500
         * orderNo : 1000201901210542826243095
         * businessAddress : 成都市金堂县赵镇金园街333号-343号1-4层（金堂车站对面）
         * boxTypeName : 大包
         * boxName : Ajfj
         * recodeDescribe : 测试余额增加
         * profitType : 1
         * businessName : 世纪怡都
         * createTime : 2019-03-06 16:37:55
         * wineCountPrice : 3000
         * recodeRole : 3
         * users : [{"nickname":"黄小小依依","sex":2,"age":19,"avatar":"http://pic.bixinyule.com/Fu_9VPUZF48catM2jt2OvaMyLgOB","constellation":"射手座"},{"nickname":"雅琪小小","sex":2,"age":8,"avatar":"http://pic.bixinyule.com/Fs-41lSOFZHL21eaJGCrZnr8D3M0","constellation":"水瓶座"},{"nickname":"娇娇呀","sex":2,"age":8,"avatar":"http://pic.bixinyule.com/FkA2tOIV33wr7sv7lj0cLo7oHZgU","constellation":"水瓶座"}]
         */

        private int walletRecodeId;
        private String recodePrice;
        private String orderNo;
        private String businessAddress;
        private String boxTypeName;
        private String boxName;
        private String recodeDescribe;
        private int profitType;
        private String businessName;
        private String createTime;
        private String wineCountPrice;
        private int recodeRole;
        private List<UsersBean> users;

        public int getWalletRecodeId() {
            return walletRecodeId;
        }

        public void setWalletRecodeId(int walletRecodeId) {
            this.walletRecodeId = walletRecodeId;
        }

        public String getRecodePrice() {
            return recodePrice;
        }

        public void setRecodePrice(String recodePrice) {
            this.recodePrice = recodePrice;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getBusinessAddress() {
            return businessAddress;
        }

        public void setBusinessAddress(String businessAddress) {
            this.businessAddress = businessAddress;
        }

        public String getBoxTypeName() {
            return boxTypeName;
        }

        public void setBoxTypeName(String boxTypeName) {
            this.boxTypeName = boxTypeName;
        }

        public String getBoxName() {
            return boxName;
        }

        public void setBoxName(String boxName) {
            this.boxName = boxName;
        }

        public String getRecodeDescribe() {
            return recodeDescribe;
        }

        public void setRecodeDescribe(String recodeDescribe) {
            this.recodeDescribe = recodeDescribe;
        }

        public int getProfitType() {
            return profitType;
        }

        public void setProfitType(int profitType) {
            this.profitType = profitType;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getWineCountPrice() {
            return wineCountPrice;
        }

        public void setWineCountPrice(String wineCountPrice) {
            this.wineCountPrice = wineCountPrice;
        }

        public int getRecodeRole() {
            return recodeRole;
        }

        public void setRecodeRole(int recodeRole) {
            this.recodeRole = recodeRole;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean {
            /**
             * nickname : 黄小小依依
             * sex : 2
             * age : 19
             * avatar : http://pic.bixinyule.com/Fu_9VPUZF48catM2jt2OvaMyLgOB
             * constellation : 射手座
             */

            private String nickname;
            private int sex;
            private int age;
            private String avatar;
            private String constellation;

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

            public String getConstellation() {
                return constellation;
            }

            public void setConstellation(String constellation) {
                this.constellation = constellation;
            }
        }
    }
}
