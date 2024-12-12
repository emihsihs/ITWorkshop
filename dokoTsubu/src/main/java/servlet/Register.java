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

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String password = request.getParameter("pass");

        // ユーザー名とパスワードのバリデーション
        if (isValidUsername(username) && isValidPassword(password)) {
            try {
                // H2データベースドライバーをロード
                Class.forName("org.h2.Driver");

                try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/desktop/SQL/dokoTsubu", "sa", "");
                     PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {

                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    int result = pstmt.executeUpdate();

                    // デバッグ用ログ
                    System.out.println("Result: " + result);

                    if (result > 0) {
                        request.getRequestDispatcher("/WEB-INF/jsp/registerSuccess.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/WEB-INF/jsp/registerFailure.jsp").forward(request, response);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Class not found Exception: " + e.getMessage()); // デバッグ用ログ
                request.setAttribute("errorMessage", "JDBCドライバーが見つかりませんでした。");
                request.getRequestDispatcher("/WEB-INF/jsp/registerFailure.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL Exception: " + e.getMessage()); // デバッグ用ログ
                request.setAttribute("errorMessage", "データベースエラーが発生しました。もう一度お試しください。");
                request.getRequestDispatcher("/WEB-INF/jsp/registerFailure.jsp").forward(request, response);
            }
        } else {
            System.out.println("Validation failed: username or password format is incorrect"); // デバッグ用ログ
            request.setAttribute("errorMessage", "ユーザー名またはパスワードの形式が正しくありません。");
            request.getRequestDispatcher("/WEB-INF/jsp/registerFailure.jsp").forward(request, response);
        }
    }

    private boolean isValidUsername(String username) {
        // ユーザー名が6〜14文字であることをチェック
        return username != null && username.length() >= 6 && username.length() <= 14 && username.matches("^[a-zA-Z0-9一-龥ぁ-んァ-ヶー]*$");
    }

    private boolean isValidPassword(String password) {
        return password != null && password.matches("\\d{4}");
    }
}

