package com.xiaopang.dao;

import com.xiaopang.bean.StuInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.dao
 * @ClassName: StuDAO
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 14:46
 */
public class StuDAO extends BaseDAO<StuInfo> {
    public void add(StuInfo stu) {
        String sql = "insert into stuinfo(stu_id,stu_name,stu_sex,stu_addr,stu_class,stu_birth,stu_password) values('" + stu.getStu_id() + "','" + stu.getStu_name() + "',b'" + (stu.getStu_sex() ? 1 : 0) + "','" + stu.getStu_addr() + "','" + stu.getStu_class() + "','" + stu.getStu_birth() + "','" + stu.getStu_password() + "');";
        super.update(sql);
    }
    public StuInfo findById(String stu_id){
        String sql = "select stu_id,stu_name,stu_sex,stu_addr,stu_class,stu_birth,stu_password from stuinfo where stu_id = '"+stu_id+"'";
        return super.query(sql).get(0);
    }

    public void delete(String stu_id) {
        String sql = "delete from stuinfo where stu_id = '" + stu_id + "'";
        super.update(sql);
    }

    public void upd(StuInfo stu) {
        String sql = "update stuinfo set stu_name = '" + stu.getStu_name() + "',stu_sex = b'" + (stu.getStu_sex()?1:0) + "',stu_addr = '" + stu.getStu_addr() + "',stu_class = '" + stu.getStu_class() + "',stu_birth = '" + stu.getStu_birth() + "',stu_password = '" + stu.getStu_password() + "' where stu_id = '" + stu.getStu_id() + "';";
        super.update(sql);
    }
    public List<StuInfo> selectAll(){
        String sql = "select stu_id,stu_name,stu_sex,stu_addr,stu_class,stu_birth,stu_password from stuinfo";
        return super.query(sql);
    }
    public List<StuInfo> searchAndPaging(String value,int PageIndex,int PageSize){
        String sql = "select stu_id,stu_name,stu_sex,stu_addr,stu_class,stu_birth,stu_password from stuinfo where stu_id like '%"+ value +"%' or stu_name like '%"+ value +"%' or stu_addr like '%"+ value +"%' or stu_class like '%"+ value +"%' or stu_birth like '%"+ value +"%' limit " + ((PageIndex-1) * PageSize) + ", " + PageSize + ";";
        return super.query(sql);
    }
    public StuInfo login(String stu_id,String stu_password){
        String sql = "select stu_id,stu_name,stu_sex,stu_addr,stu_class,stu_birth,stu_password from stuinfo where stu_id = '"+stu_id+"' and stu_password = '"+stu_password+"';";
        List<StuInfo> list = super.query(sql);
        return list.size()>0?list.get(0):null;
    }

    @Override
    protected void rs2list(List<StuInfo> list) {
        try{
            while (rs.next()){
                StuInfo stu = new StuInfo();
                stu.setStu_id(rs.getString("stu_id"));
                stu.setStu_name(rs.getString("stu_name"));
                stu.setStu_sex(rs.getBoolean("stu_sex"));
                stu.setStu_addr(rs.getString("stu_addr"));
                stu.setStu_class(rs.getString("stu_class"));
                stu.setStu_birth(rs.getString("stu_birth"));
                stu.setStu_password(rs.getString("stu_password"));
                list.add(stu);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
