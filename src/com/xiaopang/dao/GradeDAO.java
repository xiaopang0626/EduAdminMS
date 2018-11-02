package com.xiaopang.dao;

import com.xiaopang.bean.ScoreInfo;
import com.xiaopang.bean.StuInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.dao
 * @ClassName: GradeDAO
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/10 10:41
 */
public class GradeDAO extends BaseDAO<ScoreInfo> {
    public List<ScoreInfo> grade(StuInfo stu) {
        String sql = "select elct.stu_id,cs.course_name,score from electivecourse elct join courseinfo cs on elct.course_id = cs.course_id where elct.stu_id = '" + stu.getStu_id() + "';";
        return super.query(sql);
    }

    @Override
    protected void rs2list(List<ScoreInfo> list) {
        try {
            while (rs.next()) {
                ScoreInfo scoinfo = new ScoreInfo();
                scoinfo.setStu_id(rs.getString("stu_id"));
                scoinfo.setCourse_name(rs.getString("course_name"));
                scoinfo.setScore(rs.getInt("score"));
                list.add(scoinfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
