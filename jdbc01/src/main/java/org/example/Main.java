package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Mysql jDBC Driver Loading
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
            } catch (SQLException e) {
                System.out.println("Connection failed");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } finally {

        }
        // 3, SQL
        String insertSql = "INSERT INTO cust VALUES(?,?,?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(insertSql);
            ps.setString(1, "id06");
            ps.setString(2, "pwd07");
            ps.setString(3, "제갈주민");
            int result = ps.executeUpdate();
            System.out.println(result);
            System.out.println("Inserted rows into database");
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
            }
        }
    }
    }

