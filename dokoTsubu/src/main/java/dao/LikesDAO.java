package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DatabaseUtils;

public class LikesDAO {

    public boolean insert(int mutterId, int userId) {
        String sql = "INSERT INTO likes (mutterId, userId) VALUES (?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {
            pStmt.setInt(1, mutterId);
            pStmt.setInt(2, userId);
            return pStmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert like", e);
        }
    }

    public boolean delete(int mutterId, int userId) {
        String sql = "DELETE FROM likes WHERE mutterId=? AND userId=?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {
            pStmt.setInt(1, mutterId);
            pStmt.setInt(2, userId);
            return pStmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete like", e);
        }
    }

    public int countLikes(int mutterId) {
        String sql = "SELECT COUNT(*) FROM likes WHERE mutterId = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {
            pStmt.setInt(1, mutterId);
            try (ResultSet rs = pStmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to count likes", e);
        }
    }

    public boolean isLiked(int mutterId, int userId) {
        String sql = "SELECT COUNT(*) FROM likes WHERE mutterId = ? AND userId = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {
            pStmt.setInt(1, mutterId);
            pStmt.setInt(2, userId);
            try (ResultSet rs = pStmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check if liked", e);
        }
    }
}
