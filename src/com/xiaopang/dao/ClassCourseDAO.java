package com.xiaopang.dao;

import com.xiaopang.bean.ClassInfo;
import com.xiaopang.bean.CourseInfo;
import com.xiaopang.bean.StuInfo;
import com.xiaopang.bean.TeacInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.dao
 * @ClassName: ClassCourseDAO
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/9 19:19
 */
public class ClassCourseDAO extends BaseDAO<CourseInfo> {
    public List<CourseInfo> class_courses(String class_name) {
        String sql = "select course_id,course_name from courseinfo where course_major = (select major_id from major where major_name = (select class_major from classinfo where class_name = '" + class_name + "')) and course_type = b'0'";
        return super.query(sql);
    }

    public List<CourseInfo> optional_courses() {
        String sql = "select course_id,course_name from courseinfo where course_type = b'1';";
        return super.query(sql);
    }

    public void addelectivecourse(StuInfo stu, CourseInfo courseInfo) {
        String sql = "insert into electivecourse(stu_id,course_id) values('" + stu.getStu_id() + "','" + courseInfo.getCourse_id() + "');";
        super.update(sql);
    }

    public List<CourseInfo> stuselectedcourse(StuInfo stu) {
        String sql = "select elcs.course_id,cs.course_name from stuinfo stu join electivecourse elcs on stu.stu_id = elcs.stu_id join courseinfo cs on elcs.course_id = cs.course_id where stu.stu_id = '" + stu.getStu_id() + "' and cs.course_type = b'1';";
        return super.query(sql);
    }
    public List<CourseInfo> teacCourse(TeacInfo tc){
        String sql = "select course_id,course_name from courseinfo where course_teac = '"+tc.getTeac_id()+"'";
        return super.query(sql);
    }


    @Override
    protected void rs2list(List<CourseInfo> list) {
        try {
            while (rs.next()) {
                CourseInfo csinfo = new CourseInfo();
                csinfo.setCourse_id(rs.getInt("course_id"));
                csinfo.setCourse_name(rs.getString("course_name"));
                list.add(csinfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
