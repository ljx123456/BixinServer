package com.ycwl.servebixin.cn.ui.message.mvp.bean;

import java.util.List;

public class BannerBean {

    /**
     * code : 0
     * message : 请求成功.
     * data : [{"image":"http://pic.bixinyule.com/service/banner/service_banner_01.png","name":"比心召集令","description":"","url":"http://service.bixinyule.com/service/banner/call_up"},{"image":"http://pic.bixinyule.com/service/banner/service_banner_02.png","name":"好礼在比心，单单送现金","description":"","url":"http://service.bixinyule.com/service/banner/gift"},{"image":"http://pic.bixinyule.com/service/banner/service_banner_03.png","name":"比心福利季，奖励送不停","description":"","url":"http://service.bixinyule.com/service/banner/reward"},{"image":"http://pic.bixinyule.com/service/banner/service_banner_04.png","name":"推荐有礼，等你来取","description":"","url":"http://service.bixinyule.com/service/banner/recommend"}]
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
         * image : http://pic.bixinyule.com/service/banner/service_banner_01.png
         * name : 比心召集令
         * description :
         * url : http://service.bixinyule.com/service/banner/call_up
         */

        private String image;
        private String name;
        private String description;
        private String url;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
