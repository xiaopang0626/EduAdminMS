package com.xiaopang.view;

import com.xiaopang.bean.TeacInfo;
import com.xiaopang.dao.TeacDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: TcLoginFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 21:27
 */
class TcLoginFrame extends JFrame {
    private TeacInfo tc;
    private JTextField txt_addr;
    private JTextField txt_password;
    private JButton jb_edit;
    private JButton jb_submit;
    private JButton jb_lookclass;
    private JButton jb_cls_schedule;
    private JButton jb_mycourse;
    private JButton jb_addgrade;

    private void initFrame(){
        this.setSize(600, 500);
        this.setTitle("已登陆-->" + tc.getTeac_name());
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComp(){
        JLabel jl_id = new JLabel("编号:");
        jl_id.setBounds(20,20,60,30);
        this.add(jl_id);

        JLabel jl_name = new JLabel("姓名:");
        jl_name.setBounds(320,20,60,30);
        this.add(jl_name);

        JLabel jl_sex = new JLabel("性别:");
        jl_sex.setBounds(20,60,60,30);
        this.add(jl_sex);

        JLabel jl_birth = new JLabel("出生日期:");
        jl_birth.setBounds(320,60,60,30);
        this.add(jl_birth);

        JLabel jl_addr = new JLabel("籍贯:");
        jl_addr.setBounds(20,100,60,30);
        this.add(jl_addr);

        JLabel jl_password = new JLabel("登陆密码:");
        jl_password.setBounds(320,100,60,30);
        this.add(jl_password);

        JTextField txt_id = new JTextField(tc.getTeac_id());
        txt_id.setBounds(100,20,180,30);
        txt_id.setEditable(false);
        this.add(txt_id);

        JTextField txt_name = new JTextField(tc.getTeac_name());
        txt_name.setBounds(400,20,180,30);
        txt_name.setEditable(false);
        this.add(txt_name);

        JTextField txt_sex = new JTextField(tc.getTeac_sex()?"男":"女");
        txt_sex.setBounds(100,60,180,30);
        txt_sex.setEditable(false);
        this.add(txt_sex);

        JTextField txt_birth = new JTextField(tc.getTeac_birth());
        txt_birth.setBounds(400,60,180,30);
        txt_birth.setEditable(false);
        this.add(txt_birth);

        txt_addr = new JTextField(tc.getTeac_addr());
        txt_addr.setBounds(100,100,180,30);
        txt_addr.setEditable(false);
        this.add(txt_addr);

        txt_password = new JTextField(tc.getTeac_password());
        txt_password.setBounds(400,100,180,30);
        txt_password.setEditable(false);
        this.add(txt_password);

        jb_edit = new JButton("编辑个人信息");
        jb_edit.setBounds(120,150,150,40);
        this.add(jb_edit);

        jb_submit = new JButton("提交");
        jb_submit.setBounds(400,150,80,40);
        this.add(jb_submit);

        jb_lookclass = new JButton("查看所带班级");
        jb_lookclass.setBounds(100,250,150,50);
        this.add(jb_lookclass);

        jb_mycourse = new JButton("查看我的课程");
        jb_mycourse.setBounds(330,250,150,50);
        this.add(jb_mycourse);

        jb_cls_schedule = new JButton("查看课程表");
        jb_cls_schedule.setBounds(100,350,150,50);
        this.add(jb_cls_schedule);

        jb_addgrade = new JButton("管理学生成绩");
        jb_addgrade.setBounds(330,350,150,50);
        this.add(jb_addgrade);

    }
    TcLoginFrame(TeacInfo tc){
        this.tc = tc;
        //初始化窗体
        initFrame();

        //初始化控件
        initComp();

        //事件处理
        jb_edit.addActionListener(new ButtonEditListener());
        jb_submit.addActionListener(new ButtonSubmitListener());
        jb_lookclass.addActionListener(new ButtonListener());
        jb_cls_schedule.addActionListener(new ButtonListener());
        jb_mycourse.addActionListener(new ButtonListener());
        jb_addgrade.addActionListener(new ButtonListener());

        //窗体可见
        this.setVisible(true);
    }
    class ButtonEditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TcLoginFrame.this.txt_addr.setEditable(true);
            TcLoginFrame.this.txt_password.setEditable(true);
        }
    }
    class ButtonSubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showConfirmDialog(null, "确定提交?", "提交", JOptionPane.YES_NO_OPTION);
            tc.setTeac_addr(txt_addr.getText());
            tc.setTeac_password(txt_password.getText());
            new TeacDAO().upd(tc);
            TcLoginFrame.this.txt_addr.setEditable(false);
            TcLoginFrame.this.txt_password.setEditable(false);
        }
    }
    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb_lookclass){
                new Teac_ClassFrame(tc);
            }
            if (e.getSource() == jb_cls_schedule){
                new ClassSchedule(tc);
            }
            if (e.getSource() == jb_mycourse){
                new TeacCourseFrame(tc);
            }
            if (e.getSource() == jb_addgrade){
                new AddGradeFrame(tc);
            }
        }
    }
}
