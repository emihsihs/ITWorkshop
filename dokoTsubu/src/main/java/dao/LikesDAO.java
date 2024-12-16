package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikesDAO {
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/desktop/SQL/dokoTsubu";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";

    public boolean insert(int mutterId, int userId) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO likes (mutterId, userId) VALUES (?, ?)";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setInt(1, mutterId);
                pStmt.setInt(2, userId);
                int result = pStmt.executeUpdate();
                return result == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int mutterId, int userId) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "DELETE FROM likes WHERE mutterId=? AND userId=?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setInt(1, mutterId);
                pStmt.setInt(2, userId);
                int result = pStmt.executeUpdate();
                return result == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int countLikes(int mutterId) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT COUNT(*) FROM likes WHERE mutterId = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setInt(1, mutterId);
                try (ResultSet rs = pStmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 新しいメソッドを追加
    public boolean isLiked(int mutterId, int userId) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT COUNT(*) FROM likes WHERE mutterId = ? AND userId = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setInt(1, mutterId);
                pStmt.setInt(2, userId);
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
}
