package com.xiaopang.view;

import com.xiaopang.bean.CourseInfo;
import com.xiaopang.bean.StuInfo;
import com.xiaopang.dao.ClassCourseDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static javax.swing.JOptionPane.showConfirmDialog;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: ElectiveCoursesFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/9 21:26
 */
class ElectiveCoursesFrame extends JFrame {
    private StuInfo stu;
    private List<CourseInfo> courseInfos = new ClassCourseDAO().optional_courses();
    private JButton jb_ok;
    private ButtonGroup bg;

    private void initFrame(){
        this.setSize(400, 500);
        this.setTitle("已登陆-->" + stu.getStu_name() + "-->选课");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void initComp(){
        JLabel lbl = new JLabel("课程选择:");
        lbl.setBounds(20,30,120,30);
        this.add(lbl);
        bg = new ButtonGroup();
        for (int i = 0;i < courseInfos.size();i++) {
            JRadioButton button=new JRadioButton(courseInfos.get(i).getCourse_name());
            button.setBounds(50,70 + i*30,200,30);
            this.add(button);
            bg.add(button);
        }
        jb_ok = new JButton("提交");
        jb_ok.setBounds(120,400,80,30);
        this.add(jb_ok);
    }

    ElectiveCoursesFrame(StuInfo stu){
        this.stu = stu;
        //初始化窗体
        initFrame();

        //初始化控件
        initComp();

        //事件处理
        jb_ok.addActionListener(new ButtonOKListener());

        //窗口可见
        this.setVisible(true);
    }
    class ButtonOKListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Enumeration<AbstractButton> enu = bg.getElements();
            for (int i = 0;enu.hasMoreElements();i++){
                if (enu.nextElement().isSelected()){
                    int j = showConfirmDialog(null, "确定提交?", "提交", JOptionPane.YES_NO_OPTION);
                    if (j == 0) new ClassCourseDAO().addelectivecourse(stu,courseInfos.get(i));
                }
            }
        }
    }
}
