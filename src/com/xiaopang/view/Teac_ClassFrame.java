package com.xiaopang.view;

import com.xiaopang.bean.TeacInfo;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectName: EduAdminMS
 * @Package: com.xiaopang.view
 * @ClassName: Teac_ClassFrame
 * @Description: java类作用描述
 * @Author: 小胖
 * @CreateDate: 2018/8/10 14:14
 */
class Teac_ClassFrame extends JFrame {
    private TeacInfo tc;

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("微软雅黑",Font.BOLD,40));
        g.setColor(Color.MAGENTA);
        super.paint(g);
        g.drawString("该功能尚未完善", 50, 130);
        g.drawString("敬请期待!", 100, 230);
    }

    private void initFrame(){
        this.setSize(400, 300);
        this.setTitle("已登陆-->" + tc.getTeac_name() + "-->班级列表");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    Teac_ClassFrame(TeacInfo tc){
        this.tc = tc;
        //初始化窗体
        initFrame();

        //初始化控件


        //事件处理

        //窗体可见
        this.setVisible(true);
    }
}
