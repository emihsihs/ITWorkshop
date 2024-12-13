package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/PostComment")
public class PostComment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        String mutterId = request.getParameter("mutterId");
        String commentText = request.getParameter("commentText");
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        // データベースに接続してコメントを保存
        try {
            Class.forName("org.h2.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/desktop/SQL/dokoTsubu", "sa", "")) {
                String sql = "INSERT INTO comments (mutterId, userName, text) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, Integer.parseInt(mutterId));
                    pstmt.setString(2, loginUser.getName());
                    pstmt.setString(3, commentText);
                    pstmt.executeUpdate();
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
