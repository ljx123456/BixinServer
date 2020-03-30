package com.ycwl.servebixin.cn.db.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TagsDB {
    @Id
    private Long id;
    private int tagId;
    private String tagName;
    @Generated(hash = 120643484)
    public TagsDB(Long id, int tagId, String tagName) {
        this.id = id;
        this.tagId = tagId;
        this.tagName = tagName;
    }
    @Generated(hash = 330786379)
    public TagsDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getTagId() {
        return this.tagId;
    }
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
    public String getTagName() {
        return this.tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
