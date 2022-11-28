package com.gjc.sams.service;

import com.gjc.sams.dao.BasicDao;
import com.gjc.sams.domain.Course;
import com.gjc.sams.domain.StuSelectPlus;
import com.gjc.sams.domain.sdept;

import java.util.List;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/16-20:48
 * 教师的相关功能类
 */

public class TeacherService {
    private BasicDao dao=new BasicDao();
    /**
     * 根据学号查询旧密码
     * @param sno
     * @return
     */
    public String findPwd(String sno){
        String pwd =(String) dao.queryScalar("select password from User where Sno=?",sno);
        return pwd;
    }
    /**
     * 更新密码
     * @param pwd
     * @param Sno
     * @return
     */
    public boolean updatePwd(String pwd,String Sno){
        int update = 0;
        try {
            update = dao.update("update User set password=? where Sno=?",pwd,Sno);
        } catch (Exception e) {
            System.out.println("密码更新失败");
        }
        return update==1;
    }
    /**
     * 根据课程名查找对应的课程id
     * @param cname 课程名
     * @return
     */
    public int findCID(String cname){
        int Cno = (int)dao.queryScalar("select Cno from course where Cname=?",cname);
        return Cno;
    }

    /**
     * 添加学生课程得分
     * @param sno 学号
     * @param cname 所学课程名
     * @param grade 得分
     * @return
     */
    public boolean addSc(String sno,int cname , int grade){
        int update = 0;
        try {
            update = dao.update("insert into sc values(?,?,?)",sno,cname,grade);
        } catch (Exception e) {
            System.out.println("课程添加失败");
        }
        return update>0;
    }

    /**
     * 查询返回所有课程
     * @return
     */
    public List<Course> findCourse(){
        List<Course> list = dao.queryMulti("select * from course", Course.class);
        return list;
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
     * 无学号情况下
     * 所有院系所有课程
     * @return
     */
    public List<StuSelectPlus> findAllSc(){
        List<StuSelectPlus> list = dao.queryMulti("SELECT sc.`Sno`,student.`Sname`,course.`Cname`,sc.`Grade` " +
                " FROM sc,student,course WHERE sc.`Sno`=student.`Sno` AND sc.`Cno`=course.`Cno`; ",StuSelectPlus.class);
        return list;
    }

    /**
     * 无学号情况下
     * 所有院系某一课程
     * @param cname
     * @return
     */
    public List<StuSelectPlus> findAllSc1(String cname){
        List<StuSelectPlus> list = dao.queryMulti("SELECT sc.`Sno`,student.`Sname`,course.`Cname`,sc.`Grade` " +
                " FROM sc,student,course WHERE sc.`Sno`=student.`Sno` AND sc.`Cno`=course.`Cno` and Cname=?; ",StuSelectPlus.class,cname);
        return list;
    }

    /**
     * 无学号情况下
     * 某一学院所有课程
     * @param sdept_name
     * @return
     */
    public List<StuSelectPlus> findAllSc2(String sdept_name){
        List<StuSelectPlus> list = dao.queryMulti("SELECT sc.`Sno`,student.`Sname`,course.`Cname`,sc.`Grade`" +
                " FROM sc,student,course,sdept " +
                " WHERE sc.`Sno`=student.`Sno` AND sc.`Cno`=course.`Cno` AND sdept.`id`=student.`Sdept_id` " +
                " AND sdept.`sdept_name`=?;", StuSelectPlus.class, sdept_name);
        return list;
    }

    /**
     * 无学号情况下
     * 某一学院某一课程
     * @param sdept_name 学院
     * @param Cname 课程
     * @return
     */
    public List<StuSelectPlus> findAllSc3(String sdept_name,String Cname){
        List<StuSelectPlus> list = dao.queryMulti("SELECT sc.`Sno`,student.`Sname`,course.`Cname`,sc.`Grade`" +
                " FROM sc,student,course,sdept " +
                " WHERE sc.`Sno`=student.`Sno` AND sc.`Cno`=course.`Cno` AND sdept.`id`=student.`Sdept_id` " +
                " AND sdept.`sdept_name`=? AND course.`Cname`=?;", StuSelectPlus.class, sdept_name,Cname);
        return list;
    }

    /**
     * 有学号的情况下
     * 院系信息已经无影响了
     * 查询该学生全部课程
     * @param sno 学号
     * @return
     */
    public List<StuSelectPlus> findGrade1(String sno){
        List<StuSelectPlus> list = dao.queryMulti("SELECT sc.`Sno`,student.`Sname`,course.`Cname`,sc.`Grade` " +
                "FROM sc,student,course WHERE sc.`Sno`=student.`Sno` AND sc.`Cno`=course.`Cno` AND sc.`Sno`=?;", StuSelectPlus.class, sno);
        return list;
    }

    /**
     *有学号的情况下
     *院系信息已经无影响了
     *查询该学生某一课程成绩
     * @param sno 学号
     * @param cname 课程名
     * @return
     */
    public List<StuSelectPlus> findGrade2(String sno,String cname){
        List<StuSelectPlus> list = dao.queryMulti("SELECT sc.`Sno`,student.`Sname`,course.`Cname`,sc.`Grade` " +
                "FROM sc,student,course WHERE sc.`Sno`=student.`Sno` AND sc.`Cno`=course.`Cno` " +
                "AND sc.`Sno`=? and course.`Cname`=?;", StuSelectPlus.class, sno,cname);
        return list;
    }

}
