package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main3 {
    public static void main(String[] args) {
        // MySQL JDBC Driver 로딩
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

            // SQL 업데이트 쿼리문
            String updateSql = "UPDATE cust SET pwd = ?, name = ? WHERE id = ?";
            ps = conn.prepareStatement(updateSql);

            // 쿼리 파라미터 설정
            ps.setString(1, "newPassword"); // 새 비밀번호
            ps.setString(2, "newName");     // 새 이름
            ps.setString(3, "id06");        // 업데이트할 행의 ID

            // 쿼리 실행
            int result = ps.executeUpdate();
            System.out.println(result + " rows updated in database");
            System.out.println("Update successful");

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
