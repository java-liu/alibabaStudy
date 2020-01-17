package com.example.demo.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName JdbcTransaction
 * @Description jdbc有事务
 * @Author Lenovo
 * @Date 2019/7/2 15:34
 * @Verson 1.0
 **/
public class JdbcTransaction {
    private final static Logger log= LoggerFactory.getLogger(JdbcTransaction.class);
//    public static void main(String[] args){
//        Connection con = null;
//        try{
//            con = JdbcConnection.getConnection();
//            con.setAutoCommit(false);
//            JdbcConnection.insertTest1(con);
//            JdbcConnection.insertTest2(con);
//            con.commit();
//            log.info("======JDBC Transaction commit=====");
//        }catch (ClassNotFoundException| SQLException e){
//            try{
//                con.rollback();
//                log.info("====JDBC Transaction rolled back successfully====");
//            }catch (SQLException e1){
//                log.info("=======SQL Exception in rollback======" + e1.getMessage());
//            }
//            e.printStackTrace();
//        }finally {
//            if(con != null){
//                try{
//                    con.close();
//                }   catch (SQLException e1){
//                    e1.printStackTrace();
//                }
//            }
//        }
//    }
}
