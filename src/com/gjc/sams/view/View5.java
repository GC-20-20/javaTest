package com.gjc.sams.view;



import com.gjc.sams.domain.sdept;
import com.gjc.sams.service.AdminService;
import com.gjc.sams.utils.SetMyStyle;
import com.gjc.sams.utils.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/14-18:43
 * admin
 * 重置密码
 * 录入信息
 * 修改学生信息
 */

public class View5 extends View_Father {

    private AdminService service=new AdminService();
    private JLabel labTitle ;
    private JLabel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13;
    private JButton b1,b2,b3,b4,b5;
    private JTextField pwd1,t1,t2,t3,t4,t5,t6,t7;
    private JPanel p,pp,ppp;
    public static String mySno;
    View5(){
        super();
        labTitle = SetMyStyle.SetMyFont1("管理员界面",1);
        labTitle.setBounds(360,10,300,50);
        //其他
        t1=new JTextField(15);
        t2=new JTextField(15);
        t3=new JTextField(15);
        t4=new JTextField(15);
        p1= SetMyStyle.SetMyFont1("重置所有人的密码",3);
        p1.setBounds(120,60,200,50);
        pwd1=new JTextField(15);
        pwd1.setText("输入要重置的新密码");
        pwd1.setForeground(Color.PINK);
//        //当鼠标移动到输入框时出现弹出小提示
        pwd1.setBounds(300,100,250,30);
        //设置焦点监听
        pwd1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if("输入要重置的新密码".equals(pwd1.getText().trim())){
                    pwd1.setText("");
                    pwd1.setForeground(Color.BLACK);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //当失去焦点时，判断是否为空，若为空时，直接显示提示信息，设置颜色
                if(pwd1.getText().length()<1){
                    pwd1.setText("输入要重置的新密码");
                    pwd1.setForeground(Color.PINK);
                }
            }
        });
        b1=SetMyStyle.SetMyButton1("确认重置",2);
        b1.setBounds(600,100,80,30);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取文本框内容
                String s = pwd1.getText().trim();
                //密码校验
                if ("输入要重置的新密码".equals(s)){
                    return;
                }
                if (!ValidationUtil.checkPassword(s)){
                    JOptionPane.showMessageDialog(null, "密码格式不正确", "message", JOptionPane.WARNING_MESSAGE);
                }else{
                    boolean flag = service.updatePwd(s);
                    System.out.println(flag);
                    if (flag){
                        JOptionPane.showMessageDialog(null,"重置成功");
                    }else{
                        JOptionPane.showMessageDialog(null,"抱歉，密码更新失败！");
                    }
                }

            }
        });

        /**
         * 添加人员Jpanel
         */
        p=new JPanel();
        p.setForeground(Color.BLUE);
        p.setBounds(110,200,700,130);
        p.setLayout(null);
        //下拉框
        JComboBox<String> jcb = SetMyStyle.setMyJComboBox(1);
        jcb.addItem("老师");
        jcb.addItem("学生");
        jcb.setBounds(60,5,80,30);

        p7=SetMyStyle.SetMyFont1("身份: ",5);
        p8=SetMyStyle.SetMyFont1("院系: ",5);
        p2=SetMyStyle.SetMyFont1("工(学)号: ",5);
        p3=SetMyStyle.SetMyFont1("姓名: ",5);
        p4=SetMyStyle.SetMyFont1("性别: ",5);
        p5=SetMyStyle.SetMyFont1("年龄: ",5);
        p6=SetMyStyle.SetMyFont1("添加新的同学或老师",3);
        p6.setBounds(120,150,200,50);
        p7.setBounds(10,5,70,30);
        p8.setBounds(180,5,70,30);
        p2.setBounds(400,5,100,30);
        p3.setBounds(10,40,70,50);
        p4.setBounds(210,40,70,50);
        p5.setBounds(400,40,70,50);
        t1.setBounds(490,5,200,30);
        t2.setBounds(65,50,120,30);
        t3.setBounds(270,50,120,30);
        t4.setBounds(460,50,120,30);
        //下拉框
        JComboBox<String> jcb1 = SetMyStyle.setMyJComboBox(1);
        List<sdept> sdeptName = service.findAllSName();
        for (sdept sdept : sdeptName) {
            jcb1.addItem(sdept.getSdept_name());
        }
        jcb1.setBounds(230,5,150,30);

        //提交按钮
        b2=SetMyStyle.SetMyButton1("提交",3);
        b2.setBounds(310,95,70,30);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String job = (String) jcb.getSelectedItem();
                String sdeptName=(String) jcb1.getSelectedItem();
                String sno = t1.getText().trim();
                String name = t2.getText().trim();
                String sex = t3.getText().trim();
                byte age = 0;
                //在java里面String是不能用==或!=比较的，要用equals(),
                boolean flag = false;
                if(sno.length()==0||name.length()==0||sex.length()==0||"".equals(t4.getText())){
                    JOptionPane.showMessageDialog(null, "信息部分为空无法提交", "message", JOptionPane.ERROR_MESSAGE);
                }else if(!ValidationUtil.checkSno(sno)){
                    JOptionPane.showMessageDialog(null, "工(学)号输入有误", "message", JOptionPane.ERROR_MESSAGE);
                }else if(!ValidationUtil.checkName(name)){
                    JOptionPane.showMessageDialog(null, "请输入正确的中文名字2-6位", "message", JOptionPane.ERROR_MESSAGE);
                } else if (!ValidationUtil.checkSex(sex)){
                    JOptionPane.showMessageDialog(null, "请输入正确的性别", "message", JOptionPane.ERROR_MESSAGE);
                }else if(!ValidationUtil.checkAge(t4.getText())){
                    JOptionPane.showMessageDialog(null, "请输入正确的年龄(1-120岁)", "message", JOptionPane.ERROR_MESSAGE);
                }else{
                    age=Byte.valueOf(Byte.parseByte(t4.getText().trim()));
                    StringBuffer buffer = new StringBuffer(sno);
                    String s = buffer.substring(0, 4);
                    if ("学生".equals(job)) {
                        if (!"2003".equals(s)) {
                            flag=true;
                        }
                    } else if ("老师".equals(job)) {
                        if (!"2002".equals(s)) {
                            flag=true;
                        }
                    }
                    if (flag){
                        JOptionPane.showMessageDialog(null, "工(学)号与身份不匹配", "message", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        boolean b = service.addPerson(sno, name, sex, age, sdeptName);
                        if (b){
                            JOptionPane.showMessageDialog(null,"添加成功");
                        }else{
                            JOptionPane.showMessageDialog(null,"抱歉,添加失败!");
                        }
                    }
                }
            }
        });
        p.add(b2);
        p.add(p7);
        p.add(p8);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        p.add(t1);
        p.add(t2);
        p.add(t3);
        p.add(t4);
        p.add(jcb);
        p.add(jcb1);
        /**
         * 添加课程Jpanel
         */
        pp=new JPanel();
        pp.setLayout(null);
        pp.setBounds(110,380,700,50);
        p9=SetMyStyle.SetMyFont1("添加新的院系或课程",3);
        p9.setBounds(120,330,200,50);
        p10=SetMyStyle.SetMyFont1("课程学分: ",5);
        p10.setBounds(320,10,100,30);
        t5=new JTextField(15);
        t5.setBounds(110,10,200,30);
        t7=new JTextField(5);
        t7.setBounds(400,10,100,30);
        //下拉框
        JComboBox<String> jcb2 = SetMyStyle.setMyJComboBox(1);
        jcb2.addItem("院系");
        jcb2.addItem("课程");
        jcb2.setBounds(2,10,80,30);
        b3=SetMyStyle.SetMyButton1("点击添加",3);
        b3.setBounds(530,15,100,20);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String x = (String) jcb2.getSelectedItem();
                String cname = t5.getText().trim();
                String s = t7.getText().trim();
                boolean flag=false;
                if (cname.length()==0){
                    JOptionPane.showMessageDialog(null, "信息部分为空无法提交", "message", JOptionPane.ERROR_MESSAGE);
                    return;
                }else if ("院系".equals(x)){
                    if (!ValidationUtil.checkSdept(cname)){
                        JOptionPane.showMessageDialog(null, "请输入正确的学院名", "message", JOptionPane.ERROR_MESSAGE);
                        return;
                    }else{
                        flag=service.addSdept(cname);
                    }
                }else if ("课程".equals(x)){
                    if(!ValidationUtil.checkCname(cname)){
                        JOptionPane.showMessageDialog(null, "请输入正确的课程名", "message", JOptionPane.ERROR_MESSAGE);
                        return;
                    }else {
                        if (!ValidationUtil.checkCredit(s)){
                            JOptionPane.showMessageDialog(null, "请输入正确的学分(1-6)", "message", JOptionPane.ERROR_MESSAGE);
                            return;
                        }else{
                            flag=service.addCourse(cname,Byte.valueOf(Byte.parseByte(s)));
                        }
                    }
                }
                if (flag){
                    JOptionPane.showMessageDialog(null,"添加成功");
                }else{
                    JOptionPane.showMessageDialog(null,"抱歉,添加失败!");
                }
            }
        });
        pp.add(jcb2);
        pp.add(t7);
        pp.add(b3);
        pp.add(t5);
        pp.add(p10);
        /**
         * ppp 修改Jpanel
         */
        ppp=new JPanel();
        ppp.setLayout(null);
        ppp.setBounds(110,480,700,50);
        p11=SetMyStyle.SetMyFont1("修改/删除人员信息",3);
        p11.setBounds(120,430,200,50);
        p12=SetMyStyle.SetMyFont1("请输入工(学)号：",5);
        p12.setBounds(20,10,230,30);
        t6=new JTextField(15);
        t6.setBounds(240,10,200,30);
        b4=SetMyStyle.SetMyButton1("点击修改",3);
        b4.setBounds(460,15,100,20);
        b5=SetMyStyle.SetMyButton1("点击删除",3);
        b5.setBounds(560,15,100,20);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sno = t6.getText().trim();
                if (!ValidationUtil.checkSno(sno)){
                    JOptionPane.showMessageDialog(null, "工(学)号输入有误", "message", JOptionPane.ERROR_MESSAGE);
                }else{
                    mySno=sno;
                    setVisible(false);
                    new View6();
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sno = t6.getText().trim();
                if (!ValidationUtil.checkSno(sno)|| !service.delCheck(sno)){
                    JOptionPane.showMessageDialog(null, "工(学)号输入有误", "message", JOptionPane.ERROR_MESSAGE);
                }else {
                    int i = JOptionPane.showConfirmDialog(null, "是否确删除该人员,请慎重！！！","message",JOptionPane.WARNING_MESSAGE);
                    if (i == 0) {
                        boolean flag = service.delPerson(sno);
                        if (flag){
                            JOptionPane.showMessageDialog(null,"删除成功");
                        }else{
                            JOptionPane.showMessageDialog(null,"抱歉,删除失败!");
                        }
                    }
                }
            }
        });
        ppp.add(t6);
        ppp.add(p12);
        ppp.add(b4);
        ppp.add(b5);


        this.add(pp);
        this.add(ppp);
        this.add(p9);
        this.add(p11);
        this.add(p);
        this.add(p6);
        this.add(p1);
        this.add(pwd1);
        this.add(b1);
        this.add(labTitle);
        this.setVisible(true);
    }

}
