package com.ycwl.servebixin.cn.ui.main.mvp.body;

public class UpdateBody {

    private int osType;
    private int appVersion;

    public UpdateBody(int osType, int appVersion) {
        this.osType = osType;
        this.appVersion = appVersion;
    }

    public int getOsType() {
        return osType;
    }

    public void setOsType(int osType) {
        this.osType = osType;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }
}
