package com.gjc.sams.domain;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/18-19:09
 */

public class sdept {
    private int id;
    private String sdept_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSdept_name() {
        return sdept_name;
    }

    public void setSdept_name(String sdept_name) {
        this.sdept_name = sdept_name;
    }

    @Override
    public String toString() {
        return "sdept{" +
                "id=" + id +
                ", sdept_name='" + sdept_name + '\'' +
                '}';
    }
}
