package com.xiaopang.view;

import com.xiaopang.bean.StuInfo;
import com.xiaopang.bean.TeacInfo;
import com.xiaopang.dao.StuDAO;
import com.xiaopang.dao.TeacDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.JOptionPane.showConfirmDialog;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: AdminLoginFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/8 22:50
 */
class AdminLoginFrame extends JFrame {
    private DefaultTableModel model;
    private JTable table;

    private JButton jb_add;
    private JButton jb_delete;
    private JButton jb_update;
    private JButton jb_PageUp;
    private JButton jb_PageDown;
    private JButton jb_FirsePage;
    private JButton jb_LastPage;
    private JButton jb_search;
    private JTextField txt_search;
    private JTextField txt_currentPageIndex;
    private JTextField txt_totalPage;
    private JComboBox comboBox;
    private int PageIndex = 1;
    private final int PAGESIZE = 5;
    private int totalPages = 1;
    private StringBuilder value = new StringBuilder("");


    private void initFrame() {
        this.setSize(800, 600);
        this.setTitle("已登陆-->管理员");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComp() {
        JPanel NorthPanel = new JPanel();
        NorthPanel.setBackground(Color.CYAN);
        NorthPanel.setPreferredSize(new Dimension(0, 40));
        this.add(NorthPanel, BorderLayout.NORTH);

        NorthPanel.setLayout(null);
        comboBox = new JComboBox();
        comboBox.addItem("学生信息管理");
        comboBox.addItem("教师信息管理");
        comboBox.setBounds(20, 5, 110, 30);
        comboBox.setSelectedIndex(0);
        NorthPanel.add(comboBox);

        jb_add = new JButton("添加");
        jb_add.setBounds(200, 5, 120, 30);
        NorthPanel.add(jb_add);

        jb_delete = new JButton("删除");
        jb_delete.setBounds(400, 5, 120, 30);
        NorthPanel.add(jb_delete);

        jb_update = new JButton("修改");
        jb_update.setBounds(600, 5, 120, 30);
        NorthPanel.add(jb_update);

        JPanel CenterPanel = new JPanel();
        CenterPanel.setLayout(null);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(40, 50, 700, 450);
        table.setCellSelectionEnabled(true);
        table.setRowHeight(30);

        table.setOpaque(false);//将table设置为透明
        jsp.setOpaque(false);//将jsp根面板设置为透明
        jsp.getViewport().setOpaque(false);

        jb_PageUp = new JButton("上一页");
        jb_PageUp.setBounds(240, 400, 80, 30);
        CenterPanel.add(jb_PageUp);

        jb_PageDown = new JButton("下一页");
        jb_PageDown.setBounds(460, 400, 80, 30);
        CenterPanel.add(jb_PageDown);

        jb_FirsePage = new JButton("首页");
        jb_FirsePage.setBounds(140, 400, 80, 30);
        CenterPanel.add(jb_FirsePage);

        jb_LastPage = new JButton("尾页");
        jb_LastPage.setBounds(560, 400, 80, 30);
        CenterPanel.add(jb_LastPage);

        jb_search = new JButton("搜索");
        jb_search.setBounds(460, 450, 60, 30);
        CenterPanel.add(jb_search);

        txt_search = new JTextField();
        txt_search.setBounds(200, 450, 250, 30);
        CenterPanel.add(txt_search);

        txt_currentPageIndex = new JTextField();
        txt_currentPageIndex.setBounds(380, 400, 15, 30);
        txt_currentPageIndex.setText("" + PageIndex);
        CenterPanel.add(txt_currentPageIndex);

        txt_totalPage = new JTextField();
        txt_totalPage.setBounds(395, 400, 20, 30);
        txt_totalPage.setText(" / " + totalPages);
        txt_totalPage.setEnabled(false);
        CenterPanel.add(txt_totalPage);

        CenterPanel.add(jsp);

        this.add(CenterPanel);
    }

    /**
     * 刷新数据
     */
    void flushData() {
        Object[][] dataVector = stuInfos(this.PageIndex);
        Object[] columnIdentifiers = {"学号", "姓名", "性别", "地址", "班级", "出生日期", "密码"};
        model.setDataVector(dataVector, columnIdentifiers);
    }

    void flushData2() {
        Object[][] dataVector = tcInfos(this.PageIndex);
        Object[] columnIdentifiers = {"编号", "姓名", "性别", "地址", "出生日期", "密码"};
        model.setDataVector(dataVector, columnIdentifiers);
    }

    /**
     * 将数据写入表格
     *
     * @return 二维数组数据
     */
    private Object[][] stuInfos(int PageIndex) {
        List<StuInfo> list = new StuDAO().searchAndPaging(value.toString(), PageIndex, PAGESIZE);
        Object[][] dataVector = new Object[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            dataVector[i][0] = list.get(i).getStu_id();
            dataVector[i][1] = list.get(i).getStu_name();
            dataVector[i][2] = list.get(i).getStu_sex() ? "男" : "女";
            dataVector[i][3] = list.get(i).getStu_addr();
            dataVector[i][4] = list.get(i).getStu_class();
            dataVector[i][5] = list.get(i).getStu_birth();
            dataVector[i][6] = list.get(i).getStu_password();
        }
        return dataVector;
    }

    private Object[][] tcInfos(int PageIndex) {
        List<TeacInfo> list = new TeacDAO().searchAndPaging(value.toString(), PageIndex, PAGESIZE);
        Object[][] dataVector = new Object[list.size()][6];
        for (int i = 0; i < list.size(); i++) {
            dataVector[i][0] = list.get(i).getTeac_id();
            dataVector[i][1] = list.get(i).getTeac_name();
            dataVector[i][2] = list.get(i).getTeac_sex() ? "男" : "女";
            dataVector[i][3] = list.get(i).getTeac_birth();
            dataVector[i][4] = list.get(i).getTeac_addr();
            dataVector[i][5] = list.get(i).getTeac_password();
        }
        return dataVector;
    }

    AdminLoginFrame() {
        //初始化窗体
        initFrame();

        //初始化控件
        initComp();

        //事件处理
        flushData();
        jb_add.addActionListener(new ButtonListener());
        jb_delete.addActionListener(new ButtonListener());
        jb_update.addActionListener(new ButtonListener());
        jb_PageUp.addActionListener(new ButtonListener());
        jb_PageDown.addActionListener(new ButtonListener());
        jb_FirsePage.addActionListener(new ButtonListener());
        jb_LastPage.addActionListener(new ButtonListener());
        jb_search.addActionListener(new ButtonListener());
        comboBox.addActionListener(new comboboxListener());

        //窗体可见
        this.setVisible(true);
    }

    class comboboxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (comboBox.getSelectedIndex() == 0) {
                AdminLoginFrame.this.flushData();
            }
            if (comboBox.getSelectedIndex() == 1) {
                AdminLoginFrame.this.flushData2();
            }
        }
    }

    /**
     * 按钮监听器类
     */
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (comboBox.getSelectedIndex() == 0) {
                if (e.getSource() == jb_search) {
                    value = value.append(txt_search.getText());
                    flushData();
                }
                if (e.getSource() == jb_PageUp) {
                    if (AdminLoginFrame.this.PageIndex > 1)
                        AdminLoginFrame.this.PageIndex--;
                    txt_currentPageIndex.setText("" + AdminLoginFrame.this.PageIndex);
                    flushData();
                }
                if (e.getSource() == jb_PageDown) {
                    if (AdminLoginFrame.this.PageIndex < totalPages)
                        AdminLoginFrame.this.PageIndex++;
                    txt_currentPageIndex.setText("" + AdminLoginFrame.this.PageIndex);
                    flushData();
                }
                if (e.getSource() == jb_FirsePage) {
                    AdminLoginFrame.this.PageIndex=1;
                    txt_currentPageIndex.setText("" + AdminLoginFrame.this.PageIndex);
                    flushData();
                }
                if (e.getSource() == jb_LastPage) {
                    AdminLoginFrame.this.PageIndex=totalPages;
                    txt_currentPageIndex.setText("" + AdminLoginFrame.this.PageIndex);
                    flushData();
                }
                if (e.getSource() == jb_add) {
                    new InputOrUpdateFrame(AdminLoginFrame.this, comboBox.getSelectedIndex());
                    txt_totalPage.setText(" / " + totalPages);
                }
                if (e.getSource() == jb_delete) {
                    if (table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, "请选中要删除的学生!");
                        return;
                    }
                    int i = showConfirmDialog(null, "确定要删除此学生?", "删除", JOptionPane.YES_NO_OPTION);
                    System.out.println(i);
                    if (i == 0) {
                        String stu_id = model.getValueAt(table.getSelectedRow(), 0).toString();
                        new StuDAO().delete(stu_id);
                        flushData();
                        txt_totalPage.setText(" / " + totalPages);
                    } else return;
                }
                if (e.getSource() == jb_update) {
                    if (table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, "请选中要修改的学生!");
                        return;
                    }
                    String stu_id = model.getValueAt(table.getSelectedRow(), 0).toString();
                    String stu_name = model.getValueAt(table.getSelectedRow(), 1).toString();
                    boolean stu_sex = model.getValueAt(table.getSelectedRow(), 2).toString().equals("男");
                    String stu_addr = model.getValueAt(table.getSelectedRow(), 3).toString();
                    String stu_class = model.getValueAt(table.getSelectedRow(), 4).toString();
                    String stu_birth = model.getValueAt(table.getSelectedRow(), 5).toString();
                    String stu_password = model.getValueAt(table.getSelectedRow(), 6).toString();

                    StuInfo stu = new StuInfo(stu_id, stu_name, stu_sex, stu_addr, stu_class, stu_birth, stu_password);
                    new InputOrUpdateFrame(stu, AdminLoginFrame.this);
                }
                AdminLoginFrame.this.flushData();
            }
            if (comboBox.getSelectedIndex() == 1) {
                if (e.getSource() == jb_search) {
                    value = value.append(txt_search.getText());
                    flushData2();
                }
                if (e.getSource() == jb_PageUp) {
                    if (AdminLoginFrame.this.PageIndex > 1)
                        AdminLoginFrame.this.PageIndex--;
                    txt_currentPageIndex.setText("" + AdminLoginFrame.this.PageIndex);
                    flushData2();
                }
                if (e.getSource() == jb_PageDown) {
                    if (AdminLoginFrame.this.PageIndex < totalPages)
                        AdminLoginFrame.this.PageIndex++;
                    txt_currentPageIndex.setText("" + AdminLoginFrame.this.PageIndex);
                    flushData2();
                }
                if (e.getSource() == jb_add) {
                    new InputOrUpdateFrame(AdminLoginFrame.this, comboBox.getSelectedIndex());
                    txt_totalPage.setText(" / " + totalPages);
                }
                if (e.getSource() == jb_delete) {
                    if (table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, "请选中要删除的教师!");
                        return;
                    }
                    int i = showConfirmDialog(null, "确定要删除此教师?", "删除", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        String tc_id = model.getValueAt(table.getSelectedRow(), 0).toString();
                        new TeacDAO().del(tc_id);
                        flushData2();
                        txt_totalPage.setText(" / " + totalPages);
                    } else return;
                }
                if (e.getSource() == jb_update) {
                    if (table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, "请选中要修改的教师!");
                        return;
                    }
                    String tc_id = model.getValueAt(table.getSelectedRow(), 0).toString();
                    String tc_name = model.getValueAt(table.getSelectedRow(), 1).toString();
                    boolean tc_sex = model.getValueAt(table.getSelectedRow(), 2).toString().equals("男");
                    String tc_addr = model.getValueAt(table.getSelectedRow(), 3).toString();
                    String tc_birth = model.getValueAt(table.getSelectedRow(), 4).toString();
                    String tc_password = model.getValueAt(table.getSelectedRow(), 5).toString();

                    TeacInfo tc = new TeacInfo(tc_id, tc_name, tc_sex, tc_addr, tc_birth, tc_password);
                    new InputOrUpdateFrame(tc, AdminLoginFrame.this);
                }
                AdminLoginFrame.this.flushData2();
            }
        }
    }
}
