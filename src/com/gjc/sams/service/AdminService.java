package com.gjc.sams.service;

import com.gjc.sams.dao.BasicDao;
import com.gjc.sams.domain.Student;
import com.gjc.sams.domain.User;
import com.gjc.sams.domain.sdept;

import java.util.List;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/16-20:49
 * 管理员相关功能类
 */

public class AdminService {
    private BasicDao dao =new BasicDao();

    /**
     * 重置除管理员外的其他用户的密码
     * @param pwd
     * @return
     */
    public boolean updatePwd(String pwd){
        int update = 0;
        try {
            update = dao.update(" UPDATE USER SET PASSWORD=? WHERE SUBSTRING(sno,1,4)='2002' OR SUBSTRING(sno,1,4)='2003'",pwd);
        } catch (Exception e) {
            System.out.println("密码重置失败");
        }
        return update>0;
    }

    /**
     * 查询返回院系列表
     * @return
     */
    public List<sdept> findAllSName(){
        List<sdept> list = dao.queryMulti("select * from sdept", sdept.class);
        return list;
    }

    /**
     * 根据院系id，返回某个院系名
     * @param id
     * @return
     */
    public String findOneSName(int id){
        String sname =(String) dao.queryScalar("select sdept_name from sdept where id=? ", id);
        return sname;
    }
    /**
     * 添加新的人员
     * @param Sno 工(学)号
     * @param name 姓名
     * @param sex 性别
     * @param age 年龄
     * @param sdeptName 学院
     * @return Boolean
     */
    public boolean addPerson(String Sno,String name,String sex ,byte age,String sdeptName){
        int update = 0;
        int id=0;
        try {
             id =(int)dao.queryScalar("select id from sdept where sdept_name=?", sdeptName);
            update = dao.update("insert into student values(?,?,?,?,?)",Sno,name,sex,age,id);
        } catch (Exception e) {
            System.out.println("添加人员失败");
        }
        return update>0;

    }
    public int findSID(String sdeptName){
        return (int)dao.queryScalar("select id from sdept where sdept_name=?", sdeptName);
    }
    /**
     * 添加课程
     * @param cname 课程名称
     * @return Boolean
     */
    public boolean addCourse(String cname,byte Ccredit){
        int update = 0;
        try {
            update = dao.update("insert into course values(null,?,?)",cname,Ccredit);
        } catch (Exception e) {
            System.out.println("课程添加失败");
        }
        return update>0;
    }

    /**
     * 添加院系
     * @param name
     * @return
     */
    public boolean addSdept(String name){
        int update = 0;
        try {
            update = dao.update("insert into sdept values(null,?)",name);
        } catch (Exception e) {
            System.out.println("院系添加失败");
        }
        return update>0;
    }

    /**
     * 通过工(学)号，查询人员信息
     * @param sno
     * @return
     */
    public Student findBySno(String sno){
        Student student = dao.querySingle("select * from student where Sno=?", Student.class, sno);
        return student;
    }

    /**
     * 更新人员信息
     * @param s 封装的Bean
     * @return
     */
    public boolean updatePerson(Student s){
        int update = 0;
        try {
            update = dao.update("update student set Sname=?,Ssex=?,Sage=? ,Sdept_id=? where Sno=? ",
                    s.getSname(),s.getSsex(),s.getSage(),s.getSdept_id(),s.getSno());
        } catch (Exception e) {
            System.out.println("信息修改失败");
        }
        return update>0;
    }

    /**
     * student的sno与User表和Sc表的sno都建立外键关系
     * 并且添加了删除级联
     * 当从student表中删除一个人员时，也会同时删除User和Sc表中与其关联的所有信息
     *
     * 慎用！！！！！！
     *
     * @param s 工(学)号
     * @return
     */
    public boolean delPerson(String s){
        int update = 0;
        try {
            update = dao.update("delete from student where Sno=? ", s);
        } catch (Exception e) {
            System.out.println("人员删除失败");
        }
        return update>0;
    }

    /**
     * 检查要删除的人员还在不在
     * @param Sno 学号
     * @return 在-TRUE 不在-FALSE
     */
    public boolean delCheck(String Sno){
        User user=null;
        user = dao.querySingle("select * from User where Sno=?", User.class, Sno);
        return user!=null;
    }


}
