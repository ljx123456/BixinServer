package com.ycwl.servebixin.cn.db.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MerchantDB {
    @Id(autoincrement = true)
    private Long id;
    private String MerchantName;//商家名称
    private String MerchantID;//商家ID
    private String MerchantImage;//商家图片
    private String BaofangType;//包房类型
    private String BaofangTypeName;//包房类型name
    private String BaofangID;//包房ID
    private String BaofangName;//包房name
    private String MerchantAdds;//商家地址
    private String baofangPrice;//包房服务费
    @Generated(hash = 609450260)
    public MerchantDB(Long id, String MerchantName, String MerchantID,
            String MerchantImage, String BaofangType, String BaofangTypeName,
            String BaofangID, String BaofangName, String MerchantAdds,
            String baofangPrice) {
        this.id = id;
        this.MerchantName = MerchantName;
        this.MerchantID = MerchantID;
        this.MerchantImage = MerchantImage;
        this.BaofangType = BaofangType;
        this.BaofangTypeName = BaofangTypeName;
        this.BaofangID = BaofangID;
        this.BaofangName = BaofangName;
        this.MerchantAdds = MerchantAdds;
        this.baofangPrice = baofangPrice;
    }
    @Generated(hash = 1670692506)
    public MerchantDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMerchantName() {
        return this.MerchantName;
    }
    public void setMerchantName(String MerchantName) {
        this.MerchantName = MerchantName;
    }
    public String getMerchantID() {
        return this.MerchantID;
    }
    public void setMerchantID(String MerchantID) {
        this.MerchantID = MerchantID;
    }
    public String getMerchantImage() {
        return this.MerchantImage;
    }
    public void setMerchantImage(String MerchantImage) {
        this.MerchantImage = MerchantImage;
    }
    public String getBaofangType() {
        return this.BaofangType;
    }
    public void setBaofangType(String BaofangType) {
        this.BaofangType = BaofangType;
    }
    public String getBaofangTypeName() {
        return this.BaofangTypeName;
    }
    public void setBaofangTypeName(String BaofangTypeName) {
        this.BaofangTypeName = BaofangTypeName;
    }
    public String getBaofangID() {
        return this.BaofangID;
    }
    public void setBaofangID(String BaofangID) {
        this.BaofangID = BaofangID;
    }
    public String getBaofangName() {
        return this.BaofangName;
    }
    public void setBaofangName(String BaofangName) {
        this.BaofangName = BaofangName;
    }
    public String getMerchantAdds() {
        return this.MerchantAdds;
    }
    public void setMerchantAdds(String MerchantAdds) {
        this.MerchantAdds = MerchantAdds;
    }
    public String getBaofangPrice() {
        return this.baofangPrice;
    }
    public void setBaofangPrice(String baofangPrice) {
        this.baofangPrice = baofangPrice;
    }
}
