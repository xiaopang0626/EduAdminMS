package com.xiaopang.bean;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.bean
 * @ClassName: CourseInfo
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/9 8:40
 */
public class CourseInfo {
    private int course_id;
    private String course_name;
    private boolean course_type;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public boolean isCourse_type() {
        return course_type;
    }

    public void setCourse_type(boolean course_type) {
        this.course_type = course_type;
    }

    public CourseInfo(int course_id, String course_name, boolean course_type) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_type = course_type;
    }

    public CourseInfo(int course_id, String course_name) {
        this.course_id = course_id;
        this.course_name = course_name;
    }

    public CourseInfo() {
    }
}
