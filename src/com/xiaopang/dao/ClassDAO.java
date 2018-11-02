package com.xiaopang.dao;

import com.xiaopang.bean.ClassInfo;
import com.xiaopang.bean.StuInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.dao
 * @ClassName: ClassDAO
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/9 18:52
 */
public class ClassDAO extends BaseDAO<ClassInfo> {
    public ClassInfo queryClass(StuInfo stu) {
        String class_name = stu.getStu_class();
        String sql = "select class_id,class_name,class_major from classinfo where class_name = '" + class_name + "';";
        return super.query(sql).get(0);
    }

    @Override
    protected void rs2list(List<ClassInfo> list) {
        try {
            while (rs.next()) {
                ClassInfo cls = new ClassInfo();
                cls.setClass_id(rs.getString("class_id"));
                cls.setClass_name(rs.getString("class_name"));
                cls.setClass_major(rs.getString("class_major"));
                list.add(cls);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
