package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/disconnect")
public class EmployeeDisconnectServlet extends HttpServlet {

    /** VIEWS **/
    private static final String VIEW = "/views/employee-login.jsp";

    /** SESSION **/
    HttpSession session;
    private static final String NAME_USER_SESSION = "user";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.session = request.getSession();

        if(this.session.getAttribute(NAME_USER_SESSION) != null)
            this.session.invalidate();

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

}
