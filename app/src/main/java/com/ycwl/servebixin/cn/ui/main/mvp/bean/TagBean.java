package com.ycwl.servebixin.cn.ui.main.mvp.bean;

import java.util.List;

public class TagBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"typeName":"性格","tags":[{"tagId":4,"tagName":"小清新"},{"tagId":5,"tagName":"中二病"}]},{"typeName":"兴趣爱好","tags":[{"tagId":8,"tagName":"乐天派"},{"tagId":9,"tagName":"大姐大"}]}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * typeName : 性格
         * tags : [{"tagId":4,"tagName":"小清新"},{"tagId":5,"tagName":"中二病"}]
         */

        private String typeName;
        private List<TagsBean> tags;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean {
            /**
             * tagId : 4
             * tagName : 小清新
             */

            private int tagId;
            private String tagName;

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }
        }
    }
}
