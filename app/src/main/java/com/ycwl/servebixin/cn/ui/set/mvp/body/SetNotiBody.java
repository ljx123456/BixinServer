package com.ycwl.servebixin.cn.ui.set.mvp.body;

public class SetNotiBody {

    /**
     * id : 1
     * type : 1
     */

    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SetNotiBody(String id, String type) {
        this.id = id;
        this.type = type;
    }
}
