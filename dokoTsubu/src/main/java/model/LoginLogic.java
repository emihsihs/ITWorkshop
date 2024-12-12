package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginLogic {
    public boolean execute(User user) {
        boolean isLogin = false;

        try {
            // H2データベースドライバーをロード
            Class.forName("org.h2.Driver");

            try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/desktop/SQL/dokoTsubu", "sa", "")) {
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, user.getName());
                    pstmt.setString(2, user.getPass());

                    // デバッグ用ログ
                    System.out.println("Executing query with username: " + user.getName() + " and password: " + user.getPass());

                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            isLogin = true;
                            // デバッグ用ログ
                            System.out.println("Login successful for user: " + user.getName());
                        } else {
                            // デバッグ用ログ
                            System.out.println("Login failed: no user found with given username and password");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class not found Exception: " + e.getMessage()); // デバッグ用ログ
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage()); // デバッグ用ログ
        }

        return isLogin;
    }
}

