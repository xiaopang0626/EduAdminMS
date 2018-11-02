package com.xiaopang.view;

import com.xiaopang.bean.ScoreInfo;
import com.xiaopang.bean.StuInfo;
import com.xiaopang.dao.GradeDAO;

import javax.swing.*;
import java.util.List;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: StuGradeFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/10 10:38
 */
class StuGradeFrame extends JFrame {
    private StuInfo stu;
    private List<ScoreInfo> scoreInfos;

    private void initFrame(){
        this.setSize(400, 500);
        this.setTitle("已登陆-->" + stu.getStu_name());
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void initComp(){
        for (int i = 0;i < scoreInfos.size();i++){
            JLabel lbl = new JLabel("科目:    "+scoreInfos.get(i).getCourse_name());
            lbl.setBounds(30,30+i*40,200,30);
            this.add(lbl);
            JTextField txt_score = new JTextField();
            txt_score.setText("" + scoreInfos.get(i).getScore());
            txt_score.setEditable(false);
            txt_score.setBounds(240,30+i*40,50,30);
            this.add(txt_score);
        }
    }
    StuGradeFrame (StuInfo stu){
        this.stu = stu;
        //初始化窗体
        initFrame();
        scoreInfos = new GradeDAO().grade(stu);
        //初始化控件
        initComp();
        this.setVisible(true);
    }
}
