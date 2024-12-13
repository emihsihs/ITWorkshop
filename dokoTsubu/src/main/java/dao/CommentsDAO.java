package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentsDAO {
    // データベース接続に使用する情報
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/desktop/SQL/dokoTsubu";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";

    public boolean deleteByMutterId(int mutterId) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "DELETE FROM COMMENTS WHERE MUTTERID=?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setInt(1, mutterId);
                int result = pStmt.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
