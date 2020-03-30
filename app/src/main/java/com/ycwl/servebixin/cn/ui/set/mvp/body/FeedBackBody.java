package com.ycwl.servebixin.cn.ui.set.mvp.body;

public class FeedBackBody {

    /**
     * content : 这个产品真好用
     */

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FeedBackBody(String content) {
        this.content = content;
    }
}
