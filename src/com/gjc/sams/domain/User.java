package com.gjc.sams.domain;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/16-20:34
 */

public class User {
    private String Sno;
    private String username;
    private String password;

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
