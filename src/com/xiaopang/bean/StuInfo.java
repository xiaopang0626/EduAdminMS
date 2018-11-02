package com.xiaopang.bean;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.bean
 * @ClassName: StuInfo
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 12:36
 */
public class StuInfo {
    private String stu_id;
    private String stu_name;
    private boolean stu_sex;
    private String stu_addr;
    private String stu_class;
    private String stu_birth;
    private String stu_password = "123";

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public boolean getStu_sex() {
        return stu_sex;
    }

    public void setStu_sex(Boolean stu_sex) {
        this.stu_sex = stu_sex;
    }

    public String getStu_addr() {
        return stu_addr;
    }

    public void setStu_addr(String stu_addr) {
        this.stu_addr = stu_addr;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public String getStu_birth() {
        return stu_birth;
    }

    public void setStu_birth(String stu_birth) {
        this.stu_birth = stu_birth;
    }

    public String getStu_password() {
        return stu_password;
    }

    public void setStu_password(String stu_password) {
        this.stu_password = stu_password;
    }

    public StuInfo(String stu_id, String stu_name, Boolean stu_sex, String stu_addr, String stu_class, String stu_birth, String stu_password) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.stu_sex = stu_sex;
        this.stu_addr = stu_addr;
        this.stu_class = stu_class;
        this.stu_birth = stu_birth;
        this.stu_password = stu_password;
    }

    public StuInfo() {
    }
}
