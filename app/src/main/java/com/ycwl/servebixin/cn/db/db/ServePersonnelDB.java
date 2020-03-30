package com.ycwl.servebixin.cn.db.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ServePersonnelDB {
    @Id(autoincrement = true)
    private Long id;
    private String userId;//服务人员ID
    private String nickname;//服务人员名字
    private String avatar;//图片
    private String skillPrice;//多少钱一场
    private String bixinId;//比心号
    private String age;//年龄
    private String sex;//性别
    private String constellation;//星座
    private String occupationName;//职业
    @Generated(hash = 1420056853)
    public ServePersonnelDB(Long id, String userId, String nickname, String avatar,
            String skillPrice, String bixinId, String age, String sex,
            String constellation, String occupationName) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.avatar = avatar;
        this.skillPrice = skillPrice;
        this.bixinId = bixinId;
        this.age = age;
        this.sex = sex;
        this.constellation = constellation;
        this.occupationName = occupationName;
    }
    @Generated(hash = 1893155015)
    public ServePersonnelDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getSkillPrice() {
        return this.skillPrice;
    }
    public void setSkillPrice(String skillPrice) {
        this.skillPrice = skillPrice;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getConstellation() {
        return this.constellation;
    }
    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }
    public String getOccupationName() {
        return this.occupationName;
    }
    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }
    public String getBixinId() {
        return this.bixinId;
    }
    public void setBixinId(String bixinId) {
        this.bixinId = bixinId;
    }
}
