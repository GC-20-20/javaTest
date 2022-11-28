package com.gjc.sams.view;

import com.gjc.sams.domain.Course;
import com.gjc.sams.domain.StuSelectPlus;
import com.gjc.sams.domain.sdept;
import com.gjc.sams.service.TeacherService;
import com.gjc.sams.utils.SetMyStyle;
import com.gjc.sams.utils.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/14-18:43
 * 教师界面
 * 修改密码
 * 查询学生信息
 * 录入成绩
 */

public class View4 extends View_Father {
    Font font1 = new Font("宋体", Font.BOLD, 30);
    Font font = new Font("宋体", Font.BOLD, 16);
    private TeacherService service=new TeacherService();
    private JLabel labTitle,login ;
    private JLabel p1,p2,p3,p4,p5,p6;
    private JPasswordField pwd1,pwd2;
    private JTextField t1;
    private JButton b1,b2,b3;
    private JPanel jp1,jp2;
    private JComboBox jcb,jcb2;
    /**
     * JScrollPane是Container类的子类，也是一种容器，但是只能添加一个组件,滚动窗体
     */
    private JScrollPane scpDemo;
    private JTable tabDemo;


    public View4(){
        super();
        //标题和登录欢迎
        labTitle = SetMyStyle.SetMyFont1("教师界面",1);
        labTitle.setBounds(360,10,300,50);
        login=new JLabel("欢迎 "+View1.loginName+" 老师");
        System.out.println("loname:"+View1.loginName);
        login.setFont(font);
        login.setForeground(Color.red);
        login.setBounds(750,40,200,50);
        //其他提示语
        /**
         * 上层
         */
        p1=SetMyStyle.SetMyFont1("修改密码",3);
        p1.setBounds(100,50,200,50);
        p2=SetMyStyle.SetMyFont1("旧密码: ",5);
        p3=SetMyStyle.SetMyFont1("新密码: ",5);
        jp1=new JPanel();
        jp1.setLayout(null);
        jp1.setBounds(150,100,580, 90);
        p2.setBounds(30,0,100,50);
        p3.setBounds(30,40,100,50);
        pwd1=new JPasswordField(15);
        pwd2=new JPasswordField(15);
        pwd1.setBounds(100,10,250,30);
        pwd2.setBounds(100,50,250,30);
        b1=SetMyStyle.SetMyButton1("提交修改",2);
        b1.setBounds(370,50,80,30);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取输入的信息
                String trim1 = new String(pwd1.getPassword()).trim();
                String trim = new String(pwd2.getPassword()).trim();
                //校验旧密码，保存新密码
                if(ValidationUtil.checkPassword(trim)&&ValidationUtil.checkPassword(trim1)){
                    String pwd = service.findPwd(View1.myUser.getSno());
                    if (!pwd.equals(trim1)){
                        JOptionPane.showMessageDialog(null,"输入的旧密码不正确","message", JOptionPane.ERROR_MESSAGE);
                    }else if(trim.equals(trim1)){
                        JOptionPane.showMessageDialog(null,"新旧密码一样","message",JOptionPane.ERROR_MESSAGE);
                    }else{
                        boolean flag = service.updatePwd(trim, View1.myUser.getSno());
                        if (flag){
                            JOptionPane.showMessageDialog(null,"修改成功,即将重新登录");
                            setVisible(false);
                            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            new View1();
                        }else{
                            JOptionPane.showMessageDialog(null,"抱歉，密码更新失败！");
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"请输入正确格式的密码(6-16位数字和字母的组合)","message",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jp1.add(b1);
        jp1.add(p2);
        jp1.add(p3);
        jp1.add(pwd1);
        jp1.add(pwd2);
        /**
         * 中层
         */
        p4=SetMyStyle.SetMyFont1("添加学生课程成绩: ",3);
        p4.setBounds(100,190,200,50);
        b2=SetMyStyle.SetMyButton1("点击添加",3);
        b2.setBounds(300,200,100,30);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new View7();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        /**
         * 下层
         */
        //查询成绩
        p5=SetMyStyle.SetMyFont1("查询本学年学生选课成绩: ",3);
        p5.setBounds(100,230,220,50);
        b3=SetMyStyle.SetMyButton1("查询",2);
        b3.setBounds(330,240,80,30);
        //创建下拉框
        jcb=SetMyStyle.setMyJComboBox(1);
        jcb2=SetMyStyle.setMyJComboBox(1);
        jcb2.addItem("所有课程");
        jcb.addItem("所有院系");
        //添加数据
        List<sdept> sName = service.findAllSName();
        List<Course> Cname = service.findCourse();
        for (sdept sdept : sName) {
            jcb.addItem(sdept.getSdept_name());
        }
        for (Course course : Cname) {
            jcb2.addItem(course.getCname());
        }
        jcb.setBounds(5,0,140,30);
        jcb2.setBounds(150,0,140,30);
        p6=SetMyStyle.SetMyFont1("学号: ",5);
        p6.setBounds(300,0,80,30);
        t1=new JTextField(15);
        t1.setBounds(350,0,200,30);
        scpDemo = new JScrollPane();
        jp2=new JPanel();
        jp2.setBounds(150, 270, 580, 270);
        jp2.setLayout(null);
        jp2.add(jcb);
        jp2.add(jcb2);
        jp2.add(p6);
        jp2.add(t1);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取值
                String sdept =(String) jcb.getSelectedItem();
                String Cname = (String) jcb2.getSelectedItem();
                String sno = t1.getText().trim();
                List<StuSelectPlus> list=null;
                //分情况查询
                if("".equals(sno)){
                    //无学号
                    if("所有院系".equals(sdept)&&"所有课程".equals(Cname)){
                        //所有院系所有课程，全表
                        list = service.findAllSc();
                    }else if ("所有院系".equals(sdept)&&!"所有课程".equals(Cname)){
                        //所有院系,某一课程
                        list=service.findAllSc1(Cname);
                    }
                    if (!"所有院系".equals(sdept)&&"所有课程".equals(Cname)){
                        //某一院系的所有课程
                        list=service.findAllSc2(sdept);

                    }else if(!"所有院系".equals(sdept)&&!"所有课程".equals(Cname)){
                        //某一院系的某一课程
                        list=service.findAllSc3(sdept,Cname);
                    }

                }else{
                    //有学号（校验）
                    if (!ValidationUtil.checkSno(sno)){
                        JOptionPane.showMessageDialog(null,"请输入正确的学号","messade",JOptionPane.ERROR_MESSAGE);
                        return;
                    }else{
                        //检查是不是2003开头的
                        StringBuffer buffer = new StringBuffer(sno);
                        String s = buffer.substring(0, 4);
                        if (!"2003".equals(s)){
                            JOptionPane.showMessageDialog(null,"学号与身份不符","message",JOptionPane.ERROR_MESSAGE);
                            return;
                        }else{
                            if ("所有课程".equals(Cname)){
                                //查询该学生的全部课程
                                list=service.findGrade1(sno);
                            }else{
                                //查询该学生的某一课程
                                list=service.findGrade2(sno,Cname);
                            }
                        }
                    }
                }
                int num=list.size();
                Object[][] info = null;
                String[] title = {"编号","学号", "姓名","课程名", " 得分"};
               if(num>0){
                   info = new Object[num][5];
                   int count=0;
                   for (StuSelectPlus stu : list) {
                       info[count][0]=count+1;
                       info[count][1]=stu.getSno();
                       info[count][2]=stu.getSname();
                       info[count][3]=stu.getCname();
                       info[count][4]=stu.getGrade();
                       count++;

                   }

               }else{
                   info = new Object[1][5];
                   info[0][0]=1;
                   info[0][1]="无记录";
                   info[0][2]="无记录";
                   info[0][3]="无记录";
                   info[0][4]="无记录";
               }
                // 创建JTable表格
                tabDemo = new JTable(info, title);
                // 获得tabDemo的表头，并设置字体样式
                tabDemo.getTableHeader().setFont(font);
                scpDemo.setBounds(0,40,580,230);
                // 将JTable加入到带滚动条的面板中
                scpDemo.getViewport().add(tabDemo);
                jp2.add(scpDemo);
            }
        });

        this.add(jp2);
        this.add(jp1);
        this.add(p4);
        this.add(b2);
        this.add(b3);
        this.add(p1);
        this.add(p5);
        this.add(labTitle);
        this.add(login);
        this.setVisible(true);
    }
}
