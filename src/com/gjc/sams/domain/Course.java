package com.gjc.sams.domain;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/14-16:25
 */

public class Course {
    private int Cno; //课程号
    private String Cname; //课程名
    private byte Ccredit; //课程分数

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

    public byte getCcredit() {
        return Ccredit;
    }

    public void setCcredit(byte ccredit) {
        Ccredit = ccredit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Cno=" + Cno +
                ", Cname='" + Cname + '\'' +
                ", Ccredit=" + Ccredit +
                '}';
    }
}
