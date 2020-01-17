package com.example.demo.jdbc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import	java.util.ArrayList;
import	java.sql.ResultSet;

import com.example.demo.entity.Configuration;
import com.example.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName JdbcConnection
 * @Description
 * @Author Lenovo
 * @Date 2019/7/2 15:35
 * @Verson 1.0
 **/
public class JdbcConnection {
    private final static Logger log = LoggerFactory.getLogger(JdbcConnection.class);
    //public final static String DB_DRIVER_CLASS = "sgcc.nds.jdbc.driver.NdsDriver";
    //public final static String DB_URL = "jdbc:nds://172.21.2.150:18702,172.21.2.151:18702,172.21.2.152:18702/v_18702_sgxcksh?appname=app_aqscgkxt";
    public final static String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://192.168.1.112:3306/ksh?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    public final static String DB_USERNAME = "root";
    public final static String DB_PASSWORD = "root";
    public final static String INSERT_TEST1 = "";
    public final static String INSERT_TEST2 = "";

    //public static String filePath = "D:\\liuys\\images\\02026-1.jpg";
    public static String filePath = "/home/images/02026-1.jpg";

    public static Connection getConnection() throws ClassNotFoundException , SQLException {
        Connection conn = null;
        Class.forName(DB_DRIVER_CLASS);
        conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        return conn;
    }

    public static void insertTest1(Connection conn) throws SQLException, FileNotFoundException {
        PreparedStatement stmt = null;
        File file = new File(filePath);
        InputStream input = null;
        input = new FileInputStream(file);

        stmt = conn.prepareStatement("Insert into test1(id,name,img) values(?,?,?)");
        stmt.setInt(1,1);
        stmt.setString(2,"1");
        stmt.setBinaryStream(3, input, (int)file.length());
        stmt.executeUpdate();
        log.info("=====insert into test1 successfully====");
        stmt.close();

    }

    public static void insertTest2(Connection conn) throws SQLException{
        PreparedStatement stmt;
        stmt = conn.prepareStatement("insert into test1(id,name) values(?,?)");
        stmt.setInt(1,1);
        //数据库中长度是1，这里故意长度超出
        stmt.setString(2,"11");
        stmt.executeUpdate();
        log.info("======insert into test2 successfully======");
        stmt.close();
    }
    public static List<User> getAll(Connection conn) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User> ();
        try{
            stmt = conn.prepareStatement("select id,name from user");
            rs = stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                User user = new User(id,name);
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }
        return users;
    }
    public static List<Configuration> getConfigurations(Connection conn) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Configuration> cons = new ArrayList<Configuration> ();
        try{
            stmt = conn.prepareStatement("select id,config_code,config_value,miaoshu from rt_configuration");
            rs = stmt.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String configCode = rs.getString(2);
                String configValue = rs.getString(3);
                String miaoshu = rs.getString(4);
                Configuration con = new Configuration(id,configCode, configValue,miaoshu);
                cons.add(con);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }
        return cons;
    }
}
