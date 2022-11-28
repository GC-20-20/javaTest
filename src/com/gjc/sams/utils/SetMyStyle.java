package com.gjc.sams.utils;

import javax.swing.*;
import java.awt.*;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/19-10:02
 * 给组件设置不同的样式
 */

public class SetMyStyle {
    private static Font font1 = new Font("宋体", Font.BOLD, 30);
    private static Font font = new Font("宋体", Font.BOLD, 16);
    private static Font font2 = new Font("宋体", Font.BOLD, 12);
    /**
     * 提示语样式设置
     * @param name 提示语
     * @param a 选择标签
     * @return Jlabel
     */
    public  static JLabel SetMyFont1(String name, int a){
        JLabel p=new JLabel(name);
        p.setForeground(Color.WHITE);
        if (a==1){
            p.setFont(font1);
        }else if (a==2){
            p.setFont(font);
        }else if(a==3){
            p.setForeground(Color.CYAN);
            p.setFont(font);
        }else if(a==4){
            p.setForeground(Color.RED);
            p.setFont(font2);
        }else if(a==5){
            p.setForeground(Color.RED);
            p.setFont(font);
        }

        return p;
    }

    /**
     * 按钮的样式设置
     * @param name
     * @param a
     * @return
     */
    public  static JButton SetMyButton1(String name,int a){
        JButton p=new JButton(name);
        p.setForeground(Color.WHITE);
        if (a==1){
            p.setFont(font2);
        }else if(a==2){
            p.setForeground(Color.RED);
            p.setFont(font2);
        }else if(a==3){
            p.setForeground(Color.RED);
            p.setFont(font);
        }else if(a==4){
            p.setFont(font);
        }

        return p;
    }

    /**
     * 设置下拉框风格
     * @param x
     * @return
     */
    public static JComboBox<String> setMyJComboBox(int x){
        //下拉框
        JComboBox<String> jcb = new JComboBox<String>();
        //设置组件的宽和高
        Dimension dim = new Dimension(200, 30);
        jcb.setPreferredSize(dim);
        if(x==1){
            jcb.setFont(font);
        }else if(x==2){
            jcb.setFont(font1);
        }else{
            jcb.setFont(font2);
        }
        return jcb;
    }
}
