package com.gjc.sams.view;

import com.gjc.sams.domain.Student;
import com.gjc.sams.domain.sdept;
import com.gjc.sams.service.AdminService;
import com.gjc.sams.utils.SetMyStyle;
import com.gjc.sams.utils.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/17-21:00
 * 信息修改页面
 */

public class View6 extends View_Father{
    private AdminService service=new AdminService();
    private JLabel labTitle;
    private JLabel t1,t2,t3,t4;
    private JTextField p1,p2,p3;
    private JButton b1,b2;
    private Student stu=null;
    private JComboBox jcb;
    View6() {
        super();
        b2=new JButton("退回上一页");
        b2.setBounds(2,25,90,20);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new View5();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        this.add(b2);
        labTitle = SetMyStyle.SetMyFont1("信息修改界面",1);
        labTitle.setBounds(330, 20, 300, 50);
        t1=SetMyStyle.SetMyFont1("修改姓名: ",2);
        t2=SetMyStyle.SetMyFont1("修改性别: ",2);
        t3=SetMyStyle.SetMyFont1("修改年龄: ",2);
        t4=SetMyStyle.SetMyFont1("修改院系: ",2);
        p1=new JTextField(15);
        p2=new JTextField(15);
        p3=new JTextField(15);
        t1.setBounds(230,200,600,50);
        t2.setBounds(230,250,600,50);
        t3.setBounds(230,300,600,50);
        t4.setBounds(230,350,600,50);
        p1.setBounds(330,210,250,30);
        p2.setBounds(330,260,250,30);
        p3.setBounds(330,310,250,30);
        //下拉框
        jcb=SetMyStyle.setMyJComboBox(1);
        jcb.setBounds(330,360,250,30);
        List<sdept> sdeptName = service.findAllSName();
        for (sdept sdept : sdeptName) {
            jcb.addItem(sdept.getSdept_name());
        }

        b1=SetMyStyle.SetMyButton1("提交修改",3);
        b1.setBounds(400,450,100,30);
        //根据学号查询信息，封装到javaBean里面，然后存储到对应的信息框上
        stu=service.findBySno(View5.mySno);
        String oneSName = service.findOneSName(stu.getSdept_id());
        //设置默认选择
        jcb.setSelectedItem(oneSName);
        p1.setText(stu.getSname());
        p2.setText(stu.getSsex());
        p3.setText(String.valueOf(stu.getSage()));
        //给组件添加监听
        p1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(stu.getSname().equals(p1.getText().trim())){
                    p1.setText("");
                    p1.setForeground(Color.BLACK);
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
                if(p1.getText().length()<1){
                    p1.setText(stu.getSname());
                    p1.setForeground(Color.BLACK);
                }
            }
        });
        p2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(stu.getSsex().equals(p2.getText().trim())){
                    p2.setText("");
                    p2.setForeground(Color.BLACK);
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
                if(p2.getText().length()<1){
                    p2.setText(stu.getSsex());
                    p2.setForeground(Color.BLACK);
                }
            }
        });
        p3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ValidationUtil.checkAge(p3.getText().trim())&&stu.getSage()== Byte.parseByte(p3.getText().trim())){
                    p3.setText("");
                    p3.setForeground(Color.BLACK);
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
                if(p3.getText().length()<1){
                    p3.setText(String.valueOf(stu.getSage()));
                    p3.setForeground(Color.BLACK);
                }
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获得文本框中的信息
                String name = p1.getText().trim();
                String sex = p2.getText().trim();
                String age = p3.getText().trim();
                if(!ValidationUtil.checkCname(name)){
                    JOptionPane.showMessageDialog(null,"请输入正确的姓名","message",JOptionPane.ERROR_MESSAGE);
                }else if(!ValidationUtil.checkSex(sex)){
                    JOptionPane.showMessageDialog(null,"请输入正确的性别","message",JOptionPane.ERROR_MESSAGE);
                }else if(!ValidationUtil.checkAge(age)){
                    JOptionPane.showMessageDialog(null,"请输入正确的年龄","message",JOptionPane.ERROR_MESSAGE);
                }else{
                    stu.setSname(name);
                    stu.setSsex(sex);
                    stu.setSage(Byte.parseByte(p3.getText().trim()));
                    stu.setSdept_id(service.findSID((String) jcb.getSelectedItem()));
                    boolean flag = service.updatePerson(stu);
                    if (flag){
                        JOptionPane.showMessageDialog(null,"修改成功！");
                        setVisible(false);
                        new View5();
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }else{
                        JOptionPane.showMessageDialog(null,"抱歉,修改失败！");
                    }
                }
            }
        });
        this.add(labTitle);
        this.add(t1);
        this.add(t2);
        this.add(t3);
        this.add(t4);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(jcb);
        this.add(b1);
        this.setVisible(true);

    }


}
