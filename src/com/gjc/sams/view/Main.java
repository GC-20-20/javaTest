package com.gjc.sams.view;

/**
 * @author gjc
 * @version 1.0
 * @date 2022/10/14-10:29
 *  启动类
 */

public class Main {
    public static void main(String[] args) {
        //窗体美化
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
            System.out.println("hellowrld");
        }
        new View1();
    }

}
