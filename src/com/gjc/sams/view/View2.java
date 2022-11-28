package com.gjc.sams.view;

import com.gjc.sams.service.LoginService;
import com.gjc.sams.utils.SetMyStyle;
import com.gjc.sams.utils.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/14-18:42
 * 注册界面
 */

public class View2 extends View_Father {
    private LoginService service=new LoginService();
    private JButton b1;
    private JTextField jtf1,jtf2;
    private JPasswordField jpf1,jpf2;
    private JLabel jlb1,jlb2,jlb3,jlb4,jlb5;
    private JLabel labTitle ;

    private String selectedItem;

    public View2() {
        super();
        //提示语和按钮的创建
        jlb1 = SetMyStyle.SetMyFont1("工(学)号：",2);
        jlb2 = SetMyStyle.SetMyFont1("用户名：",2);
        jlb3 = SetMyStyle.SetMyFont1("密 码：",2);
        jlb4 = SetMyStyle.SetMyFont1("密码确认：",2);
        jlb5 = SetMyStyle.SetMyFont1("选择身份：",2);
        b1 = SetMyStyle.SetMyButton1("提交",3);
        //设计对话框及密码框的长度
        jtf1 = new JTextField(15);
        jtf2 = new JTextField(15);
        jpf1 = new JPasswordField(15);
        jpf2 = new JPasswordField(15);
        //下拉框
        JComboBox<String> jcb =SetMyStyle.setMyJComboBox(1);
        jcb.addItem("老师");
        jcb.addItem("学生");

        //设置位置
        jlb5.setBounds(230,140,600,50);
        jlb1.setBounds(230,200,600,50);
        jlb2.setBounds(230,250,600,50);
        jlb3.setBounds(230,300,600,50);
        jlb4.setBounds(230,350,600,50);

        jcb.setBounds(330,150,250,30);

        jtf1.setBounds(330,210,250,30);
        jtf2.setBounds(330,260,250,30);
        jpf1.setBounds(330,310,250,30);
        jpf2.setBounds(330,360,250,30);

        b1.setBounds(400,450,100,30);

        //设置系统名称大字
        labTitle = SetMyStyle.SetMyFont1("注册",1);
        labTitle.setBounds(400,30,300,50);
        this.add(labTitle);
        this.add(jlb1);
        this.add(jlb2);
        this.add(jlb3);
        this.add(jlb4);
        this.add(jlb5);
        this.add(jcb);
        this.add(jtf1);
        this.add(jtf2);
        this.add(jpf1);
        this.add(jpf2);
        this.add(b1);
        //设置界面可显示
        this.setVisible(true);
        //提交按钮功能
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                selectedItem=(String) jcb.getSelectedItem();
                String pwd1 = new String(jpf1.getPassword()).trim();
                String pwd2 = new String(jpf2.getPassword()).trim();
                String username=jtf2.getText().trim();
                String sno=jtf1.getText().trim();
                if(sno.length()==0||pwd1.length()==0 ||username.length()==0||pwd2.length()==0){
                    //trim():用于删除头尾的空白符
                    JOptionPane.showMessageDialog(null,"工号用户名密码不允许为空","message",JOptionPane.ERROR_MESSAGE);
                }else if(!ValidationUtil.checkSno(sno)){
                    JOptionPane.showMessageDialog(null,"工(学)号输入有误","message",JOptionPane.ERROR_MESSAGE);
                }else{
                    //判断学号或者工号与身份是否对应 2002开头是老师 2003开头是学生
                    StringBuffer buffer = new StringBuffer(sno);
                    String s = buffer.substring(0, 4);
                    boolean flag = false;
                    if ("学生".equals(selectedItem)) {
                        if (!"2003".equals(s)) {
                            flag = true;
                        }
                    } else if ("老师".equals(selectedItem)) {
                        if (!"2002".equals(s)) {
                            flag=true;
                        }
                    }
                    if (flag) {
                        JOptionPane.showMessageDialog(null, "工号输入有误", "message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (!ValidationUtil.checkName(username)||!ValidationUtil.checkPassword(pwd1)||!ValidationUtil.checkPassword(pwd2)) {
                            JOptionPane.showMessageDialog(null, "用户名或密码输入有误", "message", JOptionPane.WARNING_MESSAGE);
                        }
                        //与数据库信息比较
                        else if (!pwd1.equals(pwd2)) {
                            JOptionPane.showMessageDialog(null, "密码不一致", "message", JOptionPane.WARNING_MESSAGE);
                        }else if (service.register(sno,username,pwd1)){
                            JOptionPane.showMessageDialog(null, "注册成功，即将返回登录页");
                            new View1();
                            //关闭当前页面
                            setVisible(false);
                            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        }else{
                            JOptionPane.showMessageDialog(null, "注册失败，请检查工(学)号", "message", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }

            }
        });


    }

}
