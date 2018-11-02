package com.xiaopang.bean;

import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.bean
 * @ClassName: Major
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 16:28
 */
public class Major {
    private String major_id;
    private String major_name;
    private List<ClassInfo> classInfos;
    void addclass(ClassInfo cls){
        for (ClassInfo classInfo : classInfos) {
            if (classInfo.equals(cls)) {
                return;
            }
        }
        classInfos.add(cls);
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public List<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(List<ClassInfo> classInfos) {
        this.classInfos = classInfos;
    }

    public Major(String major_id, String major_name) {
        this.major_id = major_id;
        this.major_name = major_name;
    }

    public Major() {
    }
}
