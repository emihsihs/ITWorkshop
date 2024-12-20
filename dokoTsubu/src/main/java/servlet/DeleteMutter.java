package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DeleteMutterLogic;
import model.User;

@WebServlet("/DeleteMutter")
public class DeleteMutter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mutterId = Integer.parseInt(request.getParameter("mutterId"));
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            System.out.println("ログインユーザーが見つかりませんでした。");
            response.sendRedirect("login.jsp");
            return;
        }

        String loginUserName = loginUser.getName();
        System.out.println("ログインユーザー名: " + loginUserName);

        DeleteMutterLogic deleteMutterLogic = new DeleteMutterLogic();
        boolean success = deleteMutterLogic.execute(mutterId, loginUserName);

        if (success) {
            System.out.println("投稿が削除されました。");
        } else {
            System.out.println("投稿の削除に失敗しました。");
        }

        response.sendRedirect("Main");
    }
}
