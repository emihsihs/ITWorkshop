package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetCommentListLogic {
    public List<Comment> execute(int mutterId) {
        List<Comment> commentList = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/desktop/SQL/dokoTsubu", "sa", "");
                 Statement stmt = conn.createStatement()) {
                String sql = "SELECT id, userName, text FROM comments WHERE mutterId = " + mutterId;
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String userName = rs.getString("userName");
                        String text = rs.getString("text");
                        Comment comment = new Comment(id, mutterId, userName, text);
                        commentList.add(comment);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentList;
    }
}
