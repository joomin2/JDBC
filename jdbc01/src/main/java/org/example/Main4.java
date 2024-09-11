package org.example;

import java.sql.*;

public class Main4 {
    public static void main(String[] args) {
        // MySQL JDBC Driver Loading
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace();
        }

        // 2. CONNECTION
        String url = "jdbc:mysql://localhost:3306/smdb";
        String sqlid = "smuser";
        String sqlpwd = "111111";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establishing connection
            conn = DriverManager.getConnection(url, sqlid, sqlpwd);

            // 3. SQL Query
            String selectOneSql = "SELECT * FROM cust WHERE id = ?";
            ps = conn.prepareStatement(selectOneSql);
            ps.setString(1, "id04");  // Set the id value to "id04"

            // Execute the query
            rs = ps.executeQuery();

            // Iterate through the ResultSet
            while (rs.next()) {
                // Get data from columns by index or column name
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");

                // Print the data
                System.out.println("ID: " + id);
                System.out.println("Password: " + pwd);
                System.out.println("Name: " + name);
            }

        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
