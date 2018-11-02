package com.xiaopang.view;

import com.xiaopang.bean.CourseInfo;
import com.xiaopang.bean.StuInfo;
import com.xiaopang.dao.ClassCourseDAO;

import javax.swing.*;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: CourseSelectedFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/10 9:53
 */
class CourseSelectedFrame extends JFrame {
    private StuInfo stu;
    private List<CourseInfo> courses;
    private void initFrame(){
        this.setSize(400, 300);
        this.setTitle("已登陆-->" + stu.getStu_name() + "-->所选课程");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void initComp(){
        for (int i = 0;i < courses.size();i++){
            JLabel lbl_id = new JLabel("课程id:  " + courses.get(i).getCourse_id());
            lbl_id.setBounds(30,30+i*40,100,30);
            this.add(lbl_id);
            JTextField txt_name = new JTextField(courses.get(i).getCourse_name());
            txt_name.setBounds(120,30+i*40,200,30);
            txt_name.setEditable(false);
            this.add(txt_name);
        }
    }
    CourseSelectedFrame(StuInfo stu){
        this.stu = stu;
        //初始化窗体
        initFrame();
        courses=  new ClassCourseDAO().stuselectedcourse(stu);

        //初始化控件
        initComp();

        //窗口可见
        this.setVisible(true);
    }
}
