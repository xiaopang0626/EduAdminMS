package com.xiaopang.dao;

import com.xiaopang.bean.TeacInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.dao
 * @ClassName: TeacDAO
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 15:48
 */
public class TeacDAO extends BaseDAO<TeacInfo> {
    public void add(TeacInfo tc) {
        String sql = "insert into teacinfo(teac_id,teac_name,teac_sex,teac_birth,teac_addr,teac_password)values('" + tc.getTeac_id() + "','" + tc.getTeac_name() + "',b'" + (tc.getTeac_sex() ? 1 : 0) + "','" + tc.getTeac_birth() + "','" + tc.getTeac_addr() + "','" + tc.getTeac_password() + "');";
        super.update(sql);
    }

    public void del(String teac_id) {
        String sql = "delete from teacinfo where teac_id = '" + teac_id + "';";
        super.update(sql);
    }

    public void upd(TeacInfo tc) {
        String sql = "update teacinfo set teac_name = '" + tc.getTeac_name() + "',teac_sex = b'" + (tc.getTeac_sex() ? 1 : 0) + "',teac_birth = '" + tc.getTeac_birth() + "',teac_addr = '" + tc.getTeac_addr() + "',teac_password = '" + tc.getTeac_password() + "' where teac_id = '" + tc.getTeac_id() + "';";
        super.update(sql);
    }

    public List<TeacInfo> selectAll() {
        String sql = "select * from teacinfo;";
        return super.query(sql);
    }
    public TeacInfo login(String tc_id,String tc_password){
        String sql = "select teac_id,teac_name,teac_sex,teac_birth,teac_addr,teac_password from teacinfo where teac_id = '"+tc_id+"' and teac_password = '"+tc_password+"';";
        List<TeacInfo> list = super.query(sql);
        return list.size()>0?list.get(0):null;
    }
    public List<TeacInfo> searchAndPaging(String value,int PageIndex,int PageSize){
        String sql = "select teac_id,teac_name,teac_sex,teac_birth,teac_addr,teac_password from teacinfo where teac_id like '%"+value+"%' or teac_name like '%"+value+"%' or teac_sex like '%"+value+"%' or teac_birth like '%"+value+"%' or teac_addr like '%"+value+"%' limit " + ((PageIndex-1) * PageSize) + ", " + PageSize + ";";
        return super.query(sql);
    }

    @Override
    protected void rs2list(List<TeacInfo> list) {
        try {
            while (rs.next()){
                TeacInfo tc = new TeacInfo();
                tc.setTeac_id(rs.getString("teac_id"));
                tc.setTeac_name(rs.getString("teac_name"));
                tc.setTeac_sex(rs.getBoolean("teac_sex"));
                tc.setTeac_birth(rs.getString("teac_birth"));
                tc.setTeac_addr(rs.getString("teac_addr"));
                tc.setTeac_password(rs.getString("teac_password"));
                list.add(tc);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
