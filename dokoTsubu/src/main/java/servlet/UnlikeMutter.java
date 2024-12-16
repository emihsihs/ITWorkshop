package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UnlikeMutterLogic;
import model.User;

@WebServlet("/UnlikeMutter")
public class UnlikeMutter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mutterId = Integer.parseInt(request.getParameter("mutterId"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            System.out.println("ログインユーザーが見つかりませんでした。");
            response.sendRedirect("login.jsp"); // ログインページにリダイレクト
            return;
        }

        int userId = loginUser.getId();
        System.out.println("ログインユーザーID: " + userId);

        UnlikeMutterLogic unlikeMutterLogic = new UnlikeMutterLogic();
        boolean success = unlikeMutterLogic.execute(mutterId, userId);

        if (success) {
            System.out.println("いいね解除が成功しました");
        } else {
            System.out.println("いいね解除が失敗しました");
        }

        response.sendRedirect("Main");
    }
}

