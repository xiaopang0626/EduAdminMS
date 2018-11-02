package com.xiaopang.dao;

import com.xiaopang.bean.ScoreInfo;
import com.xiaopang.bean.TeacInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.dao
 * @ClassName: AddGradeDAO
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/10 18:22
 */
public class AddGradeDAO extends BaseDAO<ScoreInfo> {
    public List<ScoreInfo> allGrade(TeacInfo tc, String course_name, String value) {
        String sql = "select stu.stu_id,stu.stu_name,elct.score from stuinfo stu join electivecourse elct on stu.stu_id = elct.stu_id join courseinfo cs on elct.course_id = cs.course_id where cs.course_teac = '" + tc.getTeac_id() + "' and course_name = '" + course_name + "' and (stu.stu_id like '%" + value + "%' or stu.stu_name like '%" + value + "%' or elct.score like '%" + value + "%');";
        return super.query(sql);
    }

    public List<ScoreInfo> allGradePaging(TeacInfo tc, String course_name, int PageIndex, int PageSize, String value) {
        String sql = "select stu.stu_id,stu.stu_name,elct.score from stuinfo stu join electivecourse elct on stu.stu_id = elct.stu_id join courseinfo cs on elct.course_id = cs.course_id where cs.course_teac = '" + tc.getTeac_id() + "' and course_name = '" + course_name + "' and (stu.stu_id like '%" + value + "%' or stu.stu_name like '%" + value + "%' or elct.score like '%" + value + "%')  limit " + ((PageIndex - 1) * PageSize) + ", " + PageSize + ";";
        return super.query(sql);
    }

    public boolean isretake(String stu_id, String course_name) {
        String sql = "select stu.stu_id,stu.stu_name,elct.score from stuinfo stu join electivecourse elct on stu.stu_id = elct.stu_id join courseinfo cs on elct.course_id = cs.course_id where elct.stu_id = '" + stu_id + "' and course_name = '" + course_name + "';";
        return !(super.query(sql).get(0).getScore() > 0);
    }

    public void addscore(String stu_id, int course_id, int score) {
        String sql = "insert into electivecourse(stu_id,course_id,score) values('" + stu_id + "','" + course_id + "','" + score + "')";
        super.update(sql);
    }

    public void updscore(String stu_id, int course_id, int score) {
        String sql = "update electivecourse set score = '" + score + "' where stu_id = '" + stu_id + "' and course_id = '" + course_id + "';";
        super.update(sql);
    }

    @Override
    protected void rs2list(List<ScoreInfo> list) {
        try {
            while (rs.next()) {
                ScoreInfo scoreInfo = new ScoreInfo();
                scoreInfo.setStu_id(rs.getString("stu_id"));
                scoreInfo.setStu_name(rs.getString("stu_name"));
                scoreInfo.setScore(rs.getString("score") == null ? -1 : rs.getInt("score"));
                list.add(scoreInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
