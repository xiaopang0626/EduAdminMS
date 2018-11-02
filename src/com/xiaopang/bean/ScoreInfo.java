package com.xiaopang.bean;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.bean
 * @ClassName: ScoreInfo
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/10 10:48
 */
public class ScoreInfo {
    private String stu_id;
    private String stu_name;
    private String course_name;
    private int score;

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public ScoreInfo(String stu_id,String stu_name, String course_name, int score) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.course_name = course_name;
        this.score = score;
    }

    public ScoreInfo() {
    }
}
