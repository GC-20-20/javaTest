package com.gjc.sams.view;

import com.gjc.sams.domain.User;
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
 * @date 2022/10/14-16:57
 * 登录界面
 */
@SuppressWarnings("{all}")
public class View1 extends View_Father {
    private LoginService service=new LoginService();

    //工号和用户名文本框

    private JTextField jtf1,jtf2;
    //密码框

    private JPasswordField jpf1;
    //提示语

    private JLabel jlb1,jlb2,jlb3,jlb4;
    //登录，退出，注册按钮

    private JButton jb1,jb2,jb3;


    //设置大标题

    private JLabel labTitle ;
    //设置几个字体样式

    Font font1 = new Font("宋体", Font.BOLD, 50);
    Font font = new Font("宋体", Font.BOLD, 16);

    public static String loginName ="";
    public static User myUser=null;
     View1(){
         super();
         labTitle = SetMyStyle.SetMyFont1("学生成绩管理系统",1);
         labTitle.setBounds(340,30,600,100);
         this.add(labTitle);

        //设计对话框及密码框的长度
         jtf1 = new JTextField(15);
         jtf2 = new JTextField(15);
         jpf1 = new JPasswordField(15);

        //设置按钮和其他字体
         jlb1 = new JLabel("用户名： ");
         jlb2 = new JLabel("密 码： ");
         jlb3 = new JLabel("没有账号，请先注册");
         jlb4 = new JLabel("工(学)号：");
         jlb1.setFont(font);
         jlb1.setForeground(Color.WHITE);
         jlb2.setFont(font);
         jlb2.setForeground(Color.WHITE);
         jlb3.setFont(font);
         jlb3.setForeground(Color.PINK);
         jlb4.setFont(font);
         jlb4.setForeground(Color.WHITE);
         jlb4.setBounds(230,150,600,80);
         jlb1.setBounds(230,220,600,80);
         jlb3.setBounds(710,100,600,80);
         jlb2.setBounds(230,290,600,80);
         jtf1.setBounds(350,175,250,30);
         jtf2.setBounds(350,240,250,30);
         jpf1.setBounds(350,310,250,30);

         this.add(jlb1);
         this.add(jlb2);
         this.add(jlb3);
         this.add(jlb4);
         this.add(jtf1);
         this.add(jtf2);
         this.add(jpf1);


        jb1 = new JButton("登录");
        jb2 = new JButton("退出");
        jb3 = new JButton("注册账号");

        jb1.setFont(font);
        jb2.setFont(font);
        jb3.setFont(font);
        jb1.setBounds(320,430,100,30);
        jb2.setBounds(500,430,100,30);
        jb3.setBounds(730,160,100,30);
        this.add(jb1);
        this.add(jb2);
        this.add(jb3);
        //在组件都加载完后，才显示
        this.setVisible(true);

        //登录按钮功能
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String sno = jtf1.getText().trim();
                String username=jtf2.getText().trim();
                String password = new String(jpf1.getPassword()).trim();
                User u=null;
                if(sno.length()==0||username.length()==0||password.length()==0){
                    JOptionPane.showMessageDialog(null,"工号用户名密码不允许为空","message",JOptionPane.ERROR_MESSAGE);
                }
                else if (!ValidationUtil.checkSno(sno)) {
                    JOptionPane.showMessageDialog(null, "工号输入有误", "message", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!ValidationUtil.checkName(username)||!ValidationUtil.checkPassword(password)) {
                        JOptionPane.showMessageDialog(null, "用户名或密码有误", "message", JOptionPane.WARNING_MESSAGE);
                    }
                    //与数据库信息比较
                    else if ((myUser=service.loginCheck(sno,username,password))!=null) {
                        //JOptionPane.showMessageDialog(null, "登录成功");
                        //判断学号或者工号与身份是否对应 2002开头是老师 2003开头是学生
                        String trim = jtf1.getText().trim();
                        StringBuilder buffer = new StringBuilder(trim);
                        String s = buffer.substring(0, 4);
                        //System.out.println(s);
                        if ("2003".equals(s)) {
                            //打开学生页面
                            //System.out.println("成功！");
                            View1.loginName=myUser.getUsername();
                            new View3();
                        }else if ("2002".equals(s)){
                            //打开教师页面
                            View1.loginName=myUser.getUsername();
                            new View4();
                        }else if ("2001".equals(s)){
                            //打开管理员页面
                            View1.loginName=myUser.getUsername();
                            new View5();
                        }
                        //关闭当前页面
                        setVisible(false);
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败，请检查工(学)号用户名或密码", "message", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        //退出功能
        jb2.addActionListener(new ActionListener() {
            //设置监听
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose();
                //关闭窗口释放屏幕资源
                System.exit(0);
            }
        });
        //注册页面
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //关闭当前页面
                setVisible(false);
                //跳转到注册页面
                new View2();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });


    }

}
