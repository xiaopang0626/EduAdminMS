package com.xiaopang.view;

import com.xiaopang.bean.StuInfo;
import com.xiaopang.bean.TeacInfo;
import com.xiaopang.dao.StuDAO;
import com.xiaopang.dao.TeacDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: LoginFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 16:50
 */
public class LoginFrame extends JFrame {
    private JTextField txt_id;
    private JPasswordField txt_password;
    private JButton jb_ok;
    private JRadioButton stu;
    private JRadioButton tc;
    private JRadioButton admin;


    /**
     * 初始化窗体
     */
    private void initFrame() {
        this.setSize(300, 300);
        this.setTitle("登陆");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 初始化控件
     */
    private void initController() {
        JLabel lbl_id = new JLabel("账号:");
        txt_id = new JTextField("");
        txt_id.addKeyListener(new KeyMonitor());


        JLabel lbl_password = new JLabel("密码:");
        txt_password = new JPasswordField("");
        txt_password.addKeyListener(new KeyMonitor());


        jb_ok = new JButton("登陆");


        ButtonGroup btg_choose = new ButtonGroup();
        JLabel lbl_type = new JLabel("登陆类型:");
        lbl_type.setBounds(10, 200, 60, 20);
        stu = new JRadioButton("学生",true);
        stu.setBounds(70, 200, 60, 20);
        stu.addKeyListener(new KeyMonitor());
        tc = new JRadioButton("教师");
        tc.setBounds(130, 200, 60, 20);
        tc.addKeyListener(new KeyMonitor());
        admin = new JRadioButton("管理员");
        admin.setBounds(190, 200, 70, 20);
        admin.addKeyListener(new KeyMonitor());

        lbl_id.setBounds(20, 50, 40, 20);
        txt_id.setBounds(70, 50, 200, 20);


        lbl_password.setBounds(20, 100, 40, 20);
        txt_password.setBounds(70, 100, 200, 20);


        jb_ok.setBounds(80, 140, 80, 30);

        btg_choose.add(stu);
        btg_choose.add(tc);
        btg_choose.add(admin);

        this.add(lbl_id);
        this.add(txt_id);


        this.add(lbl_password);
        this.add(txt_password);

        this.add(jb_ok);

        this.add(lbl_type);
        this.add(stu);
        this.add(tc);
        this.add(admin);
    }


    /**
     * 窗体显示
     */
    public LoginFrame() {
        //初始化窗体
        initFrame();

        //初始化控件
        initController();

        //事件处理
        jb_ok.addActionListener(new ButtonOKListener());

        //窗体可见
        this.setVisible(true);
    }

    /**
     * 学生登陆操作
     */
    private void stulogin() {

        StuInfo stu = new StuDAO().login(txt_id.getText(), String.valueOf(txt_password.getPassword()));
        if (stu == null) {
            JOptionPane.showMessageDialog(null, "账号密码不正确,请重新输入!");
            return;
        }
        LoginFrame.this.dispose();
        new StuLoginFrame(stu);
    }

    /**
     * 教师登陆操作
     */
    private void tclogin(){
        TeacInfo tc = new TeacDAO().login(txt_id.getText(), String.valueOf(txt_password.getPassword()));
        if (tc == null) {
            JOptionPane.showMessageDialog(null, "账号密码不正确,请重新输入!");
            return;
        }
        LoginFrame.this.dispose();
        new TcLoginFrame(tc);
    }

    /**
     * 管理员登陆
     */
    private void adminlogin(){
        if (txt_id.getText().equals("admin") && String.valueOf(txt_password.getPassword()).equals("admin")) {
            LoginFrame.this.dispose();
            new AdminLoginFrame();

        } else {
            JOptionPane.showMessageDialog(null, "账号密码不正确,请重新输入!");
        }
    }

    /**
     * 按下回车就可以登陆
     */
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (stu.isSelected()) {
                    stulogin();
                }
                if (tc.isSelected()) {
                    tclogin();
                }
                if (admin.isSelected()) {
                    adminlogin();
                }
            }
        }
    }


    /**
     * ok按钮监听器
     */
    class ButtonOKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (stu.isSelected()) {
                stulogin();
            }
            if (tc.isSelected()) {
                tclogin();
            }
            if (admin.isSelected()) {
                adminlogin();
            }
        }
    }
}
