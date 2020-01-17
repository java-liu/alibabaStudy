package com.example.demo.jdbc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName JdbcNoTransaction
 * @Description jdbc没有事务
 * @Author Lenovo
 * @Date 2019/7/2 13:43
 * @Verson 1.0
 **/
public class JdbcNoTransaction {
    private final static Logger log = LoggerFactory.getLogger(JdbcNoTransaction.class);


//    public static void main(String[] args){
//        Connection conn = null;
//        try{
//            conn = JdbcConnection.getConnection();
//            JdbcConnection.insertTest1(conn);
//            JdbcConnection.insertTest2(conn);
//        }catch (ClassNotFoundException|SQLException e){
//            e.printStackTrace();
//        }finally {
//            if(conn != null){
//                try{
//                    conn.close();
//                }catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
