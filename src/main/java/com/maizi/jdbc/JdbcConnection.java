package com.maizi.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName JdbcConnection
 * @Description
 * @Author Lenovo
 * @Date 2019/7/2 15:35
 * @Verson 1.0
 **/
public class JdbcConnection {
    private final static Logger log = LoggerFactory.getLogger(JdbcConnection.class);
    public final static String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/jdbc?useSSL=false&useUnicode=true" +
            "&characterEncoding=utf-8&serverTimezone=UTC";
    public final static String DB_USERNAME = "root";
    public final static String DB_PASSWORD = "root";
    public final static String INSERT_TEST1 = "";
    public final static String INSERT_TEST2 = "";


    public static Connection getConnection() throws ClassNotFoundException , SQLException {
        Connection conn = null;
        Class.forName(DB_DRIVER_CLASS);
        conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        return conn;
    }

    public static void insertTest1(Connection conn) throws SQLException {
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("Insert into test1(id,name) values(?,?)");
        stmt.setInt(1,1);
        stmt.setString(2,"1");
        stmt.executeUpdate();
        log.info("=====insert into test1 successfully====");
        stmt.close();

    }

    public static void insertTest2(Connection conn) throws SQLException{
        PreparedStatement stmt;
        stmt = conn.prepareStatement("insert into test2(id,name) values(?,?)");
        stmt.setInt(1,1);
        //数据库中长度是1，这里故意长度超出
        stmt.setString(2,"11");
        stmt.executeUpdate();
        log.info("======insert into test2 successfully======");
        stmt.close();
    }
}
