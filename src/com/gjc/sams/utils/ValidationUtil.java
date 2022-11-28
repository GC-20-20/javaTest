package com.gjc.sams.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/18-20:47
 * 校验输入字符的合法性
 * 用户名（汉字、字母、数字的组合）：^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$
 *
 * 密码（6-16位数字和字母的组合）：^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$
 *
 * 年龄（1岁--120岁）：^(?:[1-9][0-9]?|1[01][0-9]|120)$
 *
 * 电话号码：^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$
 */

public class ValidationUtil {
    /**
     * 校验学号是不是11位，并且是不是都是数字,开头三位必须得是200
     * @param str 输入的学号
     * @return
     */
    public static boolean checkSno(String str){
        Pattern pattern = compile("^200\\d{8}");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 性别的校验
     * @param sex
     * @return
     */
    public static boolean checkSex(String sex){
        if ("男".equals(sex)){
            return true;
        }else if("女".equals(sex)){
            return true;
        }
        return false;
    }

    /**
     * 正则校验年龄在1-120之间
     * @param age
     * @return
     */
    public static boolean checkAge(String age){
          Pattern pattern=compile("^(?:[1-9][0-9]?|1[01][0-9]|120)$");
        Matcher isName = pattern.matcher(age);
        if (!isName.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 检查分数的合法性（0-100）
     * @param grade
     * @return
     */
    public static boolean checkGrade(String grade){
        Pattern pattern=compile("^(?:[0-9][0-9]?|100)$");
        Matcher isName = pattern.matcher(grade);
        if (!isName.matches() ){
            return false;
        }
        return true;
    }
    /**
     * 校验名字是不是中文，并且是1,6位之间（包含）
     * @param name
     * @return
     */
    public static boolean checkName(String name){
        Pattern pattern=compile("^[\\u4e00-\\u9fa5]{2,6}$");
        Matcher isName = pattern.matcher(name);
        if (!isName.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 校验密码数字和字母的组合（6-16位）
     * @param pwd
     * @return
     */
    public static boolean checkPassword(String pwd){
        Pattern pattern=compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
        Matcher pwds = pattern.matcher(pwd);
        if(!pwds.matches()){
            return false;
        }
        return true;
    }
    /**
     * 校验添加的课程名开头可以为字母
     * @param name
     * @return
     */
    public static boolean checkCname(String name){
        Pattern pattern=compile("^[a-zA-Z\\u4e00-\\u9fa5]{2,10}$");
        Matcher isName = pattern.matcher(name);
        if (!isName.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 校验院系名称（中文+最后二字为学院）
     * @param name
     * @return
     */
    public static boolean checkSdept(String name){
        Pattern pattern=compile("^[\\u4e00-\\u9fa5]{2,10}$");
        Matcher isName = pattern.matcher(name);
        StringBuffer buffer=new StringBuffer(name);
        int len=buffer.length();
        String s = buffer.substring(len - 2, len);
        System.out.println("s="+s);
        if (!isName.matches() ){
            return false;
        }else if(!"学院".equals(s)){
            return false;
        }
        return true;
    }


    /**
     * 检查学分1-5之间的任意值
     * @param credit
     * @return
     */
    public static boolean checkCredit(String credit){
        Pattern pattern=compile("[1-5]");
        Matcher isName = pattern.matcher(credit);
        if (!isName.matches() ){
            return false;
        }
        return true;
    }



}
