package servlet;

import utils.URLUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class EmployeeLogoutServlet extends HttpServlet {

    /** VIEWS **/
    private static final String VIEW = "/views/employee-login.jsp";

    /** SESSION **/
    private static final String NAME_USER_SESSION = "user";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute(NAME_USER_SESSION) != null)
            session.invalidate();

        response.sendRedirect(URLUtil.baseUrl(""));
    }

}
