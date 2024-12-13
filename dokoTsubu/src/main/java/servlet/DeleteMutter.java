package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DeleteMutterLogic;

@WebServlet("/DeleteMutter")
public class DeleteMutter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mutterId = request.getParameter("mutterId");

        DeleteMutterLogic deleteMutterLogic = new DeleteMutterLogic();
        deleteMutterLogic.execute(Integer.parseInt(mutterId));

        response.sendRedirect("Main");
    }
}
