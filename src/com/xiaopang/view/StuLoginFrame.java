package com.xiaopang.view;

import com.xiaopang.bean.StuInfo;
import com.xiaopang.dao.StuDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showConfirmDialog;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: StuLoginFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 19:21
 */
class StuLoginFrame extends JFrame {
    private StuInfo stu;
    private JTextField txt_addr;
    private JTextField txt_class;
    private JTextField txt_password;
    private JButton jb_edit;
    private JButton jb_submit;
    private JButton jb_lookclass;
    private JButton jb_lookcourse;
    private JButton jb_electivecourse;
    private JButton jb_grade;

    private void initFrame(){
        this.setSize(600, 500);
        this.setTitle("已登陆-->" + stu.getStu_name());
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void initComp(){
        JLabel lbl_id = new JLabel("学号:");
        lbl_id.setBounds(20,30,60,30);
        this.add(lbl_id);

        JLabel lbl_name = new JLabel("姓名:");
        lbl_name.setBounds(20,80,60,30);
        this.add(lbl_name);

        JLabel lbl_sex = new JLabel("性别:");
        lbl_sex.setBounds(20,130,60,30);
        this.add(lbl_sex);

        JLabel lbl_addr = new JLabel("籍贯:");
        lbl_addr.setBounds(20,180,60,30);
        this.add(lbl_addr);

        JLabel lbl_class = new JLabel("班级:");
        lbl_class.setBounds(20,230,60,30);
        this.add(lbl_class);

        JLabel lbl_birth = new JLabel("出生日期:");
        lbl_birth.setBounds(20,280,60,30);
        this.add(lbl_birth);

        JLabel lbl_password = new JLabel("登陆密码:");
        lbl_password.setBounds(20,330,60,30);
        this.add(lbl_password);

        JTextField txt_id = new JTextField(stu.getStu_id());
        txt_id.setBounds(100,30,300,30);
        txt_id.setEditable(false);
        this.add(txt_id);

        JTextField txt_name = new JTextField(stu.getStu_name());
        txt_name.setBounds(100,80,300,30);
        txt_name.setEditable(false);
        this.add(txt_name);

        JTextField txt_sex = new JTextField(stu.getStu_sex() ? "男" : "女");
        txt_sex.setBounds(100,130,300,30);
        txt_sex.setEditable(false);
        this.add(txt_sex);

        txt_addr= new JTextField(stu.getStu_addr());
        txt_addr.setBounds(100,180,300,30);
        txt_addr.setEditable(false);
        this.add(txt_addr);

        txt_class= new JTextField(stu.getStu_class());
        txt_class.setBounds(100,230,300,30);
        txt_class.setEditable(false);
        this.add(txt_class);

        JTextField txt_birth = new JTextField(stu.getStu_birth());
        txt_birth.setBounds(100,280,300,30);
        txt_birth.setEditable(false);
        this.add(txt_birth);

        txt_password= new JTextField(stu.getStu_password());
        txt_password.setBounds(100,330,300,30);
        txt_password.setEditable(false);
        this.add(txt_password);

        jb_edit = new JButton("编辑个人信息");
        jb_edit.setBounds(80,400,140,30);
        this.add(jb_edit);

        jb_submit = new JButton("提交");
        jb_submit.setBounds(260,400,60,30);
        this.add(jb_submit);

        jb_lookclass = new JButton("查看所在班级");
        jb_lookclass.setBounds(430,60,120,50);
        this.add(jb_lookclass);

        jb_electivecourse = new JButton("选择课程");
        jb_electivecourse.setBounds(430,140,120,50);
        this.add(jb_electivecourse);

        jb_lookcourse = new JButton("查看所选课程");
        jb_lookcourse.setBounds(430,220,120,50);
        this.add(jb_lookcourse);

        jb_grade = new JButton("查看成绩");
        jb_grade.setBounds(430,300,120,50);
        this.add(jb_grade);

    }
    StuLoginFrame(StuInfo stu){
        this.stu = stu;
        //初始化窗体
        initFrame();

        //初始化控件
        initComp();

        //事件处理
        jb_edit.addActionListener(new ButtonEditListener());
        jb_submit.addActionListener(new ButtonSubmitListener());
        jb_lookclass.addActionListener(new ButtonListener());
        jb_lookcourse.addActionListener(new ButtonListener());
        jb_electivecourse.addActionListener(new ButtonListener());
        jb_grade.addActionListener(new ButtonListener());

        //窗体可见
        this.setVisible(true);
    }

    class ButtonEditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StuLoginFrame.this.txt_addr.setEditable(true);
            StuLoginFrame.this.txt_class.setEditable(true);
            StuLoginFrame.this.txt_password.setEditable(true);
        }
    }
    class ButtonSubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = showConfirmDialog(null, "确定提交?", "提交", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                stu.setStu_addr(txt_addr.getText());
                stu.setStu_class(txt_class.getText());
                stu.setStu_password(txt_password.getText());
                new StuDAO().upd(stu);
                StuLoginFrame.this.txt_addr.setEditable(false);
                StuLoginFrame.this.txt_class.setEditable(false);
                StuLoginFrame.this.txt_password.setEditable(false);
            }
        }
    }
    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb_lookclass){
                new ClassInfoFrame(stu);

            }
            if (e.getSource() == jb_electivecourse){
                new ElectiveCoursesFrame(stu);
            }
            if (e.getSource() == jb_lookcourse){
                new CourseSelectedFrame(stu);

            }
            if (e.getSource() == jb_grade){
                new StuGradeFrame(stu);
            }
        }
    }
}
