package com.xiaopang.bean;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.bean
 * @ClassName: TeacInfo
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 15:46
 */
public class TeacInfo {
    private String teac_id;
    private String teac_name;
    private boolean teac_sex;
    private String teac_birth;
    private String teac_addr;
    private String teac_password = "123";

    public String getTeac_id() {
        return teac_id;
    }

    public void setTeac_id(String teac_id) {
        this.teac_id = teac_id;
    }

    public String getTeac_name() {
        return teac_name;
    }

    public void setTeac_name(String teac_name) {
        this.teac_name = teac_name;
    }

    public boolean getTeac_sex() {
        return teac_sex;
    }

    public void setTeac_sex(boolean teac_sex) {
        this.teac_sex = teac_sex;
    }

    public String getTeac_birth() {
        return teac_birth;
    }

    public void setTeac_birth(String teac_birth) {
        this.teac_birth = teac_birth;
    }

    public String getTeac_addr() {
        return teac_addr;
    }

    public void setTeac_addr(String teac_addr) {
        this.teac_addr = teac_addr;
    }

    public String getTeac_password() {
        return teac_password;
    }

    public void setTeac_password(String teac_password) {
        this.teac_password = teac_password;
    }

    public TeacInfo(String teac_id, String teac_name, boolean teac_sex, String teac_birth, String teac_addr, String teac_password) {
        this.teac_id = teac_id;
        this.teac_name = teac_name;
        this.teac_sex = teac_sex;
        this.teac_birth = teac_birth;
        this.teac_addr = teac_addr;
        this.teac_password = teac_password;
    }

    public TeacInfo() {
    }
}
