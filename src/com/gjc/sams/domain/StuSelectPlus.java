package com.gjc.sams.domain;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/19-18:06
 */

public class StuSelectPlus {
    private String Sno;
    private String Sname;
    private String Cname;
    private int Grade;

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
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
        return "StuSelectPlus{" +
                "Sno='" + Sno + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Grade=" + Grade +
                '}';
    }
}
