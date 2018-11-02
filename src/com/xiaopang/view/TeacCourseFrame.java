package com.xiaopang.view;

import com.xiaopang.bean.CourseInfo;
import com.xiaopang.bean.TeacInfo;
import com.xiaopang.dao.ClassCourseDAO;

import javax.swing.*;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: TeacCourseFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/10 15:24
 */
class TeacCourseFrame extends JFrame {
    private TeacInfo tc;
    private List<CourseInfo> courses;

    private void initFrame(){
        this.setSize(400, 400);
        this.setTitle("已登陆-->" + tc.getTeac_name() + "-->课程信息");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void initComp(){
        for (int i = 0;i < courses.size();i++){
            JLabel lbl_id = new JLabel("所教课程id:  " + courses.get(i).getCourse_id());
            lbl_id.setBounds(30,30+i*40,100,30);
            this.add(lbl_id);
            JTextField txt_name = new JTextField(courses.get(i).getCourse_name());
            txt_name.setBounds(120,30+i*40,200,30);
            txt_name.setEditable(false);
            this.add(txt_name);
        }
    }

    TeacCourseFrame(TeacInfo tc){
        this.tc = tc;
        //初始化窗体
        initFrame();

        courses = new ClassCourseDAO().teacCourse(tc);

        //初始化控件
        initComp();

        //事件处理

        //窗体可见
        this.setVisible(true);
    }
}
