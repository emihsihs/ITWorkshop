package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.DatabaseUtils;

public class CommentsDAO {

    public boolean deleteByMutterId(int mutterId) {
        String sql = "DELETE FROM COMMENTS WHERE MUTTERID=?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {
            pStmt.setInt(1, mutterId);
            return pStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete comments by mutter ID", e);
        }
    }
}
