package com.ycwl.servebixin.cn.ui.main.mvp.bean;

import java.util.List;

public class ServeSetBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : {"isPerfectData":1,"userSkillDtos":[{"userSkillId":299,"skillTypeId":1,"state":1,"skillName":"欢歌纵酒","openState":1},{"userSkillId":-1,"skillTypeId":2,"state":0,"skillName":"game","openState":0}]}
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
         * isPerfectData : 1
         * userSkillDtos : [{"userSkillId":299,"skillTypeId":1,"state":1,"skillName":"欢歌纵酒","openState":1},{"userSkillId":-1,"skillTypeId":2,"state":0,"skillName":"game","openState":0}]
         */

        private int isPerfectData;
        private List<UserSkillDtosBean> userSkillDtos;

        public int getIsPerfectData() {
            return isPerfectData;
        }

        public void setIsPerfectData(int isPerfectData) {
            this.isPerfectData = isPerfectData;
        }

        public List<UserSkillDtosBean> getUserSkillDtos() {
            return userSkillDtos;
        }

        public void setUserSkillDtos(List<UserSkillDtosBean> userSkillDtos) {
            this.userSkillDtos = userSkillDtos;
        }

        public static class UserSkillDtosBean {
            /**
             * userSkillId : 299
             * skillTypeId : 1
             * state : 1
             * skillName : 欢歌纵酒
             * openState : 1
             */

            private int userSkillId;
            private int skillTypeId;
            private int state;
            private String skillName;
            private int openState;

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

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getSkillName() {
                return skillName;
            }

            public void setSkillName(String skillName) {
                this.skillName = skillName;
            }

            public int getOpenState() {
                return openState;
            }

            public void setOpenState(int openState) {
                this.openState = openState;
            }
        }
    }
}
