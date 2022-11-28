package com.gjc.sams.service;

import com.gjc.sams.dao.BasicDao;
import com.gjc.sams.domain.User;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/16-20:47
 *
 */

public class LoginService {
    private  BasicDao dao =new BasicDao();

    /**
     * 登录
     * @param Sno 工（学）号
     * @param username 用户名
     * @param password 密码
     * @return 查询User表，返回一个User对象
     */
    public  User loginCheck(String Sno,String username,String password){
        User user=null;
        user = dao.querySingle("select * from User where Sno=? and username=? and password=?", User.class, Sno, username, password);
        return user;
    }

    /**
     * 注册
     * @param Sno 工（学）号
     * @param username 用户名
     * @param password 密码
     * @return 注册成功TRUE 失败false
     */
    public  boolean register(String Sno,String username,String password){
        int update = 0;
        try {
            update = dao.update("insert into User values(?,?,?)", Sno, username, password);
        } catch (Exception e) {
            System.out.println("主键重复");
        } finally {
            return update == 1;
        }

    };
}
