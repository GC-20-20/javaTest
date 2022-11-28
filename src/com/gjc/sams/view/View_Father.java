package com.gjc.sams.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/15-16:08
 * 所有窗体的父类
 */

public class View_Father extends JFrame {


    private ImageIcon background;
    private JPanel myPanel;
    private JLabel label;
    private JButton b1,b2;


    public View_Father(){
        b1=new JButton("退出系统");
        b1.setBounds(800,5,80,20);
        b2 = new JButton("回到登录页");
        b2.setBounds(2,5,90,20);
        this.add(b1);
        this.add(b2);
        /**
         * 加载图标
         */
        URL u = View_Father.class.getResource("imgs/tou.png");
        ImageIcon icon=new ImageIcon(u);
        this.setIconImage(icon.getImage());
        /**
         * 加载背景图片
         */
        URL url = View_Father.class.getResource("imgs/bg.jpg");
        background=new ImageIcon(url);
        //把背景图片添加到标签里
        label = new JLabel(background);
        //把标签设置为和图片等高等宽
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
        //把我的面板设置为内容面板
        myPanel = (JPanel)this.getContentPane();
        //把我的面板设置为不可视
        myPanel.setOpaque(false);
        //把我的面板设置为流动布局
        myPanel.setLayout(null);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //关闭窗口释放屏幕资源
                System.exit(0);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new View1();
                //关闭当前页面
                setVisible(false);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
        //把分层面板的布局置空
        this.getLayeredPane().setLayout(null);
        //设置窗口不可改变，固定窗口大小
        this.setResizable(false);
        //把标签添加到分层面板的最底层
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        this.setTitle("SAMS学生成绩管理系统");
        this.setBounds(300, 300, background.getIconWidth(),background.getIconHeight());
        //窗体弹出位置始终保证在屏幕中央
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



}
