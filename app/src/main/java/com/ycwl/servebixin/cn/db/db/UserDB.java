package com.ycwl.servebixin.cn.db.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserDB {

    @Id
    private Long id;
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
    private int leaderIDCardAuditState;
    private int isEnableSkill;
    private int serviceIDCardAuditState;
    private int orderNum;
    private int fansNum;
    private int followNum;
    private int bixinId;
    private int age;
    private String userID;
    private String jmpassword;

    @Generated(hash = 789501835)
    public UserDB(Long id, String token, int identity, String rongToken,
            String phone, String nickname, int sex, String birthday,
            String constellation, String avatar, String occupationName,
            int isPerfectData, int dataAuditState, int leaderIDCardAuditState,
            int isEnableSkill, int serviceIDCardAuditState, int orderNum,
            int fansNum, int followNum, int bixinId, int age, String userID,
            String jmpassword) {
        this.id = id;
        this.token = token;
        this.identity = identity;
        this.rongToken = rongToken;
        this.phone = phone;
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday;
        this.constellation = constellation;
        this.avatar = avatar;
        this.occupationName = occupationName;
        this.isPerfectData = isPerfectData;
        this.dataAuditState = dataAuditState;
        this.leaderIDCardAuditState = leaderIDCardAuditState;
        this.isEnableSkill = isEnableSkill;
        this.serviceIDCardAuditState = serviceIDCardAuditState;
        this.orderNum = orderNum;
        this.fansNum = fansNum;
        this.followNum = followNum;
        this.bixinId = bixinId;
        this.age = age;
        this.userID = userID;
        this.jmpassword = jmpassword;
    }
    @Generated(hash = 1312299826)
    public UserDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public int getIdentity() {
        return this.identity;
    }
    public void setIdentity(int identity) {
        this.identity = identity;
    }
    public String getRongToken() {
        return this.rongToken;
    }
    public void setRongToken(String rongToken) {
        this.rongToken = rongToken;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getConstellation() {
        return this.constellation;
    }
    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getOccupationName() {
        return this.occupationName;
    }
    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }
    public int getIsPerfectData() {
        return this.isPerfectData;
    }
    public void setIsPerfectData(int isPerfectData) {
        this.isPerfectData = isPerfectData;
    }
    public int getDataAuditState() {
        return this.dataAuditState;
    }
    public void setDataAuditState(int dataAuditState) {
        this.dataAuditState = dataAuditState;
    }
    public int getIsEnableSkill() {
        return this.isEnableSkill;
    }
    public void setIsEnableSkill(int isEnableSkill) {
        this.isEnableSkill = isEnableSkill;
    }
    public int getOrderNum() {
        return this.orderNum;
    }
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    public int getFansNum() {
        return this.fansNum;
    }
    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }
    public int getFollowNum() {
        return this.followNum;
    }
    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }
    public int getBixinId() {
        return this.bixinId;
    }
    public void setBixinId(int bixinId) {
        this.bixinId = bixinId;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getUserID() {
        return this.userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getJmpassword() {
        return this.jmpassword;
    }
    public void setJmpassword(String jmpassword) {
        this.jmpassword = jmpassword;
    }
    public int getServiceIDCardAuditState() {
        return this.serviceIDCardAuditState;
    }
    public void setServiceIDCardAuditState(int serviceIDCardAuditState) {
        this.serviceIDCardAuditState = serviceIDCardAuditState;
    }
    public int getLeaderIDCardAuditState() {
        return this.leaderIDCardAuditState;
    }
    public void setLeaderIDCardAuditState(int leaderIDCardAuditState) {
        this.leaderIDCardAuditState = leaderIDCardAuditState;
    }


}
