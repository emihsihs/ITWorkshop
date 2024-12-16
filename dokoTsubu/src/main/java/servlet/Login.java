package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        // デバッグ用ログ
        System.out.println("Attempting login with username: " + name + " and password: " + pass);

        // ログイン処理
        LoginLogic loginLogic = new LoginLogic();
        User loginUser = loginLogic.execute(name, pass);

        // ログイン成功時の処理
        if (loginUser != null) {
            // デバッグ用ログ
            System.out.println("Login successful for user: " + loginUser.getName() + " with ID: " + loginUser.getId());

            // ユーザー情報をセッションスコープに保存
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
        } else {
            System.out.println("Login failed: invalid username or password");
            request.setAttribute("errorMessage", "ユーザー名またはパスワードが正しくありません。");
        }

        // ログイン結果画面にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
        dispatcher.forward(request, response);
    }
}


