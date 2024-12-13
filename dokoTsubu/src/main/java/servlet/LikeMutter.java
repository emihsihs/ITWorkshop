package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/LikeMutter")
public class LikeMutter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String userName = loginUser.getName();
        String mutterId = request.getParameter("mutterId");

        // データベースに接続していいねカウントを更新
        try {
            Class.forName("org.h2.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/desktop/SQL/dokoTsubu", "sa", "")) {
                // 既にいいねしているかチェック
                String checkSql = "SELECT * FROM likes WHERE userName = ? AND mutterId = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                    checkStmt.setString(1, userName);
                    checkStmt.setString(2, mutterId);
                    try (ResultSet rs = checkStmt.executeQuery()) {
                        if (!rs.next()) {
                            // いいねを追加
                            String insertSql = "INSERT INTO likes (userName, mutterId) VALUES (?, ?)";
                            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                                insertStmt.setString(1, userName);
                                insertStmt.setString(2, mutterId);
                                insertStmt.executeUpdate();
                            }

                            // いいねカウントを更新
                            String updateSql = "UPDATE mutters SET likeCount = likeCount + 1 WHERE id = ?";
                            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                                updateStmt.setString(1, mutterId);
                                updateStmt.executeUpdate();
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // メインページにリダイレクト
        response.sendRedirect("Main");
    }
}

