package com.xiaopang.view;

import com.xiaopang.bean.CourseInfo;
import com.xiaopang.bean.ScoreInfo;
import com.xiaopang.bean.TeacInfo;
import com.xiaopang.dao.AddGradeDAO;
import com.xiaopang.dao.ClassCourseDAO;

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
 * @ClassName: AddGradeFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/10 15:35
 */

class AddGradeFrame extends JFrame {
    private TeacInfo tc;
    private List<CourseInfo> courses;
    private List<ScoreInfo> list;

    private DefaultTableModel model;
    private JTable table;

    private JButton jb_submit;
    private JButton jb_search;
    private JButton jb_PageUp;
    private JButton jb_PageDown;
    private JButton jb_FirsePage;
    private JButton jb_LastPage;
    private JButton jb_jumpPage;
    private JTextField txt_currentPageIndex;
    private JTextField txt_search;
    private JTextField txt_totalPage;
    private JComboBox<String> comboBox;
    private int PageIndex = 1;
    private static final int PAGESIZE = 5;
    private int totalPages = 1;
    private StringBuilder value = new StringBuilder("");

    private void initFrame() {
        this.setSize(800, 600);
        this.setTitle("已登陆-->" + tc.getTeac_name() + "-->学生成绩管理");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComp() {
        JPanel NorthPanel = new JPanel();
        NorthPanel.setBackground(Color.GREEN);
        NorthPanel.setPreferredSize(new Dimension(0, 40));
        this.add(NorthPanel, BorderLayout.NORTH);
        NorthPanel.setLayout(null);

        JLabel lbl = new JLabel("课程:");
        lbl.setBounds(20, 5, 50, 30);
        NorthPanel.add(lbl);

        comboBox = new JComboBox<String>();
        comboBox.setBounds(80, 5, 110, 30);
        for (CourseInfo course : courses) {
            comboBox.addItem(course.getCourse_name());
        }
        comboBox.setSelectedIndex(0);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < courses.size(); i++) {
                    if (comboBox.getSelectedIndex() == i) {
                        AddGradeFrame.this.flushData();
                    }
                }
            }
        });
        NorthPanel.add(comboBox);

        txt_search = new JTextField();
        txt_search.setBounds(200, 5, 300, 30);
        NorthPanel.add(txt_search);

        jb_search = new JButton("搜索");
        jb_search.setBounds(550, 5, 60, 30);
        NorthPanel.add(jb_search);

        jb_submit = new JButton("提交");
        jb_submit.setBounds(650, 5, 100, 30);
        NorthPanel.add(jb_submit);

        JPanel CenterPanel = new JPanel();
        CenterPanel.setLayout(null);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        table = new JTable(model);
        JScrollPane jsp = new JScrollPane(table);

        jsp.setBounds(40, 20, 700, 420);
        table.setCellSelectionEnabled(true);
        table.setRowHeight(30);
        CenterPanel.add(jsp);

        jb_PageUp = new JButton("上一页");
        jb_PageUp.setBounds(240, 470, 80, 30);
        CenterPanel.add(jb_PageUp);

        jb_PageDown = new JButton("下一页");
        jb_PageDown.setBounds(460, 470, 80, 30);
        CenterPanel.add(jb_PageDown);

        jb_FirsePage = new JButton("首页");
        jb_FirsePage.setBounds(140, 470, 80, 30);
        CenterPanel.add(jb_FirsePage);

        jb_LastPage = new JButton("尾页");
        jb_LastPage.setBounds(560, 470, 80, 30);
        CenterPanel.add(jb_LastPage);

        txt_currentPageIndex = new JTextField();
        txt_currentPageIndex.setBounds(330, 470, 25, 30);
        txt_currentPageIndex.setText("" + PageIndex);
        CenterPanel.add(txt_currentPageIndex);

        txt_totalPage = new JTextField();
        txt_totalPage.setBounds(355, 470, 30, 30);
        txt_totalPage.setEditable(false);
        CenterPanel.add(txt_totalPage);

        jb_jumpPage = new JButton("跳转");
        jb_jumpPage.setBounds(390, 470, 60, 30);
        CenterPanel.add(jb_jumpPage);


        this.add(CenterPanel);
    }

    /**
     * 刷新数据
     */
    private void flushData() {
        Object[][] dataVector = scores(this.PageIndex);
        Object[] columnIdentifiers = {"学号", "姓名", "成绩"};
        model.setDataVector(dataVector, columnIdentifiers);
    }

    /**
     * 将数据写入表格
     *
     * @param PageIndex 当前页数
     * @return 二维表格数据
     */
    private Object[][] scores(int PageIndex) {
        int j = comboBox.getSelectedIndex();
        List list1 = new AddGradeDAO().allGrade(tc, courses.get(j).getCourse_name(), value.toString());
        list = new AddGradeDAO().allGradePaging(tc, courses.get(j).getCourse_name(), PageIndex, PAGESIZE, value.toString());
        totalPages = ((list1.size() - 1) / PAGESIZE) + 1;
        txt_totalPage.setText(" / " + totalPages);
        Object[][] dataVector = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            dataVector[i][0] = list.get(i).getStu_id();
            dataVector[i][1] = list.get(i).getStu_name();
            dataVector[i][2] = list.get(i).getScore();
        }
        return dataVector;
    }

    AddGradeFrame(TeacInfo tc) {
        this.tc = tc;
        //初始化窗体
        initFrame();

        courses = new ClassCourseDAO().teacCourse(tc);

        //初始化控件
        initComp();

        //事件处理
        flushData();
        jb_PageUp.addActionListener(new ButtonListener());
        jb_PageDown.addActionListener(new ButtonListener());
        jb_FirsePage.addActionListener(new ButtonListener());
        jb_LastPage.addActionListener(new ButtonListener());
        jb_search.addActionListener(new ButtonListener());
        jb_jumpPage.addActionListener(new ButtonListener());
        jb_submit.addActionListener(new ButtonListener());


        //窗体可见
        this.setVisible(true);
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb_search) {
                value = value.append(txt_search.getText());
                flushData();
                value.setLength(0);
            }
            if (e.getSource() == jb_PageUp) {
                if (AddGradeFrame.this.PageIndex > 1)
                    AddGradeFrame.this.PageIndex--;
                txt_currentPageIndex.setText("" + AddGradeFrame.this.PageIndex);
                flushData();
            }
            if (e.getSource() == jb_PageDown) {
                if (AddGradeFrame.this.PageIndex < totalPages)
                    AddGradeFrame.this.PageIndex++;
                txt_currentPageIndex.setText("" + AddGradeFrame.this.PageIndex);
                flushData();
            }
            if (e.getSource() == jb_FirsePage) {
                AddGradeFrame.this.PageIndex = 1;
                txt_currentPageIndex.setText("" + AddGradeFrame.this.PageIndex);
                flushData();
            }
            if (e.getSource() == jb_LastPage) {
                AddGradeFrame.this.PageIndex = totalPages;
                txt_currentPageIndex.setText("" + AddGradeFrame.this.PageIndex);
                flushData();
            }
            if (e.getSource() == jb_jumpPage) {
                AddGradeFrame.this.PageIndex = Integer.parseInt(txt_currentPageIndex.getText());
                txt_currentPageIndex.setText("" + AddGradeFrame.this.PageIndex);
                flushData();
            }
            if (e.getSource() == jb_submit) {
                if (table.isEditing()) table.getCellEditor().stopCellEditing();
                int i = showConfirmDialog(null, "确定提交?", "提交", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    for (int j = 0; j < AddGradeFrame.this.list.size(); j++) {
                        if (new AddGradeDAO().isretake(list.get(i).getStu_id(), courses.get(comboBox.getSelectedIndex()).getCourse_name())) {
                            int k = showConfirmDialog(null, "该生" + list.get(i).getStu_name() + "为重修,是否要重新添加成绩?", "覆盖成绩?", JOptionPane.YES_NO_OPTION);
                            if (k == 0) {
                                int score = Integer.parseInt(model.getValueAt(j, 2).toString());
                                new AddGradeDAO().addscore(list.get(j).getStu_id(), courses.get(comboBox.getSelectedIndex()).getCourse_id(), score);
                            }
                        } else {
                            int score = Integer.parseInt(model.getValueAt(j, 2).toString());
                            new AddGradeDAO().updscore(list.get(j).getStu_id(), courses.get(comboBox.getSelectedIndex()).getCourse_id(), score);
                        }
                    }
                    flushData();
                }
            }
        }
    }
}
