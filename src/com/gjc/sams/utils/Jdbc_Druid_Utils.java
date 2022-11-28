package com.gjc.sams.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * 德鲁伊连接池工具类
 * 连接池工具类，连接数据库
 * @author gjc
 */

public class Jdbc_Druid_Utils {
    private static DataSource source;
    //初始化连接
    static {
        Properties properties = new Properties();
        try {
            //使用ClassLoader加载配置文件，获取字节输入流
            InputStream is = Jdbc_Druid_Utils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            source = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取连接
    public static Connection getConnection() throws SQLException {
        return source.getConnection();
    }

    /**
     * 释放连接(不是断掉连接，而是归还释放)
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void Close(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if (resultSet!=null) {
                resultSet.close();
            }
            if (statement!=null) {
                statement.close();
            }
            if (connection!=null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
