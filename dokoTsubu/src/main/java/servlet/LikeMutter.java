package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LikeMutterLogic;
import model.User;

@WebServlet("/LikeMutter")
public class LikeMutter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mutterId = Integer.parseInt(request.getParameter("mutterId"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            System.out.println("ログインユーザーが見つかりませんでした。");
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = loginUser.getId();
        System.out.println("ログインユーザーID: " + userId);

        LikeMutterLogic likeMutterLogic = new LikeMutterLogic();
        boolean success = likeMutterLogic.execute(mutterId, userId);

        if (success) {
            System.out.println("いいねが成功しました");
        } else {
            System.out.println("既にいいねされています、またはいいねが失敗しました。");
        }

        response.sendRedirect("Main");
    }
}
