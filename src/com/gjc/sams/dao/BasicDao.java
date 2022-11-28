package com.gjc.sams.dao;



import com.gjc.sams.utils.Jdbc_Druid_Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * 是其它dao的父类
 * 有update（增删改） 多行返回，单行返回，单行单列返回
 * @author gjc
 */
public class BasicDao {
    private QueryRunner qr=new QueryRunner();


    /**
     *  插入、更新或删除
     * @param sql
     * @param parameters
     * @return
     */
    public int update(String sql,Object... parameters){
        Connection connection=null;
        try {
            connection= Jdbc_Druid_Utils.getConnection();
            int update = qr.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Jdbc_Druid_Utils.Close(null,null,connection);
        }
    }

    /**
     * 返回多行记录
     * @param sql
     * @param tClass
     * @param parameters
     * @param <T>
     * @return
     */
    public <T> List<T> queryMulti(String sql, Class<T> tClass, Object... parameters){
        Connection connection=null;
        try {
            connection= Jdbc_Druid_Utils.getConnection();
            return qr.query(connection, sql, new BeanListHandler<T>(tClass), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Jdbc_Druid_Utils.Close(null,null,connection);
        }

    }

    /**
     * 返回单行
     * @param sql
     * @param tClass
     * @param parameters
     * @param <T>
     * @return
     */
    public <T> T querySingle(String sql, Class<T> tClass, Object... parameters){
        Connection connection=null;
        try {
            connection= Jdbc_Druid_Utils.getConnection();
            return qr.query(connection, sql, new BeanHandler<T>(tClass), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Jdbc_Druid_Utils.Close(null,null,connection);
        }
    }

    /**
     * 返回单行单列
     * @param sql
     * @param parameters
     * @return
     */
    public Object queryScalar(String sql,Object...parameters){
        Connection connection=null;
        try {
            connection= Jdbc_Druid_Utils.getConnection();
            return qr.query(connection, sql, new ScalarHandler<>(),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Jdbc_Druid_Utils.Close(null,null,connection);
        }
    }
}
