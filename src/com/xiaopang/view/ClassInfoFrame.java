package com.xiaopang.view;

import com.xiaopang.bean.ClassInfo;
import com.xiaopang.bean.CourseInfo;
import com.xiaopang.bean.StuInfo;
import com.xiaopang.dao.ClassCourseDAO;
import com.xiaopang.dao.ClassDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: ClassInfoFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/9 17:19
 */
class ClassInfoFrame extends JFrame {
    private StuInfo stu;
    private ClassInfo cls;
    private DefaultTableModel model;

    private void initFrame(){
        this.setSize(400, 400);
        this.setTitle("所在班级");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void initComp(){
        JLabel class_id = new JLabel("班级编号");
        class_id.setBounds(20,20,100,30);
        this.add(class_id);

        JTextField txt_id = new JTextField();
        if (cls != null) txt_id.setText(cls.getClass_id());
        txt_id.setBounds(140,20,200,30);
        txt_id.setEnabled(false);
        this.add(txt_id);

        JLabel class_major = new JLabel("所属专业");
        class_major.setBounds(20,70,100,30);
        this.add(class_major);

        JTextField txt_major = new JTextField();
        if (cls != null) txt_major.setText(cls.getClass_major());
        txt_major.setBounds(140,70,200,30);
        txt_major.setEnabled(false);
        this.add(txt_major);


        JLabel class_name = new JLabel("班级名称");
        class_name.setBounds(20,120,100,30);
        this.add(class_name);

        JTextField txt_name = new JTextField();
        if (cls != null) txt_name.setText(cls.getClass_name());
        txt_name.setBounds(140,120,200,30);
        txt_name.setEnabled(false);
        this.add(txt_name);


        JLabel class_course = new JLabel("班级专业必修课程");
        class_course.setBounds(20,170,120,30);
        this.add(class_course);

        model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane jsp = new JScrollPane(table);

        jsp.setBounds(20, 200, 360, 150);
        table.setEnabled(false);
        table.setRowHeight(20);

        this.add(jsp);

    }

    private void flushData() {
        Object[][] dataVector = courses();
        Object[] columnIdentifiers = {"课程号", "课程名称"};
        model.setDataVector(dataVector, columnIdentifiers);
    }
    private Object[][] courses(){
        List<CourseInfo> courseInfos = new ClassCourseDAO().class_courses(stu.getStu_class());
        Object[][]objects = new Object[courseInfos.size()][2];
        for (int i = 0;i< courseInfos.size();i++){
            objects[i][0] = courseInfos.get(i).getCourse_id();
            objects[i][1] = courseInfos.get(i).getCourse_name();
        }
        return objects;
    }
    ClassInfoFrame(StuInfo stu){
        this();
        this.stu = stu;
        this.cls = new ClassDAO().queryClass(stu);


        //初始化控件
        initComp();
        flushData();

    }
    private ClassInfoFrame(){
        //初始化窗体
        initFrame();

        //设置窗口可见
        this.setVisible(true);
    }
}
