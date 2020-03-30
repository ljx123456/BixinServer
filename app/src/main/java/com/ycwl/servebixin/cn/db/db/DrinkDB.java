package com.ycwl.servebixin.cn.db.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DrinkDB {
    @Id(autoincrement = true)
    private Long id;
    private String mealName;//种类名称
    private String mealId;//种类ID
    private String drinkName;//酒名
    private String drinkText;//酒内容
    private String drinkMoney;//酒价格
    private String drinkNum;//酒数量
    private String drinkID;//酒ID
    private String drinkImage;
    @Generated(hash = 1142342917)
    public DrinkDB(Long id, String mealName, String mealId, String drinkName,
            String drinkText, String drinkMoney, String drinkNum, String drinkID,
            String drinkImage) {
        this.id = id;
        this.mealName = mealName;
        this.mealId = mealId;
        this.drinkName = drinkName;
        this.drinkText = drinkText;
        this.drinkMoney = drinkMoney;
        this.drinkNum = drinkNum;
        this.drinkID = drinkID;
        this.drinkImage = drinkImage;
    }
    @Generated(hash = 1703144607)
    public DrinkDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDrinkName() {
        return this.drinkName;
    }
    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }
    public String getDrinkText() {
        return this.drinkText;
    }
    public void setDrinkText(String drinkText) {
        this.drinkText = drinkText;
    }
    public String getDrinkMoney() {
        return this.drinkMoney;
    }
    public void setDrinkMoney(String drinkMoney) {
        this.drinkMoney = drinkMoney;
    }
    public String getDrinkNum() {
        return this.drinkNum;
    }
    public void setDrinkNum(String drinkNum) {
        this.drinkNum = drinkNum;
    }
    public String getDrinkID() {
        return this.drinkID;
    }
    public void setDrinkID(String drinkID) {
        this.drinkID = drinkID;
    }
    public String getDrinkImage() {
        return this.drinkImage;
    }
    public void setDrinkImage(String drinkImage) {
        this.drinkImage = drinkImage;
    }
    public String getMealName() {
        return this.mealName;
    }
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
    public String getMealId() {
        return this.mealId;
    }
    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
   
}
