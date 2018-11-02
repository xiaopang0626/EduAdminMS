package com.xiaopang.view;

import com.xiaopang.bean.StuInfo;
import com.xiaopang.bean.TeacInfo;
import com.xiaopang.dao.StuDAO;
import com.xiaopang.dao.TeacDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: InputOrUpdateFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/9 10:28
 */
class InputOrUpdateFrame extends JFrame {
    private JTextField txt_id;
    private JTextField txt_name;
    private JComboBox comb_sex;
    private JTextField txt_addr;
    private JTextField txt_class;
    private JTextField txt_birth;
    private JTextField txt_password;

    private JButton jb_ok;
    private JButton jb_clear;
    private AdminLoginFrame adminLoginFrame;

    private StuInfo stu;
    private TeacInfo tc;
    private int stuortc;

    /**
     * 初始化窗体
     */
    private void initFrame() {
        this.setSize(300, 300);
        this.setTitle("信息修改");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        jb_ok = new JButton("保存");
        jb_ok.setBounds(80, 230, 60, 20);
        this.add(jb_ok);

        jb_clear = new JButton("清空");
        jb_clear.setBounds(150, 230, 60, 20);
        this.add(jb_clear);
    }

    /**
     * 初始化控件
     */
    private void initController() {
        JLabel lbl_id = new JLabel("账号:");
        lbl_id.setBounds(20, 20, 40, 20);
        this.add(lbl_id);

        txt_id = new JTextField("");
        txt_id.setBounds(70, 20, 200, 20);
        if (stu != null)txt_id.setText(stu.getStu_id());
        if (tc != null)txt_id.setText(tc.getTeac_id());
        this.add(txt_id);

        JLabel lbl_name = new JLabel("姓名:");
        lbl_name.setBounds(20, 50, 40, 20);
        this.add(lbl_name);

        txt_name = new JTextField("");
        txt_name.setBounds(70, 50, 200, 20);
        if (stu != null)txt_name.setText(stu.getStu_name());
        if (tc != null)txt_name.setText(tc.getTeac_name());
        this.add(txt_name);

        JLabel lbl_sex = new JLabel("性别:");
        lbl_sex.setBounds(20, 80, 40, 20);
        this.add(lbl_sex);


        comb_sex = new JComboBox();
        comb_sex.addItem("男");
        comb_sex.addItem("女");
        comb_sex.setBounds(70, 80, 200, 20);
        this.add(comb_sex);

        JLabel lbl_addr = new JLabel("地址:");
        lbl_addr.setBounds(20, 110, 40, 20);
        this.add(lbl_addr);

        txt_addr = new JTextField("");
        txt_addr.setBounds(70, 110, 200, 20);
        if (stu != null)txt_addr.setText(stu.getStu_addr());
        if (tc != null)txt_addr.setText(tc.getTeac_addr());
        this.add(txt_addr);

        JLabel lbl_class = new JLabel("班级:");
        lbl_class.setBounds(20, 140, 40, 20);
        this.add(lbl_class);

        txt_class = new JTextField("");
        txt_class.setBounds(70, 140, 200, 20);
        if (stuortc == 1) this.txt_class.setEnabled(false);
        if (stu != null)txt_class.setText(stu.getStu_class());
        if (tc != null)txt_class.setEnabled(false);
        this.add(txt_class);

        JLabel lbl_birth = new JLabel("生日:");
        lbl_birth.setBounds(20, 170, 40, 20);
        this.add(lbl_birth);

        txt_birth = new JTextField("");
        txt_birth.setBounds(70, 170, 200, 20);
        if (stu != null)txt_birth.setText(stu.getStu_birth());
        if (tc != null)txt_birth.setText(tc.getTeac_birth());
        this.add(txt_birth);

        JLabel lbl_password = new JLabel("密码:");
        lbl_password.setBounds(20, 200, 40, 20);
        this.add(lbl_password);

        txt_password = new JTextField("");
        txt_password.setBounds(70, 200, 200, 20);
        if (stu != null)txt_password.setText(stu.getStu_password());
        else txt_password.setText("123");
        if (tc != null)txt_password.setText(tc.getTeac_password());
        this.add(txt_password);



    }

    InputOrUpdateFrame(AdminLoginFrame frame,int stuortc){
        this();
        this.adminLoginFrame = frame;
        this.stuortc = stuortc;

        //初始化控件
        initController();
    }
    InputOrUpdateFrame(StuInfo stu,AdminLoginFrame frame){
        this();
        this.adminLoginFrame = frame;
        this.stu = stu;

        //初始化控件
        initController();
    }
    InputOrUpdateFrame(TeacInfo tc,AdminLoginFrame frame){
        this();
        this.adminLoginFrame = frame;
        this.tc = tc;

        //初始化控件
        initController();
    }

    private InputOrUpdateFrame() {
        //初始化窗体
        initFrame();

        //事件处理
        jb_ok.addActionListener(new ButtonOKListener());
        jb_clear.addActionListener(new ButtonClearListener());

        //窗体可见
        this.setVisible(true);
    }
    class ButtonOKListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (stu != null){
                stu.setStu_id(txt_id.getText());
                stu.setStu_name(txt_name.getText());
                stu.setStu_sex(comb_sex.getSelectedIndex() == 0);
                stu.setStu_addr(txt_addr.getText());
                stu.setStu_class(txt_class.getText());
                stu.setStu_birth(txt_birth.getText());
                stu.setStu_password(txt_password.getText());
                new StuDAO().upd(stu);
                adminLoginFrame.flushData();
                InputOrUpdateFrame.this.dispose();
            }
            if (stu == null && stuortc ==0){
                stu = new StuInfo();
                stu.setStu_id(txt_id.getText());
                stu.setStu_name(txt_name.getText());
                stu.setStu_sex(comb_sex.getSelectedIndex() == 0);
                stu.setStu_addr(txt_addr.getText());
                stu.setStu_class(txt_class.getText());
                stu.setStu_birth(txt_birth.getText());
                stu.setStu_password(txt_password.getText());
                new StuDAO().add(stu);
                adminLoginFrame.flushData();
                InputOrUpdateFrame.this.dispose();
            }
            if (tc != null){
                tc.setTeac_id(txt_id.getText());
                tc.setTeac_name(txt_name.getText());
                tc.setTeac_sex(comb_sex.getSelectedIndex() == 0);
                tc.setTeac_addr(txt_addr.getText());
                tc.setTeac_birth(txt_birth.getText());
                tc.setTeac_password(txt_password.getText());
                new TeacDAO().upd(tc);
                adminLoginFrame.flushData2();
                InputOrUpdateFrame.this.dispose();
            }
            if (tc == null && stuortc == 1){
                tc = new TeacInfo();
                tc.setTeac_id(txt_id.getText());
                tc.setTeac_name(txt_name.getText());
                tc.setTeac_sex(comb_sex.getSelectedIndex() == 0);
                tc.setTeac_addr(txt_addr.getText());
                tc.setTeac_birth(txt_birth.getText());
                tc.setTeac_password(txt_password.getText());
                new TeacDAO().add(tc);
                adminLoginFrame.flushData2();
                InputOrUpdateFrame.this.dispose();
            }
        }
    }
    class ButtonClearListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            txt_id.setText("");
            txt_name.setText("");
            txt_addr.setText("");
            txt_class.setText("");
            txt_birth.setText("");
            txt_password.setText("");
        }
    }
}
