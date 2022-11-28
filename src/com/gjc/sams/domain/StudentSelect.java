package com.gjc.sams.domain;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/16-10:20
 */


public class StudentSelect {

    private int Cno;
    private String Cname;
    private int Grade;


    public int getCno() {
        return Cno;
    }

    public void setCno(int cno) {
        Cno = cno;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    @Override
    public String toString() {
        return "StudentSelect{" +
                "Cno=" + Cno +
                ", Cname='" + Cname + '\'' +
                ", Grade=" + Grade +
                '}';
    }
}
