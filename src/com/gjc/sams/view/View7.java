package com.gjc.sams.view;

import com.gjc.sams.domain.Course;
import com.gjc.sams.service.TeacherService;
import com.gjc.sams.utils.SetMyStyle;
import com.gjc.sams.utils.ValidationUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/17-21:01
 * 成绩录入页面
 */

public class View7 extends View_Father {
    private TeacherService service=new TeacherService();
    private JLabel labTitle;
    private JLabel t1,t2,t3;
    private JTextField p1,p2;
    private JButton b1,b2;
    private JComboBox jcb;
    View7() {
        super();
        b2=new JButton("退回上一页");
        b2.setBounds(2,25,90,20);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new View4();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        this.add(b2);
        labTitle = SetMyStyle.SetMyFont1("成绩录入界面", 1);
        labTitle.setBounds(330, 20, 300, 50);
        t1 = SetMyStyle.SetMyFont1("学号: ", 2);
        t2 = SetMyStyle.SetMyFont1("课程: ", 2);
        t3 = SetMyStyle.SetMyFont1("得分: ", 2);
        p1 = new JTextField(15);
        p2 = new JTextField(15);
        t1.setBounds(230,200,600,50);
        t2.setBounds(230,250,600,50);
        t3.setBounds(230,300,600,50);
        p1.setBounds(330,210,250,30);
        p2.setBounds(330,310,250,30);
        jcb=SetMyStyle.setMyJComboBox(1);
        List<Course> CourseName = service.findCourse();
        for (Course course : CourseName) {
            jcb.addItem(course.getCname());
        }
        jcb.setBounds(330,260,250,30);
        b1=SetMyStyle.SetMyButton1("提交修改",3);
        b1.setBounds(400,450,100,30);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取数据
                String sno = p1.getText().trim();
                String grade = p2.getText().trim();
                String cname =(String) jcb.getSelectedItem();
                if("".equals(sno)||"".equals(grade)||"".equals(cname)){
                    JOptionPane.showMessageDialog(null,"部分信息为空,无法提交","message",JOptionPane.ERROR_MESSAGE);

                } else if(!ValidationUtil.checkSno(sno)){
                    JOptionPane.showMessageDialog(null,"请输入正确的学号","message",JOptionPane.ERROR_MESSAGE);

                }else{
                    //判断学号 2003开头是学生,学生才应该有成绩
                    StringBuffer buffer = new StringBuffer(sno);
                    String s = buffer.substring(0, 4);
                    if(!"2003".equals(s)){
                        JOptionPane.showMessageDialog(null,"学号与身份不符","message",JOptionPane.ERROR_MESSAGE);
                    } else if(!ValidationUtil.checkCname(cname)){
                        JOptionPane.showMessageDialog(null,"请输入正确的课程名","message",JOptionPane.ERROR_MESSAGE);

                    }else if(!ValidationUtil.checkGrade(grade)){
                        JOptionPane.showMessageDialog(null,"请输入正确的分数0-100","message",JOptionPane.ERROR_MESSAGE);

                    }else {
                        //根据课程名查询课程id
                        int cid = service.findCID(cname);
                        boolean flag = service.addSc(sno, cid, Integer.parseInt(grade));
                        System.out.println(cid+" "+flag);
                        if (flag){
                            JOptionPane.showMessageDialog(null,"添加成功！");
                        }else{
                            JOptionPane.showMessageDialog(null,"抱歉,添加失败！");
                        }
                    }
                }
            }
        });
        this.add(labTitle);
        this.add(t1);
        this.add(t2);
        this.add(t3);
        this.add(p1);
        this.add(p2);
        this.add(jcb);
        this.add(b1);
        this.setVisible(true);
    }

}
