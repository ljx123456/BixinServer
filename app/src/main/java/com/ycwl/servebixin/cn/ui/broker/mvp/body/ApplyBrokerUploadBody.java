package com.ycwl.servebixin.cn.ui.broker.mvp.body;

import java.util.List;

public class ApplyBrokerUploadBody {

    private List<VideosBean> videos;

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean {
        /**
         * url : http://e.hiphotos.baidu.com/nuomi/pic/item/caef76094b36acaf937c13a775d98d1000e99cc9.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public VideosBean(String url) {
            this.url = url;
        }
    }

    public ApplyBrokerUploadBody(List<VideosBean> videos) {
        this.videos = videos;
    }
}
