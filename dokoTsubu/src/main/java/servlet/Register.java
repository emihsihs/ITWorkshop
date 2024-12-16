package servlet;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

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
            UserDAO userDAO = new UserDAO();
            boolean isUserNameTaken = userDAO.isUserNameTaken(username);

            if (isUserNameTaken) {
                // エラーメッセージを設定して登録ページに戻る
                request.setAttribute("errorMessage", "既にそのユーザー名は使われています。別のユーザー名を入力してください。");
                request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
            } else {
                User user = new User(username, password);
                boolean isCreated = userDAO.create(user);

                if (isCreated) {
                    request.getRequestDispatcher("/WEB-INF/jsp/registerSuccess.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "ユーザーの作成に失敗しました。");
                    request.getRequestDispatcher("/WEB-INF/jsp/registerFailure.jsp").forward(request, response);
                }
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
