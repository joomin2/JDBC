package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return; // 드라이버 로드 실패 시 프로그램 종료
        }

        // 데이터베이스 연결 설정
        String url = "jdbc:mysql://localhost:3306/smdb";
        String sqlid = "smuser";
        String sqlpwd = "111111";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // 데이터베이스 연결
            conn = DriverManager.getConnection(url, sqlid, sqlpwd);

            // SQL 삭제 쿼리문
            String deleteSql = "DELETE FROM cust WHERE id = ?";
            ps = conn.prepareStatement(deleteSql);

            // 쿼리 파라미터 설정
            ps.setString(1, "id06"); // 삭제할 행의 ID

            // 쿼리 실행
            int result = ps.executeUpdate();
            System.out.println(result + " rows deleted from database");
            System.out.println("Delete successful");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 자원 정리
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
