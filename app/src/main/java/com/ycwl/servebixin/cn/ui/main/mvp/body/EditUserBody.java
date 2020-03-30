package com.ycwl.servebixin.cn.ui.main.mvp.body;

import java.util.List;

public class EditUserBody {

    /**
     * nickname : 啦啦啦
     * birthday : 1999-07-01 00:00:00
     * occupation : 1
     * tagIds : [4,5,6,7]
     * videos : [{"url":"http://pic.bixinyule.com/b8d72629-d5f0-4e8e-9105-e1a7d102f409","type":1},{"url":"http://pic.bixinyule.com/8a52f371-debd-49b5-853a-a9c457768d3a","type":2}]
     */

    private String nickname;
    private String birthday;
    private String occupation;
    private String avatar;
    private List<Integer> tagIds;
    private List<VideosBean> videos;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean {
        /**
         * url : http://pic.bixinyule.com/b8d72629-d5f0-4e8e-9105-e1a7d102f409
         * type : 1
         */

        private String url;
        private int type;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public VideosBean(String url, int type) {
            this.url = url;
            this.type = type;
        }
    }

    public EditUserBody(String nickname, String birthday, String occupation, List<Integer> tagIds, List<VideosBean> videos) {
        this.nickname = nickname;
        this.birthday = birthday;
        this.occupation = occupation;
        this.tagIds = tagIds;
        this.videos = videos;
    }

    public EditUserBody(String nickname, String birthday, String occupation, String avatar, List<Integer> tagIds, List<VideosBean> videos) {
        this.nickname = nickname;
        this.birthday = birthday;
        this.occupation = occupation;
        this.avatar = avatar;
        this.tagIds = tagIds;
        this.videos = videos;
    }



    public EditUserBody(String birthday, String occupation, List<Integer> tagIds, List<VideosBean> videos) {
        this.birthday = birthday;
        this.occupation = occupation;
        this.tagIds = tagIds;
        this.videos = videos;
    }

    public EditUserBody(String birthday, String occupation, List<Integer> tagIds, List<VideosBean> videos, String avatar) {
        this.birthday = birthday;
        this.occupation = occupation;
        this.avatar = avatar;
        this.tagIds = tagIds;
        this.videos = videos;
    }



}
