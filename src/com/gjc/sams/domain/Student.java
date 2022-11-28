package com.gjc.sams.domain;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/14-16:24
 */

public class Student {
    private String Sno;
    private String Sname;
    private String Ssex;
    private byte Sage;
    private int Sdept_id;

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

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public byte getSage() {
        return Sage;
    }

    public void setSage(byte sage) {
        Sage = sage;
    }

    public int getSdept_id() {
        return Sdept_id;
    }

    public void setSdept_id(int sdept_id) {
        Sdept_id = sdept_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Sno='" + Sno + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Ssex='" + Ssex + '\'' +
                ", Sage=" + Sage +
                ", Sdept_id=" + Sdept_id +
                '}';
    }
}
