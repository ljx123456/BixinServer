package com.ycwl.servebixin.cn.ui.login.mvp.body;

public class ValidationCodeBody {
    /**
     * phone : 15101053755
     * code : 4263
     * codeType : 0
     */

    private String phone;
    private String code;
    private int codeType;

    public ValidationCodeBody(String phone, String code, int codeType) {
        this.phone = phone;
        this.code = code;
        this.codeType = codeType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }
}
