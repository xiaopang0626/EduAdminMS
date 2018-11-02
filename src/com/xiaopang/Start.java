package com.xiaopang;

import com.xiaopang.view.LoginFrame;


/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang
 * @ClassName: Start
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 14:48
 */
public class Start {
    public static void main(String[] args) {
        /*StuDAO stu = new StuDAO();
        StuInfo stu1 = new StuInfo();
        stu1.setStu_id("20180101002");
        stu1.setStu_name("小胖");
        stu1.setStu_sex(false);
        stu1.setStu_addr("中国山西省临汾市汾西县");
        stu1.setStu_birth("1996-06-26");
        stu1.setStu_class("软件工程一班");
        stu1.setStu_password("123123123");
        stu.upd(stu1);
        List list = stu.selectAll();
        System.out.println(list.size());*/
        new LoginFrame();
    }
}
