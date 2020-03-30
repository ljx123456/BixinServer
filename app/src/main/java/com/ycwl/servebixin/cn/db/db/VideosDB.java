package com.ycwl.servebixin.cn.db.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VideosDB {

    @Id
    private Long id;
    private String url;
    private int type;
    @Generated(hash = 169785939)
    public VideosDB(Long id, String url, int type) {
        this.id = id;
        this.url = url;
        this.type = type;
    }
    @Generated(hash = 189665865)
    public VideosDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }

}
