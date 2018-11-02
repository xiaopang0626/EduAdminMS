package com.xiaopang.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.dao
 * @ClassName: BaseDAO
 * @Description: 基础数据库连接类
 * @Author: 小胖
 * @CreateDate: 2018/8/8 11:04
 */
abstract class BaseDAO <E>{
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/eduadmindatabase?useSSL=FALSE&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PWD = "321123";
    private Connection conn = null;
    private Statement stmt = null;
    ResultSet rs = null;
    static{
        //加载驱动
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private void getconn(){
        try {
            conn = DriverManager.getConnection(URL,USER,PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createstmt(){
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void closeresourse(){
        try {
            if (rs != null){
                rs.close();
                rs = null;
            }
            if (stmt != null){
                stmt.close();
                stmt = null;
            }
            if (conn != null){
                conn.close();
                conn = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void update(String sql){
        try {
            getconn();
            createstmt();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeresourse();
        }
    }
    List<E> query(String sql){
        List<E> list = new ArrayList<E>();
        try {
            getconn();
            createstmt();
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            rs2list(list);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            closeresourse();
        }
    }
    protected abstract void rs2list(List<E> list) throws SQLException;
}
