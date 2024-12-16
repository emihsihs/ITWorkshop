package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.Mutter;

public class MuttersDAO {
    // データベース接続に使用する情報
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/desktop/SQL/dokoTsubu";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";

    public List<Mutter> findAll() {
        List<Mutter> mutterList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT ID, NAME, TEXT, TAG, LIKECOUNT, IMAGEURL FROM MUTTERS ORDER BY ID DESC";
            try (PreparedStatement pStmt = conn.prepareStatement(sql); ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String userName = rs.getString("NAME");
                    String text = rs.getString("TEXT");
                    String tag = rs.getString("TAG");
                    int likeCount = rs.getInt("LIKECOUNT");
                    String imageUrl = rs.getString("IMAGEURL");
                    List<Comment> comments = new ArrayList<>();
                    Mutter mutter = new Mutter(id, userName, text, tag, likeCount, comments, imageUrl);
                    mutterList.add(mutter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mutterList;
    }

    public boolean create(Mutter mutter) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pStmt = conn.prepareStatement("INSERT INTO MUTTERS(NAME, TEXT, TAG, IMAGEURL) VALUES(?, ?, ?, ?)")) {
            pStmt.setString(1, mutter.getUserName());
            pStmt.setString(2, mutter.getText());
            pStmt.setString(3, mutter.getTag());
            pStmt.setString(4, mutter.getImageUrl());
            int result = pStmt.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int mutterId) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // まず、関連するcommentsレコードを削除
            try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM comments WHERE mutterId = ?")) {
                pstmt.setInt(1, mutterId);
                pstmt.executeUpdate();
            }

            // 次に、関連するlikesレコードを削除
            try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM likes WHERE mutterId = ?")) {
                pstmt.setInt(1, mutterId);
                pstmt.executeUpdate();
            }

            // 最後に、mutterレコードを削除
            try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM mutters WHERE id = ?")) {
                pstmt.setInt(1, mutterId);
                int result = pstmt.executeUpdate();
                return result == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String findUserByMutterId(int mutterId) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pStmt = conn.prepareStatement("SELECT NAME FROM mutters WHERE ID = ?")) {
            pStmt.setInt(1, mutterId);
            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("NAME");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
