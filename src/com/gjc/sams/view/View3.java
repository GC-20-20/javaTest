package com.gjc.sams.view;

import com.gjc.sams.domain.StudentSelect;
import com.gjc.sams.service.StudentService;
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
 * @date 2022/10/14-18:42
 * 学生页面
 * 修改密码
 * 查询成绩
 */

public class View3 extends View_Father{
    private static Font font = new Font("宋体", Font.BOLD, 16);
    private StudentService service=new StudentService();
    private JLabel labTitle,login ;
    private JLabel p1,p2,p3,p4,p5;
    private JPasswordField pwd1,pwd2;
    private JButton b1,b2;
    private JPanel bp;
    /**
     * JScrollPane是Container类的子类，也是一种容器，但是只能添加一个组件,滚动窗体
     */
    private JScrollPane scpDemo;
    private JTable tabDemo;


    public View3(){
        super();
        //标题和登录欢迎
        labTitle = SetMyStyle.SetMyFont1("学生界面",1);
        labTitle.setBounds(360,10,300,50);
        login=SetMyStyle.SetMyFont1("欢迎 "+View1.loginName+" 同学",5);
        System.out.println("loname:"+View1.loginName);
        login.setBounds(750,40,200,50);
        //其他提示语
        p1=SetMyStyle.SetMyFont1("修改密码",3);
        p1.setBounds(100,50,200,50);
        p2=SetMyStyle.SetMyFont1("旧密码：",2);
        p3=SetMyStyle.SetMyFont1("新密码：",2);
        p2.setBounds(250,100,100,50);
        p3.setBounds(250,150,100,50);
        pwd1=new JPasswordField(15);
        pwd2=new JPasswordField(15);
        pwd1.setBounds(340,110,250,30);
        pwd2.setBounds(340,160,250,30);
        b1=SetMyStyle.SetMyButton1("提交修改",2);
        b1.setBounds(600,160,80,30);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取输入的信息
                String trim1 = new String(pwd1.getPassword()).trim();
                String trim = new String(pwd2.getPassword()).trim();
                //校验旧密码，保存新密码
                String pwd = service.findPwd(View1.myUser.getSno());
                if(trim.length()==0||trim1.length()==0){
                    JOptionPane.showMessageDialog(null,"新旧密码不允许为空","message",JOptionPane.ERROR_MESSAGE);
                }else if(!ValidationUtil.checkPassword(trim)||!ValidationUtil.checkPassword(trim1)){
                    JOptionPane.showMessageDialog(null,"密码格式不正确","message", JOptionPane.ERROR_MESSAGE);
                } else if (!pwd.equals(trim1)){
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
            }
        });
        //查询成绩
        p4=SetMyStyle.SetMyFont1("查询本学年的选课成绩",3);
        p4.setBounds(100,200,200,50);
        b2=SetMyStyle.SetMyButton1("查询成绩",2);
        b2.setBounds(340,210,80,30);
        scpDemo = new JScrollPane();
        //底部bp
        bp=new JPanel();
        bp.setBounds(150, 240, 580, 300);
        bp.setLayout(null);
        //从数据库获取
        String sdept =service.findSdept(View1.myUser.getSno());
        if (sdept==null){
            sdept="未知";
        }
        String tit="院系："+sdept+" 学号："+View1.myUser.getSno()+" 姓名: "+View1.myUser.getUsername();
        p5=SetMyStyle.SetMyFont1(tit,4);
        p5.setBounds(130,0,580,20);
        bp.add(p5);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                //count为数据库查询的记录数
                List<StudentSelect> list = service.findGrade(View1.myUser.getSno());
                int num=list.size();
                int count=0;
                Object[][] info = null;
                String[] title = {"课程编号", "课程名称", " 课程得分"};
                double sum=0;
                if (num==0){
                    info = new Object[num+1][3];
                    info[count][2]="课程平均分"+0.0;
                }else{
                    info = new Object[num+1][3];
                    for (StudentSelect stu : list) {
                        info[count][0]=stu.getCno();
                        info[count][1]=stu.getCname();
                        info[count][2]=stu.getGrade();
                        sum+=(double) (Integer)info[count][2];
                        count++;
                    }
                    info[count][2]="课程平均分"+sum/count;
                }
                info[count][0]="共选课程"+count+"门";
                info[count][1]="课程总分："+sum;

                // 创建JTable
                tabDemo = new JTable(info, title);
                // 获得tabDemo的表头，并设置字体样式
                tabDemo.getTableHeader().setFont(font);
                scpDemo.setBounds(0,20,580,280);
                // 将JTable加入到带滚动条的面板中
                scpDemo.getViewport().add(tabDemo);
                bp.add(scpDemo);
            }
        });

        this.add(bp);
        this.add(b2);
        this.add(p4);
        this.add(b1);
        this.add(pwd1);
        this.add(pwd2);
        this.add(p2);
        this.add(p3);
        this.add(p1);
        this.add(labTitle);
        this.add(login);
        this.setVisible(true);
    }


}
