package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
    // データベース接続に使用する情報
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/desktop/SQL/dokoTsubu";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";

    static {
        try {
            // H2データベースドライバーをロード
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User findUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * FROM users WHERE USERNAME = ? AND PASSWORD = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setString(1, username);
                pStmt.setString(2, password);

                try (ResultSet rs = pStmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("ID");
                        return new User(id, username, password);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ユーザー名の重複をチェックするメソッド
    public boolean isUserNameTaken(String username) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setString(1, username);
                try (ResultSet rs = pStmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ユーザーを作成するメソッド
    public boolean create(User user) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setString(1, user.getName());
                pStmt.setString(2, user.getPassword());
                int result = pStmt.executeUpdate();
                return result == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
