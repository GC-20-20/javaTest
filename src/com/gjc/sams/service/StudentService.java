package com.gjc.sams.service;

import com.gjc.sams.dao.BasicDao;
import com.gjc.sams.domain.StudentSelect;

import java.util.List;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/16-20:48
 * 学生相关功能的实现
 */

public class StudentService {
   private BasicDao dao = new BasicDao();
    /**
     * 根据sno查找返回院系
     * @param sno
     * @return
     */
    public String findSdept(String sno){
        String Sdept_name =(String) dao.queryScalar("SELECT sdept_name FROM sdept WHERE sdept.`id` IN "
                + " (SELECT student.`Sdept_id` FROM student WHERE sno=?)",sno);
        return Sdept_name;
    }

    /**
     * 根据学号查询所学学科和得分
     * @param sno
     * @return
     */
    public List<StudentSelect> findGrade(String sno){
        List<StudentSelect> list = dao.queryMulti("SELECT course.`Cno`,course.`Cname`,sc.`Grade` FROM sc,course " +
                " WHERE sc.`Cno`=course.`Cno` AND sc.`Sno`=?;", StudentSelect.class, sno);
        return list;
    }

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
}
