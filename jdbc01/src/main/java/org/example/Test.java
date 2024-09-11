package org.example;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {

        //Mysql jDBC Driver Loading
        try {
            Class.forName("com.mysql.ck.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // 2.CONNECTION
        String url = "jdbc:mysql://localhost:3306/smdb";
        String sqlid = "smuser";
        String sqlpwd = "111111";

        Connection conn = null;
        try {
            try {
                conn = DriverManager.getConnection(url, sqlid, sqlpwd);
            } catch (CommunicationsException e){
                System.out.println("Connection failed");
                e.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (SQLException e) {
                System.out.println("Connection failed");
                System.out.println(e.getMessage());
                try {
                    Thread.sleep(5000);
                    try {
                        conn = DriverManager.getConnection(url, sqlid, sqlpwd);
                    } catch (Exception ex) {
                        System.out.println("Connection failed");
                        try {
                            throw new Exception(ex);
                        } catch (Exception exc) {
                            throw new RuntimeException(exc);
                        }
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } finally {

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
