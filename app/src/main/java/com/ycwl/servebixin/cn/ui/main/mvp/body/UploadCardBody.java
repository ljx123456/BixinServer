package com.ycwl.servebixin.cn.ui.main.mvp.body;

import java.util.List;

public class UploadCardBody {

    /**
     * realName : 张三
     * idCardNo : 13454131543154313
     * videos : [{"url":"http://pic.bixinyule.com/Ftcfvpg1MT-ExYRxhP9KXx5XyPzz"},{"url":"http://pic.bixinyule.com/Ftcfvpg1MT-ExYRxhP9KXx5XyPzz"}]
     */

    private String realName;
    private String idCardNo;
    private List<VideosBean> videos;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean {
        /**
         * url : http://pic.bixinyule.com/Ftcfvpg1MT-ExYRxhP9KXx5XyPzz
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

    public UploadCardBody(String realName, String idCardNo, List<VideosBean> videos) {
        this.realName = realName;
        this.idCardNo = idCardNo;
        this.videos = videos;
    }
}
