package com.xiaopang.bean;

import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.bean
 * @ClassName: ClassInfo
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 16:22
 */
public class ClassInfo {
    private String class_id;
    private String class_name;
    private String class_major;
    private List<CourseInfo> class_courses;

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_major() {
        return class_major;
    }

    public void setClass_major(String class_major) {
        this.class_major = class_major;
    }

    public List<CourseInfo> getClass_courses() {
        return class_courses;
    }

    public void setClass_courses(List<CourseInfo> class_courses) {
        this.class_courses = class_courses;
    }

    public ClassInfo(String class_id, String class_name, String class_major) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.class_major = class_major;
    }

    public ClassInfo() {
    }
}
